package se.emillindau.spacecowboy.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import se.emillindau.spacecowboy.SpaceCowboy;

/**
 * Created by Emil on 2014-05-28.
 * assigned to spacecowboy in se.emillindau.spacecowboy.controller
 */
public abstract class BaseScreen implements Screen {

    private SpaceCowboy mGame;

    public BaseScreen(SpaceCowboy game) {
        mGame = game;
    }

    public SpaceCowboy getGame() {
        return mGame;
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw(delta);
    }

    public abstract void update(float delta);
    public abstract void draw(float delta);
}
