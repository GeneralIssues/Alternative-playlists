package com.example.general.alternative_playlists;

/**
 * Created by General on 28/04/2015.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity{

    float x1,x2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // onTouchEvent () method gets called when User performs any touch event on screen
    // Method to handle touch event like left to right swap and right to left swap
    public boolean onTouchEvent(MotionEvent touchevent){
        System.out.print("SHIT MATE YOU TOUCHED");
        switch (touchevent.getAction()){
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN:{
                x1 = touchevent.getX();

                break;
            }
            case MotionEvent.ACTION_UP:{
                x2 = touchevent.getX();


                 //if left to right sweep event on screen
                if (x1 < x2){
                    Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
                    ImageView square = (ImageView)this.findViewById(R.id.imageView);
                    square.setX(square.getX() + 250);
                }

                // if right to left sweep event on screen
                if (x1 > x2){
                    Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
                    ImageView square = (ImageView)this.findViewById(R.id.imageView);
                    square.setX(square.getX() - 250);
                }
                break;


            }
        }
        return false;
    }


}
