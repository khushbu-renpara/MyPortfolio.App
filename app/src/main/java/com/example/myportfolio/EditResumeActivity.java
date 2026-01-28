package com.example.myportfolio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditResumeActivity extends AppCompatActivity {

    private static final int PICK_PDF = 101;

    PrefManager pref;
    Button btnSelect, btnSave;

    Uri selectedPdfUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resume);

        pref = new PrefManager(this);

        btnSelect = findViewById(R.id.btnSelectPdf);
        btnSave = findViewById(R.id.btnSavePdf);

        btnSelect.setOnClickListener(v -> openPdfPicker());

        btnSave.setOnClickListener(v -> savePdf());
    }

    private void openPdfPicker() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, PICK_PDF);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF && resultCode == RESULT_OK) {

            if (data != null) {
                selectedPdfUri = data.getData();

                Toast.makeText(this,
                        "PDF Selected",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void savePdf() {

        if (selectedPdfUri == null) {

            Toast.makeText(this,
                    "Select PDF first",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        pref.setResume(selectedPdfUri.toString());

        Toast.makeText(this,
                "Resume Saved",
                Toast.LENGTH_SHORT).show();

        finish();
    }
}
