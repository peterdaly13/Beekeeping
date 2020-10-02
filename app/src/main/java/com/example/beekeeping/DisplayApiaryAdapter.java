package com.example.beekeeping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DisplayApiaryAdapter extends RecyclerView.Adapter<DisplayApiaryAdapter.ViewHolder>{
    private ArrayList<String> mDataset;
    private LayoutInflater mInflater1;
    private DisplayApiaryAdapter.ItemClickListener mClickListener;
    ApiariesActivity aa = new ApiariesActivity();
    List<Apiary> aList = new ArrayList<Apiary>();
    Apiary[] apiaryArray;

    //Constructor
    public DisplayApiaryAdapter(Context context, ArrayList<String> myDataset, Apiary[] apiaryArray) {
        this.mInflater1 = LayoutInflater.from(context);
        this.mDataset = myDataset;
        this.apiaryArray = apiaryArray;
    }

    // inflates the row layout from xml when needed
    @Override
    public DisplayApiaryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater1.inflate(R.layout.listapiaries, parent, false);
        return new DisplayApiaryAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(DisplayApiaryAdapter.ViewHolder holder, final int position) {
        String song = mDataset.get(position);
        holder.myTextView.setText(song);
        holder.myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed " + apiaryArray[position].getName());
                aa.pickApiary(apiaryArray[position]);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.apiaryName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mDataset.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(DisplayApiaryAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
