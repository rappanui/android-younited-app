package com.younited.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parse.ParseUser;
import com.younited.app.MainActivity;
import com.younited.app.R;

public class UsersFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_feed);
    }

    public void logoutUser(View view){
        ParseUser.logOut();
        Intent intent = new Intent(UsersFeedActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}