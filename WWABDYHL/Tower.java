package com.towerdefense.project.towers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.towerdefense.project.Utilities;
import com.towerdefense.project.enemies.Enemy;
import com.towerdefense.project.projectiles.Projectile;

import java.util.ArrayList;

public abstract class Tower {
    /*
    TOWERS!!!! They do all the rootin' tootin' shootin'!
    Towers store a fair bit of info I guess:
    - Their damage TYPE (physical or psychic)
    - how much damage they do
    - any sprites needed for their projectiles
    - other effects? slowing stuff? idk yet
    - how often they fire (probably a number of cooldown frames)
    - whether they've been pet recently and what happens when they are pet ?
    - where they are on tha map
    - A list of their active hurtboxes (if applicable)
     */

    //Make unpublic later this is a debug thing
    public Vector2 pos; // lower-left corner
    Texture img, petimg, boostimg;
    //STATES:
    public boolean isPetting = false; //is player touching tower?
    boolean isBoosted = false;//Set to true when isPetting for more than petTimerThreshold
    boolean isCoolingDown = false; //Last flag probably, for when we're in cooldown mode.
    // I could probably make an enum instead of these flags for TOWER MODE. but maybe later.
    int width = 256;
    int height = 256;

    public Polygon hitbox = new Polygon(); // public so we can debug draw it

    //DAMAGES:
    public float mindDmg, bodyDmg;

    //TIMERS:
    double attackTimer = 0;
    double petTimer = 0;
    double petTimerThreshold = 2; //Player must pet for this long before boosts become active
    long purrSoundID; //To stop purring when needed
    int boostCooldown = 6; //Time between boosts
    double boostCooldownTimer = 0; //Increments when unable to pet
    int boostDuration = 10;
    double boostTimer= 0; //Ticks when pet is boosted.
    double attackCooldown;

    ShapeRenderer sr = new ShapeRenderer(); // for debug
    ArrayList<Enemy> debugTargets;
    int numTargets;
    public ArrayList<Projectile> projectiles = new ArrayList<>();
    Sound projectileSound;
    Sound purringSound = Gdx.audio.newSound(Gdx.files.internal("sounds/purring.mp3"));

    public Tower(Vector2 pos, int radius, int mindDmg, int bodyDmg, double attackCooldown, String imgPath, String petimgPath, String boostimgPath) {
        this.pos = pos;
        this.mindDmg = mindDmg;
        this.bodyDmg = bodyDmg;
        this.attackCooldown = attackCooldown;
        img = new Texture(Gdx.files.internal(imgPath));
        petimg = new Texture(Gdx.files.internal(petimgPath));
        boostimg = new Texture(Gdx.files.internal(boostimgPath));
        // set up attack range polygon; approximate circle with 12 points
        float[] verts = new float[24];
        for (double i = 0, theta = Math.PI; i < 12*2; i += 2, theta -= Math.PI/6) {
            float x = radius * (float)Math.cos(theta);
            float y = radius * (float)Math.sin(theta);
            verts[(int)i] = x;
            verts[(int)i+1] = y;
        }
        this.hitbox.setVertices(verts);
        this.hitbox.setOrigin(0,0);
        // 128x128 is size of each tower spot/base
        this.hitbox.setPosition(pos.x + 128/2f, pos.y + 128/2f);
    }

    abstract void petBoost();

    abstract void cooldown();

    void updateTimers() {
        if (isPetting && !isCoolingDown && !isBoosted) {
            //Gdx.app.log("", "Petting ?!?!?!?!");//Only let player pet if not boosted already
            if (petTimer >= petTimerThreshold) { //Player has pet enough!
                isBoosted = true;
                petTimer = 0;
                purringSound.stop(purrSoundID);
                petBoost(); //Set up boosted stats
            }
            else {
                //Gdx.app.log("", "Petting?");
                if (petTimer == 0) {
                    //Gdx.app.log("", "Petting audio");
                    purrSoundID = purringSound.play(1f); //Play purr sound for first time only
                }
                petTimer += Gdx.graphics.getDeltaTime();
            }
        }
        else if (isBoosted) { //Boost active! Count down til boost is over!
            boostTimer += Gdx.graphics.getDeltaTime();
            if (boostTimer >= boostDuration) { // boost over
                isBoosted = false;
                isCoolingDown = true;
                cooldown(); //Reset boosted stats to normal
                boostTimer = 0;
            }
        }
        else if (isCoolingDown) {
            boostCooldownTimer += Gdx.graphics.getDeltaTime();
            if (boostCooldownTimer >= boostCooldown) { //We can be pet again
                isCoolingDown = false;
                boostCooldownTimer = 0;
            }
        }
        else {
            petTimer = 0;
            purringSound.stop(purrSoundID);
        }
    }

    public abstract ArrayList<Enemy> update(ArrayList<Enemy> enemies);

    public void draw(Batch batch) {
        for(int i = 0; i < projectiles.size(); i++){
            projectiles.get(i).draw(batch);
        }
        if(isBoosted){
            batch.draw(boostimg, pos.x-(65), pos.y+ 20, width, height );
            batch.draw(petimg, pos.x - width/4, pos.y + 20, width, height);
        }
        else if(isPetting || isCoolingDown){
            batch.draw(petimg, pos.x - width/4, pos.y + 20, width, height);
        }
        else{
            batch.draw(img, pos.x - width/4, pos.y + 20, width, height);
        }
        if (Utilities.DEBUG) drawDebug(batch);
    }

    private void drawDebug(Batch batch) {
        batch.end();
        sr.begin(ShapeRenderer.ShapeType.Line);
        // to show attack cooldown
        if (attackTimer < attackCooldown-0.2) {
            sr.setColor(Color.RED);
        }
        else {
            sr.setColor(Color.GREEN);
        }
        sr.line(pos, new Vector2(pos.x+width, pos.y));
        sr.end();
        // to show targets
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.RED);
        if (debugTargets != null) {
            for (Enemy target : debugTargets) {
                if (target == null) continue;
                // to make it a filled-in shape
                sr.rect(target.hitbox.getX(), target.hitbox.getY(),
                        target.width, target.height);
            }
        }
        sr.end();
        batch.begin();
    }

    public void dispose() {
        img.dispose();
        petimg.dispose();
        boostimg.dispose();
        for(Projectile projectile: projectiles){
            projectile.dispose();
        }
    }
}
