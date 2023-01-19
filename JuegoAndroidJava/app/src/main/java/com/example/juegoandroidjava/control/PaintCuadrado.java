package com.example.juegoandroidjava.control;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

public class PaintCuadrado {
    Bitmap myBitmap;
    Canvas myCanvas;
    Paint paint;

    public PaintCuadrado() {
        myBitmap = Bitmap.createBitmap(750,1500,Bitmap.Config.ARGB_8888);
        myCanvas=new Canvas (myBitmap);
        paint=new Paint();
        myCanvas.drawColor(Color.WHITE);
    }
    public void pintar(ImageView myImageView){
        paint.setColor(Color.BLACK);
        myCanvas.drawRect(500,900,200,1500,paint);
        myImageView.setImageBitmap(myBitmap);

    }

}
