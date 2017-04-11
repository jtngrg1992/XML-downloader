package com.example.jatingarg.xmlparser;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by jatingarg on 11/04/17.
 */

public class Downloader extends AsyncTask<String, Void, String> {
    private static final String TAG = "Downloader";
    public interface  DownloadListener{
        void dataAvailable(List<FeedEntry> feeds);
    }
    private DownloadListener mListener;

    public Downloader(DownloadListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected String doInBackground(String... params) {
        String feed = downloadXML(params[0]);
        return feed;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Parse xml
        if (s != null) {
            XMLParser parser = new XMLParser();
            if (parser.parse(s)){
                Log.d(TAG, "onPostExecute: parse successfull");
                Log.d(TAG, "onPostExecute: parsed " + parser.feeds.size() + " items");
                mListener.dataAvailable(parser.feeds);

            }

        }else{
            Log.e(TAG, "onPostExecute: got null in xml string");
        }


    }

    private String downloadXML(String link) {
        StringBuilder xmlResult = new StringBuilder();
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int response = connection.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            int charsRead = 0;
            char[] inputBuffer = new char[500];
            while (true) {
                charsRead = reader.read(inputBuffer);
                if (charsRead < 0) {
                    break;
                } else if (charsRead > 0) {
                    xmlResult.append(String.copyValueOf(inputBuffer, 0, charsRead));
                }
            }

            reader.close();
            return xmlResult.toString();
        } catch (MalformedURLException e) {

        } catch (IOException e) {

        } catch (SecurityException e) {

        }
        return null;
    }
}
