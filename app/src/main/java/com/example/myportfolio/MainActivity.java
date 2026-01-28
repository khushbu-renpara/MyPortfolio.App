package com.example.myportfolio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    // Drawer
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MaterialToolbar toolbar;
    ActionBarDrawerToggle toggle;

    // Bottom Navigation
    BottomNavigationView bottomNavigation;

    // Home views
    TextView tvName, tvTitle, tvAbout;
    CircleImageView profileImage;

    // Quick actions
    MaterialButton btnResume, btnGithub, btnContact;

    // Drawer header views
    TextView drawerName, drawerTitle;
    CircleImageView drawerProfileImage;

    // Preferences
    PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = new PrefManager(this);

        // ===== Bind Views =====
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        tvName = findViewById(R.id.tvName);
        tvTitle = findViewById(R.id.tvTitle);
        tvAbout = findViewById(R.id.tvAbout);
        profileImage = findViewById(R.id.profileImage);

        btnResume = findViewById(R.id.btnResume);
        btnGithub = findViewById(R.id.btnGithub);
        btnContact = findViewById(R.id.btnContact);

        // ===== Toolbar =====
        setSupportActionBar(toolbar);
// 🔒 Force toolbar title color to WHITE
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

// (Recommended) make drawer icon white too
        toolbar.setNavigationIconTint(getResources().getColor(android.R.color.white));

        // ===== Drawer Toggle =====
        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // ===== Drawer Header =====
        drawerName = navigationView.getHeaderView(0).findViewById(R.id.drawerName);
        drawerTitle = navigationView.getHeaderView(0).findViewById(R.id.drawerTitle);
        drawerProfileImage = navigationView.getHeaderView(0).findViewById(R.id.drawerProfileImage);

        // ===== Drawer Menu Clicks =====
        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_home) {
                // stay here
            } else if (id == R.id.nav_about) {
                startActivity(new Intent(this, AboutActivity.class));
            } else if (id == R.id.nav_skills) {
                startActivity(new Intent(this, SkillsActivity.class));
            } else if (id == R.id.nav_projects) {
                startActivity(new Intent(this, ProjectsActivity.class));
            } else if (id == R.id.nav_education) {
                startActivity(new Intent(this, EducationActivity.class));
            } else if (id == R.id.nav_certificates) {
                startActivity(new Intent(this, CertificatesActivity.class));
            } else if (id == R.id.nav_experience) {
                startActivity(new Intent(this, ExperienceActivity.class));
            } else if (id == R.id.nav_contact) {
                startActivity(new Intent(this, ContactActivity.class));
            } else if (id == R.id.nav_edit_profile) {
                startActivity(new Intent(this, EditProfileActivity.class));
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // ===== Bottom Navigation =====
        bottomNavigation.setSelectedItemId(R.id.bottom_home);

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.bottom_home) {
                return true;

            } else if (id == R.id.bottom_projects) {
                startActivity(new Intent(MainActivity.this, ProjectsActivity.class));
                return true;

            } else if (id == R.id.bottom_contact) {
                startActivity(new Intent(MainActivity.this, ContactActivity.class));
                return true;
            }

            return false;
        });

        // ===== Quick Actions =====
        btnResume.setOnClickListener(v -> openResume());
        btnGithub.setOnClickListener(v -> openGithub());
        btnContact.setOnClickListener(v ->
                startActivity(new Intent(this, ContactActivity.class)));

        loadProfileData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProfileData();
    }

    private void loadProfileData() {

        tvName.setText(pref.getName());
        tvTitle.setText(pref.getTitle());
        tvAbout.setText(pref.getAbout());

        drawerName.setText(pref.getName());
        drawerTitle.setText(pref.getTitle());

        try {
            String photoUri = pref.getProfilePhoto();
            if (photoUri != null && !photoUri.isEmpty()) {
                Uri uri = Uri.parse(photoUri);
                profileImage.setImageURI(uri);
                drawerProfileImage.setImageURI(uri);
            } else {
                profileImage.setImageResource(R.drawable.ic_user);
                drawerProfileImage.setImageResource(R.drawable.ic_user);
            }
        } catch (Exception e) {
            profileImage.setImageResource(R.drawable.ic_user);
            drawerProfileImage.setImageResource(R.drawable.ic_user);
            pref.setProfilePhoto("");
        }
    }

    // ===== Actions =====

    private void openGithub() {
        String githubUrl = pref.getGithub();
        if (githubUrl == null || githubUrl.isEmpty()) {
            Toast.makeText(this, "GitHub link not set", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl)));
    }

    private void openResume() {
        String resumeUrl = pref.getResume();
        if (resumeUrl == null || resumeUrl.isEmpty()) {
            Toast.makeText(this, "Resume link not set", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(resumeUrl)));
    }
}
