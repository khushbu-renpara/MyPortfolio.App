package com.example.myportfolio;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EditSectionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sections);

        setClick(R.id.btnAbout, "about", "About Me");
        setClick(R.id.btnSkills, "skills", "Skills");
        setClick(R.id.btnProjects, "projects", "Projects");
        setClick(R.id.btnEducation, "education", "Education");
        setClick(R.id.btnExperience, "experience", "Experience");
        setClick(R.id.btnContact, "contact", "Contact");
    }

    private void setClick(int btnId, String key, String title) {
        findViewById(btnId).setOnClickListener(v -> {
            Intent i = new Intent(this, EditSectionDetailActivity.class);
            i.putExtra("key", key);
            i.putExtra("title", title);
            startActivity(i);
        });
    }
}
