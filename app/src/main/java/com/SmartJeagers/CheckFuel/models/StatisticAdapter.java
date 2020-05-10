package com.SmartJeagers.CheckFuel.models;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StatisticAdapter extends RecyclerView.Adapter<RefillView> {
    private List<Refill> refills;
    private Context context;

    public StatisticAdapter(List<Refill> refills, Context context) {
        this.refills = refills;
        this.context = context;
    }

    @NonNull
    @Override
    public RefillView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RefillView(parent, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RefillView holder, int position) {
        holder.bind(refills.get(position));
    }

    @Override
    public int getItemCount() {
        return refills.size();
    }

}
