package com.example.plantchat;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;

import android.os.Bundle;

import android.view.View;
import android.widget.VideoView;
import java.util.Random;

public class NatureActivity extends AppCompatActivity {
    private VideoView natureVideoView;
    private MediaPlayer mediaPlayer;
    private Random random;

    Intent intent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nature);

        random = new Random();

        // Generate a random number between 0 and 2
        int randomNumber = random.nextInt(2);

        // Select music file based on the random number
        int musicResourceId;
        if (randomNumber == 0) {
            musicResourceId = R.raw.stream;
        } else {
            musicResourceId = R.raw.birds;
        }
        // Initialize the MediaPlayer and start playing the music
        mediaPlayer = MediaPlayer.create(this, musicResourceId);
        mediaPlayer.setLooping(true); // Set the music to loop continuously
        mediaPlayer.start();

        natureVideoView = findViewById(R.id.nature_video_view);

        // Set path to your own video file in "raw" directory
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.nature_animation;
        natureVideoView.setVideoURI(Uri.parse(videoPath));
        natureVideoView.start();

        // Set completion listener for the video playback
        natureVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // Restart the video playback
                natureVideoView.start();
            }
        });

        findViewById(R.id.btnLanding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Stop the MediaPlayer
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                // Finish the activity and go back
                finish();
                startActivity(new Intent(NatureActivity.this,LandingActivity.class));


            }
        });

        intent = new Intent(getApplicationContext(), LandingActivity.class);
        intent.putExtra("a", true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer resources when the activity is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }


        intent.putExtra("a", false);


    }


}






