package com.example.myportfolio;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditSectionDetailActivity extends AppCompatActivity {

    PrefManager pref;
    String key, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_section_detail);

        pref = new PrefManager(this);

        key = getIntent().getStringExtra("key");
        title = getIntent().getStringExtra("title");

        TextView tvTitle = findViewById(R.id.tvTitle);
        EditText etContent = findViewById(R.id.etContent);
        Button btnSave = findViewById(R.id.btnSave);

        tvTitle.setText("Edit " + title);

        // load existing content
        etContent.setText(pref.getSection(key, ""));

        btnSave.setOnClickListener(v -> {
            pref.setSection(key, etContent.getText().toString().trim());
            finish();
        });
    }
}
