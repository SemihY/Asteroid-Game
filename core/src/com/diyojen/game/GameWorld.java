package com.diyojen.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.diyojen.components.*;
import com.diyojen.helpers.AssetLoader;
import com.diyojen.helpers.Util;

public class GameWorld {
	
	private Engine engine;

	public GameWorld (Engine engine) {
		this.engine = engine;
	}
	
	public static  Entity createBird(int positionX,int positionY) {
		
		Gdx.app.log("GameScreen", "createBird");
		Entity entity = new Entity();
		
		MovementComponent movement = new MovementComponent();
		TextureComponent texture = new TextureComponent();
		TransformComponent transform = new TransformComponent();
		BirdComponent player = new BirdComponent();
		
		movement.velocity.set(0,0);

		texture.textRegion =AssetLoader.bird;//*/new TextureRegion(Util.createSolidCircleTexture(Color.RED, 20, 20));
		transform.position.set(positionX, positionY);

		entity.add(movement);
		entity.add(texture);
		entity.add(transform);
		entity.add(player);
		
		return (entity);
		
	}
	
	public static  Entity createBackground(){
		
		Gdx.app.log("GameScreen", "createBackground");

		Entity entity = new Entity();
		
		BackgroundComponent background = new BackgroundComponent();
		TransformComponent position = new TransformComponent();
		TextureComponent texture = new TextureComponent();
		
		texture.textRegion = AssetLoader.backgroundRegion;
		position.position.set(320,480);
		
		entity.add(background);
		entity.add(position);
		entity.add(texture);
		
		
		return (entity);
	}

	public static Entity createEarth(){
		Gdx.app.log("GameScreen","createEarth");
		Entity entity = new Entity();

		TransformComponent position = new TransformComponent();
		TextureComponent texture = new TextureComponent();

		texture.textRegion = AssetLoader.planetRegion; //AssetLoader.earthRegion;

		position.position.set(200,200);

		entity.add(position);
		entity.add(texture);

		return entity;
	}
	public static Entity createScoreLine(){
		Gdx.app.log("GameScreen","createScoreLine");
		Entity entity = new Entity();

        ScoreLineComponent scoreLine = new ScoreLineComponent();
		TransformComponent position = new TransformComponent();
        TextureComponent texture = new TextureComponent();

        texture.textRegion = AssetLoader.cursorRegion; //AssetLoader.earthRegion;

        position.position.set(155-8,400);
        scoreLine.boundingRectangle.set(155,374,0f,0f);

        entity.add(scoreLine);
		entity.add(position);
        entity.add(texture);

		return entity;
	}

	public static void createAsteroids(final Engine engine){
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    Gdx.app.log("GameWorld:", "createAsteroids");
                    engine.addEntity(createAsteroid());
                }
            }, 0, 0.5f, -2);

        }


	public static Entity createAsteroid(){
		Gdx.app.log("GameScreen","createAsteroid");
		Entity entity = new Entity();

		MovementComponent velocity = new MovementComponent();
		TextureComponent texture = new TextureComponent();
		TransformComponent position = new TransformComponent();
		AsteroidComponent asteroid = new AsteroidComponent();

		texture.textRegion =AssetLoader.asteroidRegion;
		asteroid.velocity=MathUtils.random(100,400);
		asteroid.startX= MathUtils.random(0,320);
		asteroid.startY=480;
		asteroid.endX=asteroid.startX>160?MathUtils.random(0,160):MathUtils.random(160,320);
		asteroid.endY=0;
		velocity.velocity.set(1,1);
		position.position.set(asteroid.startX,asteroid.startY);
		entity.add(position);
		entity.add(asteroid);
		entity.add(velocity);
		entity.add(texture);

		return entity;
	}
	
	
	
	
	

}
