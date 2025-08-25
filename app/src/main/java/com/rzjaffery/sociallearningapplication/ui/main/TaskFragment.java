package com.rzjaffery.sociallearningapplication.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.rzjaffery.sociallearningapplication.R;
import com.rzjaffery.sociallearningapplication.data.repository.TaskRepository;
import com.rzjaffery.sociallearningapplication.model.TaskItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskFragment extends Fragment {
    private RecyclerView rv;
    private TaskAdapter adapter;
    private Spinner filter;
    private final List<TaskItem> all = new ArrayList<>();
    private final TaskRepository repo = new TaskRepository();
    private String uid;

    @Override
    public View onCreateView(@NonNull LayoutInflater inf, ViewGroup parent, Bundle b) {
        View v = inf.inflate(R.layout.fragment_tasks, parent, false);

        uid = FirebaseAuth.getInstance().getUid();
        if (uid == null) {
            Toast.makeText(requireContext(), "Please login first.", Toast.LENGTH_SHORT).show();
            return v;
        }

        rv = v.findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TaskAdapter(
                new ArrayList<>(),
                item -> {
                    // toggle status
                    item.status = "Done".equals(item.status) ? "Pending" : "Done";
                    repo.addOrUpdate(uid, item);
                },
                item -> repo.delete(uid, item.id)
        );
        rv.setAdapter(adapter);

        filter = v.findViewById(R.id.spinnerFilter);
        ArrayAdapter<String> sp = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                Arrays.asList("All", "Pending", "Done")
        );
        sp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(sp);
        filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> p, View vw, int pos, long id) {
                applyFilter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        v.findViewById(R.id.fabAdd).setOnClickListener(vw ->
                startActivity(new Intent(requireContext(), TaskEditFragment.class)));

        subscribe();
        return v;
    }

    private void subscribe() {
        repo.tasksRef(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                all.clear();
                for (DataSnapshot s : snap.getChildren()) {
                    TaskItem t = s.getValue(TaskItem.class);
                    if (t != null) all.add(t);
                }
                applyFilter();
            }

            @Override
            public void onCancelled(DatabaseError e) {
            }
        });
    }

    private void applyFilter() {
        String f = filter.getSelectedItem() == null ? "All" : filter.getSelectedItem().toString();
        List<TaskItem> view = new ArrayList<>();
        for (TaskItem t : all) {
            String st = t.status == null ? "Pending" : t.status;
            if ("All".equals(f) || st.equals(f)) view.add(t);
        }
        adapter.update(view);
    }

    // ---------- Adapter ----------
    static class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.VH> {

        interface OnToggle {
            void onToggle(TaskItem item);
        }

        interface OnDelete {
            void onDelete(TaskItem item);
        }

        private List<TaskItem> data;
        private final OnToggle onToggle;
        private final OnDelete onDelete;

        TaskAdapter(List<TaskItem> d, OnToggle toggle, OnDelete delete) {
            this.data = d;
            this.onToggle = toggle;
            this.onDelete = delete;
        }

        void update(List<TaskItem> d) {
            this.data = d;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup p, int v) {
            return new VH(LayoutInflater.from(p.getContext()).inflate(R.layout.item_task, p, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH h, int i) {
            TaskItem t = data.get(i);
            h.title.setText(t.title);
            h.desc.setText(t.description);
            h.priority.setText("Priority: " + (t.priority == null ? "-" : t.priority));
            h.status.setText(t.status == null ? "Pending" : t.status);
            h.btnToggle.setText("Done".equals(t.status) ? "Mark Pending" : "Mark Done");
            h.btnToggle.setOnClickListener(v -> onToggle.onToggle(t));
            h.btnDelete.setOnClickListener(v -> onDelete.onDelete(t));
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        static class VH extends RecyclerView.ViewHolder {
            android.widget.TextView title, desc, priority, status;
            android.widget.Button btnToggle, btnDelete;

            VH(View v) {
                super(v);
                title = v.findViewById(R.id.tvTitle);
                desc = v.findViewById(R.id.tvDesc);
                priority = v.findViewById(R.id.tvPriority);
                status = v.findViewById(R.id.tvStatus);
                btnToggle = v.findViewById(R.id.btnToggle);
                btnDelete = v.findViewById(R.id.btnDelete);
            }
        }
    }
}
