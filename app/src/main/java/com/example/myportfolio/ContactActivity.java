package com.example.myportfolio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactActivity extends AppCompatActivity {

    PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        pref = new PrefManager(this);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white)); // <-- add this

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Contact");
        }

        toolbar.setNavigationOnClickListener(v -> finish());


        // ================= PROFILE =================
        CircleImageView img = findViewById(R.id.imgProfile);
        TextView name = findViewById(R.id.tvName);
        TextView title = findViewById(R.id.tvTitle);

        name.setText(pref.getName());
        title.setText(pref.getTitle());

        if (!pref.getProfilePhoto().isEmpty()) {
            img.setImageURI(Uri.parse(pref.getProfilePhoto()));
        } else {
            img.setImageResource(R.drawable.ic_user);
        }

        // ================= CONTACT CARDS =================

        setupCard(
                R.id.cardEmail,
                R.drawable.ic_email,
                "Email",
                pref.getEmail(),
                v -> openEmail()
        );

        setupCard(
                R.id.cardPhone,
                R.drawable.ic_phone,
                "Phone",
                pref.getPhone(),
                v -> openPhone()
        );

        setupCard(
                R.id.cardGithub,
                R.drawable.ic_github,
                "GitHub",
                pref.getGithubContact(),
                v -> openLink(pref.getGithubContact())
        );

        setupCard(
                R.id.cardLinkedIn,
                R.drawable.ic_linkedin,
                "LinkedIn",
                pref.getLinkedin(),
                v -> openLink(pref.getLinkedin())
        );

        // ================= BOTTOM NAV =================
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);

        bottomNav.setSelectedItemId(R.id.bottom_contact);

        bottomNav.setOnItemSelectedListener(item -> {

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
                return true;
            }

            return false;
        });
    }

    // ================= CARD SETUP =================

    private void setupCard(int id,
                           int icon,
                           String label,
                           String value,
                           View.OnClickListener click) {

        View card = findViewById(id);

        ImageView img = card.findViewById(R.id.imgIcon);
        TextView tvLabel = card.findViewById(R.id.tvLabel);
        TextView tvValue = card.findViewById(R.id.tvValue);

        img.setImageResource(icon);
        tvLabel.setText(label);

        if (value == null || value.isEmpty()) {
            tvValue.setText("Not added");
            card.setEnabled(false);
            card.setAlpha(0.6f);
        } else {
            tvValue.setText(value);
            card.setEnabled(true);
            card.setAlpha(1f);
            card.setOnClickListener(click);
        }
    }

    // ================= ACTIONS =================

    private void openEmail() {
        String email = pref.getEmail();

        if (email.isEmpty()) return;

        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:" + email));
        startActivity(i);
    }

    private void openPhone() {
        String phone = pref.getPhone();

        if (phone.isEmpty()) return;

        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:" + phone));
        startActivity(i);
    }

    private void openLink(String url) {

        if (url == null || url.isEmpty()) return;

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
