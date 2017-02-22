package com.spottechnician.a12_01_2016weddingproject;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by MyPC on 12-01-2017.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public Integer[] mThumbIds = {
             R.drawable.umesh_and_shubhangi_2, R.drawable.invitation2_test
    };

    public ImageAdapter(Context c) {
        mContext = c;
        Log.d("Testing","mContext: "+mContext);
    }

    @Override
    public int getCount() {
        Log.d("Testing","mThumbIds.length: "+mThumbIds.length);
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        Log.d("Testing", "mThumbIds[position]: "+mThumbIds[position]);
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(2, 2, 2, 2);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}
