package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class ParticleManager {
    Texture img;
    Particle[] pool;
    final int MAX_AMOUNT = 1000;
    int currParticleIndex = 0;

    public ParticleManager(ParticleType type, Texture img, int particleSize) {
        this.img = img;

        // instantiate pool with particle type specified
        switch (type) {
            case Linear:
                pool = new LinearParticle[MAX_AMOUNT];
                for (int i = 0; i < MAX_AMOUNT; i++) {
                    pool[i] = new LinearParticle(img, particleSize);
                }
                break;
            case Gravity:
                pool = new GravityParticle[MAX_AMOUNT];
                for (int i = 0; i < MAX_AMOUNT; i++) {
                    pool[i] = new GravityParticle(img, particleSize);
                }
                break;
            default:
                pool = new Particle[MAX_AMOUNT];
                for (int i = 0; i < MAX_AMOUNT; i++) {
                    pool[i] = new Particle(img, particleSize);
                }
                break;
        }
    }

    public void drawParticles(int amount, float centerX, float centerY, double angleStart, double angleEnd) {
        int spawned = 0;
        while (spawned < amount) {
            pool[currParticleIndex].reset(centerX, centerY, angleStart, angleEnd);
            currParticleIndex++;
            if (currParticleIndex == MAX_AMOUNT)
                currParticleIndex = 0;
            spawned++;
        }
    }

    public void update() {
        for (int i = 0; i < MAX_AMOUNT; i++) {
            pool[i].update();
        }
    }

    public void draw(Batch batch) {
        for (int i = 0; i < MAX_AMOUNT; i++) {
            pool[i].draw(batch);
        }
    }
}
