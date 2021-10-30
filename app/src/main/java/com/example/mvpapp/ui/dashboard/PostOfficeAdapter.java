package com.example.mvpapp.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpapp.R;
import com.example.mvpapp.data.model.PostOffice;

import java.util.List;

public class PostOfficeAdapter extends RecyclerView.Adapter<PostOfficeAdapter.ViewHolder>{
    private final List<PostOffice> postOffices;

    public PostOfficeAdapter(List<PostOffice> postOffices) {
        this.postOffices = postOffices;
    }  
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item_post_office, parent, false);
        return new ViewHolder(listItem);
    }  
  
    @Override  
    public void onBindViewHolder(ViewHolder holder, int position) {  
        final PostOffice postOffice = postOffices.get(position);
        holder.textView.setText(postOffice.getName());
        holder.textViewType.setText(postOffice.getBranchType());

    }  
  
  
    @Override  
    public int getItemCount() {  
        return postOffices.size();
    }

    public void updateList(List<PostOffice> postOffices) {
        this.postOffices.clear();
        this.postOffices.addAll(postOffices);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {  
        public ImageView imageView;
        public TextView textView;
        public TextView textViewType;
        public ViewHolder(View itemView) {
            super(itemView);  
            this.imageView = (ImageView) itemView.findViewById(R.id.imageViewPostOffice);
            this.textView = (TextView) itemView.findViewById(R.id.textViewPOName);
            this.textViewType = (TextView) itemView.findViewById(R.id.textViewPOType);
        }
    }  
}  
