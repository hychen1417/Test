package com.example.chenhuayu.test;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

public class PicassoActivity extends AppCompatActivity {
    private ImageView imageView;
    private ImageView error;
    private ImageView video;
    private SimpleDraweeView frescoVideo;
    private String videoUri = "file:////storage/emulated/0/Android/VCG42sfw30470031 (1).mp4";///storage/emulated/0/Android/VCG42sfw30470031 (1).mp4

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        imageView = (ImageView) findViewById(R.id.image);
        error = (ImageView) findViewById(R.id.error);
        video = (ImageView) findViewById(R.id.video);
        frescoVideo = (SimpleDraweeView) findViewById(R.id.fresco_video);
        Picasso.with(this)
                .load("http://ww3.sinaimg.cn/large/610dc034jw1fasakfvqe1j20u00mhgn2.jpg")
                .resize(200, 200)
                .centerCrop()
//                .onlyScaleDown()
                .placeholder(R.mipmap.ic_waiting)
                .error(R.drawable.ic_launcher)
                .into(imageView);

        Picasso.with(this)
                .load("a")
                .error(R.drawable.ic_launcher)
                .into(error);

        Picasso.with(this).load(videoUri)
                .config(Bitmap.Config.ARGB_8888)
                .fit()
                .centerCrop()
                .into(video);
        //fresco 可以直接加载视频缩略图
        //setImageURI()
        frescoVideo.setImageURI(Uri.parse(videoUri));
        //video.setImageURI(Uri.parse(videoUri)); 不能加载出来视频缩略图




    }
}
