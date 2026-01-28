package com.example.myportfolio;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EditProjectsActivity extends AppCompatActivity {

    RecyclerView rv;
    PrefManager pref;
    ArrayList<Project> list;

    @Override
    protected void onCreate(Bundle b) {

        super.onCreate(b);
        setContentView(R.layout.activity_edit_projects);

        pref = new PrefManager(this);

        // Toolbar
        MaterialToolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        tb.setTitleTextColor(getResources().getColor(android.R.color.white));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Edit Projects");
        }


        tb.setNavigationOnClickListener(v -> finish());


        // Recycler
        rv = findViewById(R.id.rvProjectsEdit);
        rv.setLayoutManager(new LinearLayoutManager(this));

        load();


        // Add Button
        FloatingActionButton fab = findViewById(R.id.fabAddProject);

        fab.setOnClickListener(v ->
                startActivity(
                        new Intent(this,
                                AddEditProjectActivity.class)
                )
        );
    }


    @Override
    protected void onResume() {
        super.onResume();
        load();
    }


    private void load() {

        list = pref.getProjects();

        rv.setAdapter(
                new EditProjectAdapter(this, list)
        );
    }
}
