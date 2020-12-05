package com.younited.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.younited.app.MainActivity;
import com.younited.app.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editUsername;
    private EditText editPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null) {
            //goToMainPage();
        }

        editUsername = (EditText) findViewById(R.id.edit_login_username);
        editPassword = (EditText) findViewById(R.id.edit_login_password);
        buttonLogin = (Button) findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(v -> loginUser());
    }

    private void loginUser(){
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    Toast.makeText(LoginActivity.this, "Login realizado com sucesso!", Toast.LENGTH_LONG).show();
                    goToMainPage();
                } else {
                    Toast.makeText(LoginActivity.this, e.getMessage().toUpperCase(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public void goToSignUpPage(View view){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void goToMainPage(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}