package se.emillindau.spacecowboy.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Emil on 2014-05-28.
 * assigned to spacecowboy in se.emillindau.spacecowboy.model
 */
public enum PhysicsWorld {
    INSTANCE;

    private static final World world;
    static {
        world = new World(new Vector2(0f, 0f), true);
    }

    public World world() {
        return world;
    }
}
