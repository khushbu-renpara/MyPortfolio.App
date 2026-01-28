package com.example.myportfolio;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ExperienceActivity extends AppCompatActivity {

    RecyclerView rv;
    PrefManager pref;

    @Override
    protected void onCreate(Bundle b) {

        super.onCreate(b);
        setContentView(R.layout.activity_experience);

        pref = new PrefManager(this);

        // Toolbar
        // Toolbar
        MaterialToolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

// Force white title
        tb.setTitleTextColor(
                getResources().getColor(android.R.color.white)
        );

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Experience");
        }

        tb.setNavigationOnClickListener(v -> finish());

        // Recycler
        rv = findViewById(R.id.rvExperience);
        rv.setLayoutManager(new LinearLayoutManager(this));

        load();

        // Bottom Nav
        BottomNavigationView nav =
                findViewById(R.id.bottomNavigation);

        nav.setOnItemSelectedListener(item -> {

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

    private void load() {

        ArrayList<Experience> list =
                pref.getExperience();

        rv.setAdapter(
                new ExperienceAdapter(
                        this,
                        list,
                        false   // VIEW MODE ❌
                )
        );
    }
}
