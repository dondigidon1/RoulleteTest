package com.redrocket.customscrollertest;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mImageView = (ImageView) findViewById(R.id.imageView2);
    }

    public void onTest1Click(View v) {
        //mImageView.scrollTo(30,0);
        //mImageView.scroll
        mImageView.invalidate(new Rect(5000,0,6000,300));
    }
}
