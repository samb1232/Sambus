package com.samb1232.sambus.entity;


import com.samb1232.sambus.graphics.Animation;
import com.samb1232.sambus.graphics.Sprite;
import com.samb1232.sambus.utils.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    protected Animation anim;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;
    protected PersonStates currentAnimation;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean fallen;
    protected boolean attack;

    public boolean xCol = false;
    public boolean yCol = false;


    protected int attackSpeed;
    protected int attackDuration;

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
        hitBounds = new AABB(orgin, size, size);
        hitBounds.setXOffset(size / 2);

        anim = new Animation();
        setAnimation(PersonStates.RIGHT, sprite.getSpriteArray(PersonStates.RIGHT.getSideNumber()), 10);

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

    public float getDeAcc() {
        return deAcc;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public void setAnimation(PersonStates side, BufferedImage[] frames, int delay) {
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
            if (currentAnimation != PersonStates.UP || anim.getDelay() == -1) {
                setAnimation(PersonStates.UP, sprite.getSpriteArray(PersonStates.UP.getSideNumber()), 5);
            }
        } else if (down) {
            if (currentAnimation != PersonStates.DOWN || anim.getDelay() == -1) {
                setAnimation(PersonStates.DOWN, sprite.getSpriteArray(PersonStates.DOWN.getSideNumber()), 5);
            }
        } else if (right) {
            if (currentAnimation != PersonStates.RIGHT || anim.getDelay() == -1) {
                setAnimation(PersonStates.RIGHT, sprite.getSpriteArray(PersonStates.RIGHT.getSideNumber()), 5);

            }
        } else if (left) {
            if (currentAnimation != PersonStates.LEFT || anim.getDelay() == -1) {
                setAnimation(PersonStates.LEFT, sprite.getSpriteArray(PersonStates.LEFT.getSideNumber()), 5);
            }
        }else if(fallen) {
            if (currentAnimation != PersonStates.FALLEN || anim.getDelay() == -1) {
                setAnimation(PersonStates.FALLEN, sprite.getSpriteArray(PersonStates.FALLEN.getSideNumber()), 15);
            }

        } else {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation.getSideNumber()), -1);
        }
    }

    public void setHitboxDirection() {
        if (up) {
            hitBounds.setXOffset(0);
            hitBounds.setYOffset(-size / 2);
        }
        if (down) {
            hitBounds.setXOffset(0);
            hitBounds.setYOffset(size / 2);
        }
        if (right) {
            hitBounds.setXOffset(size / 2);
            hitBounds.setYOffset(0);
        }
        if (left) {
            hitBounds.setXOffset(-size / 2);
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
