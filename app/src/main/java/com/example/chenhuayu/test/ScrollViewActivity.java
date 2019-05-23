package com.example.chenhuayu.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.chenhuayu.test.adapter.ScrollViewAdapter;
import com.example.chenhuayu.test.view.MyScrollerView;

import java.util.ArrayList;
import java.util.List;

public class ScrollViewActivity extends AppCompatActivity {

    private ScrollViewAdapter adapter;
    private List<String> data = new ArrayList<String>();

    private ListView listView;
    private MyScrollerView scrollerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        for (int i = 0; i < 50; i++) {
            data.add("item" + i);
        }
        adapter = new ScrollViewAdapter(this, data);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        scrollerView = (MyScrollerView) findViewById(R.id.scrollView);
        scrollerView.setListView(listView);
        scrollerView.post(new Runnable() {//解决ScrollView下嵌套ListView进页面不在顶部的问题
            @Override
            public void run() {
                scrollerView.scrollTo(0, 0);
            }
        });


    }
}
