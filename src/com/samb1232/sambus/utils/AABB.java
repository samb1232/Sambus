package com.samb1232.sambus.utils;

import com.samb1232.sambus.entity.Entity;

public class AABB {// Axis aligned bounding boxes

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
        float ax = ((pos.getWorldVar().x + xOffset) + (this.w / 2));
        float ay = ((pos.getWorldVar().y + yOffset) + (this.h / 2));
        float bx = ((bBox.pos.getWorldVar().x + (bBox.xOffset)) + (bBox.getWidth() / 2));
        float by = ((bBox.pos.getWorldVar().y + (bBox.yOffset)) + (bBox.getHeight() / 2));

        if (Math.abs(ax - bx) < ((this.w + bBox.getWidth()) / 2)) {
            if (Math.abs(ay - by) < ((this.h + bBox.getHeight()) / 2)) {
                return true;
            }
        }

        return false;
    }

    public boolean colCircleBox(AABB aBox) {

        float dx = Math.max(aBox.getPos().getWorldVar().x + aBox.getXOffset(), Math.min(pos.getWorldVar().x + (r / 2),
                aBox.getPos().getWorldVar().x + aBox.getXOffset() + aBox.getWidth()));
        float dy = Math.max(aBox.getPos().getWorldVar().y + aBox.getYOffset(), Math.min(pos.getWorldVar().y + (r / 2),
                aBox.getPos().getWorldVar().y + aBox.getYOffset() + aBox.getHeight()));

        dx = pos.getWorldVar().x + (r / 2) - dx;
        dy = pos.getWorldVar().y + (r / 2) - dy;

        if (Math.sqrt(dx * dx + dy * dy) < (r / 2)) {
            return true;
        }

        return false;
    }
}
