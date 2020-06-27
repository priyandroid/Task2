package com.abc.box2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String tag ="Main";


  // PaintView view=new PaintView(this, null);
   TextView score1,score2;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PaintView paintView = new PaintView(this,null);
        setContentView(R.layout.activity_main);
        paintView=findViewById(R.id.custom_view);
        score1=findViewById(R.id.score_player_1);
        score2=findViewById(R.id.score_player_2);
        score1.setText(String.valueOf(paintView.imagepoint1.size()));
        Log.d(tag,"ACtivity");

    }
    /*void updateScore(){
       score1.setText(String.valueOf(view.imagepoint1.size()));
       score2.setText(String.valueOf(view.imagepoint2.size()));
    }

     */
}
