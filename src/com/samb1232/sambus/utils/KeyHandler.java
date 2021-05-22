package com.samb1232.sambus.utils;

import com.samb1232.sambus.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener {

    public class Key {
        public int presses;
        public int absorb;
        public boolean down;
        public boolean clicked;

        public Key() {
            keys.add(this);
        }

        public void toggle(boolean pressed) {
            if (pressed != down) {
                down = pressed;
            }
            if (pressed) {
                presses++;
            }
        }

        public void tick() {
            if (absorb < presses) {
                absorb++;
                clicked = true;
            } else {
                clicked = false;
            }
        }
    }

    public static List<Key> keys = new ArrayList<Key>();

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key attack = new Key();
    public Key menu = new Key();
    public Key enter = new Key();
    public Key escape = new Key();

    public KeyHandler(GamePanel game) {
        game.addKeyListener(this);
    }

    public void releaseAll() {
        for (Key key : keys) {
            key.down = false;
        }
    }

    public void tick() {
        for (Key key : keys) {
            key.tick();
        }
    }

    public void toggle(KeyEvent e, boolean pressed) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> up.toggle(pressed);
            case KeyEvent.VK_S -> down.toggle(pressed);
            case KeyEvent.VK_D -> right.toggle(pressed);
            case KeyEvent.VK_A -> left.toggle(pressed);
            case KeyEvent.VK_SPACE -> attack.toggle(pressed);
            case KeyEvent.VK_ENTER -> enter.toggle(pressed);
            case KeyEvent.VK_ESCAPE -> escape.toggle(pressed);
            case KeyEvent.VK_P -> menu.toggle(pressed);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing, cuz we handling those two methods below
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }
}
