package com.samb1232.sambus.states;

import com.samb1232.sambus.GamePanel;
import com.samb1232.sambus.utils.KeyHandler;
import com.samb1232.sambus.utils.MouseHandler;
import com.samb1232.sambus.utils.Vector2f;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {

    ArrayList<GameState> states;

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;

    public static Vector2f map;

    public GameStateManager() {
        map = new Vector2f(GamePanel.width, GamePanel.height);
        Vector2f.setWorldVar(map.x, map.y);
        states = new ArrayList<GameState>();

        states.add(new PlayState(this));
    }

    public void pop(int state) { //Нужен для смены режимов игры. Пока что в разработке...
        states.remove(state);
    }

    public void add(int state) {
        switch (state) { //а что если этот state уже есть в states...
            case PLAY -> states.add(new PlayState(this));
//            case MENU -> states.add(new MenuState(this));
//            case PAUSE -> states.add(new PauseState(this));
//            case GAME_OVER -> states.add(new GameOverState(this));
        }
    }

    public void addAndPop(int state) {
        pop(0);
        add(state);
    }

    public void update() {
        Vector2f.setWorldVar(map.x, map.y);

        for (GameState state : states) {
            state.update();
        }
    }


    public void input(MouseHandler mouse, KeyHandler key) {
        key.escape.tick();
        for (GameState state : states) {
            state.input(mouse, key);
        }

        if (key.escape.clicked) {
            System.exit(0);
        }

    }

    public void render(Graphics2D g) {
        for (GameState state : states) {
            state.render(g);
        }

    }

}
