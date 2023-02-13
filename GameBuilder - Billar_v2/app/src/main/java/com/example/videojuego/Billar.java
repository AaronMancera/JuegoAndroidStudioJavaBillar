package com.example.videojuego;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import com.example.videojuego.sprites.Bola;

import com.example.videojuego.sprites.Sprite;

public class Billar extends GameView implements OnTouchEventListener {


    private final Context context;
    private final int x;
    private final int y;
    public boolean perdiste=false;
    public boolean ganaste=false;

    //Actores del juego
    Bola bola1,bola2, bola3, bola4, bola5, bola6,bola7,bola8;
    Bola agujero1,agujero2,agujero3,agujero4, agujero5,agujero6;
    //

    float lineX1,lineY1,lineX2,lineY2;
    boolean estaDentro=false;
    boolean apunta=false;



    //variables del juego
    public int puntuacion = 0;
    public int vidas = 3;


    public Billar(Context context, int x, int y) {
        super(context,x,y);
        this.context = context;
        this.x = x;
        this.y = y;
        addOnTouchEventListener(this);
        setupGame();


    }

    public void setupGame() {


        bola1 = new Bola(this,300, 500, 50,Color.WHITE,false);
        bola1.setBlanca(true);
        actores.add(bola1);  bola1.setup();
        bola2 = new Bola(this, 1900, 300, 50,Color.RED,false);
        actores.add(bola2);  bola2.setup();
        bola3 = new Bola(this, 1900, 500, 50,Color.MAGENTA,false);
        actores.add(bola3);  bola3.setup();
        bola4 = new Bola(this, 1900, 700, 50,Color.YELLOW,false);
        actores.add(bola4);  bola4.setup();
        bola5 = new Bola(this, 1500, 500, 50,Color.BLUE,false);
        actores.add(bola5);  bola5.setup();
        bola6= new Bola(this, 1700,300, 50,Color.DKGRAY,false);
        actores.add(bola6);  bola6.setup();
        bola7= new Bola(this, 1700,700, 50,Color.GRAY,false);
        actores.add(bola7);  bola7.setup();
        bola8= new Bola(this, 1700,500, 50,Color.BLACK,false);
        bola8.setEsNegra(true);
        actores.add(bola8);  bola8.setup();
        agujero1=new Bola(this, 10,0,150,Color.BLACK,true);
        actores.add(agujero1); agujero1.setup();
        agujero2=new Bola(this, 10,1100,150,Color.BLACK,true);
        actores.add(agujero2); agujero2.setup();
        agujero3=new Bola(this, 1100,1100,150,Color.BLACK,true);
        actores.add(agujero3); agujero3.setup();
        agujero4=new Bola(this, 1100,0,150,Color.BLACK,true);
        actores.add(agujero4); agujero4.setup();
        agujero5=new Bola(this, 2200,1100,150,Color.BLACK,true);
        actores.add(agujero5); agujero5.setup();
        agujero6=new Bola(this, 2200,0,150,Color.BLACK,true);
        actores.add(agujero6); agujero6.setup();









    }

    //Realiza la lógica del juego, movimientos, física, colisiones, interacciones..etc
    @Override
    public void actualiza() {
        //actualizamos los actores
        for (Sprite actor : actores) {
            if(actor.isVisible())
               actor.update();
        }
    }

    //dibuja la pantalla
    @Override
    public void dibuja(Canvas canvas) {
        //se pinta desde la capa más lejana hasta la más cercana
        //Este canvas el que cambia el colo de fondo, el background
        canvas.drawColor(Color.GREEN);
        synchronized(actores) {
            for (Sprite actor : actores) {
                    actor.pinta(canvas);
            }
        }
        //dibujamos puntuacion y vidas
        paint.setTextSize(30);
        canvas.drawText("Puntuacion: " + this.puntuacion + "  Vidas: " + this.vidas, 300, 100, paint);
        paint.setTextSize(10);
        if(estaDentro){
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(5);
            canvas.drawLine(bola1.centroX,bola1.centroY,lineX2,lineY2,paint);
          if(apunta){
              paint.setColor(Color.RED);
              canvas.drawLine(bola1.centroX,bola1.centroY,(bola1.centroX-lineX2)*1000,(bola1.centroY-lineY2)*1000,paint);

          }

        }

    }


  //Responde a los eventos táctiles de la pantalla
    @Override
    public void ejecutaActionDown(MotionEvent event) {
        lineX1=event.getX();
        lineY1=event.getY();
        if (Utilidades.distancia(lineX1,lineY1,bola1.centroX,bola1.centroY)<bola1.radio){
            estaDentro=true;
            lineX1=bola1.centroX;
            lineY1=bola1.centroY;
            lineX2=bola1.centroX;
            lineY2=bola1.centroY;

        }
        Log.d("billar","X: "+lineX1+" Y: "+lineY1);

    }

    @Override
    public void ejecutaActionUp(MotionEvent event) {
        Log.d("billar","X: "+event.getX()+" Y: "+event.getY());
        if(estaDentro){
            lineX2=event.getX();
            lineY2=event.getY();
            bola1.setVelActualX((lineX1-lineX2)/10);
            bola1.setVelActualY((lineY1-lineY2)/10);
            estaDentro=false;
            apunta=false;
        }
        Log.d("billar",bola1.getVelActualX()+"----"+bola1.getVelActualY());
    }

    @Override
    public void ejecutaMove(MotionEvent event) {
        //Log.d("billar","X: "+event.getX()+" Y: "+event.getY());
        apunta=true;
        lineX2=event.getX();
        lineY2=event.getY();

    }


}
