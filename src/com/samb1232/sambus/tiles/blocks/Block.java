package com.samb1232.sambus.tiles.blocks;

import com.samb1232.sambus.utils.AABB;
import com.samb1232.sambus.utils.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block {

    protected int w;
    protected int h;

    protected BufferedImage img;
    protected Vector2f pos;

    public Block(BufferedImage img, Vector2f pos, int w, int h) {
        this.img = img;
        this.pos = pos;
        this.w = w;
        this.h = h;
    }

    public abstract boolean update(AABB p);

    public abstract boolean isInside(AABB p);

    public void render(Graphics2D g) {
        g.drawImage(img, (int) pos.getWorldVar().x, (int) pos.getWorldVar().y, w, h, null);
    }

    public Vector2f getPos() {
        return pos;
    }
}
