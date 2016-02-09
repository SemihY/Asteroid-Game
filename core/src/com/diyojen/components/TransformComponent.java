package com.diyojen.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements Component{
	
	public final Vector2 position = new Vector2();
	public final Vector2 scale = new Vector2(1.0f, 1.0f);
	public float rotation = 0.0f;
}
