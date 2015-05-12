package com.example.general.alternative_playlists;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;

import java.util.*;

public class MainActivity extends Activity implements GestureDetector.OnGestureListener{

    //The gesture detector that facilitates the touchEvents
    private GestureDetectorCompat mDetector;

    //The songs which were needed
    Song song1 = new Song("Ain't No Rest for The Wicked", 1);
    Song song2 = new Song("Don't Stop Me Now", 2);
    Song song3 = new Song("Karma", 3);
    Song song4 = new Song("Movie Klip", 4);
    Song song5 = new Song("Creep", 5);
    Song song6 = new Song("Smells Like Teen Spirit", 6);
    Song song7 = new Song("Gangsta's  Paradise", 7);
    Song song8 = new Song("Crab in Honey", 8);
    Song song9 = new Song("The Great Gig in the Sky", 9);
    Song song10 = new Song("In One Ear", 10);

    Artist Musk = new Artist(1, "Musk", new String[]{"Fixing the server", "Cookie is dead", "No More Holt"});

    //Array
    String[] albumList = {"The 2nd Law", "Cage The Elephant"};
    Song[] songList = {song1, song2, song3, song4, song5, song6, song7, song8, song9, song10};
    int[] solutionArray = new int[songList.length];

    //Values
    int songPos = 0;

    //initialising the array containing images to be drawn
    private Drawable[] drawables = null; // create a Drawables array that stores location of different images

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Create an array of scrambled numbers
        for(int i = 0; i < songList.length; i++) {
            solutionArray[i] = i;
        }
        shuffleArray(solutionArray);

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
        ImageView square = (ImageView)this.findViewById(R.id.imageView);
        //ImageView circle = (ImageView)this.findViewById(R.id.imageView2);
        TextView genreText = (TextView)this.findViewById(R.id.textView);
        TextView songName = (TextView)this.findViewById(R.id.textView2);
        String[] genres = {"Rock","Pop","Jazz","Wub-Wub"};
        int genreID = 0;
        int songID = 0;
        int imageID = 0;
        float sensitivity = 40;

        //Check for which element is being swiped
        if(e1.getY() <= 400) {
            //Check for the direction of the swipe
            if ((e1.getX() - e2.getX()) > sensitivity) {
                //Prints message on screen purely for debugging purposes
                Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();

                square.animate().translationX(1500).setDuration(500);
                transIn((int) square.getX(),1000);
                imageID ++;
                songID ++;

                if(songPos > songList.length -1) {
                    square.setImageDrawable(drawables[imageID]);
                    songName.setText("");
                    Toast.makeText(this, "There is no more songs to show", Toast.LENGTH_LONG).show();
                } else {
                    square.setImageDrawable(drawables[imageID]);
                    songName.setText(songList[solutionArray[songPos]].getSongName());
                    songPos++;
                }

            } else if ((e2.getX() - e1.getX()) > sensitivity) {
                //Prints message on screen purely for debugging purposes
                Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();


                //Simple animation sequence
                square.animate().translationX(1500).setDuration(500);
                transIn((int) square.getX(),1000);
                imageID ++;
                songID ++;

                if(songPos > songList.length -1) {
                    square.setImageDrawable(drawables[imageID]);
                    songName.setText("");
                    Toast.makeText(this, "There is no more songs to show", Toast.LENGTH_LONG).show();
                } else {
                    square.setImageDrawable(drawables[imageID]);
                    songName.setText(songList[solutionArray[songPos]].getSongName());
                    songPos++;
                }
            }

        } /* else if(e1.getY() > 400){
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

        } */

        return true;
    }

    public void transIn(int x, int x1){
        ImageView square = (ImageView)this.findViewById(R.id.imageView);
        if(x == x1){
            square.setX(square.getX() - 1000);
            square.setY(square.getY() - 500);
            square.animate().translationY(500).setDuration(500);
        }
    }

    static void shuffleArray(int[] ar) {
        Random rnd = new Random();
        for(int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
