package com.example.chenhuayu.test.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chenhuayu.test.R;
import com.example.chenhuayu.test.util.TouchFactory;

public class ExpandClickAreaView extends FrameLayout implements View.OnClickListener {

    private MyImageView myImageView;

    public ExpandClickAreaView(@NonNull Context context) {
        this(context,null);
    }

    public ExpandClickAreaView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ExpandClickAreaView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init() {
        setOnTouchListener(TouchFactory.creatTouchListener());
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View v) {

    }

    public MyImageView getMyImageView() {
        if (myImageView == null) {
            myImageView = (MyImageView) findViewById(R.id.mImage);
        }
        return myImageView;
    }
}
