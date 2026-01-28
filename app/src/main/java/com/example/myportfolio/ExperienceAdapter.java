package com.example.myportfolio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExperienceAdapter
        extends RecyclerView.Adapter<ExperienceAdapter.Holder> {

    Context context;
    ArrayList<Experience> list;
    boolean isEditMode;

    PrefManager pref;

    public ExperienceAdapter(Context context,
                             ArrayList<Experience> list,
                             boolean isEditMode) {

        this.context = context;
        this.list = list;
        this.isEditMode = isEditMode;

        pref = new PrefManager(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_experience,
                        parent,
                        false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(
            @NonNull Holder h,
            int pos) {

        Experience e = list.get(pos);

        h.tvRole.setText(e.getRole());
        h.tvCompany.setText(e.getCompany());
        h.tvDuration.setText(e.getDuration());
        h.tvDesc.setText(e.getDescription());
        h.tvTech.setText("Tech: " + e.getTech());

        // ================= EDIT MODE =================
        if (isEditMode) {

            h.btnEdit.setVisibility(View.VISIBLE);
            h.btnDelete.setVisibility(View.VISIBLE);

            // DELETE
            h.btnDelete.setOnClickListener(v -> {

                list.remove(pos);

                pref.saveExperience(list);

                notifyItemRemoved(pos);
                notifyItemRangeChanged(pos, list.size());
            });

            // EDIT
            h.btnEdit.setOnClickListener(v -> {

                Intent i =
                        new Intent(context,
                                AddEditExperienceActivity.class);

                i.putExtra("pos", pos);

                context.startActivity(i);
            });

        } else {

            // VIEW MODE
            h.btnEdit.setVisibility(View.GONE);
            h.btnDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        TextView tvRole, tvCompany, tvDuration, tvDesc, tvTech;

        ImageView btnEdit, btnDelete;

        public Holder(@NonNull View v) {
            super(v);

            tvRole = v.findViewById(R.id.tvRole);
            tvCompany = v.findViewById(R.id.tvCompany);
            tvDuration = v.findViewById(R.id.tvDuration);
            tvDesc = v.findViewById(R.id.tvDesc);
            tvTech = v.findViewById(R.id.tvTech);

            btnEdit = v.findViewById(R.id.btnEdit);
            btnDelete = v.findViewById(R.id.btnDelete);
        }
    }
}
