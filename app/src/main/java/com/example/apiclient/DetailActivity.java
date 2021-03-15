package com.example.apiclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apiclient.api.ApiClient;
import com.example.apiclient.api.ApiInterface;
import com.example.apiclient.api.UserData;
import com.example.apiclient.api.UserDataDetail;
import com.example.apiclient.ui.LogoView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private UserDataDetail mUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (getActionBar() != null)
            getActionBar().hide();

        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getString("username") != null) {
            String mUserName = getIntent().getExtras().getString("username");
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<UserDataDetail> call = apiInterface.getUser(mUserName);
            call.enqueue(new Callback<UserDataDetail>() {
                @Override
                public void onResponse(Call<UserDataDetail> call, Response<UserDataDetail> response) {
                    mUserData = (UserDataDetail) response.body();
                    setView();
                }

                @Override
                public void onFailure(Call<UserDataDetail> call, Throwable t) {
                    Log.d("Yo", "Errror!");
                }
            });
        }
    }

    private void setView() {
        ImageView imgClose = findViewById(R.id.img_close);
        LogoView userFace = findViewById(R.id.img_face);
        TextView userName = findViewById(R.id.user_name);
        TextView userBio = findViewById(R.id.user_bio);
        TextView userLogin = findViewById(R.id.user_login);
        TextView userAdmin = findViewById(R.id.user_staff);
        TextView userLocation = findViewById(R.id.user_location);
        TextView userBlog = findViewById(R.id.user_blog);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        userFace.setImage(mUserData.getAvatar_url(), LogoView.CIRCLEIMAGE);
        userName.setText(mUserData.getName());
        userBio.setText(mUserData.getBio());
        userLogin.setText(mUserData.getLogin());
        if (mUserData.isSite_admin()) {
            userAdmin.setVisibility(View.VISIBLE);
        }
        userLocation.setText(mUserData.getLocation());
        userBlog.setText(mUserData.getBlog());
        userBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(mUserData.getBlog()));
                startActivity(intent);
            }
        });
    }
}
