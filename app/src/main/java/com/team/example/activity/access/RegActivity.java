package com.team.example.activity.access;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team.example.R;
import com.team.example.activity.home.ProfileActivity;

import java.util.HashMap;

public class RegActivity extends AppCompatActivity {
    private Button btnRegReg;
    private EditText etRegCountry,etRegAge,etRegEmail,etRegPassword,etRegUsername;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        btnRegReg = findViewById(R.id.btnRegReg);
        etRegUsername = findViewById(R.id.etRegUsername);
        etRegCountry = findViewById(R.id.etRegCountry);
        etRegAge = findViewById(R.id.etRegAge);
        etRegPassword = findViewById(R.id.etRegPassword);
        etRegEmail = findViewById(R.id.etRegEmail);

        btnRegReg.setOnClickListener(v->{
            String email = etRegEmail.getText().toString().trim();
            String password = etRegPassword.getText().toString().trim();
            String age = etRegAge.getText().toString();
            String country = etRegCountry.getText().toString();
            String username = etRegUsername.getText().toString();
            mAuth = FirebaseAuth.getInstance();


            if(email.isEmpty()){
                etRegEmail.setError("Email is required");
                return;
            }

            if (password.isEmpty()){
                etRegPassword.setError("Password is required");
                return;
            }

            if(password.length()<6){
                etRegPassword.setError("Short Password");
                return;
            }

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    String uid = mAuth.getCurrentUser().getUid();
                    HashMap<Object, String> userInfo = new HashMap<>();
                    userInfo.put("email",email);
                    userInfo.put("password",password);
                    userInfo.put("age",age);
                    userInfo.put("country",country);
                    userInfo.put("image","");
                    userInfo.put("cover","");
                    userInfo.put("username",username);
                    userInfo.put("uid",uid);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference =database.getReference("Users");
                    reference.child(uid).setValue(userInfo);
                    Toast.makeText(RegActivity.this,"Account was created",Toast.LENGTH_SHORT);
                    startActivity(new Intent(RegActivity.this, ProfileActivity.class));
                } else{
                    Toast.makeText(RegActivity.this,"Account was not created",Toast.LENGTH_SHORT);
                }
            });
        });



        }
    }