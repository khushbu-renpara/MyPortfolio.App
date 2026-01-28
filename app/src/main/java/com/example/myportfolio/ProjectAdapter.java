package com.example.myportfolio;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProjectAdapter
        extends RecyclerView.Adapter<ProjectAdapter.Holder> {

    Context context;
    ArrayList<Project> list;

    public ProjectAdapter(Context context,
                          ArrayList<Project> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_project,
                        parent,
                        false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(
            @NonNull Holder h,
            int pos) {

        Project p = list.get(pos);

        h.tvName.setText(p.getName());
        h.tvDesc.setText(p.getDescription());
        h.tvTech.setText("Tech: " + p.getTech());
        h.tvLink.setText("Link: " + p.getLink());
        h.tvDuration.setText(p.getDuration());

        // Open link
        h.itemView.setOnClickListener(v -> {

            if (!p.getLink().isEmpty()) {

                Intent i =
                        new Intent(Intent.ACTION_VIEW,
                                Uri.parse(p.getLink()));

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        TextView tvName, tvDesc, tvTech, tvLink, tvDuration;

        public Holder(@NonNull View v) {
            super(v);

            tvName = v.findViewById(R.id.tvName);
            tvDesc = v.findViewById(R.id.tvDesc);
            tvTech = v.findViewById(R.id.tvTech);
            tvLink = v.findViewById(R.id.tvLink);
            tvDuration = v.findViewById(R.id.tvDuration);
        }
    }
}
