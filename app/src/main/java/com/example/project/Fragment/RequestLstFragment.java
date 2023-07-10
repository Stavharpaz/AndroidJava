package com.example.project.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.project.Adapters.RequestAdapter;
import com.example.project.DataManager;
import com.example.project.Interfaces.LoadDataCallback;

import com.example.project.Models.Request;
import com.example.project.R;

import com.example.project.Utilities.SignalGenerator;


import java.util.ArrayList;

public class RequestLstFragment extends Fragment  {

    private RecyclerView requestRV;

    private View view;

    private RequestAdapter requestAdapter;

    private DataManager dataManager;

    private LinearLayoutManager linearLayoutManager;



    public RequestLstFragment() {

        requestAdapter = new RequestAdapter();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_requestlst, container, false);
        findViews(view);

        initViews();
        return view;
    }

    private void initViews() {

        dataManager = new DataManager();

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        requestRV.setLayoutManager(linearLayoutManager);
        requestRV.setAdapter(requestAdapter);

        getSurvivors();
    }





    private void findViews(View view) {
        requestRV = view.findViewById(R.id.main_LST_requests);
    }


    private void updateRV(ArrayList<Request> requestArrayList) {
        requestAdapter.setRequestLst(requestArrayList);
        requestRV.setLayoutManager(linearLayoutManager);
        requestRV.setAdapter(requestAdapter);
    }

    public void  getSurvivors() {
        dataManager.fetchRequestsData(new LoadDataCallback() {
            @Override
            public void onRequestsLoaded(ArrayList<Request> requestsList) {
                updateRV(requestsList);
            }

            @Override
            public void onRequestsLoadError(String errorMessage) {
                SignalGenerator.getInstance().toast(errorMessage, Toast.LENGTH_SHORT);
            }
        });
    }


    public RequestAdapter getRequestAdapter() {

        return requestAdapter;
    }


    public DataManager getDataManager() {
        return dataManager;
    }
}
