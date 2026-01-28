package com.example.myportfolio;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditInfoActivity extends AppCompatActivity {

    EditText etName, etTitle, etAbout;
    Button btnSave;
    PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        pref = new PrefManager(this);

        etName = findViewById(R.id.etName);
        etTitle = findViewById(R.id.etTitle);
        etAbout = findViewById(R.id.etAbout);
        btnSave = findViewById(R.id.btnSave);

        // Load existing data
        etName.setText(pref.getName());
        etTitle.setText(pref.getTitle());
        etAbout.setText(pref.getAbout());

        btnSave.setOnClickListener(v -> {
            pref.setName(etName.getText().toString().trim());
            pref.setTitle(etTitle.getText().toString().trim());
            pref.setAbout(etAbout.getText().toString().trim());
            finish(); // Go back to EditProfile / Home
        });
    }
}
