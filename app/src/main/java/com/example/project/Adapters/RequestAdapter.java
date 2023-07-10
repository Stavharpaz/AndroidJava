package com.example.project.Adapters;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.project.Interfaces.RequestCallback;
import com.example.project.Models.Request;
import com.example.project.R;
import com.example.project.Utilities.ImageLoader;


import java.util.ArrayList;


public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestHolder> {

    private ArrayList<Request> requestLst= new ArrayList<>();

    private RequestCallback requestCallback;

    Request request;

    public void removeRequest(Request request) {
        int position = requestLst.indexOf(request);
        if (position != -1) {
            requestLst.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void setRequestLst(ArrayList<Request> requestLst) {
       this.requestLst.clear();
        this.requestLst.addAll(requestLst);
        notifyDataSetChanged();
    }





    public void setRequestCallback(RequestCallback requestCallback) {
        this.requestCallback = requestCallback;
    }

    @NonNull
    @Override
    public RequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
        RequestHolder requestHolder = new RequestHolder(view);

        return requestHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestHolder holder, int position) {
        request = getItem(position);
        holder.name.setText(request.getSurvivor().getName());
        holder.address.setText(request.getSurvivor().getAddress());
        holder.male.setText(request.getSurvivor().getMail());
        holder.phone.setText(request.getSurvivor().getPhone());
        holder.phone.setText(request.getSurvivor().getPhone());
        holder.request.setText(request.getRequest());

        if (request.getUser() != null) {
            holder.user.setVisibility(View.VISIBLE);
            holder.user.setText(request.getUser());
        } else {
            holder.user.setVisibility(View.GONE);
            holder.useradd.setVisibility(View.GONE);
        }


        ImageLoader.getInstance().loadImage(holder.appCompatImageView);


    }



    @Override
    public int getItemCount() {
        return this.requestLst == null ? 0 : this.requestLst.size();
    }

    private Request getItem(int position) {
        return this.requestLst.get(position);
    }

    public class RequestHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView address;
        private TextView phone;
        private TextView male;
        private TextView request;
        private TextView user;

        private TextView useradd;

        private AppCompatImageView appCompatImageView;



        public RequestHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_text);
            address = itemView.findViewById(R.id.address_text);
            phone = itemView.findViewById(R.id.phone_text);
            male = itemView.findViewById(R.id.mail_text);
            request = itemView.findViewById(R.id.request_text);
            user = itemView.findViewById(R.id.user_text);
            useradd = itemView.findViewById(R.id.user_text1);
            appCompatImageView = itemView.findViewById(R.id.IMG_background);


            itemView.setOnClickListener(v -> {
                if (requestCallback != null){
                    requestCallback.itemClicked(getItem(getAdapterPosition()));
                }
            });
        }
    }
}
