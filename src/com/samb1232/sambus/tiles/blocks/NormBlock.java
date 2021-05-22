package com.samb1232.sambus.tiles.blocks;

import com.samb1232.sambus.utils.AABB;
import com.samb1232.sambus.utils.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NormBlock extends Block {


    public NormBlock(BufferedImage img, Vector2f pos, int w, int h) {
        super(img, pos, w, h);
    }

    @Override
    public boolean update(AABB p) {
        return false;
    }

    @Override
    public boolean isInside(AABB p) {
        return false;
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
    }
}
