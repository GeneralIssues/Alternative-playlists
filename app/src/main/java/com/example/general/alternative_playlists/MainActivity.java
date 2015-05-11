package com.example.general.alternative_playlists;

import android.app.Activity;
import android.graphics.drawable.Drawable;
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

    //The gesture detector that facilitates the touchEvents
    private GestureDetectorCompat mDetector;

    //Array
    String[] albumList = {"The 2nd Law", "Cage The Elephant"};
    Song[] songList = new Song[10];

    Song supr;
    //Song aint = new Song("Aint No Rest for The Wicked", 1);
    //Song dont = new Song("Don't Stop Me Now", 2);

    songList[0] = new Song("Supremacy");



    Artist Musk = new Artist(1, "Musk", new String[]{"Fixing the server", "Cookie is dead", "No More Holt"});

    //initialising the array containing images to be drawn
    private Drawable[] drawables = null; // create a Drawables array that stores location of different images

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDetector = new GestureDetectorCompat(this,this);

        //The array containing the song images to be drawn
        //Makes use of deprecated methods but oh well
        drawables = new Drawable[] {
                getResources().getDrawable(R.drawable.song),
                getResources().getDrawable(R.drawable.song1)
        };
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

    //this method is called when a swipe is registered and contains almost all the relevant code for the app
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        final ImageView square = (ImageView)this.findViewById(R.id.imageView);
        //ImageView circle = (ImageView)this.findViewById(R.id.imageView2);
        TextView genreText = (TextView)this.findViewById(R.id.textView);
        TextView songName = (TextView)this.findViewById(R.id.textView2);
        String[] genres = {"Rock","Pop","Jazz","Wub-Wub"};
        int genreID = 0;
        int songID = 0;
        int imageID = 0;
        float sensitivity = 40;
/*
        //This inner class facilitates the animation ending as well as moving the layout
        //of the imageView after the animation ends
        class MyAnimationListener implements Animation.AnimationListener {

            @Override
            public void onAnimationEnd(Animation animation) {
                square.clearAnimation();
                //this still calls the "square" imageView which is deprecated and needs changeing
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

        }*/

    //Check for which element is being swiped
    if(e1.getY() <= 400) {
        //Check for the direction of the swipe
        if ((e1.getX() - e2.getX()) > sensitivity) {
            //Prints message on screen purely for debugging purposes
            Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
        /*
            //Parameters of the translate animation
            TranslateAnimation animation = new TranslateAnimation(0, 300, 0, 0);
            animation.setDuration(400);
            animation.setFillAfter(false);
            animation.setAnimationListener(new MyAnimationListener());

            square.startAnimation(animation);

           */
            square.animate().translationX(500).setDuration(500);
            imageID ++;
            songID ++;
            square.setImageDrawable(drawables[imageID]);
            songName.setText(Musk._songs[songID]);

        } else if ((e2.getX() - e1.getX()) > sensitivity) {
            //Prints message on screen purely for debugging purposes
            Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
            /*
            //Parameters of the translate animation
            TranslateAnimation animation = new TranslateAnimation(0, -300, 0, 0);
            animation.setDuration(400);
            animation.setFillAfter(false);
            animation.setAnimationListener(new MyAnimationListener());

            square.startAnimation(animation);
            */
            //Simpler animation sequence. Find some way to give animations enough time to play out
            square.animate().translationX(1000).setDuration(500);
            square.setX(square.getX() - 1000);
            square.setY(square.getY() - 500);
            square.animate().translationY(500).setDuration(500);
            imageID ++;
            songID ++;
            square.setImageDrawable(drawables[imageID]);
            songName.setText(Musk._songs[songID]);

        }
    } else if(e1.getY() > 400){
        if ((e1.getX() - e2.getX()) > sensitivity) {
            //Prints message on screen purely for debugging purposes
            Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
            if(genreID == 3){
                Toast.makeText(this, "No more playlists!", Toast.LENGTH_LONG).show();
            } else {
                genreID ++;
                genreText.setText(genres[genreID]);
            }

        } else if ((e2.getX() - e1.getX()) > sensitivity) {
            //Prints message on screen purely for debugging purposes
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
