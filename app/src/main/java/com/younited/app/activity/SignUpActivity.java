package com.younited.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;
import com.younited.app.R;
import com.younited.app.util.ParseError;

public class SignUpActivity extends AppCompatActivity {

    private EditText editUsername;
    private EditText editEmail;
    private EditText editPassword;
    private TextView textLoginDirectioner;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editUsername = (EditText) findViewById(R.id.edit_signup_username);
        editEmail = (EditText) findViewById(R.id.edit_signup_email);
        editPassword = (EditText) findViewById(R.id.edit_signup_password);
        textLoginDirectioner = (TextView) findViewById(R.id.text_signup_login);
        buttonSignUp = (Button) findViewById(R.id.button_signup);

        buttonSignUp.setOnClickListener(v -> signUpUser());
        textLoginDirectioner.setOnClickListener(v -> goToLoginPage());

    }

    private void signUpUser(){
        ParseUser user = new ParseUser();
        user.setUsername(editUsername.getText().toString());
        user.setEmail(editEmail.getText().toString());
        user.setPassword(editPassword.getText().toString());

        user.signUpInBackground(e -> {
            if(e == null){
                Toast.makeText(SignUpActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                goToLoginPage();
            } else {
                Toast.makeText(SignUpActivity.this, e.getMessage().toUpperCase(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void goToLoginPage(){
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}