package com.rockatoo.helloapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {
    EditText nameEditText;
    Button submitButton;

    public static final String PROFILE_NAME = "profile_name";
    public static final String PREF_PROFILE = "pre_profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Widget Object Reference
        nameEditText = (EditText)findViewById(R.id.name_edit_text);
        submitButton = (Button)findViewById(R.id.btn_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Profile Info
                String name = nameEditText.getText().toString();

                // Save Data in SharedPreferences Object
                SharedPreferences sharedPreferences = getSharedPreferences(PREF_PROFILE, Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(PROFILE_NAME, name);
                editor.apply(); // Save

                // Present Activity Finish
                finish();
            }
        });
    }
}
