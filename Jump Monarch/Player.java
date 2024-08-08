package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Player {
    Texture img;
    int screenWidth;
    int screenHeight;
    int width = 100;
    int height = 100;
    long centerX;
    long centerY;
    double dX = 0;
    double dY = 0;
    boolean touchingWall = true;
    static final float FORCE_RATIO = 6;
    static final float MAX_DISTANCE_PERCENTAGE = 0.2f;

    public Player(Texture img, int screenWidth, int screenHeight, long centerX, long centerY) {
        this.img = img;
        this.centerX = centerX;
        this.centerY = centerY;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    // Returns true if the player successfully jumped, false otherwise
    public boolean jump(int deltaX, int deltaY) {
        // this is considered a swipe
        //  first check that its considered a swipe based on the min distance
        if (Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2))
                >
                (Utilities.MIN_DISTANCE_PERCENTAGE * (screenWidth + screenHeight))) {
            // then see if we're allowed to jump
            if (touchingWall) {
                // check that it is a valid jump;
                // if on left wall, can jump right
                // if on right wall, can jump left
                if ((centerX < screenWidth/2) && (deltaX < 10)) {
                    return false;
                }
                else if ((centerX > screenWidth/2) && (deltaX > -10)) {
                    return false;
                }
                // check if the swipe is above our max swipe distance and clamp it
                if (Math.abs(deltaX)+Math.abs(deltaY) > MAX_DISTANCE_PERCENTAGE*(screenWidth + screenHeight)) {
                    float factor = (MAX_DISTANCE_PERCENTAGE*(screenWidth + screenHeight)) / (Math.abs(deltaX)+Math.abs(deltaY));
                    deltaX *= factor;
                    deltaY *= factor;
                }
                this.dX = deltaX * FORCE_RATIO;
                this.dY = deltaY * FORCE_RATIO;
                return true;
            }
        }
        return false;
    }

    public void update() {
        this.centerX += this.dX * Gdx.graphics.getDeltaTime();
        this.centerY += this.dY * Gdx.graphics.getDeltaTime();

        // check if we're touching walls
        if (this.centerX <= (Utilities.WALL_WIDTH + width/2)) {
            if (!touchingWall) {
                touchingWall = true;
                dY = 0;
            }
            this.centerX = (Utilities.WALL_WIDTH + width/2);
            dY -= Utilities.GRAVITY / 8;
        }
        else if (this.centerX >= screenWidth-(Utilities.WALL_WIDTH + width/2)) {
            if (!touchingWall) {
                touchingWall = true;
                dY = 0;
            }
            this.centerX = screenWidth-(Utilities.WALL_WIDTH + width/2);
            dY -= Utilities.GRAVITY / 8;
        }
        else {
            touchingWall = false;
            dY -= Utilities.GRAVITY;
        }
    }

    public void draw(Batch batch) {
        batch.draw(img, centerX - (width/2), centerY - (height/2), width, height);
    }
}
