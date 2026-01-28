package com.example.myportfolio;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EditExperienceAdapter
        extends RecyclerView.Adapter<EditExperienceAdapter.VH> {

    Context context;
    ArrayList<Experience> list;
    PrefManager pref;


    public EditExperienceAdapter(Context context,
                                 ArrayList<Experience> list) {

        this.context = context;
        this.list = list;
        pref = new PrefManager(context);
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_experience, parent, false);

        return new VH(v);
    }


    @Override
    public void onBindViewHolder(@NonNull VH h, int i) {

        Experience e = list.get(i);

        h.role.setText(e.getRole());
        h.company.setText(e.getCompany());
        h.duration.setText(e.getDuration());
        h.desc.setText(e.getDescription());
        h.tech.setText("Tech: " + e.getTech());


        // Long press delete
        h.itemView.setOnLongClickListener(v -> {

            new AlertDialog.Builder(context)

                    .setTitle("Delete")
                    .setMessage("Delete this experience?")

                    .setPositiveButton("Yes",
                            (d, w) -> {

                                list.remove(i);
                                pref.saveExperience(list);
                                notifyDataSetChanged();
                            })

                    .setNegativeButton("No", null)

                    .show();

            return true;
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    static class VH extends RecyclerView.ViewHolder {

        TextView role, company, duration, desc, tech;

        public VH(@NonNull View v) {

            super(v);

            role = v.findViewById(R.id.tvRole);
            company = v.findViewById(R.id.tvCompany);
            duration = v.findViewById(R.id.tvDuration);
            desc = v.findViewById(R.id.tvDesc);
            tech = v.findViewById(R.id.tvTech);
        }
    }
}
