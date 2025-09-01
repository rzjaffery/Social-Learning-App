package com.rzjaffery.sociallearningapplication.ui.onboarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rzjaffery.sociallearningapplication.R;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.VH> {
    private final int[] layouts = {
            R.layout.onb_page_welcome,
            R.layout.onb_page_features,
            R.layout.onb_page_privacy
    };

    private final LayoutInflater inflater;
    public OnboardingAdapter(android.content.Context ctx){ this.inflater = LayoutInflater.from(ctx); }

    @NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(layouts[viewType], parent, false);
        return new VH(v);
    }
    @Override public void onBindViewHolder(@NonNull VH holder, int position) {}
    @Override public int getItemCount() { return layouts.length; }
    @Override public int getItemViewType(int position) { return position; }

    static class VH extends RecyclerView.ViewHolder { VH(@NonNull View itemView){ super(itemView);} }

}
