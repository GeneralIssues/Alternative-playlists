package com.example.general.alternative_playlists;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;

public class MainActivity extends Activity implements GestureDetector.OnGestureListener{

    private GestureDetectorCompat mDetector;
    Artist Musk = new Artist(1, "Musk", new String[]{"Fixing the server", "Cookie is dead", "No More Holt"});
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        final ImageView square = (ImageView)this.findViewById(R.id.imageView);
        //ImageView circle = (ImageView)this.findViewById(R.id.imageView2);
        TextView genreText = (TextView)this.findViewById(R.id.textView);
        TextView songName = (TextView)this.findViewById(R.id.textView2);
        String[] genres = {"Rock","Pop","Jazz","Wub-Wub"};
        int genreID = 0;
        int songID = 0;
        float sensitivity = 40;

        class MyAnimationListener implements Animation.AnimationListener {

            @Override
            public void onAnimationEnd(Animation animation) {
                square.clearAnimation();
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(square.getWidth(), square.getHeight());
                lp.setMargins(0, 300, 0, 0);
                square.setLayoutParams(lp);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

        }

        //WORKING ON IF e1.getX() >= square.getX() && e1.getX() <= (square.getX() * 2) && e1.getY() >= square.getY() && e1.getY() <= (square.getY() * 2)
    if(e1.getY() <= 400) {
        if ((e1.getX() - e2.getX()) > sensitivity) {
            Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
            //square.setX(square.getX() - 400);

            TranslateAnimation animation = new TranslateAnimation(0, 300, 0, 0);
            animation.setDuration(1000);
            animation.setFillAfter(false);
            animation.setAnimationListener(new MyAnimationListener());

            square.startAnimation(animation);

            songID ++;
            square.setImageDrawable(getResources().getDrawable(R.drawable.song1));
            songName.setText(Musk._songs[songID]);

        } else if ((e2.getX() - e1.getX()) > sensitivity) {
            Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
            //square.setX(square.getX() + 400);

            TranslateAnimation animation = new TranslateAnimation(0, 50, 0, 100);
            animation.setDuration(1000);
            animation.setFillAfter(false);
            animation.setAnimationListener(new MyAnimationListener());

            square.startAnimation(animation);

            songID ++;
            square.setImageDrawable(getResources().getDrawable(R.drawable.song1));
            songName.setText(Musk._songs[songID]);

        }
    } else if(e1.getY() > 400){
        if ((e1.getX() - e2.getX()) > sensitivity) {
            Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
            if(genreID == 3){
                Toast.makeText(this, "No more playlists!", Toast.LENGTH_LONG).show();
            } else {
                genreID ++;
                genreText.setText(genres[genreID]);
            }

        } else if ((e2.getX() - e1.getX()) > sensitivity) {
            Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
            if(genreID == 0){
                Toast.makeText(this, "No more playlists!", Toast.LENGTH_LONG).show();
            } else {
                genreID --;
                genreText.setText(genres[genreID]);
            }
        }

    }

        return true;
    }
}
