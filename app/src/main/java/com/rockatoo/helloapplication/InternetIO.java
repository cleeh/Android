package com.rockatoo.helloapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class InternetIO extends AppCompatActivity {
    EditText urlEditText;
    ImageView downloadedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_io);

        urlEditText = (EditText)findViewById(R.id.image_url_edittext);
        downloadedImageView = (ImageView)findViewById(R.id.downloaded_image_view);

        // After fill URL in and Enter is stroked, execute download
        urlEditText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    String targetURLString = urlEditText.getText().toString();
                    // Image Download is executed
                    new DownloadImageTask().execute(targetURLString);
                }
                return false;
            }
        });
    }

    boolean isNetworkConnected(){
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get Network Info
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        // Check Network Status
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }

        return false;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                // HTTP Client Definition
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                // Connect
                connection.connect();
                int responseCode = connection.getResponseCode();
                if(responseCode != 200){
                    Log.d(DownloadImageTask.class.getSimpleName(), "Image Download failed");
                    return null;
                }

                InputStream is = connection.getInputStream();
                // Convert Stream into Bitmap
                Bitmap imageBitmap = BitmapFactory.decodeStream(is);
                is.close();

                return imageBitmap;
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            // If Download ends, Show Image
            downloadedImageView.setImageBitmap(bitmap);
        }
    }
}
