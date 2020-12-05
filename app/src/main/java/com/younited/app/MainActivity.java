package com.younited.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parse.ParseUser;
import com.younited.app.activity.LoginActivity;
import com.younited.app.activity.ProfileActivity;
import com.younited.app.activity.SearchActivity;
import com.younited.app.activity.UsersFeedActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logoutUser(View view){
        ParseUser.logOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToMainPage(View view){
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void goToProfilePage(View view){
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void goToUsersFeedPage(View view){
        Intent intent = new Intent(MainActivity.this, UsersFeedActivity.class);
        startActivity(intent);
    }

    public void goToSearchPage(View view){
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }
}