package se.emillindau.spacecowboy.model.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import se.emillindau.spacecowboy.model.PhysicsBodyFactory;
import se.emillindau.spacecowboy.model.entity.component.IPhysics;

/**
 * Created by Emil on 2014-06-11.
 * assigned to spacecowboy in se.emillindau.spacecowboy.model.entity
 */
public class GravityField extends GameObject {

    public static final float RADIUS = 3f;

    private IPhysics mPhysicsComponent;

    public GravityField(Vector2 spawn) {
        CircleShape shape = new CircleShape();
        shape.setRadius(RADIUS);

        PhysicsBodyFactory pbf = new PhysicsBodyFactory.Builder(BodyDef.BodyType.StaticBody, shape).density(0f).sensor(true).
                friction(0f).bullet(false).restitution(0f).fixedRotation(true).userData(this).build();

        mPhysicsComponent = createPhysicsComponent(pbf.getBody());
        mPhysicsComponent.getBody().setTransform(spawn.x, spawn.y, 0f);
    }

    public float getGravityFieldRadius() {
        return RADIUS;
    }

}
