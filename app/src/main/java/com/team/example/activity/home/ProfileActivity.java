package com.team.example.activity.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.team.example.DBHelper;
import com.team.example.R;
import com.team.example.activity.posts.AddPostActivity;
import com.team.example.activity.posts.PostDetailActivity;
import com.team.example.activity.users.UsersActivity;
import com.team.example.activity.access.MainActivity;
import com.team.example.adapter.PostAdapter;
import com.team.example.data.FirebaseHelper;
import com.team.example.model.PostModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private RecyclerView rvPost;
    private List<PostModel> postList;
    private PostAdapter postAdapter;
    private ImageView ivAvatar, ivCover;
    private TextView txtUsername, txtCountry, txtAge;
    private Button btnUpdProfile, btnUsers, btnChatUserChat, btnAddPost;
    private DatabaseReference likeRef = DBHelper.getInstance().getChildReference("likeRef");
    private DatabaseReference postRef = DBHelper.getInstance().getChildReference("Posts");

    public ProfileActivity() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ivAvatar = findViewById(R.id.ivAvatar);
        txtUsername = findViewById(R.id.txtRVUsername);
        txtCountry = findViewById(R.id.txtCountry);
        txtAge = findViewById(R.id.txtAge);
        btnUpdProfile = findViewById(R.id.btnUpdateProfile);
        btnUsers = findViewById(R.id.btnUsers);
        btnChatUserChat = findViewById(R.id.btnChatUserChat);
        btnAddPost = findViewById(R.id.btnAddPost);
        rvPost = findViewById(R.id.rvPost);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        rvPost.setLayoutManager(layoutManager);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("Users");

        postList = new ArrayList<>();
        loadPost();

        btnAddPost.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, AddPostActivity.class)));


        btnUpdProfile.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, UpdateProfileActivity.class)));

        btnUsers.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, PostDetailActivity.class));

        });


        Query query = dbRef.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String username = "" + ds.child("username").getValue();
                    String country = "" + ds.child("country").getValue();
                    String image = "" + ds.child("image").getValue();
                    String age = "" + ds.child("age").getValue();

                    txtUsername.setText(username);
                    txtCountry.setText(country);
                    txtAge.setText(age);

                   if (image != null)FirebaseHelper.getInstance()
                            .getReference(image)
                            .getDownloadUrl()
                            .addOnSuccessListener(uri ->
                                    Picasso.get().load(uri).placeholder(R.drawable.ic_deafult_img).into(ivAvatar))
                            .addOnFailureListener(e -> Log.e("Firebase storage:",e.getLocalizedMessage()));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {

        } else {
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        }
    }

    private void loadPost() {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Posts");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    PostModel postModel = ds.getValue(PostModel.class);
                    postList.add(postModel);
                }
                postAdapter = new PostAdapter(postList, (event, model) -> {
                    if (event.equals(PostAdapter.LIKE)) {
                        PostModel tmpModel = model;
                        ArrayList<String> pValueLikes = tmpModel.getpLikes();
                        String postIde = model.getPostId();
                        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        if (isLiked(id, tmpModel.getpLikes())) {
                            pValueLikes.remove(id);
                        } else {
                            pValueLikes.add(id);
                        }
                        postRef.child(postIde).child("likeRef").setValue(pValueLikes);
                    }
                    Toast.makeText(getBaseContext(), event, Toast.LENGTH_SHORT).show();
                });
                rvPost.setAdapter(postAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private boolean isLiked(String id, ArrayList<String> likeRef) {
        for (String likeId : likeRef) {
            if (likeId.equals(id)) return true;
        }
        return false;

    }
}

