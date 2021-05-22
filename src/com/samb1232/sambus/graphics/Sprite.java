package com.samb1232.sambus.graphics;

import com.samb1232.sambus.utils.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class Sprite {

    private BufferedImage SPRITE_SHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;

    public int w;
    public int h;

    private int wSprite;
    private int hSprite;

    public Sprite(String file) {
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("Loading " + file + "...");
        SPRITE_SHEET = loadSprite(file);

        wSprite = SPRITE_SHEET.getWidth() / w;
        hSprite = SPRITE_SHEET.getHeight() / h;
        loadSpriteArray();
    }

    public Sprite(String file, int w, int h) {
        this.w = w;
        this.h = h;

        System.out.println("Loading " + file + "...");
        SPRITE_SHEET = loadSprite(file);

        wSprite = SPRITE_SHEET.getWidth() / w;
        hSprite = SPRITE_SHEET.getHeight() / h;
        loadSpriteArray();
    }


    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int width) {
        this.w = width;
        wSprite = SPRITE_SHEET.getWidth() / w;
    }

    public int getWidth() {
        return w;
    }

    public void setHeight(int height) {
        this.h = height;
        hSprite = SPRITE_SHEET.getHeight() / h;
    }

    public int getHeight() {
        return h;
    }

    public BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(file)));
        } catch (Exception e) {
            System.err.println("ERROR: could not load file: " + file);
        }
        return sprite;
    }

    public void loadSpriteArray() {
        spriteArray = new BufferedImage[hSprite][wSprite];
        for (int y = 0; y < hSprite; y++) {
            for (int x = 0; x < wSprite; x++) {
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }

    public BufferedImage getSpriteSheet() {
        return SPRITE_SHEET;
    }

    public BufferedImage getSprite(int x, int y) {
        return SPRITE_SHEET.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage[] getSpriteArray(int index) {
        return spriteArray[index];
    }

    public BufferedImage[][] getWholeSpriteArray() {
        return spriteArray;
    }

    public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2f pos,
                                 int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;

        for (int i = 0; i < img.size(); i++) {
            if (img.get(i) != null) {
                g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
            }

            x += xOffset;
            y += yOffset;
        }
    }

    public static void drawArray(Graphics2D g, Font f, String word, Vector2f pos,
                                 int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != (int)' ') {
                g.drawImage(f.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
            }
            x += xOffset;
            y += yOffset;
        }
    }

}
