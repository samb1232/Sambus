package com.samb1232.sambus.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Font {
    private final BufferedImage FONT_SHEET;
    private BufferedImage[][] FontArray;
    private final int TILE_SIZE = 32;

    public int w;
    public int h;

    private int wLetter;
    private int hLetter;

    public Font(String file) {
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("Loading " + file + "...");
        FONT_SHEET = loadFont(file);

        wLetter = FONT_SHEET.getWidth() / w;
        hLetter = FONT_SHEET.getHeight() / h;
        loadFontArray();
    }

    public Font(String file, int w, int h) {
        this.w = w;
        this.h = h;

        System.out.println("Loading " + file + "...");
        FONT_SHEET = loadFont(file);

        wLetter = FONT_SHEET.getWidth() / w;
        hLetter = FONT_SHEET.getHeight() / h;
        loadFontArray();
    }


    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int width) {
        this.w = width;
        wLetter = FONT_SHEET.getWidth() / w;
    }

    public int getWidth() {
        return w;
    }

    public void setHeight(int height) {
        this.h = height;
        hLetter = FONT_SHEET.getHeight() / h;
    }

    public int getHeight() {
        return h;
    }

    public BufferedImage loadFont(String file) {
        BufferedImage font = null;
        try {
            font = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(file)));
        } catch (Exception e) {
            System.err.println("ERROR: could not load file: " + file);
        }
        return font;
    }

    public void loadFontArray() {
        FontArray = new BufferedImage[wLetter][hLetter];
        for (int x = 0; x < wLetter; x++) {
            for (int y = 0; y < hLetter; y++) {
                FontArray[x][y] = getLetter(x, y);
            }
        }
    }

    public BufferedImage getFontSheet() {
        return FONT_SHEET;
    }

    public BufferedImage getLetter(int x, int y) {
        return FONT_SHEET.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage getFont(char letter) {
        int value = letter;
        int x = value % wLetter;
        int y = value / wLetter;

        return getLetter(x, y);
    }
}
