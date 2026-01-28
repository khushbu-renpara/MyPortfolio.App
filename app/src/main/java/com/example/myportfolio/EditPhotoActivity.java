package com.example.myportfolio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditPhotoActivity extends AppCompatActivity {

    CircleImageView imgProfile;
    Button btnChoose, btnSave;
    Uri selectedImageUri;
    PrefManager pref;

    ActivityResultLauncher<Intent> imagePicker =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                            selectedImageUri = result.getData().getData();

                            // 🔥 PERSIST URI PERMISSION (CRITICAL)
                            final int takeFlags = result.getData().getFlags()
                                    & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                            getContentResolver()
                                    .takePersistableUriPermission(selectedImageUri, takeFlags);

                            imgProfile.setImageURI(selectedImageUri);
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photo);

        pref = new PrefManager(this);

        imgProfile = findViewById(R.id.imgProfile);
        btnChoose = findViewById(R.id.btnChoose);
        btnSave = findViewById(R.id.btnSavePhoto);

        // Load existing photo safely
        try {
            if (!pref.getProfilePhoto().isEmpty()) {
                imgProfile.setImageURI(Uri.parse(pref.getProfilePhoto()));
            }
        } catch (Exception e) {
            pref.setProfilePhoto(""); // clear broken URI
        }

        btnChoose.setOnClickListener(v -> openGallery());

        btnSave.setOnClickListener(v -> {
            if (selectedImageUri != null) {
                pref.setProfilePhoto(selectedImageUri.toString());
            }
            finish();
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        imagePicker.launch(intent);
    }
}
