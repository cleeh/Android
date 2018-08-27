package com.rockatoo.helloapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {
    EditText nameEditText, emailEditText, phoneEditText;
    Button submitButton;

    public static final String PROFILE_NAME = "profile_name";
    public static final String PREF_PROFILE = "pre_profile";
    public static final String PREF_EMAIL = "email";
    public static final String PREF_PROFILE_ID = "profile_id";

    private ProfileDbHelper mDbHelper;
    long profileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Widget Object Reference
        nameEditText = (EditText)findViewById(R.id.name_edit_text);
        submitButton = (Button)findViewById(R.id.btn_submit);
        emailEditText = (EditText)findViewById(R.id.email_edit_text);
        phoneEditText = (EditText)findViewById(R.id.phone_edit_text);

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

                // Save Info in DB
                setProfileToDB();

                // Present Activity Finish
                finish();
            }
        });
    }

    private void setProfileToDB() {
        // Get Profile Info
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        // Store in DB
        long profileId = mDbHelper.setProfile(name, email, phone);

        // Store Profile ID in SharedPreferences Object
        SharedPreferences sp = getSharedPreferences(PREF_PROFILE, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(PREF_PROFILE_ID, profileId);

        Log.d(Activity.class.getCanonicalName(), "Profile Info Save");
    }
}
