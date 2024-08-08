package com.towerdefense.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class LevelSelect {
    int screenWidth;
    int screenHeight;
    final int numLevels = 6; //How many levels are in the game?
    Texture backgroundImg;
    BitmapFont font;
    Button menuButton;
    ArrayList<Button> levelButtons = new ArrayList<>();

    public LevelSelect(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.backgroundImg = new Texture(Gdx.files.internal("ui/levelSelectBG.png"));
        int wunit = screenWidth / 19;
        int hunit = screenHeight / 9;

        // buttons
        menuButton = new Button(wunit, screenHeight - 96,
                128, 128, "ui/menuPressed.png", "ui/menuUnpressed.png");
        for(int i = 0; i < numLevels; i++){
            Button testButton = new Button(((i + 1) * 2*wunit + 300), screenHeight/2+50, 128, 128,
                    "ui/levelPressed.png", "ui/levelUnpressed.png");
            levelButtons.add(testButton);
        }

        // save fonts to be used later
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Caveat-VariableFont_wght.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = screenHeight / 15;
        font = generator.generateFont(parameter);
        font.setColor(183/255f, 225/255f, 253/255f, 1.0f);
        generator.dispose();
    }

    public void parseInput(int posX, int posY, boolean isTouching) { // location tapped
        if (isTouching) {
            menuButton.isPressed(posX, posY);
            for(int i = 0; i < levelButtons.size(); i++){
                levelButtons.get(i).isPressed(posX, posY);
            }
        }
        else {
            if (menuButton.isReleased(posX, posY)){
                GameManager.changeGameState(GameState.MainMenu);
            }
            for(int i = 0; i < levelButtons.size(); i++){
                if(levelButtons.get(i).isReleased(posX, posY)){
                    Utilities.menuMusic.stop();
                    GameManager.goToLevel(i+1);
                }
            }

        }
    }

    public void draw(Batch batch) {
        // very light teal color
        ScreenUtils.clear(215/255f, 249/255f, 240/255f, 1);
        // draw UI
        batch.draw(backgroundImg, 0, 0, screenWidth, screenHeight);
        menuButton.draw(batch);
        for(int i = 0; i < levelButtons.size(); i++){
            levelButtons.get(i).draw(batch);
            font.draw(batch, ""+(i+1), levelButtons.get(i).getPosition().x + 42, levelButtons.get(i).getPosition().y + 92);
        }
    }

    public void dispose() {
        menuButton.dispose();
        backgroundImg.dispose();
        for (Button button : levelButtons) {
            button.dispose();
        }
    }
}