package com.towerdefense.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.towerdefense.project.levels.*;

public class GameManager extends ApplicationAdapter {
	static GameState gameState;
	SpriteBatch batch;
	static int width = 100; // temp before we fetch
	static int height = 100; // temp before we fetch
	static Camera camera;
	boolean isTouching = false;

	// --------- game state instances ----------
	static MainMenu mainMenu;
	static LevelSelect levelSelect;
	static Level level;
	static LevelLose levelLose;
	static LevelWin levelWin;

	@Override
	public void create () {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		// set camera
		camera = new OrthographicCamera(width, height);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight, 0);
		camera.update();

		batch = new SpriteBatch();

		// load starting state
		gameState = GameState.MainMenu;
		mainMenu = new MainMenu(width, height);
	}

	public static void changeGameState(GameState gameState) {
		switch (GameManager.gameState) {
			case MainMenu:
				mainMenu.dispose();
				break;
			case LevelSelect:
				levelSelect.dispose();
				break;
			case Level:
				level.dispose();
				break;
			case LevelLose:
				levelLose.dispose();
				break;
			case LevelWin:
				levelWin.dispose();
				break;
		}

		GameManager.gameState = gameState;
		// change state to the passed one by re-instantiating
		switch (gameState) {
			case MainMenu:
				mainMenu = new MainMenu(width, height);
				break;
			case LevelSelect:
				levelSelect = new LevelSelect(width, height);
				break;
			case LevelLose:
				levelLose = new LevelLose(width, height);
				break;
			case LevelWin:
				levelWin = new LevelWin(width, height);
		}
	}

	public static void goToLevel(int ID) {
		// also changes gameState to be GameState.Level
		GameManager.changeGameState(GameState.Level);
		switch (ID) {
			case 1:
				level = new Level1(width, height);
				break;
			case 2:
				level = new Level2(width, height);
				break;
			case 3:
				level = new Level3(width, height);
				break;
			case 4:
				level = new Level4(width, height);
				break;
			case 5:
				level = new Level5(width, height);
				break;
			case 6:
				level = new Level6(width, height);
				break;
			default:
				changeGameState(GameState.LevelSelect);
		}
	}

	private void update() {
		// check for input
		if (!isTouching && Gdx.input.isTouched()) { // finger just went down
			isTouching = true;
			int posX = Gdx.input.getX();
			int posY = height - Gdx.input.getY();
			// pass input to be parsed by state
			switch (gameState) {
				case MainMenu:
					mainMenu.parseInput(posX, posY, isTouching);
					break;
				case LevelSelect:
					levelSelect.parseInput(posX, posY, isTouching);
					break;
				case Level:
					level.parseInput(posX, posY, isTouching);
					break;
				case LevelLose:
					levelLose.parseInput(posX, posY, isTouching);
					break;
				case LevelWin:
					levelWin.parseInput(posX, posY, isTouching);
			}
		}
		else if (isTouching && !Gdx.input.isTouched()) { // finger let go
			isTouching = false;
			int posX = Gdx.input.getX();
			int posY = height - Gdx.input.getY();
			switch (gameState) {
				case MainMenu:
					mainMenu.parseInput(posX, posY, isTouching);
					break;
				case LevelSelect:
					levelSelect.parseInput(posX, posY, isTouching);
					break;
				case Level:
					level.parseInput(posX, posY, isTouching);
					break;
				case LevelLose:
					levelLose.parseInput(posX, posY, isTouching);
					break;
				case LevelWin:
					levelWin.parseInput(posX, posY, isTouching);
			}
		}

		// update game state
		switch (gameState) {
			case Level:
				level.update();
				break;
		}
	}

	private void draw() {
		batch.begin();
		switch (gameState) {
			case MainMenu:
				mainMenu.draw(batch);
				break;
			case LevelSelect:
				levelSelect.draw(batch);
				break;
			case Level:
				level.draw(batch);
				break;
			case LevelLose:
				levelLose.draw(batch);
				break;
			case LevelWin:
				levelWin.draw(batch);
				break;
		}
		batch.end();
	}

	@Override
	public void render () {
		this.update();
		this.draw();
	}

	@Override
	public void dispose () {
		mainMenu.dispose();
		levelSelect.dispose();
		level.dispose();
		levelLose.dispose();
		levelWin.dispose();
		batch.dispose();
	}
}
