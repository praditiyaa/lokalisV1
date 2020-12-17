package com.lokalis.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    //declaring variables
    ImageView regLogo;
    TextView regStart;
    TextInputLayout regName, regUsername, regEmail, regPhoneNumber, regPassword;
    Button regButton, regToLogin;

    //FireBase
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //hooks
        regLogo = findViewById(R.id.logoRegister);
        regStart = findViewById(R.id.textStart);
        regName = findViewById(R.id.editNameReg);
        regUsername = findViewById(R.id.editUsernameReg);
        regEmail = findViewById(R.id.editEmailReg);
        regPhoneNumber = findViewById(R.id.editPhoneReg);
        regPassword = findViewById(R.id.editPasswordReg);
        regButton = findViewById(R.id.buttonRegisterReg);
        regToLogin = findViewById(R.id.buttonLoginReg);

        //Save data in FireBase on Button Click
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validation
                if (!validationName() | !validationUsername() | !validationEmail() | !validationPhoneNumber() | !validationPassword()) {
                    return;
                }

                //get values in string
                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNumber = regPhoneNumber.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                //store data in firebase
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");
                UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNumber, password);
                reference.child(username).setValue(helperClass);

                //account has been made sign
                Toast.makeText(RegisterActivity.this, "Account has been made", Toast.LENGTH_LONG).show();

                //delay to login page after account is made
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //into profile activity
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                        //animation
                        Pair[] pairs = new Pair[6];
                        pairs[0] = new Pair<View, String>(regLogo, "logo_image");
                        pairs[1] = new Pair<View, String>(regStart, "text_image");
                        pairs[2] = new Pair<View, String>(regUsername, "edit_username");
                        pairs[3] = new Pair<View, String>(regPassword, "edit_password");
                        pairs[4] = new Pair<View, String>(regButton, "button_login");
                        pairs[5] = new Pair<View, String>(regToLogin, "button_register");

                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this, pairs);
                        startActivity(intent, options.toBundle());
                    }
                }, 2000);

            }
        });

        //Back into login activity
        regToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                //animation
                Pair[] pairs = new Pair[6];
                pairs[0] = new Pair<View, String>(regLogo, "logo_image");
                pairs[1] = new Pair<View, String>(regStart, "text_image");
                pairs[2] = new Pair<View, String>(regUsername, "edit_username");
                pairs[3] = new Pair<View, String>(regPassword, "edit_password");
                pairs[4] = new Pair<View, String>(regButton, "button_login");
                pairs[5] = new Pair<View, String>(regToLogin, "button_register");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this, pairs);
                startActivity(intent, options.toBundle());

            }
        });

    }

    //validation for every input
    private Boolean validationName() {

        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validationUsername() {

        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        Query usernameCheck = FirebaseDatabase.getInstance().getReference("Users").orderByChild("username").equalTo(val);
        usernameCheck.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getChildrenCount() > 0) {
                    regUsername.setError("Username already exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("White Spaces are not allowed");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validationEmail() {

        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

        Query emailCheck = FirebaseDatabase.getInstance().getReference("Users").orderByChild("email").equalTo(val);
        emailCheck.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getChildrenCount() > 0) {
                    regEmail.setError("Email address already exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validationPhoneNumber() {

        String val = regPhoneNumber.getEditText().getText().toString();

        Query usernameCheck = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNumber").equalTo(val);
        usernameCheck.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getChildrenCount() > 0) {
                    regPhoneNumber.setError("Phone number already exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (val.isEmpty()) {
            regPhoneNumber.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNumber.setError(null);
            regPhoneNumber.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validationPassword() {

        String val = regPassword.getEditText().getText().toString();
        final Pattern passwordVal = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!passwordVal.matcher(val).matches()) {
            regPassword.setError("Password to weak, improve it");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }

    }

}
