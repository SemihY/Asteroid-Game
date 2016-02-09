package com.diyojen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by 5537 on 30.1.2016.
 */
public class AsteroidComponent implements Component {
    public  float velocity;
    public static final float WIDTH = 0.8f;
    public static final float HEIGHT = 0.8f;

    public  float startX;
    public  float startY;
    public  float endX;
    public  float endY;

    public Circle boundingCircle=new Circle();


}
