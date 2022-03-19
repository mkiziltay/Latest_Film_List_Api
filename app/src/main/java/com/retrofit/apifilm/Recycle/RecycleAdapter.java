package com.retrofit.apifilm.Recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.retrofit.apifilm.Model.FilmMainResponseItem;
import com.retrofit.apifilm.R;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    Context context;
    ArrayList<FilmMainResponseItem> datalist;
    LayoutInflater layoutInflater;

    public RecycleAdapter(Context context, ArrayList<FilmMainResponseItem> datalist) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.datalist = datalist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleV,summaryV,ratingV,dateV;ImageView imageV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleV=itemView.findViewById(R.id.title);
            summaryV=itemView.findViewById(R.id.summary);
            ratingV=itemView.findViewById(R.id.rating);
            dateV=itemView.findViewById(R.id.time);
            imageV=itemView.findViewById(R.id.imageview);
        }

        public void setData(String title,String summary, String rating, String date, String image){
            titleV.setText(title);
            // Removing html tags from json data.(like <p>,<b>,<\p>)
            summary = summary.replaceAll("\\<.*?\\>", "");
            summaryV.setText(summary);
            ratingV.setText(rating);
            dateV.setText(date);
            Picasso.get().load(image).into(imageV);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_rcycle,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String title,summary,rating,date,image;
        title = datalist.get(position).getName();
        summary = datalist.get(position).getSummary();
        rating = String.valueOf(datalist.get(position).getRating().getAverage());
        date = new SimpleDateFormat("dd/MM/yyyy", Locale.US).format(new Date(datalist.get(position).getUpdated())); //new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date(datalist.get(position).getUpdated()));
        image = datalist.get(position).getImage().getMedium(); //Picasso.get().load(datalist.get(position).getImage().getMedium()).into(image);

        holder.setData(title,summary,rating,date,image);
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }





}
