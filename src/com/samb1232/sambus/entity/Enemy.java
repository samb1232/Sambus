package com.samb1232.sambus.entity;

import com.samb1232.sambus.graphics.Sprite;
import com.samb1232.sambus.utils.AABB;
import com.samb1232.sambus.utils.Vector2f;

import java.awt.*;

public class Enemy extends Entity {

    private AABB sense;
    private int r;

    public Enemy(Sprite sprite, Vector2f orgin, int size) {
        super(sprite, orgin, size);

        acc = 1f;
        maxSpeed = 2.5f;
        r = 350;

        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setXOffset(12);
        bounds.setYOffset(40);

        sense = new AABB(new Vector2f(orgin.x + size / 2 - r / 2, orgin.y + size / 2 - r / 2), r);
    }


    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.GREEN);
        g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), (int) (pos.getWorldVar().y + bounds.getYOffset()),
                (int) bounds.getWidth(), (int) bounds.getHeight());

        g.setColor(Color.BLUE);
        g.drawOval((int) (sense.getPos().getWorldVar().x), (int) (sense.getPos().getWorldVar().y), r, r);

        g.drawImage(anim.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
    }

    public void move(Player player) {
        if (sense.colCircleBox(player.getBounds())) {
            if (pos.y > player.pos.y + 1) { // up
                dy -= acc;
                up = true;
                down = false;
                if (dy < -maxSpeed) {
                    dy += maxSpeed;
                }
            } else if (pos.y < player.pos.y - 1) { // down
                dy += acc;
                down = true;
                up = false;
                if (dy > maxSpeed) {
                    dy = maxSpeed;
                }
            } else {
                dy = 0;
                up = false;
                down = false;
            }
            if (pos.x > player.pos.x + 1) { // left
                dx -= acc;
                left = true;
                right = false;
                if (dx < -maxSpeed) {
                    dx = -maxSpeed;
                }
            } else if (pos.x < player.pos.x - 1) { // right
                dx += acc;
                right = true;
                left = false;
                if (dx > maxSpeed) {
                    dx = maxSpeed;
                }
            } else {
                dx = 0;
                right = false;
                left = false;
            }
        } else {
            up = false;
            down = false;
            left = false;
            right = false;
            dx = 0;
            dy = 0;
        }
    }


    public void update(Player player) {
        super.update();
//        move(player);
        if (!fallen) {

            if (!tc.collisionTile(dx, 0)) {
                sense.getPos().x += dx;
                pos.x += dx;
            }

            if (!tc.collisionTile(0, dy)) {
                sense.getPos().y += dy;
                pos.y += dy;
            }
        } else {
            destroy();
        }
    }

    public void destroy() {
        System.exit(0);
    }

}
