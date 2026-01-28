package com.example.myportfolio;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class EducationActivity extends AppCompatActivity {

    RecyclerView rvEducation;
    PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        // ================= TOOLBAR =================
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 🔹 SET TITLE
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Education");
        }

        // ✅ MAKE TITLE WHITE
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationOnClickListener(v -> finish());

        // ================= DATA =================
        pref = new PrefManager(this);

        // ================= RECYCLER VIEW =================
        rvEducation = findViewById(R.id.rvEducation);
        rvEducation.setLayoutManager(new LinearLayoutManager(this));

        List<Education> educationList = pref.getEducationList();
        rvEducation.setAdapter(new EducationAdapter(educationList));

        // ================= BOTTOM NAVIGATION =================
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);

        bottomNav.getMenu().setGroupCheckable(0, false, true);

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
}
