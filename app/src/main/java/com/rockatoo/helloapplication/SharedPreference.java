package com.rockatoo.helloapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SharedPreference extends AppCompatActivity {
    Button editProfileBtn;
    TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        editProfileBtn = (Button)findViewById(R.id.btn_edit_profile);
        nameTextView = (TextView)findViewById(R.id.name_text_view);

        // Event Setting
        editProfileBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SharedPreference.this, ProfileActivity.class);
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
        
        // If there is stored name, Set it
        SharedPreferences sharedPreferences = getSharedPreferences(ProfileActivity.PREF_PROFILE, Activity.MODE_PRIVATE);
        // If there is not stored name, Set default value "Someone"
        String name = sharedPreferences.getString(ProfileActivity.PROFILE_NAME, "Someone");
        nameTextView.setText(name);
    }
}
