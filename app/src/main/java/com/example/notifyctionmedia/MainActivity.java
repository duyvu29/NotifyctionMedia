package com.example.notifyctionmedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.dynamicanimation.animation.SpringAnimation;

import android.support.v4.media.session.MediaSessionCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.widget.Button;

import com.google.android.material.badge.BadgeUtils;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "Channel";
    private Button btn ;
    private MediaSessionCompat mediaSessionCompat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNotifycation();
            }
        });

    }

    private void setNotifycation() {


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_android_black_24dp);
        mediaSessionCompat = new MediaSessionCompat(this ,"tag");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_android_black_24dp)
                    .setContentText("Âm nhạc")
                    .setContentTitle("aaaaaaaaa")
                    .setLargeIcon(bitmap)
                    // Add media control buttons that invoke intents in your media service
                    .addAction(R.drawable.ic_baseline_skip_previous_24, "Previous", null) // #0
                   .addAction(R.drawable.ic_baseline_pause_24, "Pause", null) // #1
                   .addAction(R.drawable.ic_baseline_skip_next_24, "Next", null)     // #2
                    // Apply the media style template
                    .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                            .setShowActionsInCompactView(1 /* #1: pause button */)
                            .setMediaSession(mediaSessionCompat.getSessionToken()))

                    .build();
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(1,notification);
        }
    }



}
