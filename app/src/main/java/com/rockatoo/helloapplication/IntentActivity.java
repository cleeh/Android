package com.rockatoo.helloapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IntentActivity extends AppCompatActivity {
    Button btn1, btn2, btnDialNumber, btnBloodTypeQuery;
    EditText messageEditText;
    TextView bloodTypeTextView;

    public static String EXTRA_MESSAGE = "rockatoo.com.extra_message";
    public static int BLOOD_TYPE_CHOICE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        // Widget Object Reference
        btn1 = (Button)findViewById(R.id.btn_start_activity_1);
        btn2 = (Button)findViewById(R.id.btn_start_activity_2);
        btnDialNumber = (Button)findViewById(R.id.btn_dial_number);
        messageEditText = (EditText)findViewById(R.id.message_edit_text);
        btnBloodTypeQuery = (Button)findViewById(R.id.btn_choose_blood_type);
        bloodTypeTextView = (TextView)findViewById(R.id.blood_type_text_view);

        // Click Event Setting
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentActivity.this, Activity1.class);
                startActivity(intent);
                }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentActivity.this, Activity2.class);
                startActivity(intent);
            }
        });

        btnDialNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                // Tel Number Setting
                intent.setData(Uri.parse("tel:010-1234-5678"));
                startActivity(intent);
            }
	    });

        // If Enter key is stroked, New Activity is executed after Message Input
        messageEditText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_ENTER){
                        String message = messageEditText.getText().toString();
                        Intent intent = new Intent(IntentActivity.this, ShowMessageActivity.class);
                        intent.putExtra(EXTRA_MESSAGE, message);
                        startActivity(intent);
                    }
                }
                return false;
            }
	    });

        btnBloodTypeQuery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentActivity.this, BloodTypeChoiceActivity.class);
                startActivityForResult(intent, BLOOD_TYPE_CHOICE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // Recognize Activity returned Result
        if(requestCode == BLOOD_TYPE_CHOICE){
            // Check Result Status
            if(resultCode == RESULT_OK){
                // Extract Blood Type Info
                String bloodType = data.getStringExtra(BloodTypeChoiceActivity.EXTRA_BLOOD_TYPE);
                bloodTypeTextView.setText(bloodType);
            }
        }
    }
}
