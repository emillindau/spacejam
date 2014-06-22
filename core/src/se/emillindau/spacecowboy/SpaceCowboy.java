package se.emillindau.spacecowboy;

import com.badlogic.gdx.Game;
import se.emillindau.spacecowboy.controller.GameScreen;
import se.emillindau.spacecowboy.controller.SplashScreen;

public class SpaceCowboy extends Game {
	
	@Override
	public void create () {
        setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
        super.render();
	}

    @Override
    public void dispose() {
        super.dispose();
    }
}
