package se.emillindau.spacecowboy.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import se.emillindau.spacecowboy.SpaceCowboy;
import se.emillindau.spacecowboy.util.Resources;

/**
 * Created by Emil on 2014-05-28.
 * assigned to spacecowboy in se.emillindau.spacecowboy.controller
 */
public class SplashScreen implements Screen {

    private SpaceCowboy mGame;

    public SplashScreen(SpaceCowboy game) {
        mGame = game;
        Resources.INSTANCE.loadGameResources();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Resources.INSTANCE.getAssetManager().update()) {
            if(Resources.INSTANCE.setResourceInstances()) {
                mGame.setScreen(new GameScreen(mGame));
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
