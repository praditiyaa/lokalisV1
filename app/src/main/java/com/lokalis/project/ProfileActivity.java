package com.lokalis.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class ProfileActivity extends AppCompatActivity {

    //declaring variable
    TextInputLayout editName, editEmailAddress, editPhoneNumber, editPassword;
    TextView proName, proUsername;
    String user_username, user_name, user_email, user_phoneNumber, user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //hooks
        editName = findViewById(R.id.editProfileName);
        editEmailAddress = findViewById(R.id.editProfileEmail);
        editPhoneNumber = findViewById(R.id.editProfilePhone);
        editPassword = findViewById(R.id.editProfilePassword);
        proName = findViewById(R.id.profileName);
        proUsername = findViewById(R.id.profileUserName);

        //show all data
        showAllUserData();

    }

    //show user profile
    private void showAllUserData() {

        Intent intent = getIntent();
        user_username = intent.getStringExtra("username");
        user_name = intent.getStringExtra("name");
        user_email = intent.getStringExtra("email");
        user_phoneNumber = intent.getStringExtra("phoneNumber");
        user_password = intent.getStringExtra("password");

        proName.setText(user_name);
        proUsername.setText(user_username);
        editName.getEditText().setText(user_name);
        editEmailAddress.getEditText().setText(user_email);
        editPhoneNumber.getEditText().setText(user_phoneNumber);
        editPassword.getEditText().setText(user_password);

    }

    public void update(View view){

    }

}