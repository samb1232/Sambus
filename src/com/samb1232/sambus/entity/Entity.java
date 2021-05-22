package com.samb1232.sambus.entity;


import com.samb1232.sambus.graphics.Animation;
import com.samb1232.sambus.graphics.Sprite;
import com.samb1232.sambus.utils.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    protected final int UP = 3;
    protected final int DOWN = 2;
    protected final int RIGHT = 0;
    protected final int LEFT = 1;
    protected final int FALLEN = 4;

    protected Animation anim;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;
    protected int currentAnimation;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean fallen;
    protected boolean attack;
//    protected int attackSpeed;
//    protected int attackDuration;

    protected float dx;
    protected float dy;

    protected float maxSpeed = 4f;
    protected float acc = 3f; // accelerating
    protected float deAcc = 0.3f;

    protected AABB bounds;
    protected AABB hitBounds;

    protected TileCollision tc;


    public Entity(Sprite sprite, Vector2f orgin, int size) {
        this.sprite = sprite;
        pos = orgin;
        this.size = size;

        bounds = new AABB(orgin, size, size);
        hitBounds = new AABB(new Vector2f(orgin.x + (size / 2), orgin.y), size, size);

        anim = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);

        tc = new TileCollision(this);
    }

    public int getSize() {
        return size;
    }

    public Animation getAnimation() {
        return anim;
    }

    public AABB getBounds() {
        return bounds;
    }

    public void setAnimation(int side, BufferedImage[] frames, int delay) {
        currentAnimation = side;
        anim.setFrames(frames);
        anim.setDelay(delay);
    }

    public void setFallen(boolean state) {
        fallen = state;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setAcc(float acc) {
        this.acc = acc;
    }

    public void setDeAcc(float deAcc) {
        this.deAcc = deAcc;
    }

    public void animate() {
        if (up) {
            if (currentAnimation != UP || anim.getDelay() == -1) {
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
            }
        } else if (down) {
            if (currentAnimation != DOWN || anim.getDelay() == -1) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        } else if (right) {
            if (currentAnimation != RIGHT || anim.getDelay() == -1) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);

            }
        } else if (left) {
            if (currentAnimation != LEFT || anim.getDelay() == -1) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        }else if(fallen) {
            if (currentAnimation != FALLEN || anim.getDelay() == -1) {
                setAnimation(FALLEN, sprite.getSpriteArray(FALLEN), 15);
            }

        } else {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }

    public void setHitboxDirection() {
        if (up) {
            hitBounds.setXOffset(-size / 2);
            hitBounds.setYOffset(-size / 2);
        }
        if (down) {
            hitBounds.setXOffset(-size / 2);
            hitBounds.setYOffset(size / 2);
        }
        if (right) {
            hitBounds.setXOffset(-size);
            hitBounds.setYOffset(0);
        }
        if (left) {
            hitBounds.setXOffset(0);
            hitBounds.setYOffset(0);
        }
    }

    public void update() {
        animate();
        setHitboxDirection();
        anim.update();
    }

    public abstract void render(Graphics2D g);

    public void input(KeyHandler key, MouseHandler mouse) {

    }

}
