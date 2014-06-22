package se.emillindau.spacecowboy.model.entity.component;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Emil on 2014-05-28.
 * assigned to spacecowboy in se.emillindau.spacecowboy.model.entity.component
 */
public class PhysicsComponent implements IPhysics {

    private Body mBody;

    public PhysicsComponent(Body b) {
        mBody = b;
    }

    @Override
    public Vector2 getPosition() {
        return mBody.getPosition();
    }

    @Override
    public float getRotation() {
        return mBody.getAngle();
    }

    @Override
    public Vector2 getVelocity() {
        return mBody.getLinearVelocity();
    }

    @Override
    public void setPosition(Vector2 newPos) {
    }

    @Override
    public Body getBody() {
        return mBody;
    }
}
