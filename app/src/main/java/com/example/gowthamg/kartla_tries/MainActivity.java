package com.example.gowthamg.kartla_tries;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView image;

    public  void downloadimage(View view)
    {
        DownloadTask task = new DownloadTask();
        try {
            Bitmap showimage = task.execute("https://kart.la/wp-content/uploads/2016/07/wp-image-1630888628jpeg-150x150.jpeg").get();
            image.setImageBitmap(showimage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public class DownloadTask extends AsyncTask<String, Void,Bitmap> {
        ProgressDialog asyncDialog = new ProgressDialog(MainActivity.this);
        String typeStatus;
        @Override
        protected void onPreExecute() {
            //set message of the dialog
            asyncDialog.setMessage("Loading...");
            //show dialog
            asyncDialog.show();
            super.onPreExecute();
        }
        @Override
        protected Bitmap doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection connection = null;


            try {
                url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
               connection.connect();
                InputStream is = connection.getInputStream();
                Bitmap mybitmap = BitmapFactory.decodeStream(is);
                return mybitmap;



            } catch (IOException e) {
                e.printStackTrace();

            }

           return null;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.image);




    }
}
