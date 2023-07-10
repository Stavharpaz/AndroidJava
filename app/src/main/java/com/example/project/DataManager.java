package com.example.project;



import android.widget.Toast;


import androidx.annotation.NonNull;

import com.example.project.Adapters.RequestAdapter;
import com.example.project.Interfaces.ExistenceCheckCallback;
import com.example.project.Interfaces.LoadDataCallback;
import com.example.project.Models.Request;
import com.example.project.Models.Survivor;
import com.example.project.Utilities.SignalGenerator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;


public class DataManager implements Serializable {
   // DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://project-b9048-default-rtdb.firebaseio.com");
   DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://project123-1bd2d-default-rtdb.europe-west1.firebasedatabase.app");


    public void insertNewSurvivor(Survivor survivor){
        databaseReference.child(Survivor.SURVIVORS).child(survivor.getId()).setValue(survivor).addOnSuccessListener(aVoid -> SignalGenerator.getInstance().toast(SignalGenerator.FUNC_SUCCEEDED, Toast.LENGTH_SHORT))
                .addOnFailureListener(e -> SignalGenerator.getInstance().toast(SignalGenerator.FUNC_NOT_SUCCEEDED +": "+e.getMessage(), Toast.LENGTH_SHORT));

    }


    public void checkPersonExistence(String checkId, ExistenceCheckCallback callback) {

        Query query =  databaseReference.child(Survivor.SURVIVORS).orderByChild("id").equalTo(checkId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean exists = dataSnapshot.exists();
                if (!exists) {
                    callback.onExistenceChecked(false, null);
                }
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Survivor survivor = childSnapshot.getValue(Survivor.class);
                    callback.onExistenceChecked(true, survivor);
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                SignalGenerator.getInstance().toast(SignalGenerator.FUNC_NOT_SUCCEEDED +": "+databaseError.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }



    public void addRequestToPerson(Survivor survivor, String request) {
        databaseReference.child(Request.REQUESTS).child(request).child(survivor.getId()).setValue(survivor.getId()).addOnSuccessListener(aVoid ->
                        SignalGenerator.getInstance().toast(SignalGenerator.FUNC_SUCCEEDED, Toast.LENGTH_SHORT))
                .addOnFailureListener(e -> SignalGenerator.getInstance().toast(SignalGenerator.FUNC_NOT_SUCCEEDED +": "+e.getMessage(), Toast.LENGTH_SHORT));
    }


    public void fetchRequestsData(LoadDataCallback callback) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Request> requestsList = new ArrayList<>();
                getRequest(dataSnapshot, Request.GROCERIES, requestsList);
                getRequest(dataSnapshot, Request.CLOTHES, requestsList);
                getRequest(dataSnapshot, Request.COMPANY, requestsList);
                getRequest(dataSnapshot, Request.HOT_MEAL, requestsList);
                getRequest(dataSnapshot, Request.MEDICATION, requestsList);
                callback.onRequestsLoaded(requestsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                SignalGenerator.getInstance().toast(SignalGenerator.FUNC_NOT_SUCCEEDED +": "+databaseError.getMessage(), Toast.LENGTH_SHORT);
            }
        });

    }


    public void getRequest(DataSnapshot dataSnapshot, String reque, ArrayList<Request> requestsList) {
        if (dataSnapshot.child(Request.REQUESTS).hasChild(reque)) {
            DataSnapshot groceriesSnapshot = dataSnapshot.child(Request.REQUESTS).child(reque);

            for (DataSnapshot childSnapshot : groceriesSnapshot.getChildren()) {
                String id = childSnapshot.getKey();
                String value = childSnapshot.getValue(String.class);

                if(value!=null) {
                    Survivor survivor = dataSnapshot.child(Survivor.SURVIVORS).child(value).getValue(Survivor.class);


                    Request request = new Request()
                            .setRequest(reque)
                            .setId(id)
                            .setSurvivor(survivor);

                    requestsList.add(request);
                }
            }
        }
    }


    public void removeRequest(String firstChildDB, String secondChildDB, String thirdChildDB) {

        databaseReference.child(firstChildDB).child(secondChildDB).child(thirdChildDB).removeValue()
                .addOnSuccessListener(aVoid -> SignalGenerator.getInstance().toast(SignalGenerator.FUNC_SUCCEEDED, Toast.LENGTH_SHORT))
                .addOnFailureListener(e -> SignalGenerator.getInstance().toast(SignalGenerator.FUNC_NOT_SUCCEEDED +": "+e.getMessage(), Toast.LENGTH_SHORT));
    }




    public void requestWasTaken(Request request) {
        String userData;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            if (user.getEmail() == null)
                userData = user.getPhoneNumber();
            else
                userData = user.getEmail();


            databaseReference.child(Request.VOL_REQUESTS).child(request.getid()).child(request.getRequest()).setValue(userData).addOnSuccessListener(aVoid -> SignalGenerator.getInstance().toast(SignalGenerator.FUNC_SUCCEEDED, Toast.LENGTH_SHORT))
                .addOnFailureListener(e -> SignalGenerator.getInstance().toast(SignalGenerator.FUNC_NOT_SUCCEEDED + ": " + e.getMessage(), Toast.LENGTH_SHORT));
          }
    }


    public void retrieveDataAndPopulateRecyclerView(RequestAdapter adapter, LoadDataCallback callback) {
        DatabaseReference volRequestsRef = databaseReference.child(Request.VOL_REQUESTS);
        volRequestsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Request> requestsList = new ArrayList<>();

                for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                    String requestId = requestSnapshot.getKey();

                    // Iterate through the request types (e.g., "HOT MEAL", "CLOTHES")
                    for (DataSnapshot typeSnapshot : requestSnapshot.getChildren()) {
                        String requestType = typeSnapshot.getKey();

                        // Retrieve the user (email/phone) who made the request
                        String user = typeSnapshot.getValue(String.class);

                        // Retrieve the survivor details
                        databaseReference.child(Survivor.SURVIVORS).child(requestId).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Survivor survivor = dataSnapshot.getValue(Survivor.class);
                                Request request = new Request()
                                        .setRequest(requestType)
                                        .setId(requestId)
                                        .setSurvivor(survivor)
                                        .setUser(user);

                                // Add the Request object to the list
                                requestsList.add(request);
                                callback.onRequestsLoaded(requestsList); // Call the callback with the updated list
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                callback.onRequestsLoadError(databaseError.getMessage());
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onRequestsLoadError(databaseError.getMessage());
            }
        });
    }


}








