package com.example.myportfolio;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EditExperienceActivity extends AppCompatActivity {

    RecyclerView rv;
    PrefManager pref;
    ArrayList<Experience> list;

    @Override
    protected void onCreate(Bundle b) {

        super.onCreate(b);
        setContentView(R.layout.activity_edit_experience);

        pref = new PrefManager(this);

        // ===== TOOLBAR =====
        MaterialToolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

// 🔒 FORCE WHITE TITLE (theme-proof)
        tb.setTitleTextColor(getResources().getColor(android.R.color.white));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Edit Experience");
        }

// (optional but recommended) white back arrow
        tb.setNavigationIconTint(getResources().getColor(android.R.color.white));

        tb.setNavigationOnClickListener(v -> finish());


        // ===== RECYCLER =====
        rv = findViewById(R.id.rvExperienceEdit);
        rv.setLayoutManager(new LinearLayoutManager(this));

        load();


        // ===== ADD BUTTON =====
        FloatingActionButton fab = findViewById(R.id.fabAdd);

        fab.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                this,
                                AddEditExperienceActivity.class
                        )
                )
        );
    }


    @Override
    protected void onResume() {
        super.onResume();
        load();
    }


    private void load() {

        list = pref.getExperience();

        // ✅ USE MAIN ADAPTER IN EDIT MODE
        rv.setAdapter(
                new ExperienceAdapter(
                        this,
                        list,
                        true   // 🔥 EDIT MODE ON
                )
        );
    }
}
