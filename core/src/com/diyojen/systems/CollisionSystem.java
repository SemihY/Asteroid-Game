package com.diyojen.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Timer;
import com.diyojen.components.AsteroidComponent;
import com.diyojen.components.BirdComponent;
import com.diyojen.components.ScoreLineComponent;
import com.diyojen.components.TextureComponent;
import com.diyojen.helpers.Util;

/**
 * Created by 5537 on 30.1.2016.
 */
public class CollisionSystem extends EntitySystem {

    private Engine engine;


    private ImmutableArray<Entity> asteroids;
    private ImmutableArray<Entity> birds;
    private ImmutableArray<Entity> scoreLines;

    private ComponentMapper<AsteroidComponent> asteroidMapper;
    private ComponentMapper<BirdComponent> birdMapper;
    private ComponentMapper<ScoreLineComponent> scoreLineMapper;
    private ComponentMapper<TextureComponent> textureMapper;

    private  boolean sacma=false;

    public CollisionSystem() {

        asteroidMapper = ComponentMapper.getFor(AsteroidComponent.class);
        birdMapper = ComponentMapper.getFor(BirdComponent.class);
        scoreLineMapper = ComponentMapper.getFor(ScoreLineComponent.class);
        textureMapper = ComponentMapper.getFor(TextureComponent.class);
    }


    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;

        scoreLines = engine.getEntitiesFor(Family.all(ScoreLineComponent.class).get());
        asteroids = engine.getEntitiesFor(Family.all(AsteroidComponent.class).get());
        birds = engine.getEntitiesFor(Family.all(BirdComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {

        for (int i = 0; i < birds.size(); ++i) {
            Entity bird = birds.get(i);

            BirdComponent b = birdMapper.get(bird);
            TextureComponent birdTexture = textureMapper.get(bird);

            for (int j = 0; j < scoreLines.size(); ++j) {
                Entity scoreLine = scoreLines.get(j);

                ScoreLineComponent s = scoreLineMapper.get(scoreLine);


                    if (Intersector.overlaps(s.boundingRectangle,b.boundingRectangle)) {
                        if(!sacma) {
                            b.point++;
                            sacma=true;
                        }
                        else {
                            sacma=false;
                        }

                        break;
                    }

            }


            for (int j = 0; j < asteroids.size(); ++j) {
                Entity asteroid = asteroids.get(j);

                AsteroidComponent a = asteroidMapper.get(asteroid);
                if (Intersector.overlaps(a.boundingCircle,b.boundingRectangle)) {

                    if(Util.getSavedScore()<b.point)
                        Util.saveScore(b.point);
                    b.isAlive=false;

                    break;
                }
            }
        }
    }

}
