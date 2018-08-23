package com.rockatoo.helloapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Read Message which gived
        Intent receivedIntent = getIntent();
        String msg = receivedIntent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Show Message on TextView Widget
        TextView userMsgTextView = (TextView)findViewById(R.id.user_msg);
        userMsgTextView.setText(msg);
    }
}
