package com.diyojen.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Util {

    private static boolean birdAlive=true;

	public static final Texture createSolidCircleTexture (Color color, int width, int height) {

		Gdx.app.log("util", "createSolidCircleTexture");
		Pixmap map = new Pixmap(width, height, Format.RGBA8888);
		Color alpha = new Color(0, 0, 0, 0);
		map.setColor(alpha);
		map.fill();
		map.setColor(color);
		map.fillCircle(width / 2, height / 2, ((width + height) / 2) / 2);
		Texture texture = new Texture(map);
		map.dispose();
		return texture;
	}
	public static final Texture createScoreLine (Color color, int width, int height) {

		Gdx.app.log("util", "createScoreLine");
		Pixmap map = new Pixmap(width, height, Format.RGBA8888);
		Color alpha = new Color(0, 0, 0, 0);
		map.setColor(alpha);
		map.fill();
		map.setColor(color);
		Texture texture = new Texture(map);
		map.dispose();
		return texture;
	}


    public static void saveScore(int score) {
        Gdx.app.getPreferences("Asteroid").putInteger("HighScore", score).flush(); //HAVE to call flush to persist
    }

    public static int getSavedScore() {
        return Gdx.app.getPreferences("Asteroid").getInteger("HighScore", 0);
    }

    public static boolean getBirdAlive(){
        return birdAlive;
    }
    public static void setBirdAlive(boolean isAlive){
        birdAlive=isAlive;
    }
	
}
