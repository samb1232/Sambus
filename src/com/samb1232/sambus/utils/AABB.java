package com.samb1232.sambus.utils;

import com.samb1232.sambus.entity.Entity;

public class AABB {

    private Vector2f pos;
    private Entity e;
    private float w;
    private float h;
    private float r;
    private int size;

    private float xOffset = 0;
    private float yOffset = 0;


    public AABB(Vector2f pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w, h);
    }

    public AABB(Vector2f pos, int r) {
        this.pos = pos;
        this.r = r;

        size = r;
    }

    public Vector2f getPos() {
        return pos;
    }

    public float getRadius() {
        return r;
    }

    public float getWidth() {
        return w;
    }

    public float getHeight() {
        return h;
    }

    public float getXOffset() {
        return xOffset;
    }

    public float getYOffset() {
        return yOffset;
    }

    public void setBox(Vector2f pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w, h);
    }

    public void setCircle(Vector2f pos, int r, Entity e) {
        this.pos = pos;
        this.r = r;
        this.e = e;

        size = r;
    }

    public void setWidth(float w) {
        this.w = w;
    }

    public void setHeight(float h) {
        this.h = h;
    }

    public void setXOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public void setYOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public boolean collides(AABB bBox) {
        float ax = ((pos.getWorldVar().x + (xOffset)) + (w / 2));
        float ay = ((pos.getWorldVar().y + (yOffset)) + (h / 2));
        float bx = ((pos.getWorldVar().x + (bBox.xOffset / 2)) + (w / 2));
        float by = ((pos.getWorldVar().y + (bBox.yOffset / 2)) + (h / 2));

        if (Math.abs(ax - bx) < (this.w / 2) + (bBox.w / 2)) {
            if (Math.abs(ay - by) < (this.h / 2) + (bBox.h / 2)) {
                return true;
            }
        }

        return false;
    }

    public boolean colCircleBox(AABB aBox) {
        float cx = (float) ((pos.getWorldVar().x + r - e.getSize()) / Math.sqrt(2));
        float cy = (float) ((pos.getWorldVar().y + r - e.getSize()) / Math.sqrt(2));

        float xDelta = cx - Math.max(aBox.getPos().getWorldVar().x + (aBox.getWidth() / 2), Math.min(cx, aBox.pos.getWorldVar().x));
        float yDelta = cy - Math.max(aBox.getPos().getWorldVar().y + (aBox.getWidth() / 2), Math.min(cy, aBox.pos.getWorldVar().y));

        return (xDelta * xDelta + yDelta * yDelta) < ((this.r * this.r / 2));
    }
}
