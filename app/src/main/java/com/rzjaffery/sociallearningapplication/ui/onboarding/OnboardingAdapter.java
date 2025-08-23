package com.rzjaffery.sociallearningapplication.ui.onboarding;

import android.view.*;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rzjaffery.sociallearningapplication.R;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.VH> {
    public interface FinishListener {
        void onFinish();
    }

    private final FinishListener listener;
    private final String[] titles;
    private final String[] subtitles;

    public OnboardingAdapter(android.content.Context ctx, FinishListener l) {
        listener = l;
        titles = new String[]{"Welcome", "Features", "Privacy & Terms"};
        subtitles = new String[]{
                "Welcome to the Social Learning App",
                "Quizzes, Tasks, Chat, and Profiles",
                "By continuing you agree to our terms"
        };
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup p, int v) {
        return new VH(LayoutInflater.from(p.getContext()).inflate(R.layout.item_onboarding, p, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        h.title.setText(titles[pos]);
        h.subtitle.setText(subtitles[pos]);
        h.finish.setVisibility(pos == 2 ? View.VISIBLE : View.GONE);
        h.finish.setOnClickListener(v -> {
            if (listener != null) listener.onFinish();
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView title, subtitle;
        Button finish;

        VH(View v) {
            super(v);
            title = v.findViewById(R.id.tvTitle);
            subtitle = v.findViewById(R.id.tvSubtitle);
            finish = v.findViewById(R.id.btnFinish);
        }
    }
}
