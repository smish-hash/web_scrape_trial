package com.example.web_scrape_trial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ParseAdapter extends RecyclerView.Adapter<ParseAdapter.ViewHolder> {

    private ArrayList<ParseItem> parseItems;
    private Context context;

    public ParseAdapter(ArrayList<ParseItem> parseItems, Context context) {
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ParseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parse_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapter.ViewHolder holder, int position) {

        ParseItem parseItem = parseItems.get(position);
        holder.textView.setText(parseItem.getTitle());
        holder.textYear.setText(parseItem.getYear());
        Picasso.get().load(parseItem.getImgUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        TextView textYear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textYear = itemView.findViewById(R.id.textYear);
        }
    }
}
