package com.example.chenhuayu.test;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends Activity {

    private Button btnSendNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initView();
        final long [] vibrates = {0, 1000, 1000, 1000};
        btnSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Intent intent = new Intent(NotificationActivity.this, NotificationContextActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                Notification notification = new Notification.Builder(NotificationActivity.this).setContentTitle("通知的标题")
                        .setContentText("通知的内容").setSmallIcon(R.drawable.ic_launcher).setContentIntent(pendingIntent).setVibrate(vibrates).build();
                manager.notify(1, notification);
            }
        });
    }

    public void initView() {
        btnSendNotification = (Button) findViewById(R.id.btn_send_notification);
    }

}
