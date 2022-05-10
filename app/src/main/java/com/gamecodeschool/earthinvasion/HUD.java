package com.gamecodeschool.earthinvasion;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

class HUD {
    private int mTextFormatting;
    private int mScreenHeight;
    private int mScreenWidth;

    private ArrayList<Rect> controls;

    static int UP = 0;
    static int DOWN = 1;
    static int FLIP = 2;
    static int SHOOT = 3;
    static int PAUSE = 4;
    static int LASERTOWER = 5;

    HUD(Point size){
        mScreenHeight = size.y;
        mScreenWidth = size.x;
        mTextFormatting = size.x / 50;

        prepareControls();
    }

    private void prepareControls(){
        Rect up = newButton(45 , 1);
        Rect down = newButton(55 , 1);
        Rect flip = newButton(65 , 1);
        Rect shoot = newButton(75 , 1);
        Rect pause = newButton(85 , 1);

        controls = new ArrayList<>();
        controls.add(UP,    up);
        controls.add(DOWN,  down);
        controls.add(FLIP,  flip);
        controls.add(SHOOT, shoot);
        controls.add(PAUSE, pause);
    }

    private Rect newButton(int x, int y){
        int buttonWidth   = mScreenWidth  / 14;
        int buttonHeight  = mScreenHeight / 12;
        int buttonPadding = mScreenWidth  / 90;

        return new Rect(
                (buttonPadding * x),
                (buttonPadding * y),
                (buttonPadding * x) + buttonWidth,
                (buttonPadding * y) + buttonHeight
        );
    }

    void draw(Canvas c, Paint p, GameState gs){
        // Draw the HUD
        drawHUDText(c, p, gs);
        drawGameOver(c, p, gs);
        drawPaused(c, p, gs);
        drawControls(c, p);
    }


    void drawHUDText(Canvas c, Paint p, GameState gs){
        p.setColor(Color.argb(255,255,255,255));
        p.setTextSize(mTextFormatting);
        c.drawText("Hi: " + gs.getHighScore(), mTextFormatting,mTextFormatting,p);
        c.drawText("Money: " + gs.getScore(), mTextFormatting,mTextFormatting * 2,p);
        c.drawText("Lives: " + gs.getNumShips(), mTextFormatting,mTextFormatting * 3,p);
    }

    void drawGameOver(Canvas c, Paint p, GameState gs){
        if(gs.getGameOver()){
            p.setTextSize(mTextFormatting * 5);
            c.drawText("PRESS PLAY", mScreenWidth /4, mScreenHeight /2 ,p);
        }
    }
    void drawPaused(Canvas c, Paint p, GameState gs){
        if(gs.getPaused() && !gs.getGameOver()){
            p.setTextSize(mTextFormatting * 5);
            c.drawText("PAUSED", mScreenWidth /3, mScreenHeight /2 ,p);
        }
    }

    // need to change these to a png
    private void drawControls(Canvas c, Paint p){
        p.setColor(Color.argb(100,255,255,255));

        for(Rect r : controls){
            c.drawRect(r.left, r.top, r.right, r.bottom, p);
        }

        // Set the colors back
        p.setColor(Color.argb(255,255,255,255));
    }
    ArrayList<Rect> getControls(){
        return controls;
    }
}