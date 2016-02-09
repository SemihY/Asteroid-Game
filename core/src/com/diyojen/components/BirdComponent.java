package com.diyojen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BirdComponent implements Component{
	public static final float MOVE_VELOCITY = 50;
	public static final float WIDTH = 0.8f;
	public static final float HEIGHT = 0.8f;
	public static final Vector2 center = new Vector2(150,240);

    public  int point;
    public Rectangle boundingRectangle = new Rectangle();
    public boolean clockWise=true;
	public boolean isAlive=true;
}
