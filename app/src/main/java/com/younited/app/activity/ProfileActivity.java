package com.younited.app.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;
import com.younited.app.MainActivity;
import com.younited.app.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {
    ParseObject profile = new ParseObject("Profile");
    ParseQuery<ParseObject> query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

        @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {

        query = ParseQuery.getQuery("Profile");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.findInBackground((objects, e) -> {
            if(e == null) {
                Log.i("teste",profile.toString());
                profile = objects.get(0);
            } else {

            }
        });

        ImageView imageView = (ImageView) findViewById(R.id.profile_image);
        TextView usernameText = (TextView) findViewById(R.id.profile_username);
        usernameText.setText(ParseUser.getCurrentUser().getUsername());

        Picasso.with(context)
                .load(profile.getParseFile("profile_image").getUrl())
                .into(imageView);
        return super.onCreateView(parent, name, context, attrs);

    }

    public void updateProfilePhoto(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            Uri imageLocal = data.getData();

            try{
                Bitmap image = MediaStore.Images.Media.getBitmap(getContentResolver(), imageLocal);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 75, stream);
                byte[] byteArray = stream.toByteArray();

                String username = ParseUser.getCurrentUser().getUsername();
                String filename = username + "profile.jpg";
                ParseFile parseFile = new ParseFile(filename, byteArray);

                ParseObject profile = new ParseObject("Profile");
                profile.put("username", username);
                profile.put("profile_photo", parseFile);

                profile.saveInBackground(e -> {
                    if(e == null){
                        Toast.makeText(ProfileActivity.this, "Foto atualizada com sucesso!", Toast.LENGTH_LONG).show();
                        updatePage();
                    } else {
                        Toast.makeText(ProfileActivity.this, e.getMessage().toUpperCase(), Toast.LENGTH_LONG).show();
                    }
                });
            } catch (IOException e){

            }
        }
    }

    public void logoutUser(View view){
        ParseUser.logOut();
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void updatePage(){
        Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}