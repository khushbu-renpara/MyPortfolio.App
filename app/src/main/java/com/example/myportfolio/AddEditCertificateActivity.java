package com.example.myportfolio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddEditCertificateActivity extends AppCompatActivity {

    EditText etTitle, etDesc, etYear;
    ImageView img;

    Uri imageUri;

    PrefManager pref;

    int editPos = -1;

    ActivityResultLauncher<Intent> picker =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    r -> {

                        if (r.getResultCode() == RESULT_OK &&
                                r.getData() != null) {

                            imageUri = r.getData().getData();

                            getContentResolver()
                                    .takePersistableUriPermission(
                                            imageUri,
                                            Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    );

                            img.setImageURI(imageUri);
                        }
                    });

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);

        setContentView(R.layout.activity_add_edit_certificate);

        pref = new PrefManager(this);

        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDesc);
        etYear = findViewById(R.id.etYear);
        img = findViewById(R.id.imgCert);

        Button btnPick = findViewById(R.id.btnPickImage);
        Button btnSave = findViewById(R.id.btnSaveCert);

        editPos = getIntent().getIntExtra("pos", -1);

        if (editPos != -1) loadEdit();

        btnPick.setOnClickListener(v -> pick());
        btnSave.setOnClickListener(v -> save());
    }

    private void pick() {

        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.setType("image/*");

        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);

        picker.launch(i);
    }

    private void loadEdit() {

        ArrayList<Certificate> list =
                pref.getCertificates();

        Certificate c = list.get(editPos);

        etTitle.setText(c.getTitle());
        etDesc.setText(c.getDescription());
        etYear.setText(c.getYear());

        if (!c.getImageUri().isEmpty()) {

            imageUri = Uri.parse(c.getImageUri());
            img.setImageURI(imageUri);
        }
    }

    private void save() {

        ArrayList<Certificate> list =
                pref.getCertificates();

        Certificate c =
                new Certificate(
                        etTitle.getText().toString(),
                        etDesc.getText().toString(),
                        etYear.getText().toString(),
                        imageUri == null ? "" : imageUri.toString()
                );

        if (editPos == -1) {

            list.add(c);

        } else {

            list.set(editPos, c);
        }

        pref.saveCertificates(list);

        finish();
    }
}
