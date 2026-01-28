package com.example.myportfolio;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CertificateAdapter
        extends RecyclerView.Adapter<CertificateAdapter.ViewHolder> {

    Context context;
    ArrayList<Certificate> list;

    // 🔥 Controls edit/delete visibility
    boolean isEditMode;

    // Constructor
    public CertificateAdapter(Context context,
                              ArrayList<Certificate> list,
                              boolean isEditMode) {

        this.context = context;
        this.list = list;
        this.isEditMode = isEditMode;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_certificate, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int pos) {

        Certificate c = list.get(pos);

        h.tvTitle.setText(c.getTitle());
        h.tvYear.setText(c.getYear());

        // Load Image
        try {
            if (!c.getImageUri().isEmpty()) {
                h.img.setImageURI(Uri.parse(c.getImageUri()));
            } else {
                h.img.setImageResource(R.drawable.ic_image);
            }
        } catch (Exception e) {
            h.img.setImageResource(R.drawable.ic_image);
        }

        // ================= EDIT MODE =================
        if (isEditMode) {

            h.btnEdit.setVisibility(View.VISIBLE);
            h.btnDelete.setVisibility(View.VISIBLE);

        } else {

            // 🔥 VIEW MODE → HIDE BUTTONS
            h.btnEdit.setVisibility(View.GONE);
            h.btnDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // ================= VIEW HOLDER =================
    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img, btnEdit, btnDelete;
        TextView tvTitle, tvYear;

        ViewHolder(View v) {
            super(v);

            img = v.findViewById(R.id.imgCert);

            tvTitle = v.findViewById(R.id.tvTitle);
            tvYear = v.findViewById(R.id.tvYear);

            btnEdit = v.findViewById(R.id.btnEdit);
            btnDelete = v.findViewById(R.id.btnDelete);
        }
    }
}
