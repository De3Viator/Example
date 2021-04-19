package com.team.example.data;

import android.graphics.Bitmap;

import com.google.firebase.storage.StorageReference;
import com.team.example.activity.access.RegActivity;
import com.team.example.model.UserModel;

public interface IDataHelper {
    StorageReference getReference(String url);
    void createUser(Bitmap bitmap, UserModel usermodel, RegActivity activity);
}
