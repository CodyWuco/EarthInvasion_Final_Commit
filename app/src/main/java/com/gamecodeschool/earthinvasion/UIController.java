package com.gamecodeschool.earthinvasion;

import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

class UIController implements InputObserver {

    int i, x, y;

    public UIController(GameEngineBroadcaster b){
        b.addObserver(this);
    }

    @Override
    public void handleInput(MotionEvent event, GameState gameState, ArrayList<Rect> buttons) {

        i = event.getActionIndex();
        x = (int) event.getX(i);
        y = (int) event.getY(i);

        int eventType = event.getAction() & MotionEvent.ACTION_MASK;

        if(eventType == MotionEvent.ACTION_UP ||
                eventType == MotionEvent.ACTION_POINTER_UP) {


            pressedPause(gameState,buttons);
        }
    }
    private void pressedPause(GameState gameState, ArrayList<Rect> buttons){
        if (buttons.get(HUD.PAUSE).contains(x, y)){
            // If the game is not paused
            if (!gameState.getPaused()) {
                // Pause the game
                gameState.pause();
            }

            // If game is over start a new game
            else if (gameState.getGameOver()) {

                gameState.startNewGame();
            }

            // Paused and not game over
            else if (gameState.getPaused()
                    && !gameState.getGameOver()) {

                gameState.resume();
            }
        }
    }
    private void isLazerTower(MotionEvent event, GameState gameState, ArrayList<Rect> buttons){
        if (buttons.get(HUD.LASERTOWER).contains(x, y)){
        }
    }
}