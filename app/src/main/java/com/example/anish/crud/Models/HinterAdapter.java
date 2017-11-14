package com.example.anish.crud.Models;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anish.crud.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;

public class HinterAdapter extends RecyclerView.Adapter<HinterAdapter.HinterViewHolder>{

    private List<Hinter> mDataset;

    public HinterAdapter(List<Hinter> hintsList) {
        mDataset = hintsList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HinterAdapter.HinterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.records_list, parent, false);
        return (new HinterViewHolder(view));
    }

    public static class HinterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.output_website) TextView displayWebsite;
        @BindView(R.id.output_username) TextView displayUsername;
        @BindView(R.id.output_hint) TextView displayHint;

        public HinterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(HinterViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Hinter hinter = mDataset.get(position);
        holder.displayUsername.setText(hinter.getUsername());
        holder.displayWebsite.setText(hinter.getWebsite());
        holder.displayHint.setText(hinter.getHint());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}