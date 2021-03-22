package com.team.example.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.team.example.R;
import com.team.example.model.PostModel;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<PostModel> postList;
    private PostHolder postHolder;

    public PostAdapter(Context context,List<PostModel>postList){
        this.context = context;
        this.postList = postList;
    }

    public PostAdapter() {
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_posts, parent,false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String uid = postList.get(position).getuId();
        String puName = postList.get(position).getPuName();
        String puTitle= postList.get(position).getPostTitle();
        String puDescription = postList.get(position).getPostDescription();
        String pImage = postList.get(position).getPostImage();
        String pTimeStape = postList.get(position).getpTimeStape();
        String puPicture = postList.get(position).getPuPicture();

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
       // calendar.setTimeInMillis(Long.parseLong(pTimeStape));
        String pTime = DateFormat.format("dd/mm/yyyy hh:mm:aa",calendar).toString();

        postHolder.txtpDescription.setText(puDescription);
        postHolder.txtpTitle.setText(puTitle);
        postHolder.txtpuName.setText(puName);
        postHolder.txtpTime.setText(pTime);

        postHolder.imgbtnMore.setOnClickListener(v->{
            Toast.makeText(context,"More",Toast.LENGTH_SHORT).show();

        });

        postHolder.btnLike.setOnClickListener(v->{
            Toast.makeText(context,"like",Toast.LENGTH_SHORT).show();

        });

        postHolder.btnShare.setOnClickListener(v->{
            Toast.makeText(context,"Share",Toast.LENGTH_SHORT).show();

        });

        postHolder.btnComment.setOnClickListener(v->{
            Toast.makeText(context,"Comment",Toast.LENGTH_SHORT).show();

        });


        try{
            Picasso.get().load(puPicture).placeholder(R.drawable.ic_deafult_img).into(postHolder.ivPictureUser);
        } catch( Exception e){
            
        }

        if(pImage.equals("NoImage")){
            postHolder.ivShowPost.setVisibility(View.GONE);

        }

       else {
            try {
                Picasso.get().load(pImage).into(postHolder.ivShowPost);
            } catch (Exception e) {

            }
        }

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    class PostHolder extends RecyclerView.ViewHolder {
        private ImageView ivPictureUser,ivShowPost;
        private TextView txtpuName,txtpTime,txtpTitle,txtpDescription,txtpLikes;
        private ImageButton imgbtnMore;
        private Button btnLike, btnShare, btnComment;
        public PostHolder(@NonNull View itemView) {

            super(itemView);
            ivPictureUser = itemView.findViewById(R.id.ivPictureUser);
            ivShowPost = itemView.findViewById(R.id.ivShowPost);
            txtpuName = itemView.findViewById(R.id.txtpuName);
            txtpTime = itemView.findViewById(R.id.txtTime);
            txtpTitle = itemView.findViewById(R.id.txtpTitle);
            txtpDescription = itemView.findViewById(R.id.txtpDescription);
            imgbtnMore =itemView.findViewById(R.id.imbgtnMore);
            btnLike = itemView.findViewById(R.id.btnLike);
            btnShare= itemView.findViewById(R.id.btnShare);
            btnComment = itemView.findViewById(R.id.btnComment);

        }
    }
}
