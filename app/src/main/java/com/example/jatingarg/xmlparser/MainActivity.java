package com.example.jatingarg.xmlparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Downloader.DownloadListener{
    private ListView mListView;
    private FeedAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        Downloader mDownloader = new Downloader(this);
        mDownloader.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/toppaidapplications/limit=10/xml");
    }

    @Override
    public void dataAvailable(List<FeedEntry> feeds) {
        FeedAdapter adapter = new FeedAdapter(this,R.layout.feed_row,feeds);
        mListView.setAdapter(adapter);
    }
}
