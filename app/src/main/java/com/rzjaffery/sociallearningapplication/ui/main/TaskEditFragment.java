package com.rzjaffery.sociallearningapplication.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.rzjaffery.sociallearningapplication.R;
import com.rzjaffery.sociallearningapplication.data.repository.TaskRepository;
import com.rzjaffery.sociallearningapplication.model.TaskItem;

public class TaskEditFragment extends AppCompatActivity {

    private EditText title, desc;
    private Spinner priority;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_edit);

        title = findViewById(R.id.etTitle);
        desc = findViewById(R.id.etDesc);
        priority = findViewById(R.id.spPriority);
        ArrayAdapter<String> sp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Low", "Medium", "High"});
        sp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priority.setAdapter(sp);
        findViewById(R.id.btnSave).setOnClickListener(v -> save());
    }

    private void save(){
        String t=title.getText().toString().trim(), d=desc.getText().toString().trim(), p=priority.getSelectedItem().toString();
        if (TextUtils.isEmpty(t)) { Toast.makeText(this,"Enter title",Toast.LENGTH_SHORT).show(); return; }
        String uid = FirebaseAuth.getInstance().getUid();
        new TaskRepository().addOrUpdate(uid, new TaskItem(null, t, d, p, "Pending", System.currentTimeMillis()));
        finish();
    }

}
