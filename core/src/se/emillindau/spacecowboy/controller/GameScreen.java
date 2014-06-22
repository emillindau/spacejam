package se.emillindau.spacecowboy.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import se.emillindau.spacecowboy.SpaceCowboy;
import se.emillindau.spacecowboy.model.WorldModel;
import se.emillindau.spacecowboy.view.Camera;
import se.emillindau.spacecowboy.view.WorldInput;
import se.emillindau.spacecowboy.view.WorldView;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Emil on 2014-05-28.
 * assigned to spacecowboy in se.emillindau.spacecowboy.controller
 */
public class GameScreen extends BaseScreen {

    private Camera mCamera;

    private WorldInput mWorldInput;
    private WorldModel mWorldModel;
    private WorldView mWorldView;

    public GameScreen(SpaceCowboy game) {
        super(game);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        mCamera = new Camera(w, h);
        mCamera.translate(Camera.CAMERA_WIDTH / 2f, Camera.CAMERA_HEIGHT / 2f);
        mCamera.update();

        mWorldModel = new WorldModel();
        mWorldInput = new WorldInput(mWorldModel.getPlayer(), mCamera);
        mWorldView = new WorldView(mWorldModel, mCamera);

        mCamera.attatchToEntity(mWorldModel.getPlayer());
    }

    @Override
    public void update(float delta) {
        mWorldInput.processCurrentInput();
        mWorldModel.update(delta);
        mCamera.update();
    }

    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        mWorldView.draw(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(mWorldInput);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        mWorldView.dispose();
    }
}
