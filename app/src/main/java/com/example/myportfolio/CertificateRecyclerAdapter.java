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

public class CertificateRecyclerAdapter
        extends RecyclerView.Adapter<CertificateRecyclerAdapter.ViewHolder> {

    Context context;
    List<Certificate> list;

    OnItemDeleteListener listener;

    public interface OnItemDeleteListener {
        void onDelete(int position);
    }

    public CertificateRecyclerAdapter(Context context,
                                      List<Certificate> list,
                                      OnItemDeleteListener listener) {

        this.context = context;
        this.list = list;
        this.listener = listener;
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

        // Image
        try {
            if (!c.getImageUri().isEmpty()) {
                h.img.setImageURI(Uri.parse(c.getImageUri()));
            } else {
                h.img.setImageResource(R.drawable.ic_image);
            }
        } catch (Exception e) {
            h.img.setImageResource(R.drawable.ic_image);
        }

        // Long press = delete
        h.itemView.setOnLongClickListener(v -> {
            listener.onDelete(pos);
            return true;
        });
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
