package com.example.general.alternative_playlists;

/**
 * Created by General on 28/04/2015.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements GestureDetector.OnGestureListener{

    float x1,x2;
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDetector = new GestureDetectorCompat(this,this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    @Override
    public boolean onDown(MotionEvent arg0) {

        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        ImageView square = (ImageView)this.findViewById(R.id.imageView);
        ImageView circle = (ImageView)this.findViewById(R.id.imageView2);
        TextView genreText = (TextView)this.findViewById(R.id.textView);
        float sensitvity = 40;
        //WORKING ON IF e1.getX() >= square.getX() && e1.getX() <= (square.getX() * 2) && e1.getY() >= square.getY() && e1.getY() <= (square.getY() * 2)
    if(e1.getY() <= 400) {
        if ((e1.getX() - e2.getX()) > sensitvity) {
            Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();

            square.setX(square.getX() - 300);
        } else if ((e2.getX() - e1.getX()) > sensitvity) {
            Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
            square.setX(square.getX() + 300);
        }
    } else if(e1.getY() > 400){
        if ((e1.getX() - e2.getX()) > sensitvity) {
            Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
            genreText.setY(genreText.getY() - 150);
            genreText.setX(genreText.getX() - 150);

        } else if ((e2.getX() - e1.getX()) > sensitvity) {
            Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
            genreText.setY(genreText.getY() + 150);
            genreText.setX(genreText.getX() + 150);
        }

    }

        return true;
    }
}
