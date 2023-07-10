package com.example.project.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.project.Adapters.RequestAdapter;
import com.example.project.Models.Request;
import com.example.project.R;


import java.util.ArrayList;


public class PersonalLstFragment extends Fragment{

    private RecyclerView requestRV;
    private RequestAdapter requestAdapter;
    private LinearLayoutManager linearLayoutManager;

    private ArrayList<Request> requestArrayList= new ArrayList<>();

    public PersonalLstFragment() {
        requestAdapter = new RequestAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_requestslst, container, false);
        findViews(view);
        initView();
        return view;
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        requestRV.setLayoutManager(linearLayoutManager);
        requestRV.setAdapter(requestAdapter);
    }

    private void findViews(View view) {
        requestRV = view.findViewById(R.id.main_LST_requests);

    }

    public void addRequest(Request request) {

        requestArrayList.add(request);
        requestAdapter.setRequestLst(requestArrayList);
        requestRV.setLayoutManager(linearLayoutManager);
        requestRV.setAdapter(requestAdapter);
    }
}
