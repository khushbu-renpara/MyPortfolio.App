package com.example.myportfolio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EditCertificatesActivity extends AppCompatActivity {

    RecyclerView rv;
    PrefManager pref;

    ArrayList<Certificate> list;
    CertificateRecyclerAdapter adapter; // NEW adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_certificates);

        pref = new PrefManager(this);

        // ===== Toolbar =====
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v -> finish());

        // ===== RecyclerView =====
        rv = findViewById(R.id.rvCertificatesEdit);
        rv.setLayoutManager(new LinearLayoutManager(this));

        // ===== Floating Button =====
        FloatingActionButton fab = findViewById(R.id.fabAdd);

        fab.setOnClickListener(v ->
                startActivity(
                        new Intent(this, AddEditCertificateActivity.class)
                )
        );

        load();
    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
    }

    private void load() {

        list = pref.getCertificates();

        adapter = new CertificateRecyclerAdapter(
                this,
                list,
                position -> {

                    // DELETE ON LONG CLICK
                    list.remove(position);
                    pref.saveCertificates(list);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(this,
                            "Certificate deleted",
                            Toast.LENGTH_SHORT).show();
                });

        rv.setAdapter(adapter);
    }
}
