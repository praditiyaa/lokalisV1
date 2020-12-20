package com.lokalis.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.zip.Inflater;

public class MyProfileFragment extends Fragment {

    //declaring variable
    TextInputLayout editName, editEmailAddress, editPhoneNumber, editPassword;
    TextView proName, proUsername;
    Button proUpdate, proLogout;

    //global variable
    String user_username, user_name, user_email, user_phoneNumber, user_password;

    //firebase
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.myprofile_fragment, container, false);
        return root;


    }


}