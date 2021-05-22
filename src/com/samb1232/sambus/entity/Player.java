package com.samb1232.sambus.entity;

import com.samb1232.sambus.GamePanel;
import com.samb1232.sambus.graphics.Sprite;
import com.samb1232.sambus.states.PlayState;
import com.samb1232.sambus.utils.KeyHandler;
import com.samb1232.sambus.utils.MouseHandler;
import com.samb1232.sambus.utils.Vector2f;

import java.awt.*;

public class Player extends Entity {
    public Player(Sprite sprite, Vector2f orgin, int size) {
        super(sprite, orgin, size);

        acc = 2f;
        maxSpeed = 3f;
        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setXOffset(12);
        bounds.setYOffset(40);
    }

    @Override
    public void update() {
        super.update();
        if (!fallen) {
            move();
            if (!tc.collisionTile(dx, 0)) {
                PlayState.map.x += dx;
                pos.x += dx;
            }
            if (!tc.collisionTile(0, dy)) {
                PlayState.map.y += dy;
                pos.y += dy;
            }
        } else {
            if (anim.hasPlayedOnce()) {
                resetPosition();
                fallen = false;
            }
        }
    }

    private void resetPosition() {
        System.out.println("Resetting player...");
        pos.x = GamePanel.width / 2 - 32;
        PlayState.map.x = 0;

        pos.y = GamePanel.height / 2 - 32;
        PlayState.map.y = 0;

//        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
    }

    @Override
    public void render(Graphics2D g) {
//        g.setColor(Color.red);
//        g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()),
//                (int) (pos.getWorldVar().y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
        g.drawImage(anim.getImage(), (int) pos.getWorldVar().x, (int) pos.getWorldVar().y, size, size, null);
    }

    @Override
    public void input(KeyHandler key, MouseHandler mouse) {

        if (mouse.getButton() == DOWN) {
            System.out.println("Player: " + pos.x + ",  " + pos.y);
        }
        if (!fallen) {
            up = key.up.down; // if true -> up = true; if false -> up = false
            down = key.down.down;
            left = key.left.down;
            right = key.right.down;
            attack = key.attack.down;
        } else {
            up = false;
            down = false;
            left = false;
            right = false;
        }
    }

    public void move() {
        if (up) {
            dy -= acc;
            if (dy < -maxSpeed) {
                dy += maxSpeed;
            }
        } else {
            if (dy < 0) {
                dy += deAcc;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }
        if (down) {
            dy += acc;
            if (dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if (dy > 0) {
                dy -= deAcc;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }
        if (left) {
            dx -= acc;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if (dx < 0) {
                dx += deAcc;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
        if (right) {
            dx += acc;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= deAcc;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }
    }
}
