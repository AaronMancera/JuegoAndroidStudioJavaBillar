package com.example.videojuego.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.example.videojuego.GameView;
import com.example.videojuego.OnColisionListener;
import com.example.videojuego.Billar;
import com.example.videojuego.Utilidades;

public class Bola extends Sprite implements OnColisionListener{

    private Billar game;
    public float centroX,centroY,radio;
    public boolean activa=true;
    public boolean desaparece=true;
    public boolean blanca=false;
    public float rozamiento= (float) 0.98;
    public boolean esAgujero;
    public boolean esNegra=false;


    public Bola(GameView game, int x, int y, int r, int color, boolean esAgujero) {
        super(game);
        this.game=(Billar)game;
        centroX=x;
        centroY=y;
        radio=r;
        this.color=color;
        velInicialX= (float) (Math.random() * 20);
        velInicialY= (float) (Math.random() * 20);
        velActualX=velInicialX;
        velActualY=velInicialY;
        this.esAgujero=esAgujero;

    }
    public boolean getEsAgujero(){
        return this.esAgujero;
    }
    public void setBlanca(Boolean blanca){
        this.blanca=blanca;
    }
    public void setEsNegra(Boolean negra){
        this.esNegra=negra;
    }
    @Override
    public void setup() {
        this.velActualX=0;
        this.velActualY=0;
       /* this.velActualX=velInicialX* game.factor_mov;
        this.velActualY=velInicialY* game.factor_mov;
        */

    }

    @Override
    public boolean colision(Sprite s){

            Bola b=(Bola)s;
            boolean col=Utilidades.colisionCirculos(centroX,centroY,radio,b.centroX,b.centroY,b.radio);
            if (!col) activa=true;
            return col;
    }

    @Override
    public void update() {
        if(!this.esAgujero) {
            //Se actualiza la posicion de la bola según la anterior
            velActualX *= rozamiento;
            //  if (velActualX==0);velActualX=0;
            centroX += velActualX;
            velActualY *= rozamiento;
            //   if (velActualY==0)velActualY=0;
            centroY += velActualY;
            //Log.d("billar",this.getVelActualX()+"----"+this.getVelActualX());
            //Comprobamos colisiones con los bordes y entre los actores
            onFireColisionSprite();
            onFireColisionBorder();
            //Se actualizan otras variables internas
        }
        else{
            desaparece=true;
        }
        if(this.game.vidas<1){
            this.game.perdiste=true;
        }
        if(this.game.puntuacion==350){
            this.game.ganaste=true;
        }

    }


    @Override
    public void onFireColisionBorder(){
        if(!this.esAgujero) {
            if (this.centroX - radio < 0)
                onColisionBorderEvent(OnColisionListener.LEFT);
            if (this.centroX + radio > game.getmScreenX())
                onColisionBorderEvent(OnColisionListener.RIGHT);
            if (this.centroY - radio < 0)
                onColisionBorderEvent(OnColisionListener.TOP);
            if (this.centroY + radio > game.getmScreenY())
                onColisionBorderEvent(OnColisionListener.BOTTOM);
        }

    }
    @Override
    public void onColisionEvent(Sprite s) {
        if (s instanceof Bola) {
            if(activa && ((Bola) s).getEsAgujero()==false){
                //Log.d("billar","Rebote");
                Bola b=(Bola)s;
                float dy=(float)(b.centroY-centroY);
                float dx=(float)(b.centroX-centroX);
                float ang=(float)Math.atan2(dy,dx);
                double cosa=Math.cos(ang);
                double sina=Math.sin(ang);
                float vx2=(float)(cosa*b.velActualX+sina*b.velActualY);
                float vy1=(float)(cosa*b.velActualY-sina*b.velActualX);
                float vx1=(float)(cosa*velActualX+sina*velActualY);
                float vy2=(float)(cosa*velActualY-sina*velActualX);
                b.velActualX=(float)(cosa*vx1-sina*vy1);
                b.velActualY=(float)(cosa*vy1+sina*vx1);
                velActualX=(float)(cosa*vx2-sina*vy2);
                velActualY=(float)(cosa*vy2+sina*vx2);
            }
            else if(desaparece&&blanca){
                centroX=300;
                centroY=500;
                velActualX=0;
                velActualY=0;
                game.vidas-=1;
            }
            else if(desaparece&& !blanca){
                centroX=4000;
                centroY=4000;
                velActualX=0;
                velActualY=0;
                game.puntuacion+=50;
                if(esNegra){
                    game.vidas=0;
                }
            }
            else if(desaparece&&s.color== Color.BLACK){
                this.game.vidas=0;
                this.game.pausado=true;
            }
        }
    }


    @Override
    public void onColisionBorderEvent(int border) {

        switch (border){
            case OnColisionListener.TOP:
                velActualY=-velActualY;
                break;
            case OnColisionListener.BOTTOM:
                velActualY=-velActualY;
                break;
            case OnColisionListener.RIGHT:
                velActualX=-velActualX;
                break;
            case OnColisionListener.LEFT:
                velActualX=-velActualX;
                break;
            default:

                break;
        }
    }

    @Override
    public  void pinta(Canvas canvas){
        paint.setColor(color);
        //paint.setStrokeWidth(8);
       // paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centroX,centroY,radio, paint);
    }

}
