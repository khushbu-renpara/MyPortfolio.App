package com.example.myportfolio;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditContactActivity extends AppCompatActivity {

    EditText etEmail, etPhone, etGithub, etLinkedin;
    Button btnSave;

    PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        pref = new PrefManager(this);

        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etGithub = findViewById(R.id.etGithub);
        etLinkedin = findViewById(R.id.etLinkedin);
        btnSave = findViewById(R.id.btnSaveContact);

        // Load old data
        etEmail.setText(pref.getEmail());
        etPhone.setText(pref.getPhone());
        etGithub.setText(pref.getGithubContact());
        etLinkedin.setText(pref.getLinkedin());

        btnSave.setOnClickListener(v -> save());
    }

    private void save() {

        pref.setEmail(etEmail.getText().toString().trim());
        pref.setPhone(etPhone.getText().toString().trim());
        pref.setGithubContact(etGithub.getText().toString().trim());
        pref.setLinkedin(etLinkedin.getText().toString().trim());

        Toast.makeText(this, "Contact Saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}
