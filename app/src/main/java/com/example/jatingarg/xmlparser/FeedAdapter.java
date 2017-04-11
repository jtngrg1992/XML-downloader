package com.example.jatingarg.xmlparser;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by jatingarg on 11/04/17.
 */

public class FeedAdapter extends ArrayAdapter {
    private static final String TAG = "FeedAdapter";
    private final int layoutResource;
    private final LayoutInflater mInflator;
    private final List<FeedEntry> feeds;


    public FeedAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<FeedEntry> feeds) {
        super(context, resource, feeds);
        this.layoutResource = resource;
        this.mInflator = LayoutInflater.from(context);
        this.feeds = feeds;
    }

    @Override
    public int getCount() {
        return feeds.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder view;
        if(convertView == null){
            convertView = mInflator.inflate(layoutResource,parent,false);
            view = new ViewHolder(convertView);
            convertView.setTag(view);
        }else{
            view = (ViewHolder) convertView.getTag();
        }

        FeedEntry currentFeed = feeds.get(position);
        view.artistTV.setText(currentFeed.getArtist());
        view.nameTV.setText(currentFeed.getName());
        view.summaryTV.setText(currentFeed.getSummary());
        return convertView;
    }

    private class ViewHolder {
        final TextView nameTV;
        final TextView artistTV;
        final TextView summaryTV;

        public ViewHolder(View v) {
            nameTV = (TextView) v.findViewById(R.id.name);
            artistTV = (TextView) v.findViewById(R.id.artist);
            summaryTV = (TextView) v.findViewById(R.id.summary);
        }
    }
}
