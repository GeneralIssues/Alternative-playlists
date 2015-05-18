package com.example.general.alternative_playlists;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity implements GestureDetector.OnGestureListener{

    //The gesture detector that facilitates the touchEvents
    private GestureDetectorCompat mDetector;

    //The songs which were needed
    Song song1 = new Song("Ain't No Rest for The Wicked", 0);
    Song song2 = new Song("Don't Stop Me Now", 1);
    Song song3 = new Song("Karma", 2);
    Song song4 = new Song("Movie Klip", 3);
    Song song5 = new Song("Radioactive", 4);
    Song song6 = new Song("Smells Like Teen Spirit", 5);
    Song song7 = new Song("Gangsta's  Paradise", 6);
    Song song8 = new Song("Crab in Honey", 7);
    Song song9 = new Song("The Great Gig in the Sky", 8);
    Song song10 = new Song("In One Ear", 0);



    //Array
    List<String> RockSongs = new ArrayList<>();
    List<String> PopSongs = new ArrayList<>();
    List<String> ClassicSongs = new ArrayList<>();

    Song[] songList = {song1, song2, song3, song4, song5, song6, song7, song8, song9, song10};
    int[] solutionArray = new int[songList.length];


    //Playlists
    Playlist Rock = new Playlist(1,"Rock",RockSongs);
    Playlist Pop = new Playlist(2,"Pop",PopSongs);
    Playlist Classic = new Playlist(3,"Classic", ClassicSongs);

    //Values
    int songPos = 0;
    int a = 0;
    int playlistPos = 0;
    Playlist[] playlists = {Rock,Pop,Classic};



    //initialising the array containing images to be drawn
    private Drawable[] drawables = null; // create a Drawables array that stores location of different images

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Create an array of scrambled numbers
        for(int i = 0; i < songList.length; i++){
            solutionArray[i] = i;
        }
        shuffleArray(solutionArray);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDetector = new GestureDetectorCompat(this,this);

        //The array containing the songs images to be drawn
        //Makes use of deprecated methods to avoid a higher API target level
        drawables = new Drawable[] {
                getResources().getDrawable(R.drawable.cagetheelephant),
                getResources().getDrawable(R.drawable.queen),
                getResources().getDrawable(R.drawable.burhang),
                getResources().getDrawable(R.drawable.nephew),
                getResources().getDrawable(R.drawable.imagedragons),
                getResources().getDrawable(R.drawable.nirvana),
                getResources().getDrawable(R.drawable.coolio),
                getResources().getDrawable(R.drawable.fevertheghost),
                getResources().getDrawable(R.drawable.pinkfloyd)
        };
        TextView songName = (TextView)this.findViewById(R.id.textView2);
        songName.setText(songList[solutionArray[songPos]].getSongName());

        TextView playlistName = (TextView)this.findViewById(R.id.textView);
        playlistName.setText(playlists[playlistPos]._playlistName);

        ImageView square = (ImageView)this.findViewById(R.id.imageView);
        square.setImageDrawable(drawables[songList[solutionArray[songPos]].getAlbumID()]);
        songPos++;

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
        final TextView songName = (TextView) this.findViewById(R.id.textView2);
        final ImageView square = (ImageView) this.findViewById(R.id.imageView);
        TextView genreText = (TextView) this.findViewById(R.id.textView);
        //String[] genres = {"Rock","Pop","Classic"};
        //int genreID = 1;
        float sensitivity = 40;

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;

        //Check for which element is being swiped
            if (e1.getY() <= height / 4 * 3) {
                //Check for the direction of the swipe
                if ((e1.getX() - e2.getX()) > sensitivity) {

                    songName.animate().translationX(-1500).setDuration(500);
                    square.animate().translationX(-1500).setDuration(500).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            songName.setText("");
                            setPosFromRight();
                            transIn();
                            square.setImageDrawable(drawables[songList[solutionArray[songPos]].getAlbumID()]);
                            //addSongToPlaylist(songList[solutionArray[songPos]], playlists[playlistPos]);
                        }
                    });


                } else if ((e2.getX() - e1.getX()) > sensitivity) {
                    songName.animate().translationX(1500).setDuration(500);
                    square.animate().translationX(1500).setDuration(500).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            songName.setText("");
                            setPosFromLeft();
                            transIn();
                            square.setImageDrawable(drawables[songList[solutionArray[songPos]].getAlbumID()]);
                            addSongToPlaylist(songList[solutionArray[songPos]], playlists[playlistPos]);
                        }
                    });


                }
            } else if (e1.getY() > (height / 4) * 3) {
                if ((e1.getX() - e2.getX()) > sensitivity / 2 /* || e1.getY() - e2.getY() > sensitivity/2*/) {
                    //Prints message on screen purely for debugging purposes
                    //Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
                    if (playlistPos > 0) {
                        Toast.makeText(this, "No more playlists!", Toast.LENGTH_LONG).show();
                    } else {
                        playlistPos++;
                        genreText.setText(playlists[playlistPos]._playlistName);
                    }

                } else if ((e2.getX() - e1.getX()) > sensitivity / 2 /* || e2.getY() - e1.getY() > sensitivity/2 */) {
                    //Prints message on screen purely for debugging purposes
                    //Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
                    if (playlistPos < 0) {
                        Toast.makeText(this, "No more playlists!", Toast.LENGTH_LONG).show();
                    } else {
                        playlistPos--;
                        genreText.setText(playlists[playlistPos]._playlistName);
                    }
                }

            }
        return true;
    }

    public void setPosFromLeft(){
        ImageView square = (ImageView)this.findViewById(R.id.imageView);
        TextView songName = (TextView)this.findViewById(R.id.textView2);
        square.setX(square.getX() - 1500);
        square.setY(square.getY() - 600);
        songName.setX(songName.getX() - 1500);
        songName.setY(songName.getY() - 600);
    }

    public void setPosFromRight(){
        ImageView square = (ImageView)this.findViewById(R.id.imageView);
        TextView songName = (TextView)this.findViewById(R.id.textView2);
        square.setX(square.getX() + 1500);
        square.setY(square.getY() - 600);
        songName.setX(songName.getX() + 1500);
        songName.setY(songName.getY() - 600);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void transIn(){
        ImageView square = (ImageView)this.findViewById(R.id.imageView);
        final TextView songName = (TextView)this.findViewById(R.id.textView2);
                songName.animate().translationY(50).setDuration(500).setStartDelay(500);
                square.animate().translationY(50).setDuration(500).setStartDelay(500).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        if (songPos > songList.length - 1) {
                            songName.setText("");
                        } else {
                            songName.setText(songList[solutionArray[songPos]].getSongName());
                            songPos++;

                        }
                    }
                });
        }
    public void shuffleArray(int[] ar){
        Random rnd = new Random();
        for(int i = ar.length -1; i > 0; i--){
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public void addSongToPlaylist(Song a, Playlist b){
       b._songs.add(a.getSongName());
        System.out.println(b._playlistName + " " +  b._songs);
    }

}