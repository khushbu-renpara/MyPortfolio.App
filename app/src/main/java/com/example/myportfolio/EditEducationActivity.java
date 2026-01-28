package com.example.myportfolio;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class EditEducationActivity extends AppCompatActivity {

    Spinner spinnerTitle;
    EditText etInstitute, etDuration, etResult;
    Button btnAddEducation;

    RecyclerView rvEducationEdit;

    PrefManager pref;
    List<Education> list;
    EducationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_education);

        // Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        pref = new PrefManager(this);
        list = pref.getEducationList();

        // Bind views
        spinnerTitle = findViewById(R.id.spinnerTitle);
        etInstitute = findViewById(R.id.etInstitute);
        etDuration = findViewById(R.id.etDuration);
        etResult = findViewById(R.id.etResult);
        btnAddEducation = findViewById(R.id.btnAddEducation);
        rvEducationEdit = findViewById(R.id.rvEducationEdit);

        // Spinner (education type)
        String[] titles = {
                "Bachelor's Degree",
                "Master's Degree",
                "Diploma",
                "Higher Secondary Education",
                "Secondary Education",
                "Other"
        };

        spinnerTitle.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                titles
        ));

        // RecyclerView
        adapter = new EducationAdapter(list);
        rvEducationEdit.setLayoutManager(new LinearLayoutManager(this));
        rvEducationEdit.setAdapter(adapter);

        btnAddEducation.setOnClickListener(v -> addEducation());
    }

    private void addEducation() {

        String title = spinnerTitle.getSelectedItem().toString();
        String institute = etInstitute.getText().toString().trim();
        String duration = etDuration.getText().toString().trim();
        String result = etResult.getText().toString().trim();

        if (institute.isEmpty()) {
            Toast.makeText(this, "Enter institute name", Toast.LENGTH_SHORT).show();
            return;
        }

        list.add(new Education(title, institute, duration, result));
        pref.saveEducation(list);

        adapter.notifyItemInserted(list.size() - 1);

        etInstitute.setText("");
        etDuration.setText("");
        etResult.setText("");
    }
}
