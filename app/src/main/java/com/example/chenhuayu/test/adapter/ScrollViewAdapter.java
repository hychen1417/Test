package com.example.chenhuayu.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chenhuayu.test.R;

import java.util.List;

public class ScrollViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mData;
    private LayoutInflater mInflater;

    public ScrollViewAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_scrollerview, null);
            viewHolder = new ViewHolder();
            viewHolder.item= (TextView) convertView.findViewById(R.id.item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.item.setText(mData.get(position));
        return convertView;

    }

    class ViewHolder {
        TextView item;
    }
}
