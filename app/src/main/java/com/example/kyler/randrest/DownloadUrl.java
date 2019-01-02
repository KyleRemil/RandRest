package com.example.kyler.randrest;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kyler on 4/14/2017.
 */

public class DownloadUrl {
    public String readUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            //create http connection with url
            urlConnection = (HttpURLConnection) url.openConnection();

            //connecting to url
            urlConnection.connect();

            //reading data from url
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            Log.d("Exception", data.toString());
            br.close();
        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
}
