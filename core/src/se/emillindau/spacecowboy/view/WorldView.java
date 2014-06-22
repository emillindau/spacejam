package se.emillindau.spacecowboy.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import se.emillindau.spacecowboy.model.PhysicsWorld;
import se.emillindau.spacecowboy.model.WorldModel;
import se.emillindau.spacecowboy.view.renderer.PlanetRenderer;
import se.emillindau.spacecowboy.view.renderer.PlayerRenderer;

/**
 * Created by Emil on 2014-05-28.
 * assigned to spacecowboy in se.emillindau.spacecowboy.view
 */
public class WorldView {

    private WorldModel mWorldModel;
    private Camera mCamera;
    private SpriteBatch mSpriteBatch;

    private PlayerRenderer mPlayerRenderer;
    private PlanetRenderer mPlanetRenderer;
    private Box2DDebugRenderer mDebugRenderer;

    public WorldView(WorldModel model, Camera cam) {
        mWorldModel = model;
        mCamera = cam;
        mSpriteBatch = new SpriteBatch();

        mDebugRenderer = new Box2DDebugRenderer();
        mPlayerRenderer = new PlayerRenderer(model.getPlayer(), cam, mSpriteBatch);
        mPlanetRenderer = new PlanetRenderer(model.getPlanets(), cam, mSpriteBatch);
    }

    public void draw(float delta) {
        mSpriteBatch.begin();
        mSpriteBatch.setProjectionMatrix(mCamera.combined);

        mPlayerRenderer.draw(delta);
        mPlanetRenderer.draw(delta);

        mSpriteBatch.end();

        mDebugRenderer.render(PhysicsWorld.INSTANCE.world(), mCamera.combined);
    }

    public void dispose() {
        mSpriteBatch.dispose();
        mDebugRenderer.dispose();
    }
}
