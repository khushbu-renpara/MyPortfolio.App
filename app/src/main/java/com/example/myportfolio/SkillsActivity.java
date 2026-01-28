package com.example.myportfolio;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class SkillsActivity extends AppCompatActivity {

    RecyclerView rvSkills;
    PrefManager pref;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        // ================= TOOLBAR =================
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Skills");
        }

        // Force white title & back arrow
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationIconTint(getResources().getColor(android.R.color.white));

        toolbar.setNavigationOnClickListener(v -> finish());

        // ================= SKILLS LIST =================
        pref = new PrefManager(this);
        rvSkills = findViewById(R.id.rvSkills);
        rvSkills.setLayoutManager(new LinearLayoutManager(this));

        List<Skill> skills = pref.getSkillsList();
        rvSkills.setAdapter(new SkillAdapter(skills, null));

        // ================= BOTTOM NAVIGATION =================
        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.setSelectedItemId(R.id.bottom_projects);

        bottomNavigation.setOnItemSelectedListener(item -> {

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
