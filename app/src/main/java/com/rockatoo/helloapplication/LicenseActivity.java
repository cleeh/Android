package com.rockatoo.helloapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LicenseActivity extends AppCompatActivity {
    TextView licenseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        licenseTextView = (TextView)findViewById(R.id.license_text_view);
        String licenseBody = readFromResource(R.raw.gpl);
        licenseTextView.setText(licenseBody);
    }

    private String readFromResource(int resID){
        String text = "";
        String line;
        InputStream is = getResources().openRawResource(resID);
        // Read File Buffer
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }

            text = stringBuilder.toString();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        return text;
    }
}
