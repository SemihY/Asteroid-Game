package com.diyojen.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.diyojen.components.*;
import com.diyojen.game.Asteroid;
import com.diyojen.game.Screens.GameScreen;
import com.diyojen.helpers.AssetLoader;
import com.diyojen.helpers.Util;


public class RenderingSystem extends IteratingSystem{

	private Sprite sprite;
    private SpriteBatch batch;
	private OrthographicCamera camera;

	private Array<Entity> renderQueue;

    float  rotatedX=0;
    float  rotatedY=0;


    Asteroid game;

    private static int x=0;

    float state;

    static final float PIXELS_TO_METRES = 1.0f / 32.0f;
	
	private ComponentMapper<TextureComponent> textureMapper;
	private ComponentMapper<TransformComponent> transformMapper;
	private ComponentMapper<BirdComponent> birdMapper;
	private ComponentMapper<BackgroundComponent> backgroundMapper;
    private ComponentMapper<AsteroidComponent> asteroidMapper;

    private ComponentMapper<ScoreLineComponent> scoreLineMapper;
	
	public RenderingSystem(Asteroid game) {
		super(Family.all(TransformComponent.class,TextureComponent.class).get());

        this.game=game;

        batch = new SpriteBatch();
        sprite = new Sprite(AssetLoader.bird);

		camera = new OrthographicCamera(320,480);
		camera.position.set(320/2,480/2,0);
		renderQueue = new Array<Entity>();

		textureMapper = ComponentMapper.getFor(TextureComponent.class);
		transformMapper = ComponentMapper.getFor(TransformComponent.class);
		birdMapper = ComponentMapper.getFor(BirdComponent.class);
		backgroundMapper = ComponentMapper.getFor(BackgroundComponent.class);
		asteroidMapper = ComponentMapper.getFor(AsteroidComponent.class);
        scoreLineMapper = ComponentMapper.getFor(ScoreLineComponent.class);
	}

	@Override
	public void update(float deltaTime) {

		super.update(deltaTime);
		state+=Gdx.graphics.getDeltaTime();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Entity entity : renderQueue) {
			TextureComponent texture = textureMapper.get(entity);
			BirdComponent bird = birdMapper.get(entity);
            BackgroundComponent background = backgroundMapper.get(entity);
            AsteroidComponent  asteroid    = asteroidMapper.get(entity);
            ScoreLineComponent scoreLine   = scoreLineMapper.get(entity);
			if (texture.textRegion == null) {
				continue;
			}
			TransformComponent transform = transformMapper.get(entity);

			if(background!=null){                 // Background

               batch.disableBlending();
				batch.draw(texture.textRegion,0,0, transform.position.x, transform.position.y);
			   batch.enableBlending();
            }
            if(background==null && bird ==null && asteroid==null && scoreLine==null){  // Earth
                batch.draw(texture.textRegion,160-100,240-100, transform.position.x, transform.position.y);
            }
            if(scoreLine!=null ){  // scoreLine
                batch.draw(texture.textRegion, transform.position.x, transform.position.y,texture.textRegion.getRegionWidth()*2,texture.textRegion.getRegionHeight()*2);
            }

			if(asteroid!=null ){  // Asteroid
                batch.draw(texture.textRegion, transform.position.x, transform.position.y,18,18);

              //  batch.draw(texture.textRegion, asteroid.boundingCircle.x,asteroid.boundingCircle.y,18,18);
			}

			if(bird!=null){  // Rotate Circle

                Util.setBirdAlive(bird.isAlive);
				float rotateBy= (float) (bird.clockWise?1/deltaTime*(Math.PI/180)/25:(1/deltaTime*(Math.PI/180)/60));
                Gdx.app.log("RenderingSystem","Rotate:"+rotateBy);
                if(bird.isAlive) {
                     rotatedX = (float) (Math.cos(rotateBy) * (transform.position.x - bird.center.x) - Math.sin(rotateBy) * (transform.position.y - bird.center.y) + bird.center.x);
                     rotatedY = (float) (Math.sin(rotateBy) * (transform.position.x - bird.center.x) + Math.cos(rotateBy) * (transform.position.y - bird.center.y) + bird.center.y);
                }
                else{
                    texture.textRegion= AssetLoader.explosionAnim.getKeyFrame(state,true);
                }

//                sprite.setRegion(texture.textRegion,0,0,texture.textRegion.getRegionWidth(),texture.textRegion.getRegionHeight());
                  transform.position.set(rotatedX, rotatedY);
//                sprite.setOrigin(bird.center.x,bird.center.y);
//                sprite.setPosition(transform.position.x,transform.position.y);
//
//               // batch.draw(sprite,transform.position.x,transform.position.y);
//                sprite.draw(batch);
//				Gdx.app.log("RenderingSystem","RotateX:"+rotatedX+" RotateY:"+rotatedY);
                //batch.draw(texture.textRegion,bird.boundingCircle.x,bird.boundingCircle.y,texture.textRegion.getRegionWidth(),texture.textRegion.getRegionHeight());
				batch.draw(texture.textRegion,transform.position.x,transform.position.y,bird.center.x,bird.center.y
                        ,texture.textRegion.getRegionWidth(),texture.textRegion.getRegionHeight(),1,1,(float)(x=(int)rotateBy+x));

                String temp=""+bird.point;
                AssetLoader.font.draw(batch, "" + bird.point, 160-8-temp.length()*5, 480-40);
                if(!bird.isAlive){
                    AssetLoader.font.draw(batch, "GAME OVER", 160-85-8, 480-70);
                    AssetLoader.font.draw(batch, "HIGH SCORE", 160-85-8, 480-100);
                    temp =  ""+ Util.getSavedScore();
                    AssetLoader.font.draw(batch, ""+temp, 160-8-temp.length()*5, 480-130);
                    Timer.instance().clear();
                    getEngine().getSystem(CollisionSystem.class).setProcessing(false);
                    if(Gdx.input.justTouched()){
                        game.setScreen(new GameScreen(game));
                    }
                }

			}

		}

		batch.end();
		renderQueue.clear();
	};
	
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
        // TODO Auto-generated method stub
        renderQueue.add(entity);
    }



}
