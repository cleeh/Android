package com.rockatoo.helloapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowMessageActivity extends AppCompatActivity {
    TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);

        Intent intent = getIntent();
        String receviedMessage = intent.getStringExtra(IntentActivity.EXTRA_MESSAGE);

        messageTextView = (TextView)findViewById(R.id.message_text_view);
        messageTextView.setText(receviedMessage);
    }
}
