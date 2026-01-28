package com.example.myportfolio;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EditSkillsActivity extends AppCompatActivity {

    EditText etSkillName;
    Spinner spinnerLevel;
    Button btnAddSkill;
    RecyclerView rvEditSkills;

    PrefManager pref;
    List<Skill> skillList;
    SkillAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_skills);

        pref = new PrefManager(this);

        etSkillName = findViewById(R.id.etSkillName);
        spinnerLevel = findViewById(R.id.spinnerLevel);
        btnAddSkill = findViewById(R.id.btnAddSkill);
        rvEditSkills = findViewById(R.id.rvEditSkills);

        spinnerLevel.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Basic", "Intermediate", "Advanced"}
        ));

        skillList = pref.getSkillsList();

        adapter = new SkillAdapter(skillList, position -> {
            skillList.remove(position);
            pref.saveSkills(skillList);
            adapter.notifyItemRemoved(position);
        });

        rvEditSkills.setLayoutManager(new LinearLayoutManager(this));
        rvEditSkills.setAdapter(adapter);

        btnAddSkill.setOnClickListener(v -> addSkill());
    }

    private void addSkill() {
        String name = etSkillName.getText().toString().trim();
        String level = spinnerLevel.getSelectedItem().toString();

        if (name.isEmpty()) {
            Toast.makeText(this, "Enter skill name", Toast.LENGTH_SHORT).show();
            return;
        }

        skillList.add(new Skill(name, level));
        pref.saveSkills(skillList);
        adapter.notifyItemInserted(skillList.size() - 1);
        etSkillName.setText("");
    }
}
