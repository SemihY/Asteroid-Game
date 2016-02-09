package com.diyojen.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.diyojen.game.Asteroid;
import com.diyojen.helpers.AssetLoader;

import static javafx.scene.paint.Color.*;

public class MainMenuScreen extends ScreenAdapter {

	Asteroid game;
	OrthographicCamera guiCam;
	Rectangle soundBounds;
	Rectangle playBounds;
	Vector3 touchPoint;

    private ShapeRenderer shapeRenderer;

	public MainMenuScreen(Asteroid game) {
		this.game=game;
        shapeRenderer = new ShapeRenderer();
		guiCam = new OrthographicCamera(320,480);
		guiCam.position.set(320/2,480/2,0);
		soundBounds = new Rectangle(0, 0, 64, 64);
		playBounds = new Rectangle(160 - 150, 200 + 18, 300, 36);
		touchPoint = new Vector3();
		
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (playBounds.contains(touchPoint.x, touchPoint.y)) {
				//Assets.playSound(Assets.clickSound);
				Gdx.app.log("MainMenuScreen", "update");
				game.setScreen(new GameScreen(game));
				return;
			}
			
		}
		
	}

	public void draw () {
        GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
        game.batch.setProjectionMatrix(guiCam.combined);
        shapeRenderer.setProjectionMatrix(game.batch.getProjectionMatrix());

		game.batch.disableBlending();
        game.batch.begin();
        game.batch.draw(AssetLoader.backgroundRegion, 0, 0, 320, 480);
		game.batch.end();
		game.batch.enableBlending();

//		game.batch.begin();
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(new Color(Color.rgb565(13, 127, 151)));
//        shapeRenderer.rect(160 - 150, 200 + 18, 300, 36);
//        shapeRenderer.end();
//        game.batch.end();


		game.batch.begin();
    	AssetLoader.font.draw(game.batch, "PLAY", 160-40, 200 + 18+36);
//		game.batch.draw(Assets.mainMenu, 10, 200 - 110 / 2, 300, 110);
//		game.batch.draw(Settings.soundEnabled ? Assets.soundOn : Assets.soundOff, 0, 0, 64, 64);
		game.batch.end();
	}

	@Override
	public void render (float delta) {
		update();
		draw();
	}

}
