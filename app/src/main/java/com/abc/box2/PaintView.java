package com.abc.box2;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class PaintView extends View {

    int i = 4, j = 6;
    Paint dotcolor, linecolor, linecolor2;
    Path connectline, connectline2;
    private int distx, disty, initx, inity;
    int dpHeight, dpWidth;
    boolean colorturn = true, imageturn;
    Bitmap doraemonlogo;
    Bitmap nobitalogo;
    List<Point> pointloc = new ArrayList<>();
   public List<Point> imagepoint2 = new ArrayList<>();
    public List<Point> imagepoint1 = new ArrayList<>();
    DisplayMetrics display;
    public static final String TAG = "Paint View";

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        dotcolor = new Paint();
        dotcolor.setColor(Color.BLUE);
        dotcolor.setStyle(Paint.Style.FILL);
        connectline = new Path();
        connectline2 = new Path();

        linecolor = new Paint();
        linecolor.setColor(Color.BLACK);
        linecolor.setStrokeWidth(10f);
        linecolor.setStyle(Paint.Style.STROKE);
        linecolor2 = new Paint();
        linecolor2.setColor(Color.RED);
        linecolor2.setStrokeWidth(10f);
        linecolor2.setStyle(Paint.Style.STROKE);

        doraemonlogo = BitmapFactory.decodeResource(getResources(), R.drawable.doraemon);
        nobitalogo = BitmapFactory.decodeResource(getResources(), R.drawable.nobita);

        display = getResources().getDisplayMetrics();
        dpHeight = Math.round(display.heightPixels);
        dpWidth = Math.round(display.widthPixels);

        initx = (dpWidth - (i - 1) * 200) / 2 - 200;
        inity = (dpHeight - (j - 1) * 200) / 2 - 200;

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float posx = event.getX();
        float posy = event.getY();
        if (colorturn) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    for (int l = 1; l <= i; l++) {
                        for (int k = 1; k <= j; k++) {
                            Point pt = new Point(initx + l * 200, inity + k * 200);
                            if (Math.abs(posx - pt.x) <= 50 && Math.abs(posx - pt.x) >= 0 && Math.abs(posy - pt.y) <= 50 && Math.abs(posy - pt.y) >= 0) {
                                connectline.moveTo(pt.x, pt.y);
                                distx = pt.x;
                                disty = pt.y;
                            }
                        }
                    }
                    break;

                case MotionEvent.ACTION_UP:

                    for (int l = 1; l <= i; l++) {
                        for (int k = 1; k <= j; k++) {
                            Point pt = new Point(initx + l * 200, inity + k * 200);
                            if (Math.abs(posx - pt.x) <= 50 && Math.abs(posx - pt.x) >= 0 && Math.abs(posy - pt.y) <= 50 && Math.abs(posy - pt.y) >= 0) {
                                if (Math.sqrt((pt.x - distx) * (pt.x - distx) + (pt.y - disty) * (pt.y - disty)) <= 200) {

                                    if (!pointloc.contains(new Point((pt.x + distx) / 2, (pt.y + disty) / 2))) {

                                        connectline.lineTo(pt.x, pt.y);
                                        colorturn = false;
                                        imageturn = true;
                                        Log.d(TAG,"imageturn"+imageturn);
                                        pointloc.add(new Point((pt.x + distx) / 2, (pt.y + disty) / 2));
                                        invalidate();
                                    }
                                }
                            }
                        }
                    }
                    default:
                    return super.onTouchEvent(event);
            }
        }
        else {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    for (int l = 1; l <= i; l++) {
                        for (int k = 1; k <= j; k++) {
                            Point pt = new Point(initx + l * 200, inity + k * 200);
                            if (Math.abs(posx - pt.x) <= 50 && Math.abs(posx - pt.x) >= 0 && Math.abs(posy - pt.y) <= 50 && Math.abs(posy - pt.y) >= 0) {

                                connectline2.moveTo(pt.x, pt.y);
                                distx = pt.x;
                                disty = pt.y;
                            }
                        }
                    }

                    break;
                case MotionEvent.ACTION_UP:

                    for (int l = 1; l <= i; l++) {
                        for (int k = 1; k <= j; k++) {
                            Point pt = new Point(initx + l * 200, inity + k * 200);
                            if (Math.abs(posx - pt.x) <= 50 && Math.abs(posx - pt.x) >= 0 && Math.abs(posy - pt.y) <= 50 && Math.abs(posy - pt.y) >= 0) {
                                if (Math.sqrt((pt.x - distx) * (pt.x - distx) + (pt.y - disty) * (pt.y - disty)) <= 200) {
                                    Point point = new Point((pt.x + distx) / 2, (pt.y + disty) / 2);
                                    if (!pointloc.contains(point)) {
                                        connectline2.lineTo(pt.x, pt.y);
                                        colorturn = true;
                                        imageturn = false;
                                        Log.d(TAG,"imageturn"+imageturn);
                                        pointloc.add(point);
                                        invalidate();
                                    }
                                }
                            }
                        }
                    }
                default:
                    return super.onTouchEvent(event);
            }
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
Log.d(TAG,"matrix drawn");
        for (int l = 1; l <= i; l++) {
            for (int k = 1; k <= j; k++) {

                Point pt = new Point(initx + l * 200, inity + k * 200);
                canvas.drawCircle(pt.x, pt.y, 20, dotcolor);
            }
        }

        for (int l = 1; l <= i; l++)
            for (int k = 1; k <= j; k++) {
                Point pt = new Point(initx + l * 200, inity + k * 200);

                if (pointloc.contains(new Point(initx + l * 200 + 100, inity + k * 200)) && pointloc.contains(new Point(initx + l * 200, inity + k * 200 + 100)) && pointloc.contains(new Point(initx + l * 200 + 100, inity + k * 200 + 200)) && pointloc.contains(new Point(initx + l * 200 + 200, inity + k * 200 + 100))
                        && !imagepoint2.contains(pt) && !imagepoint1.contains(pt)) {

                    if (imageturn) {
                        imagepoint1.add(pt);

                        Log.d(TAG,"DORAEMON");
                        colorturn = true;
                    }
                    else {
                        imagepoint2.add(pt);

                        Log.d(TAG,"NOBITA");
                        colorturn = false;
                    }
                }
            }

        for (Point ptr1 : imagepoint1) {
            canvas.drawBitmap(doraemonlogo, ptr1.x, ptr1.y, null);
            Log.d(TAG, "score1" + imagepoint1.size());
        }
        for (Point ptr2 : imagepoint2) {
            canvas.drawBitmap(nobitalogo, ptr2.x, ptr2.y, null);
            Log.d(TAG, "score2" + imagepoint2.size());
        }
        canvas.drawPath(connectline, linecolor);
        canvas.drawPath(connectline2, linecolor2);
    }
}

