package com.samb1232.sambus.graphics;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] frames;
    private int currentFrame;
    private int numFrames;

    private int count;
    private int delay;

    private int timesPlayed;

    public Animation(BufferedImage[] frames) {
        timesPlayed = 0;
        setFrames(frames);
    }

    public Animation() {
        timesPlayed = 0;
    }

    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        currentFrame = 0;
        count = 0;
        timesPlayed = 0;
        delay = 2;
        numFrames = frames.length;
    }

    public void setDelay(int value) {
        delay = value;
    }

    public void setFrame(int value) {
        currentFrame = value;
    }

    public void setNumFrames(int value) {
        numFrames = value;
    }

    public int getDelay() {
        return delay;
    }

    public int getFrame() {
        return currentFrame;
    }

    public int getNumFrames() {
        return numFrames;
    }

    public BufferedImage getImage() {
        return frames[currentFrame];
    }

    public boolean hasPlayed(int i) {
        return timesPlayed == i;
    }

    public boolean hasPlayedOnce() {
        return timesPlayed == 1;
    }


    public void update() {
        if (delay == -1) return;

        count++;

        if (count == delay) {
            currentFrame++;
            count = 0;
        }

        if (currentFrame == numFrames) {
            currentFrame = 0;
            timesPlayed++;
        }
    }
}
