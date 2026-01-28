package com.example.myportfolio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // ================= TOOLBAR =================
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("About Me");
        }

        // Force white title & back arrow
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationIconTint(getResources().getColor(android.R.color.white));

        toolbar.setNavigationOnClickListener(v -> finish());

        // ================= CONTENT =================
        TextView title = findViewById(R.id.tvSectionTitle);
        TextView content = findViewById(R.id.tvSectionContent);

        // ❌ Remove duplicate title inside card
        title.setVisibility(View.GONE);

        PrefManager pref = new PrefManager(this);
        content.setText(
                pref.getSection(
                        "about",
                        "About information not added yet."
                )
        );

        // ================= BOTTOM NAVIGATION =================
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setSelectedItemId(R.id.bottom_home);

        bottomNav.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.bottom_home) {
                startActivity(new Intent(this, MainActivity.class));
                return true;

            } else if (id == R.id.bottom_projects) {
                startActivity(new Intent(this, ProjectsActivity.class));
                return true;

            } else if (id == R.id.bottom_contact) {
                startActivity(new Intent(this, ContactActivity.class));
                return true;
            }

            return false;
        });
    }
}
