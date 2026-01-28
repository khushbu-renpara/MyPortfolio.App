package com.example.myportfolio;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddEditExperienceActivity extends AppCompatActivity {

    EditText etRole, etCompany, etDuration, etDesc, etTech;
    PrefManager pref;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_add_edit_experience);

        pref = new PrefManager(this);

        etRole = findViewById(R.id.etRole);
        etCompany = findViewById(R.id.etCompany);
        etDuration = findViewById(R.id.etDuration);
        etDesc = findViewById(R.id.etDesc);
        etTech = findViewById(R.id.etTech);

        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> save());
    }

    private void save() {

        if (etRole.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Enter Role", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Experience> list = pref.getExperience();

        list.add(new Experience(

                etRole.getText().toString().trim(),
                etCompany.getText().toString().trim(),
                etDuration.getText().toString().trim(),
                etDesc.getText().toString().trim(),
                etTech.getText().toString().trim()   // ✅ FIXED (5th param)
        ));

        pref.saveExperience(list);

        finish();
    }
}
