package com.team.example.activity.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.team.example.R;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView rvChatDialog;
    private ImageView ivCircularProfile;
    private TextView txtOnline, txtHisName;
    private EditText etMes;
    private ImageButton imbSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        rvChatDialog = findViewById(R.id.rvChatDialog);
        ivCircularProfile = findViewById(R.id.ivCircularProfile);
        txtHisName = findViewById(R.id.txtHisName);
        txtOnline = findViewById(R.id.txtOnline);
        etMes = findViewById(R.id.etMes);
        imbSend = findViewById(R.id.imbSend);
    }
}