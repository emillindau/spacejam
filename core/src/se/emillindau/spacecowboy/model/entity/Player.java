package se.emillindau.spacecowboy.model.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import se.emillindau.spacecowboy.model.PhysicsBodyFactory;
import se.emillindau.spacecowboy.model.entity.component.IMovement;
import se.emillindau.spacecowboy.model.entity.component.IPhysics;

/**
 * Created by Emil on 2014-05-28.
 * assigned to spacecowboy in se.emillindau.spacecowboy.model.entity
 */
public class Player extends GameObject {
    // 0.35f, 0.4f
    public static final Vector2 SIZE = new Vector2(0.56f, 0.7f);

    private IPhysics mPhysicsComponent;
    private IMovement mMovementComponent;

    private Array<Body> mDebris;
    private boolean mChangeMovementComponent = false;

    public Player(Vector2 spawn) {
        super();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(SIZE.x / 2f, SIZE.y / 2f);

        PhysicsBodyFactory pbf = new PhysicsBodyFactory.Builder(BodyDef.BodyType.DynamicBody, shape).density(1f).sensor(false).
                friction(0f).bullet(true).restitution(0.1f).fixedRotation(false).userData(this).build();

        mPhysicsComponent = createPhysicsComponent(pbf.getBody());
        mMovementComponent = createControlledComponent(mPhysicsComponent.getBody());

        mDebris = new Array<>();
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        // Time to change movement
        if(mChangeMovementComponent) {
            System.out.println("TIME TO CHANGE MOVEMENT");
            mChangeMovementComponent = false;
        }

        if(mMovementComponent.hasMouseOneClicked()) {
            addBox(mMovementComponent.getMouseOneClickPos());
        }

        mMovementComponent.update(delta);
    }

    private void addBox(Vector2 atPos) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1f / 4f, 1f / 4f);
        PhysicsBodyFactory pbf = new PhysicsBodyFactory.Builder(BodyDef.BodyType.DynamicBody, shape).density(1f).sensor(false).
                friction(0f).bullet(true).restitution(0.1f).fixedRotation(false).userData(this).build();
        Body debris = pbf.getBody();
        debris.setTransform(atPos, 0f);
        mDebris.add(debris);
    }

    public void changeMovementComponent(IMovement newComponent) {
        mChangeMovementComponent = true;
    }

    public Array<Body> getDebris() {
        return mDebris;
    }

    public Vector2 getPosition() {
        return mPhysicsComponent.getPosition();
    }

    public float getRotation() {
        return mPhysicsComponent.getRotation();
    }

    public Body getBody() {
        return mPhysicsComponent.getBody();
    }
}
