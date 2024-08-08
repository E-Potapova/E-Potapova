package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game {
	//  ----- game screen -----
	Camera camera;
	int screenWidth;
	int screenHeight;
	// ----- player vars -----
	Texture playerImg;
	Player player;
	Texture jumpParticleImg;
	ParticleManager jumpParticleManager;
	// ----- coins -----
	CoinManager coinManager;
	// ----- spikes and hitting them -----
	SpikeManager spikeManager;
	boolean hitSpike = false;
	final float HIT_PAUSE = 0.6f; // in seconds
	float hitPauseTimer = 0.0f;
	// ----- score vars -----
	BitmapFont fontScore;
	long score = 0;
	long heightScore = 0;
	long coinScore = 0;

	public Game(int screenWidth, int screenHeight, Camera camera) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.camera = camera;

		// save images to be used
		playerImg = new Texture("sprites/Guyble.png");
		jumpParticleImg = new Texture("sprites/jumpParticle.png");

		// save font to be used for score
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/UniSans-Trial-Heavy.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = screenHeight / 20;
		fontScore = generator.generateFont(parameter);
		generator.dispose();
		fontScore.setColor(251/255f, 202/255f, 151/255f, 1.0f);

		// create necessary instances
		player = new Player(playerImg, screenWidth, screenHeight, screenWidth/2-50, screenHeight/2-150);
		player.jump(250, 500);
		jumpParticleManager = new ParticleManager(ParticleType.Gravity, jumpParticleImg, 32);
		coinManager = new CoinManager(Utilities.WALL_WIDTH+100, screenWidth-Utilities.WALL_WIDTH-100, screenHeight);
		spikeManager = new SpikeManager(Utilities.WALL_WIDTH, screenWidth-Utilities.WALL_WIDTH, screenHeight);
	}

	public void parseInput(int deltaX, int deltaY) {
		if (hitSpike) return; // switching to game over, so can't do anything
		if (player.jump(deltaX, deltaY)) {
			Gdx.app.log("game parseInput()", "playerY: " + player.centerY);
			// display particles only when we successfully jump
			jumpParticleManager.drawParticles(5,
					player.centerX,
					player.centerY-50,
					210,
					330
			);
		}
	}

	public void update() {
		// check if we are in the hit pause, if so update it
		if (hitSpike && hitPauseTimer < HIT_PAUSE) {
			hitPauseTimer += Gdx.graphics.getDeltaTime();
			return;
		}
		else if (hitSpike && hitPauseTimer >= HIT_PAUSE && hitPauseTimer < HIT_PAUSE + 0.5f) {
			player.dX = 100;
			if (player.centerX > screenWidth/2) player.dX *= -1;
			player.dY = 1500;
			hitPauseTimer += 1.0f; // used as a 'flag' to only set these once
		}

		player.update();
		jumpParticleManager.update();
		// move camera but only upward as the player moves up
		camera.position.y = Utilities.smooth(camera.position.y, Math.max(camera.position.y, player.centerY+screenHeight/4), 80, 0.1f);
		camera.update();

		// check if the player fell
		if (player.centerY < camera.position.y - camera.viewportHeight/2 - 100) {
			Gdx.app.log("game update()", "switch to game over");
			Utilities.latestHighScore = score;
			Utilities.gameState = GameState.GameOver;
		}

		// switching to game over, so we don't want to
		// check coin, spike collisions; don't update score
		if (hitSpike) {
			spikeManager.updateParticles(player.centerX, player.centerY);
			return;
		}

		// check if player picked up any coins
		coinScore += coinManager.update(player.centerX, player.centerY, player.width, camera.position.y, screenHeight);
		// check if player collided with a spike
		boolean hitSpikeJustNow = spikeManager.update(player.centerX, player.centerY, player.width, camera.position.y, screenHeight);
		if (hitSpikeJustNow) {
			Gdx.app.log("game update()", "player hit spike, player falling");
			hitSpike = true;
		}
		// udpate score
		heightScore = (int)Math.max(heightScore, (player.centerY - screenHeight/2) / 80);
		score = heightScore + coinScore;
	}

	public void draw(SpriteBatch batch) {
		ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
		// draw background and walls
		Utilities.drawBackground(batch, camera.position.y, screenWidth, screenHeight);
		Utilities.drawWalls(batch, camera.position.y, screenWidth, screenHeight);
		// draw instances
		jumpParticleManager.draw(batch);
		coinManager.draw(batch);
		spikeManager.draw(batch);
		player.draw(batch);
		// draw UI
		fontScore.draw(batch, ""+score, screenWidth/2, camera.position.y - screenHeight/2 + fontScore.getCapHeight()+50,
				0, Align.center, false);
	}

	public void dispose () {
		playerImg.dispose();
		jumpParticleImg.dispose();
		coinManager.dispose();
		spikeManager.dispose();
	}
}
