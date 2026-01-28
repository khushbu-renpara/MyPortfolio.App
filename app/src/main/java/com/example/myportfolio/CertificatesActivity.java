package com.example.myportfolio;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class CertificatesActivity extends AppCompatActivity {

    RecyclerView rv;
    PrefManager pref;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_certificates);

        pref = new PrefManager(this);

        // ================= TOOLBAR =================
        MaterialToolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Certificates");
        }

        // Force WHITE title
        tb.setTitleTextColor(getResources().getColor(android.R.color.white));

        tb.setNavigationOnClickListener(v -> finish());


        // ================= RECYCLER =================
        rv = findViewById(R.id.rvCertificates);
        rv.setLayoutManager(new LinearLayoutManager(this));

        load();


        // ================= BOTTOM NAV =================
        BottomNavigationView bottomNav =
                findViewById(R.id.bottomNavigation);

        // Optional: highlight home (or remove if not needed)
        bottomNav.setSelectedItemId(R.id.bottom_home);

        bottomNav.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.bottom_home) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;

            } else if (id == R.id.bottom_projects) {
                startActivity(new Intent(this, ProjectsActivity.class));
                finish();
                return true;

            } else if (id == R.id.bottom_contact) {
                startActivity(new Intent(this, ContactActivity.class));
                finish();
                return true;
            }

            return false;
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        load();
    }


    // ================= LOAD DATA =================
    private void load() {

        List<Certificate> list = pref.getCertificates();

        // Safe casting
        rv.setAdapter(
                new CertificateAdapter(
                        this,
                        new ArrayList<>(list),
                        false   // VIEW MODE (NO edit/delete)
                )
        );
    }
}
