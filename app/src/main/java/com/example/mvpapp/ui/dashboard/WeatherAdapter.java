package com.example.mvpapp.ui.dashboard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpapp.R;
import com.example.mvpapp.data.model.WeatherDataResponse;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{
    private final List<WeatherDataResponse> weatherDataResponses;
    ExecutorService executor =  Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    public WeatherAdapter(List<WeatherDataResponse> weatherDataResponses) {
        this.weatherDataResponses = weatherDataResponses;
    }  
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item_weather, parent, false);
        return new ViewHolder(listItem);
    }  
  
    @Override  
    public void onBindViewHolder(ViewHolder holder, int position) {  
        final WeatherDataResponse weatherDataResponse = weatherDataResponses.get(position);
        holder.textViewLocation.setText(weatherDataResponse.getLocation().getName());
        holder.textViewSubLoc.setText(weatherDataResponse.getLocation().getRegion());
        holder.textViewType.setText(weatherDataResponse.getCurrent().getCondition().getText());
        holder.textViewTemp.setText(Html.fromHtml((weatherDataResponse.getCurrent().getTempC()+holder.itemView.getContext().getString(R.string.celsius))));
        holder.textViewTempF.setText(Html.fromHtml((weatherDataResponse.getCurrent().getTempF()+holder.itemView.getContext().getString(R.string.fahrenheit))));
        holder.textViewHumidity.setText((weatherDataResponse.getCurrent().getHumidity().toString()));
        holder.textViewLastUpdated.setText( weatherDataResponse.getCurrent().getLastUpdated());
        holder.textViewFeel.setText((weatherDataResponse.getCurrent().getFeelslikeC()+holder.itemView.getContext().getString(R.string.celsius)));

        executor.execute(() -> {
            try {
                Bitmap image;
                InputStream inputStream = new URL("https:"+weatherDataResponse.getCurrent().getCondition().getIcon()).openStream();
                image = BitmapFactory.decodeStream(inputStream);
                handler.post(() -> holder.imageViewWeather.setImageBitmap(image));
            } catch ( Exception e) {
                e.printStackTrace();
            }
        });


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
        public TextView textViewLocation,textViewType,textViewTemp,textViewSubLoc,textViewHumidity,textViewLastUpdated,textViewFeel,textViewTempF;
        public ImageView imageViewWeather;
        public ViewHolder(View itemView) {
            super(itemView);  
            this.textViewLocation = (TextView) itemView.findViewById(R.id.textViewLocation);
            this.textViewSubLoc = (TextView) itemView.findViewById(R.id.textViewSubLoc);
            this.textViewType = (TextView) itemView.findViewById(R.id.textViewType);
            this.textViewTemp = (TextView) itemView.findViewById(R.id.textViewTemp);
            this.textViewHumidity = (TextView) itemView.findViewById(R.id.textViewHumidity);
            this.textViewLastUpdated = (TextView) itemView.findViewById(R.id.textViewLastUpdated);
            this.textViewFeel = (TextView) itemView.findViewById(R.id.textViewFeel);
            this.textViewTempF = (TextView) itemView.findViewById(R.id.textViewTempF);
            this.imageViewWeather =  itemView.findViewById(R.id.imageViewWeather);
        }
    }  
}  
