package se.emillindau.spacecowboy.view.renderer;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import se.emillindau.spacecowboy.model.entity.Planetoid;
import se.emillindau.spacecowboy.util.Resources;
import se.emillindau.spacecowboy.view.Camera;

/**
 * Created by Emil on 2014-05-29.
 * assigned to spacecowboy in se.emillindau.spacecowboy.view.renderer
 */
public class PlanetRenderer {

    private Array<Planetoid> mPlanets;
    private Sprite mSprite;

    private Camera mCamera;
    private SpriteBatch mBatch;

    public PlanetRenderer(Array<Planetoid> planets, Camera cam, SpriteBatch batch) {
        mPlanets = planets;
        mSprite = new Sprite(Resources.INSTANCE.planet);
        mSprite.setSize(Planetoid.SIZE.x * 2f, Planetoid.SIZE.y * 2f);

        mBatch = batch;
        mCamera = cam;
    }

    public void draw(float delta) {
        for (Planetoid planet : mPlanets) {
            mSprite.setPosition(planet.getPosition().x - Planetoid.SIZE.x, planet.getPosition().y - Planetoid.SIZE.y );
            mSprite.draw(mBatch);
        }
    }
}
