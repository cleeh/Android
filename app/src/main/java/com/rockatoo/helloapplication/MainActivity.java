package com.rockatoo.helloapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static String EXTRA_MESSAGE = "rockatoo.com.android.HelloApplication.extra_message";
    EditText messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageEditText = (EditText)findViewById(R.id.edit_message);
    }

    public void showMessage(View view){
        // Get Text from Editor which is User Input
        String msg = messageEditText.getText().toString();
        // Delete Input Message
        messageEditText.setText("");
        // Show Message on Display
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void sendMessage(View view){
        // Set Activity to Convert
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        // Read User Input Text
        String msg = messageEditText.getText().toString();
        // Put in Message to give to Activity
        intent.putExtra(EXTRA_MESSAGE, msg);
        // Start Activity
        startActivity(intent);
    }

    public void processEvent(View view) {
        Intent intent = new Intent(this, EventProcessing.class);
        startActivity(intent);
    }

    public void intentActivity(View view) {
        Intent intent = new Intent(this, Intent.class);
        startActivity(intent);
    }

    public void sharedPreferenceActivity(View view){
        Intent intent = new Intent(this, Intent.class);
        startActivity(intent);
    }

    public void internetIO(View view){
        Intent intent = new Intent(this, InternetIO.class);
        startActivity(intent);
    }

    public void serviceActivity(View view){
        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
    }
}
