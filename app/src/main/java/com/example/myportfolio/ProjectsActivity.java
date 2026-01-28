package com.example.myportfolio;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ProjectsActivity extends AppCompatActivity {

    RecyclerView rv;
    PrefManager pref;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_projects);

        pref = new PrefManager(this);

        // ================= TOOLBAR =================
        MaterialToolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Projects");
        }

        // Force white title & back arrow
        tb.setTitleTextColor(getResources().getColor(android.R.color.white));
        tb.setNavigationIconTint(getResources().getColor(android.R.color.white));

        tb.setNavigationOnClickListener(v -> finish());

        // ================= RECYCLER =================
        rv = findViewById(R.id.rvProjects);
        rv.setLayoutManager(new LinearLayoutManager(this));

        load();

        // ================= BOTTOM NAV =================
        BottomNavigationView nav = findViewById(R.id.bottomNavigation);

        nav.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.bottom_home) {
                startActivity(new Intent(this, MainActivity.class));
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

    private void load() {
        ArrayList<Project> list = pref.getProjects();
        rv.setAdapter(new ProjectAdapter(this, list));
    }
}
