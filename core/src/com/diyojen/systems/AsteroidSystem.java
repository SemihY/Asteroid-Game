package com.diyojen.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.diyojen.components.*;
import com.diyojen.game.GameWorld;
import com.diyojen.helpers.Util;

/**
 * Created by 5537 on 30.1.2016.
 */
public class AsteroidSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> transformMapper;
    private ComponentMapper<MovementComponent> movementMapper;
    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<AsteroidComponent> asteroidMapper;

    public  AsteroidSystem(){
        super(Family.all(TransformComponent.class, TextureComponent.class, MovementComponent.class, AsteroidComponent.class).get());


        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        movementMapper  = ComponentMapper.getFor(MovementComponent.class);
        textureMapper   = ComponentMapper.getFor(TextureComponent.class);
        asteroidMapper  = ComponentMapper.getFor(AsteroidComponent.class);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        TextureComponent   texture   = textureMapper.get(entity);
        TransformComponent position = transformMapper.get(entity);
        MovementComponent  velocity  = movementMapper.get(entity);
        AsteroidComponent  asteroid  = asteroidMapper.get(entity);


        float distance = (float)Math.sqrt(Math.pow(asteroid.endX-asteroid.startX,2)+Math.pow(asteroid.endY-asteroid.startY,2));
        float directionX = (asteroid.endX-asteroid.startX) / distance;
        float directionY = (asteroid.endY-asteroid.startY) / distance;

        asteroid.boundingCircle.set(position.position.x+9,position.position.y+9,9);

       if(!position.position.equals(new Vector2(asteroid.endX,asteroid.endY))){
           if(Util.getBirdAlive()) {
               position.position.x += directionX * asteroid.velocity * 0.01f;
               position.position.y += directionY * asteroid.velocity * 0.01f;
           }
       }
        else
            getEngine().removeEntity(entity);
    }
}
