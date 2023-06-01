package com.example.plantchat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class LandingActivity extends AppCompatActivity implements Runnable {
    /* tag */
    private static final String TAG = "BluetoothSample";

    /* Bluetooth Adapter */
    private BluetoothAdapter mAdapter;

    /* Bluetoothデバイス */
    private BluetoothDevice mDevice;

    /* Bluetooth UUID */
    private final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    /* デバイス名 */
    private final String DEVICE_NAME = "FireFly-11A4";

    /* Soket */
    private BluetoothSocket mSocket;

    /* Thread */
    private Thread mThread;

    /* Threadの状態を表す */
    private boolean isRunning;


    /** Connect確認用フラグ */
    private boolean connectFlg = false;



    private VideoView landingVideoView;
    private MediaPlayer mediaPlayer;
    private TextView statusValue;

    private boolean activityOpen = false;








    @SuppressLint("MissingPermission")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);


        // Bluetoothのデバイス名を取得
        // デバイス名は、RNBT-XXXXになるため、
        // DVICE_NAMEでデバイス名を定義
        mAdapter = BluetoothAdapter.getDefaultAdapter();

        @SuppressLint("MissingPermission") Set<BluetoothDevice> devices = mAdapter.getBondedDevices();

        for (BluetoothDevice device : devices) {
            String DEVICE_NAME = "FireFly-11A4";
            if (device.getName().equals(DEVICE_NAME)) {
                //mStatusTextView.setText("find: " + device.getName());
                mDevice = device;
            }
            else
            {
                //mStatusTextView.setText("not");
            }
        }

        if (!connectFlg) {
            //mStatusTextView.setText("try connect");

            mThread = new Thread(this);
            // Threadを起動し、Bluetooth接続
            isRunning = true;
            mThread.start();
        }


        landingVideoView = findViewById(R.id.landing_video_view);
        statusValue = findViewById(R.id.statusValue);

        // Set path to your own video file in "raw" directory
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.landing_animation;
        landingVideoView.setVideoURI(Uri.parse(videoPath));
        landingVideoView.start();

        // Set completion listener for the video playback
        landingVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // Restart the video playback
                landingVideoView.start();
            }
        });

        


    }

    @Override
    protected void onPause() {
        super.onPause();

        isRunning = false;
        try {
            mSocket.close();
        } catch (Exception e) {
        }
    }

    @Override
    @SuppressLint("MissingPermission")
    public void run() {
        InputStream mmInStream = null;
        Message valueMsg = new Message();

        try {


            // 取得したデバイス名を使ってBluetoothでSocket接続
            mSocket = mDevice.createRfcommSocketToServiceRecord(MY_UUID);
            mSocket.connect();
            mmInStream = mSocket.getInputStream();

            connectFlg = true;
            Intent activityIntent = new Intent(getApplicationContext(), MusicActivity.class);


            while(isRunning){
                // InputStreamのバッファを格納
                byte[] buffer = new byte[1];

                // 取得したバッファのサイズを格納
                int bytes;

                activityOpen = activityIntent.getBooleanExtra("a", false);


                // InputStreamの読み込み
                bytes = mmInStream.read(buffer);
                Log.i(TAG,"bytes="+bytes);
                // String型に変換
                String readMsg = new String(buffer, 0, bytes);

                // null以外なら表示
                if(readMsg.trim() != null && !readMsg.trim().equals("") && !activityOpen){

                    if(readMsg.trim().equals("m") ){

                        startActivity(new Intent(LandingActivity.this,MusicActivity.class));
                        activityOpen = true;

                    }else if(readMsg.trim().equals("n")){

                        startActivity(new Intent(LandingActivity.this,NatureActivity.class));
                        activityOpen = true;

                    }else if(readMsg.trim().equals("c") ) {

                        startActivity(new Intent(LandingActivity.this, ChatActivity.class));
                        activityOpen = true;

                    }

                }


            }


        }catch(Exception e){


            try{
                mSocket.close();
            }catch(Exception ee){}

            connectFlg = false;
            run();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer resources when the activity is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }



}