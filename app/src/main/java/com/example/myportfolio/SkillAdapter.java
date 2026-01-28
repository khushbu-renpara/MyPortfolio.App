package com.example.myportfolio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder> {

    public interface OnSkillDeleteListener {
        void onDelete(int position);
    }

    private final List<Skill> skillList;
    private final OnSkillDeleteListener deleteListener;

    public SkillAdapter(List<Skill> skillList, OnSkillDeleteListener listener) {
        this.skillList = skillList;
        this.deleteListener = listener;
    }

    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_skill, parent, false);
        return new SkillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        Skill skill = skillList.get(position);

        holder.tvName.setText(skill.getName());
        holder.tvLevel.setText("Level: " + skill.getLevel());

        if (deleteListener == null) {
            holder.btnDelete.setVisibility(View.GONE);
        } else {
            holder.btnDelete.setVisibility(View.VISIBLE);
            holder.btnDelete.setOnClickListener(v ->
                    deleteListener.onDelete(position));
        }
    }

    @Override
    public int getItemCount() {
        return skillList.size();
    }

    static class SkillViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvLevel;
        ImageView btnDelete;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvSkillName);
            tvLevel = itemView.findViewById(R.id.tvSkillLevel);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
