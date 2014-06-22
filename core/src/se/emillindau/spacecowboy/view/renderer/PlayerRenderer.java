package se.emillindau.spacecowboy.view.renderer;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import se.emillindau.spacecowboy.model.entity.Player;
import se.emillindau.spacecowboy.util.Resources;
import se.emillindau.spacecowboy.view.Camera;

/**
 * Created by Emil on 2014-05-29.
 * assigned to spacecowboy in se.emillindau.spacecowboy.view
 */
public class PlayerRenderer {

    private Player mPlayer;
    private Sprite mSprite;

    private Camera mCamera;
    private SpriteBatch mBatch;

    public PlayerRenderer(Player player, Camera cam, SpriteBatch batch) {
        mPlayer = player;
        mSprite = new Sprite(Resources.INSTANCE.player);
        mSprite.setSize(Player.SIZE.x, Player.SIZE.y);

        mBatch = batch;
        mCamera = cam;
    }

    public void draw(float delta) {
        mSprite.setPosition(mPlayer.getPosition().x - Player.SIZE.x / 2f, mPlayer.getPosition().y - Player.SIZE.y / 2f);
        mSprite.setOrigin(Player.SIZE.x / 2f, Player.SIZE.y / 2f);
        float rot = MathUtils.radiansToDegrees * mPlayer.getRotation();
        mSprite.setRotation(rot);
        mSprite.draw(mBatch);
    }
}
