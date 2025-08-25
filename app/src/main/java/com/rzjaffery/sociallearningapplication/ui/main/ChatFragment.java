package com.rzjaffery.sociallearningapplication.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.rzjaffery.sociallearningapplication.R;
import com.rzjaffery.sociallearningapplication.data.repository.ChatRepository;
import com.rzjaffery.sociallearningapplication.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatFragment extends Fragment {
    private RecyclerView rv;
    private ChatAdapter adapter;
    private EditText input;
    private final List<ChatMessage> data = new ArrayList<>();
    private final ChatRepository repo = new ChatRepository();


    @Override public View onCreateView(@NonNull LayoutInflater inf, ViewGroup parent, Bundle b) {
        View v = inf.inflate(R.layout.fragment_chat, parent, false);
        rv = v.findViewById(R.id.recycler); rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ChatAdapter(data); rv.setAdapter(adapter);
        input = v.findViewById(R.id.etMessage);
        v.findViewById(R.id.btnSend).setOnClickListener(vw -> send());
        subscribe();
        return v;
    }

    private void send() {
        String text = input.getText().toString().trim();
        if (TextUtils.isEmpty(text)) return;
        String uid = FirebaseAuth.getInstance().getUid();
        String name = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail();
        repo.sendMsg(new ChatMessage(null, uid, name, text, System.currentTimeMillis()));
        input.setText("");
    }

    private void subscribe() {
        repo.messageRef().limitToLast(200).addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot s : snapshot.getChildren()) {
                    ChatMessage m = s.getValue(ChatMessage.class);
                    if (m != null) data.add(m);
                }
                adapter.notifyDataSetChanged();
                rv.scrollToPosition(Math.max(0,data.size()-1));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    static class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.VH> {
        private final List<ChatMessage> data;
        ChatAdapter(List<ChatMessage> d){ data=d; }
        @NonNull
        @Override public VH onCreateViewHolder(@NonNull ViewGroup p, int v) {
            return new VH(LayoutInflater.from(p.getContext()).inflate(R.layout.item_chat, p, false));
        }
        @Override public void onBindViewHolder(@NonNull VH h, int i) {
            ChatMessage m = data.get(i);
            h.name.setText(m.name);
            h.text.setText(m.text);
            h.time.setText(android.text.format.DateFormat.format("HH:mm", m.timestamp));
        }
        @Override public int getItemCount(){ return data.size(); }
        static class VH extends RecyclerView.ViewHolder {
            TextView name, text, time;
            VH(View v){ super(v); name=v.findViewById(R.id.tvName); text=v.findViewById(R.id.tvText); time=v.findViewById(R.id.tvTime);}
        }
    }
}
