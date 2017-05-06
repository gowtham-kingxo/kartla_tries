package com.example.gowthamg.kartla_tries;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView image;

    public  void downloadimage(View view)
    {
        DownloadTask2 task = new DownloadTask2();


        try {
          //  asyncDialog.setMessage("Loading");
          //  asyncDialog.show();

           // Bitmap showimage = task.execute("https://kart.la/wp-content/uploads/2016/07/wp-image-1630888628jpeg-150x150.jpeg").get();

            String html = task.execute("https://kart.la/search-results/?gmw_keywords&gmw_address%5B0%5D=ambattur&gmw_post=post&tax_category%5B0%5D=235&gmw_distance=100&gmw_units=metric&gmw_form=2&gmw_per_page=10&gmw_lat=13.1143167&gmw_lng=80.14805509999997&gmw_px=pt&action=gmw_post").get();
            Toast.makeText(this, "Done Downloading!!!", Toast.LENGTH_SHORT).show();
          //  asyncDialog.dismiss();
           // image.setImageBitmap(showimage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public class DownloadTask extends AsyncTask<String, Void,Bitmap> {
      //  ProgressDialog asyncDialog = new ProgressDialog(MainActivity.this);
        String typeStatus;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /* @Override
                protected void onPreExecute() {
                    //set message of the dialog
                    asyncDialog.setMessage("Loading...");

                    //show dialog
                    asyncDialog.show();
                    super.onPreExecute();
                }*/
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

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }
    }

    public class  DownloadTask2 extends AsyncTask<String,Void,String>
    { ProgressDialog g ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            g =  ProgressDialog.show(MainActivity.this, "Uploading Image","Please Wait...");

        }

        @Override
        protected String doInBackground(String... urls) {
            String result ="";
            URL url;
            HttpURLConnection urlconnection = null;






            try {
                url = new URL(urls[0]);
                urlconnection = (HttpURLConnection) url.openConnection();
                InputStream inputstream = urlconnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputstream);
                long a = reader.skip(41600);
                int data = reader.read();
                 int i =1;

                while(data != -1 && i<10000)
                {
                    char current = (char) data;
                    result+= current;
                    data = reader.read();
                      i++;

                }

            } catch (IOException e) {
                e.printStackTrace();
                return  "failed";
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
           // pd.dismiss();

            super.onPostExecute(s);
            g.dismiss();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.image);




    }
}
