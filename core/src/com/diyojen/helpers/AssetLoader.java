  package com.diyojen.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

  public class AssetLoader {
	public static Texture texture;
    public static Texture bg , earth , asteroid , planet , explosion ,cursor;
	public static TextureRegion backgroundRegion,earthRegion,asteroidRegion , planetRegion , cursorRegion;
    public static TextureRegion bird;

    public static Animation explosionAnim;

    public static BitmapFont font;
    
    public static Preferences prefs;	
    
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
    public static void load() {

        bg  = loadTexture("data/de.png");
        bg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        backgroundRegion = new TextureRegion(bg,0,0,320,480);
        texture = loadTexture("data/enemy.png");
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bird = new TextureRegion(texture);

        asteroid = loadTexture("data/meteor3.png");
        asteroid.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        asteroidRegion = new TextureRegion(asteroid);

        planet  = loadTexture("data/globe2.png");
        planet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        planetRegion = new TextureRegion(planet);

        explosion = loadTexture("data/explosion.png");
        explosionAnim =new Animation(0.025f,new TextureRegion(explosion,0,0,16,16)
                                         ,new TextureRegion(explosion,16,0,16,16)
                                         ,new TextureRegion(explosion,32,0,16,16)
                                         ,new TextureRegion(explosion,48,0,16,16)
                                         ,new TextureRegion(explosion,64,0,16,16));

        cursor = loadTexture("data/cursor.png");
        cursorRegion= new TextureRegion(cursor);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("data/font/kenvector_future.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 25;

        font =  generator.generateFont(parameter);
        generator.dispose();
        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("ZombieBird");

        // Provide default high score of 0
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
        
        

    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        bg.dispose();
        cursor.dispose();
        texture.dispose();
        asteroid.dispose();
        planet.dispose();
        explosion.dispose();
        font.dispose();
    }
    
    // Receives an integer and maps it to the String highScore in prefs
    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    // Retrieves the current high score
    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }
    


}
