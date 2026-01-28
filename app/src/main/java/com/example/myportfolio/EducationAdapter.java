package com.example.myportfolio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.ViewHolder> {

    List<Education> list;

    public EducationAdapter(List<Education> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_education, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int i) {
        Education e = list.get(i);
        h.title.setText(e.getTitle());
        h.institute.setText(e.getInstitute());
        h.duration.setText(e.getDuration());
        h.result.setText(e.getResult());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, institute, duration, result;

        ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.tvTitle);
            institute = v.findViewById(R.id.tvInstitute);
            duration = v.findViewById(R.id.tvDuration);
            result = v.findViewById(R.id.tvResult);
        }
    }
}
