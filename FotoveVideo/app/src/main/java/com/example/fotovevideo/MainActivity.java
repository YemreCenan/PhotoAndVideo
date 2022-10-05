package com.example.fotovevideo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private static final int VIDEO_ACTION_CODE = 101;
    private static final int PHOTO_ACTION_CODE = 102;
    Button captureVideo,takePicture;
    ImageView imagePreview;
    VideoView videoPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoPreview = findViewById(R.id .videoPreview);
        captureVideo =findViewById(R.id.captureVideo);
        takePicture =findViewById(R.id.takePicture);
        imagePreview =findViewById(R.id.imagePreview);




        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent,PHOTO_ACTION_CODE);
            }
        });

        captureVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(takeVideoIntent,VIDEO_ACTION_CODE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode !=RESULT_OK) return;
        switch (requestCode){
            case VIDEO_ACTION_CODE:
                videoPreview.setVideoURI(data.getData());
                videoPreview.setMediaController(new MediaController(this));
                videoPreview.requestFocus();
                videoPreview.start();
                break;

            case PHOTO_ACTION_CODE:
                Bundle exrtas = data.getExtras();
                imagePreview.setImageBitmap((Bitmap)exrtas.get("data"));
                break;
               default:
                   break;
        }
    }


}