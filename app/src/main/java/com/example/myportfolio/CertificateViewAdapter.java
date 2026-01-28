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

import java.util.List;

public class CertificateViewAdapter
        extends RecyclerView.Adapter<CertificateViewAdapter.ViewHolder> {

    Context context;
    List<Certificate> list;

    public CertificateViewAdapter(Context context, List<Certificate> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_certificate, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int pos) {

        Certificate c = list.get(pos);

        h.tvTitle.setText(c.getTitle());
        h.tvYear.setText(c.getYear());

        try {
            if (!c.getImageUri().isEmpty()) {
                h.img.setImageURI(Uri.parse(c.getImageUri()));
            } else {
                h.img.setImageResource(R.drawable.ic_image);
            }
        } catch (Exception e) {
            h.img.setImageResource(R.drawable.ic_image);
        }

        // ❌ NO CLICK
        // ❌ NO LONG CLICK
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView tvTitle, tvYear;

        ViewHolder(View v) {
            super(v);

            img = v.findViewById(R.id.imgCert);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvYear = v.findViewById(R.id.tvYear);
        }
    }
}
