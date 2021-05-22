package com.samb1232.sambus.utils;

import com.samb1232.sambus.entity.Entity;
import com.samb1232.sambus.tiles.TileMapObj;
import com.samb1232.sambus.tiles.blocks.Block;
import com.samb1232.sambus.tiles.blocks.HoleBlock;

public class TileCollision {

    private final Entity e;
    private final AABB bounds;


    public TileCollision(Entity e) {
        this.e = e;
        bounds = e.getBounds();
    }

    public boolean collisionTile(float ax, float ay) {
        for (int c = 0; c < 4; c++) {
            int xt = (int) ((bounds.getPos().x + ax) + (c % 2) * bounds.getWidth() + bounds.getXOffset()) / 64;
            int yt = (int) ((bounds.getPos().y + ay) + (c / 2) * bounds.getHeight() + bounds.getYOffset()) / 64;

            if (TileMapObj.tmo_blocks.containsKey(xt + "," + yt)) {
                Block block = TileMapObj.tmo_blocks.get(xt + "," + yt);
                if (block instanceof HoleBlock) {
                    return collisionHole(ax, ay, xt, yt, block);
                }
                return block.update(bounds);
            }
        }
        return false;
    }

    private boolean collisionHole(float ax, float ay, float xt, float yt, Block block) {
        int nextXt = (int) ((bounds.getPos().x + ax + bounds.getXOffset() + bounds.getWidth()) / 64);
        int nextYt = (int) ((bounds.getPos().y + ay + bounds.getYOffset() + bounds.getHeight()) / 64);

        if (block.isInside(bounds)) {
            e.setFallen(true);
            return false;
        } else if ((nextXt == xt + 1) || (nextXt == yt + 1)) {
            if (TileMapObj.tmo_blocks.containsKey(nextXt + "," + nextYt)) {
                if (bounds.getPos().x > block.getPos().x) {
                    e.setFallen(true);
                }
                return false;
            }
        }
        e.setFallen(false);
        return false;
    }
}
