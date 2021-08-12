package com.samb1232.sambus.utils;

import com.samb1232.sambus.GamePanel;
import com.samb1232.sambus.entity.Entity;
import com.samb1232.sambus.entity.PersonStates;
import com.samb1232.sambus.states.PlayState;

import java.awt.*;

public class Camera {

    private AABB collisionCam;
    private AABB bounds;

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    private float dx;
    private float dy;
    private float maxSpeed = 4f;
    private float acc = 1f;
    private float deAcc = 0.3f;

    private int withLimit;
    private int heightLimit;

    private Entity e;

    public Camera(AABB collisionCam) {

        this.collisionCam = collisionCam;
    }

    public void setLimit(int withLimit, int heightLimit) {
        this.withLimit = withLimit;
        this.heightLimit = heightLimit;
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

    public void update() {
        move();

        if(!e.xCol) {
            PlayState.map.x += dx;
        }

        if (!e.yCol) {
            PlayState.map.y += dy;
        }
    }

    public void target(Entity e) {
        this.e = e;
        deAcc = e.getDeAcc();
        maxSpeed = e.getMaxSpeed();
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        if (e == null) {
            up = key.up.down; // if true -> up = true; if false -> up = false
            down = key.down.down;
            left = key.left.down;
            right = key.right.down;
        } else {
            if (!e.yCol){
                if (PlayState.map.y + (GamePanel.height - e.getSize()) / 2 + dy > e.getBounds().getPos().y + e.getDy() + 2) {
                    up = true;
                    down = false;
                } else if (PlayState.map.y + (GamePanel.height - e.getSize()) / 2 + dy < e.getBounds().getPos().y + e.getDy() - 2) {
                    down = true;
                    up = false;
                } else {
                    dy = 0;
                    up = false;
                    down = false;
                }
            }

            if (!e.xCol){
                if (PlayState.map.x + (GamePanel.width - e.getSize()) / 2 + dx > e.getBounds().getPos().x + e.getDx() + 2) {
                    left = true;
                    right = false;
                } else if (PlayState.map.x + (GamePanel.width - e.getSize()) / 2 + dx < e.getBounds().getPos().x + e.getDx() - 2) {
                    right = true;
                    left = false;
                } else {
                    dx = 0;
                    right = false;
                    left = false;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawRect((int) (collisionCam.getPos().x), (int) (collisionCam.getPos().y),
                (int) (collisionCam.getWidth()), (int) (collisionCam.getHeight()));
    }

}









