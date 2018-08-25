package com.rockatoo.helloapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BloodTypeChoiceActivity extends AppCompatActivity {
    Button btnTypeA, btnTypeB, btnTypeO, btnTypeAB;

    public static final String EXTRA_BLOOD_TYPE = "rockatoo.com.extra_blood_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_type_choice);

        // Widget Object Reference
        btnTypeA = (Button)findViewById(R.id.btn_blood_type_A);
        btnTypeB = (Button)findViewById(R.id.btn_blood_type_B);
        btnTypeO = (Button)findViewById(R.id.btn_blood_type_O);
        btnTypeAB = (Button)findViewById(R.id.btn_blood_type_AB);

// Click Event Setting
        btnTypeA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                reportBloodType("A");
            }
        });
        btnTypeB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                reportBloodType("B");
            }
        });
        btnTypeO.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                reportBloodType("O");
            }
        });
        btnTypeAB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                reportBloodType("AB");
            }
        });
    }

    // Set Selected Blood Type Info and Exit present Acitivity
    private void reportBloodType(String bloodType){
        Intent data = new Intent();
        data.putExtra(EXTRA_BLOOD_TYPE, bloodType);
        setResult(RESULT_OK, data);
        finish(); // Present Activity Exit
    }
}
