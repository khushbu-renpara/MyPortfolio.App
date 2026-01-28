package com.example.myportfolio;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddEditProjectActivity extends AppCompatActivity {

    EditText etName, etDesc, etTech, etLink, etDuration;
    PrefManager pref;

    @Override
    protected void onCreate(Bundle b) {

        super.onCreate(b);
        setContentView(R.layout.activity_add_edit_project);

        pref = new PrefManager(this);

        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);
        etTech = findViewById(R.id.etTech);
        etLink = findViewById(R.id.etLink);
        etDuration = findViewById(R.id.etDuration);

        Button btn = findViewById(R.id.btnSaveProject);

        btn.setOnClickListener(v -> save());
    }


    private void save() {

        if (etName.getText().toString().isEmpty()) {

            Toast.makeText(this,
                    "Enter project name",
                    Toast.LENGTH_SHORT).show();

            return;
        }


        ArrayList<Project> list =
                pref.getProjects();


        list.add(
                new Project(
                        etName.getText().toString(),
                        etDesc.getText().toString(),
                        etTech.getText().toString(),
                        etLink.getText().toString(),
                        etDuration.getText().toString()
                )
        );

        pref.saveProjects(list);

        finish();
    }
}
