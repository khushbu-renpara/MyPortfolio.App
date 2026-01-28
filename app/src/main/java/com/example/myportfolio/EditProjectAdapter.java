package com.example.myportfolio;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EditProjectAdapter
        extends RecyclerView.Adapter<EditProjectAdapter.Holder> {

    Context context;
    ArrayList<Project> list;
    PrefManager pref;

    public EditProjectAdapter(Context context,
                              ArrayList<Project> list) {

        this.context = context;
        this.list = list;
        pref = new PrefManager(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_project_edit,
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


        // Delete
        h.imgDelete.setOnClickListener(v -> {

            new AlertDialog.Builder(context)
                    .setTitle("Delete")
                    .setMessage("Delete this project?")
                    .setPositiveButton("Yes", (d, w) -> {

                        list.remove(pos);
                        pref.saveProjects(list);

                        notifyDataSetChanged();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        TextView tvName, tvDesc;
        ImageView imgDelete;

        public Holder(@NonNull View v) {
            super(v);

            tvName = v.findViewById(R.id.tvName);
            tvDesc = v.findViewById(R.id.tvDesc);
            imgDelete = v.findViewById(R.id.imgDelete);
        }
    }
}
