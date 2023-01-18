package com.example.juegoandroidjava.control;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;

public class PaintCuadrado {
    Bitmap myBitmap = Bitmap.createBitmap(750,1500,Bitmap.Config.ARGB_8888);
    Canvas myCanvas=new Canvas (myBitmap);
    Paint paint=new Paint();
}
