package se.emillindau.spacecowboy.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import se.emillindau.spacecowboy.model.entity.GameObject;

/**
 * Created by Emil on 2014-05-28.
 * assigned to spacecowboy in se.emillindau.spacecowboy.model
 */
public class PhysicsBodyFactory {
    // Resulting return
    private Body mBody;
    private Fixture mFixture;

    // Internal parameters
    private BodyDef mBodyDef;
    private FixtureDef mFixtureDef;

    // All parameters
    private final BodyDef.BodyType mBodyType;
    private final Shape mShape;
    private final Vector2 mPosition;
    private final boolean mAllowSleep;
    private final float mDensity;
    private final float mFriction;
    private final float mRestitution;
    private final boolean mIsSensor;
    private final boolean mIsBullet;
    private final boolean mFixedRotation;
    private final GameObject mUserData;

    // The actual builder with chaining
    public static class Builder {

        // Required parameters
        private final BodyType mBodyType;
        private final Shape mShape;

        // Optional parameters - initialized to default values
        private Vector2 mPosition = new Vector2(0f, 0f);
        private boolean mAllowSleep = true;
        private float mDensity = 1f;
        private float mFriction = 1f;
        private float mRestitution = 1f;
        private boolean mIsSensor = false;
        private boolean mIsBullet = false;
        private boolean mFixedRotation = true;
        private GameObject mUserData = null;

        public Builder(BodyType type, Shape shape) {
            mBodyType = type;
            mShape = shape;
        }

        // Optional parameters, return this for chaining
        public Builder position(Vector2 val) { mPosition = val; return this; }
        public Builder allowSleep(boolean val) { mAllowSleep = val; return this; }
        public Builder density(float val) { mDensity = val; return this; }
        public Builder friction(float val) { mFriction = val; return this; }
        public Builder restitution(float val) { mRestitution = val; return this; }
        public Builder sensor(boolean val) { mIsSensor = val; return this; }
        public Builder bullet(boolean val) { mIsBullet = val; return this; }
        public Builder fixedRotation(boolean val) { mFixedRotation = val; return this; }
        public Builder userData(GameObject val) { mUserData = val; return this; }

        public PhysicsBodyFactory build() {
            return new PhysicsBodyFactory(this);
        }
    }

    private PhysicsBodyFactory(Builder builder) {

        mBodyType = builder.mBodyType;
        mShape = builder.mShape;
        mPosition = builder.mPosition;
        mAllowSleep = builder.mAllowSleep;
        mDensity = builder.mDensity;
        mFriction = builder.mFriction;
        mRestitution = builder.mRestitution;
        mIsSensor = builder.mIsSensor;
        mIsBullet = builder.mIsBullet;
        mFixedRotation = builder.mFixedRotation;
        mUserData = builder.mUserData;

        // Creation of the actual body
        mBodyDef = new BodyDef();
        mBodyDef.type = mBodyType;
        mBodyDef.allowSleep = mAllowSleep;

        mBody = PhysicsWorld.INSTANCE.world().createBody(mBodyDef);
        mFixtureDef = new FixtureDef();
        mFixtureDef.density = mDensity;
        mFixtureDef.isSensor = mIsSensor;
        mFixtureDef.shape = mShape;
        mFixtureDef.friction = mFriction;
        mFixtureDef.restitution = mRestitution;

        mFixture = mBody.createFixture(mFixtureDef);
        mBody.setBullet(mIsBullet);
        mBody.setFixedRotation(mFixedRotation);

        if(mUserData != null)
            mBody.setUserData(mUserData);

        mBody.setTransform(mPosition, 0);

        mShape.dispose();
    }

    public Body getBody() {
        if(mBody == null)
            throw new NullPointerException("No Body created");
        return mBody;
    }

    public Fixture getFixture() {
        if(mFixture == null)
            throw new NullPointerException("No Fixture created");
        return mFixture;
    }
}
