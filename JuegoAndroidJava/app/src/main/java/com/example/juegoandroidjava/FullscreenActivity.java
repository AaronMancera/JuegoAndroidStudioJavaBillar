package com.example.juegoandroidjava;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.ImageView;

import com.example.juegoandroidjava.control.PaintCuadrado;
import com.example.juegoandroidjava.databinding.ActivityFullscreenBinding;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configView();
        ImageView myImageView=(ImageView)findViewById(R.id.imagen);
        //Contenido de graficos
        PaintCuadrado paintCuadrado=new PaintCuadrado();
        paintCuadrado.pintar(myImageView);
    }
    public void configView(){
        setContentView(R.layout.activity_fullscreen);
    }

}