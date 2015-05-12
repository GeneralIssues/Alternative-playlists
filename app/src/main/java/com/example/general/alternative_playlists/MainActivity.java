package com.example.general.alternative_playlists;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.animation.Animator.AnimatorListener;

public class MainActivity extends Activity implements GestureDetector.OnGestureListener{

    //The gesture detector that facilitates the touchEvents
    private GestureDetectorCompat mDetector;

    //The songs which were needed
    Song song1 = new Song("Ain't No Rest for The Wicked");
    Song song2 = new Song("Don't Stop Me Now");
    Song song3 = new Song("");
    Song song4 = new Song("");
    Song song5 = new Song("");
    Song song6 = new Song("");
    Song song7 = new Song("");
    Song song8 = new Song("");
    Song song9 = new Song("");
    Song song10 = new Song("");


    //Array
    String[] albumList = {"The 2nd Law", "Cage The Elephant"};
    Song[] songList = {song1, song2, song3, song4, song5, song6, song7, song8, song9, song10};

    Artist Musk = new Artist(1, "Musk", new String[]{"Fixing the server", "Cookie is dead", "No More Holt"});

    //initialising the array containing images to be drawn
    private Drawable[] drawables = null; // create a Drawables array that stores location of different images

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDetector = new GestureDetectorCompat(this,this);
        TextView songName = (TextView)this.findViewById(R.id.textView2);

        //The array containing the song images to be drawn
        //Makes use of deprecated methods but oh well
        drawables = new Drawable[] {
                getResources().getDrawable(R.drawable.song),
                getResources().getDrawable(R.drawable.song1)
        };
        songName.setText(Musk._songs[0]);
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
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        ImageView square = (ImageView)this.findViewById(R.id.imageView);
        TextView genreText = (TextView)this.findViewById(R.id.textView);
        TextView songName = (TextView)this.findViewById(R.id.textView2);
        String[] genres = {"Rock", "Pop", "Wub-Wub"};
        int genreID = 0;
        int songID = 0;
        int imageID = 0;
        float sensitivity = 40;

    //Check for which element is being swiped
    if(e1.getY() <= 400) {
        //Check for the direction of the swipe
        if ((e1.getX() - e2.getX()) > sensitivity) {
            //Prints message on screen purely for debugging purposes
            //Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();

            square.animate().translationX(-1500).setDuration(500).withEndAction( new Runnable() {
                @Override
                public void run() {
                    setPosFromRight();
                    transIn();
                }
            });
            imageID ++;
            songID ++;
            square.setImageDrawable(drawables[imageID]);
            songName.setText(Musk._songs[songID]);

        } else if ((e2.getX() - e1.getX()) > sensitivity) {
            //Prints message on screen purely for debugging purposes
            //Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();

            //Simple animation sequence
            square.animate().translationX(1500).setDuration(500).withEndAction(new Runnable() {
                @Override
                public void run() {
                    setPosFromLeft();
                    transIn();
                }
            });
            imageID ++;
            songID ++;
            square.setImageDrawable(drawables[imageID]);
            songName.setText(Musk._songs[songID]);

        }
    } else if(e1.getY() > 400){
        if ((e1.getX() - e2.getX()) > sensitivity) {
            //Prints message on screen purely for debugging purposes
            //Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
            if(genreID == 3){
                Toast.makeText(this, "No more playlists!", Toast.LENGTH_LONG).show();
            } else {
                genreID ++;
                genreText.setText(genres[genreID]);
            }

        } else if ((e2.getX() - e1.getX()) > sensitivity) {
            //Prints message on screen purely for debugging purposes
            //Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
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

    public void setPosFromLeft(){
        ImageView square = (ImageView)this.findViewById(R.id.imageView);
        square.setX(square.getX() - 1500);
        square.setY(square.getY() - 600);
    }

    public void setPosFromRight(){
        ImageView square = (ImageView)this.findViewById(R.id.imageView);
        square.setX(square.getX() + 1500);
        square.setY(square.getY() - 600);
    }

    public void transIn(){
        ImageView square = (ImageView)this.findViewById(R.id.imageView);
                square.animate().translationY(50).setDuration(500).setStartDelay(500);
        }
}

