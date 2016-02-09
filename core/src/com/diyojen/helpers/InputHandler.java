package com.diyojen.helpers;

import com.badlogic.gdx.InputProcessor;
import com.diyojen.game.Asteroid;

public class InputHandler implements InputProcessor{

	private Asteroid myWorld;

    // Ask for a reference to the Bird when InputHandler is created.
    public InputHandler(Asteroid myWorld) {
        // myBird now represents the gameWorld's bird.
       this.myWorld = myWorld;
   }
    
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return true;
    }

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
