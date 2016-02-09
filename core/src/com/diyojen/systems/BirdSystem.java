package com.diyojen.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Timer;
import com.diyojen.components.BirdComponent;
import com.diyojen.components.MovementComponent;
import com.diyojen.components.TransformComponent;

public class BirdSystem extends IteratingSystem{


	private ComponentMapper<TransformComponent> transformMapper;
	private ComponentMapper<MovementComponent> movementMapper;
	private ComponentMapper<BirdComponent> birdMapper;

	private int touched=0;
    private static boolean firstCheck=false;

    private Entity entity;

    public BirdSystem() {
		super(Family.all(BirdComponent.class,TransformComponent.class,MovementComponent.class).get());
		
		birdMapper      = ComponentMapper.getFor(BirdComponent.class);
		transformMapper = ComponentMapper.getFor(TransformComponent.class);
		movementMapper  = ComponentMapper.getFor(MovementComponent.class);

	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
        this.entity=entity;
		BirdComponent      bird      = birdMapper.get(entity);
		TransformComponent transform = transformMapper.get(entity);
		MovementComponent  movement  = movementMapper.get(entity);


		if (Gdx.input.isKeyPressed(Keys.A)) {
            bird.clockWise = false;//velocityX -= bird.MOVE_VELOCITY * deltaTime;
		}

		if (Gdx.input.isKeyPressed(Keys.D)) {
            bird.clockWise = true;//velocityX += bird.MOVE_VELOCITY * deltaTime;
		}
        if (Gdx.input.isKeyPressed(Keys.S) && bird.isAlive==true) {
            Gdx.app.log("BirdSystem","pressed S");
           bird.isAlive=false;
        }

        if (Gdx.input.isKeyPressed(Keys.S) && bird.isAlive==false) {
            bird.isAlive=true;
            Timer.instance().start();
        }


		bird.boundingRectangle.set(transform.position.x,transform.position.y,30,30);

        // PROBLEM
		if(Gdx.input.isTouched()){
                bird.clockWise = false;
		}
        else{
            bird.clockWise = true;

        }
		
		
	}


}
