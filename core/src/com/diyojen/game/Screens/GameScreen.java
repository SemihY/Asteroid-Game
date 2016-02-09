package com.diyojen.game.Screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.diyojen.game.Asteroid;
import com.diyojen.game.GameWorld;
import com.diyojen.systems.AsteroidSystem;
import com.diyojen.systems.BirdSystem;
import com.diyojen.systems.CollisionSystem;
import com.diyojen.systems.RenderingSystem;

public class GameScreen extends ScreenAdapter {
	
	Asteroid game;
	OrthographicCamera guiCam;
	Vector3 touchPoint;
	GameWorld world;

	

	Engine engine;

	public GameScreen(Asteroid game) {
		Gdx.app.log("GameScreen", "constructor");
		Gdx.app.log("GameScreen X",Gdx.app.getGraphics().getHeight()+"");
		Gdx.app.log("GameScreen Y",Gdx.app.getGraphics().getWidth()+"");
		this.game=game;
		guiCam = new OrthographicCamera(320,480);

		touchPoint = new Vector3();
		
		engine = new Engine();

        GameWorld.createAsteroids(engine);
		engine.addEntity(GameWorld.createBackground());
        engine.addEntity(GameWorld.createEarth());
		engine.addEntity(GameWorld.createBird(160-100,250-100)); // Rotating Circle
		engine.addEntity(GameWorld.createScoreLine());
        //engine.addEntity(GameWorld.createAsteroid());
		//engine.addEntity(GameWorld.createBird(0,50,50)); // not rotate circle
		engine.addSystem(new BirdSystem());
		engine.addSystem(new RenderingSystem(game));
        engine.addSystem(new AsteroidSystem());
		engine.addSystem(new CollisionSystem());
        pauseSystems();
        resumeSystems();
	}


	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		engine.update(delta);



	}

    private void pauseSystems() {
        engine.getSystem(BirdSystem.class).setProcessing(false);
        engine.getSystem(AsteroidSystem.class).setProcessing(false);
        engine.getSystem(CollisionSystem.class).setProcessing(false);
    }



    private void resumeSystems() {
        engine.getSystem(BirdSystem.class).setProcessing(true);
        engine.getSystem(AsteroidSystem.class).setProcessing(true);
        engine.getSystem(CollisionSystem.class).setProcessing(true);
        engine.getSystem(RenderingSystem.class).setProcessing(true);
    }

}
