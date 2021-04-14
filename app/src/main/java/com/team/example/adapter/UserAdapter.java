package com.team.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.team.example.R;
import com.team.example.activity.users.UsersActivity;
import com.team.example.model.UserModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {
    Context context;
    List<UserModel> userList;
    ImageView ivAvatar;
    TextView txtRVUsername;

    public UserAdapter(Context context, List<UserModel> userList) {
        this.context = context;
        this.userList = userList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rwo_user, parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String hisUID = userList.get(position).getUid();
        String userimage = userList.get(position).getImage();
        String username = userList.get(position).getUsername();
        txtRVUsername.setText(username);
        try {
            Picasso.get().load(userimage).placeholder(R.drawable.ic_deafult_img).into(ivAvatar);

        } catch (Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            txtRVUsername = itemView.findViewById(R.id.txtRVUsername);
        }
    }
}
