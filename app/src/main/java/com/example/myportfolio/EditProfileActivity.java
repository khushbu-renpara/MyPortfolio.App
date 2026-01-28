package com.example.myportfolio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EditProfileActivity extends AppCompatActivity {

    Button btnEditInfo, btnEditPhoto, btnEditCertificates,
            btnEditSkills, btnEditEducation;

    Button btnEditExperience, btnEditProjects, btnEditResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
// Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

// Make the title white
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Edit Profile");
        }

        toolbar.setNavigationOnClickListener(v -> finish());
        // ===== Bind Buttons =====

        btnEditInfo = findViewById(R.id.btnEditInfo);
        btnEditPhoto = findViewById(R.id.btnEditPhoto);
        btnEditCertificates = findViewById(R.id.btnEditCertificates);
        btnEditSkills = findViewById(R.id.btnEditSkills);
        btnEditEducation = findViewById(R.id.btnEditEducation);

        Button btnEditContact = findViewById(R.id.btnEditContact);

        btnEditExperience = findViewById(R.id.btnEditExperience);
        btnEditProjects = findViewById(R.id.btnEditProjects);
        btnEditResume = findViewById(R.id.btnEditResume); // ✅ NEW


        // ===== Click Listeners =====

        btnEditInfo.setOnClickListener(v ->
                startActivity(new Intent(this, EditInfoActivity.class)));

        btnEditPhoto.setOnClickListener(v ->
                startActivity(new Intent(this, EditPhotoActivity.class)));

        btnEditCertificates.setOnClickListener(v ->
                startActivity(new Intent(this, EditCertificatesActivity.class)));

        btnEditSkills.setOnClickListener(v ->
                startActivity(new Intent(this, EditSkillsActivity.class)));

        btnEditEducation.setOnClickListener(v ->
                startActivity(new Intent(this, EditEducationActivity.class)));

        btnEditContact.setOnClickListener(v ->
                startActivity(new Intent(this, EditContactActivity.class)));

        btnEditExperience.setOnClickListener(v ->
                startActivity(new Intent(this, EditExperienceActivity.class)));

        btnEditProjects.setOnClickListener(v ->
                startActivity(new Intent(this, EditProjectsActivity.class)));

        // ✅ RESUME BUTTON
        btnEditResume.setOnClickListener(v ->
                startActivity(new Intent(this, EditResumeActivity.class)));


        // ===== Bottom Navigation =====

        BottomNavigationView nav = findViewById(R.id.bottomNavigation);

        nav.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.bottom_home) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;

            } else if (id == R.id.bottom_projects) {
                startActivity(new Intent(this, ProjectsActivity.class));
                finish();
                return true;

            } else if (id == R.id.bottom_contact) {
                startActivity(new Intent(this, ContactActivity.class));
                finish();
                return true;
            }

            return false;
        });
    }
}
