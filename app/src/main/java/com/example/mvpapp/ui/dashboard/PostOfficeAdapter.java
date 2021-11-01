package com.example.mvpapp.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpapp.R;
import com.example.mvpapp.data.model.WeatherDataResponse;

import java.util.List;

public class PostOfficeAdapter extends RecyclerView.Adapter<PostOfficeAdapter.ViewHolder>{
    private final List<WeatherDataResponse> weatherDataResponses;

    public PostOfficeAdapter(List<WeatherDataResponse> weatherDataResponses) {
        this.weatherDataResponses = weatherDataResponses;
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
        final WeatherDataResponse weatherDataResponse = weatherDataResponses.get(position);
        holder.textViewLocation.setText(weatherDataResponse.getLocation().getName());
        holder.textViewType.setText(weatherDataResponse.getCurrent().getCondition().getText());
        holder.textViewTemp.setText((weatherDataResponse.getCurrent().getTempC()+"/°C"));
        holder.textViewHumidity.setText((holder.itemView.getContext().getString(R.string.humidity) +(weatherDataResponse.getCurrent().getHumidity().toString())));
        holder.textViewLastUpdated.setText((holder.itemView.getContext().getString(R.string.last_update) + weatherDataResponse.getCurrent().getLastUpdated()));

    }  
  
  
    @Override  
    public int getItemCount() {  
        return weatherDataResponses.size();
    }

    public void updateList(List<WeatherDataResponse> weatherDataResponses) {
        this.weatherDataResponses.clear();
        this.weatherDataResponses.addAll(weatherDataResponses);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {  
        public TextView textViewLocation,textViewType,textViewTemp,textViewHumidity,textViewLastUpdated;
        public ViewHolder(View itemView) {
            super(itemView);  
            this.textViewLocation = (TextView) itemView.findViewById(R.id.textViewLocation);
            this.textViewType = (TextView) itemView.findViewById(R.id.textViewType);
            this.textViewTemp = (TextView) itemView.findViewById(R.id.textViewTemp);
            this.textViewHumidity = (TextView) itemView.findViewById(R.id.textViewHumidity);
            this.textViewLastUpdated = (TextView) itemView.findViewById(R.id.textViewLastUpdated);
        }
    }  
}  
