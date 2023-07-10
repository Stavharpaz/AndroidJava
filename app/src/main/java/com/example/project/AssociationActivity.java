package com.example.project;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Adapters.RequestAdapter;
import com.example.project.Interfaces.LoadDataCallback;
import com.example.project.Models.Request;

import java.util.ArrayList;

public class AssociationActivity extends AppCompatActivity {
    private DataManager dataManager = new DataManager();
    private RecyclerView recyclerView;
    private RequestAdapter adapter;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volrequestlst);

        recyclerView = findViewById(R.id.main_LST_volrequests);
        adapter = new RequestAdapter();

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        retrieveDataAndPopulateRecyclerView();

        removeDataFromRecyclerView();
    }

    private void removeDataFromRecyclerView() {
        adapter.setRequestCallback(request -> {
            adapter.removeRequest(request);
            dataManager.removeRequest(Request.VOL_REQUESTS, request.getid(), request.getRequest());

        });
    }

    private void retrieveDataAndPopulateRecyclerView() {
        dataManager.retrieveDataAndPopulateRecyclerView(adapter, new LoadDataCallback() {
            @Override
            public void onRequestsLoaded(ArrayList<Request> requestsList) {
                adapter.setRequestLst(requestsList);
            }

            @Override
            public void onRequestsLoadError(String errorMessage) {
                Toast.makeText(AssociationActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }




}

