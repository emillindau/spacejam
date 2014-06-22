package se.emillindau.spacecowboy.model.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import se.emillindau.spacecowboy.model.PhysicsBodyFactory;
import se.emillindau.spacecowboy.model.entity.component.IPhysics;

/**
 * Created by Emil on 2014-05-29.
 * assigned to spacecowboy in se.emillindau.spacecowboy.model.entity
 */
public class Planetoid extends GameObject {

    public static final Vector2 SIZE = new Vector2(1f, 1f);

    private IPhysics mPhysicsComponent;
    private GravityField mGravityField;

    public Planetoid(Vector2 spawn) {
        super();

        CircleShape shape = new CircleShape();
        shape.setRadius(SIZE.x);

        PhysicsBodyFactory pbf = new PhysicsBodyFactory.Builder(BodyDef.BodyType.StaticBody, shape).density(1f).sensor(false).
                friction(0f).bullet(false).restitution(0f).fixedRotation(true).userData(this).build();

        mPhysicsComponent = createPhysicsComponent(pbf.getBody());
        mPhysicsComponent.getBody().setTransform(spawn.x, spawn.y, 0f);

        mGravityField = new GravityField(spawn);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    public GravityField getGravityField() {
        return mGravityField;
    }

    public Vector2 getPosition() {
        return mPhysicsComponent.getPosition();
    }

    public Body getBody() {
        return mPhysicsComponent.getBody();
    }
}
