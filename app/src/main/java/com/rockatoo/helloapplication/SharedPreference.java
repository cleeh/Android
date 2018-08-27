package com.rockatoo.helloapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;

public class SharedPreference extends AppCompatActivity {
    Button editProfileBtn, btnShowLicense, btnShowContacts;
    TextView nameTextView, emailTextView;
    EditText memoEditText;

    static final String MEMO_FILE = "memo";

    ProfileDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        mDbHelper = new ProfileDbHelper(this);

        editProfileBtn = (Button)findViewById(R.id.btn_edit_profile);
        nameTextView = (TextView)findViewById(R.id.name_text_view);
        emailTextView = (TextView)findViewById(R.id.email_text_view);
        memoEditText = (EditText)findViewById(R.id.memo_edit_text);
        btnShowLicense = (Button)findViewById(R.id.btn_show_license);
        btnShowContacts = (Button)findViewById(R.id.btn_show_contacts);

        btnShowContacts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SharedPreference.this, FriendsActivity.class);
                startActivity(intent);
            }
        });

        // Event Setting
        editProfileBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SharedPreference.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        memoEditText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // When Enter is stroked, Save content
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_ENTER){
                        // Get Content
                        String memo = memoEditText.getText().toString();
                        // Save in File
                        writeToFile(MEMO_FILE, memo);
                    }
                }
                return false;
            }
        });

        btnShowLicense.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // License Activity Start
                Intent intent = new Intent(SharedPreference.this, LicenseActivity.class);
                startActivity(intent);
            }
        });

        // If there is stored name, Set it
        SharedPreferences sharedPreferences = getSharedPreferences(ProfileActivity.PREF_PROFILE, Activity.MODE_PRIVATE);
        // If there is not stored name, Set default value "Someone"
        String name = sharedPreferences.getString(ProfileActivity.PROFILE_NAME, "Someone");
        nameTextView.setText(name);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Read Profile Info
        getProfile();

        // If there is stored name, Set it
        SharedPreferences sharedPreferences = getSharedPreferences(ProfileActivity.PREF_PROFILE, Activity.MODE_PRIVATE);
        // If there is not stored name, Set default value "Someone"
        String name = sharedPreferences.getString(ProfileActivity.PROFILE_NAME, "Someone");
        nameTextView.setText(name);

        // Read Content from File
        String memo = readFromFile(getFilesDir(), MEMO_FILE);
        memoEditText.setText(memo);
    }

    private void writeToFile(String filename, String body){
        // Store in Inner Storage
        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(body.getBytes());
            fos.close();
            Log.d("MEMO", "Memo is saved in file");
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private String readFromFile(File dir, String filename){
        String text = ""; // String read from file
        File file = new File(dir, filename);
        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            text = stringBuilder.toString();
            Log.d("MEMO", "Read memo from file");
        }catch(IOException ex){
            ex.printStackTrace();
        }

        return text;
    }

    private void getProfile(){
        // Check Profile Id in SharedPreferences
        SharedPreferences sp = getSharedPreferences(ProfileActivity.PREF_PROFILE, Activity.MODE_PRIVATE);
        long profileId = sp.getLong(ProfileActivity.PREF_PROFILE_ID, -1);
        // Get Info from DB
        ContentValues values = mDbHelper.getProfile(profileId);
        if(values == null) return;
        // Info Allocation
        String name = values.getAsString(ProfileDbHelper.ProfileContract.ProfileEntry.COLUMN_NAME_NAME);
        String email = values.getAsString(ProfileDbHelper.ProfileContract.ProfileEntry.COLUMN_NAME_EMAIL);
        String phone = values.getAsString(ProfileDbHelper.ProfileContract.ProfileEntry.COLUMN_NAME_PHONE);
        // Set Info in UI
        nameTextView.setText(name);
        emailTextView.setText(email);
    }
}
