package se.emillindau.spacecowboy.model.entity.component;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Emil on 2014-05-29.
 * assigned to spacecowboy in se.emillindau.spacecowboy.model.entity.component
 */
public class SpaceMovementComponent implements IMovement {

    public enum MoveState { LEFT, RIGHT, STOP };
    private MoveState mCurrentMoveState = MoveState.STOP;

    private Body mBody;

    private boolean mMouseOneClicked = false;
    private Vector2 mMouseClickedAt;

    public SpaceMovementComponent(Body body) {
        mBody = body;
        mMouseClickedAt = new Vector2();
    }

    @Override
    public void update(float delta) {

        float desiredVel = 0f;
        switch(getMoveState()) {
            case LEFT:
                // getBody().setLinearVelocity(getVelocity().x - 5f * delta, getVelocity().y);
                desiredVel = Math.max(mBody.getLinearVelocity().x - 0.2f, -7.0f);
                break;
            case RIGHT:
                // getBody().setLinearVelocity(getVelocity().x + 5f * delta, getVelocity().y);
                desiredVel = Math.min(mBody.getLinearVelocity().x + 0.2f, 7.0f);
                break;
            case STOP:
                mBody.setLinearVelocity(0f, mBody.getLinearVelocity().y);
                // desiredVel = getVelocity().x * 0.7f;
                break;
        }
        float velChange = desiredVel - mBody.getLinearVelocity().x;
        float impulse = mBody.getMass() * velChange;

       mBody.applyLinearImpulse(new Vector2(impulse, 0f), mBody.getWorldCenter(), true);

        mMouseOneClicked = false;
    }

    @Override
    public void left() {
        if(mCurrentMoveState == MoveState.RIGHT) {
            mBody.setLinearVelocity(0f, mBody.getLinearVelocity().y);
        }
        mCurrentMoveState = MoveState.LEFT;
    }

    @Override
    public void right() {
        if(mCurrentMoveState == MoveState.LEFT) {
            mBody.setLinearVelocity(0f, mBody.getLinearVelocity().y);
        }
        mCurrentMoveState = MoveState.RIGHT;
    }

    @Override
    public void stop() {
        mCurrentMoveState = MoveState.STOP;
    }

    @Override
    public MoveState getMoveState() {
        return mCurrentMoveState;
    }

    @Override
    public void up() {

    }

    @Override
    public void down() {

    }

    @Override
    public void shoot() {

    }

    @Override
    public void jump() {

    }

    @Override
    public void mouseOneClick(float x, float y) {
        mMouseOneClicked = true;
        mMouseClickedAt.x = x;
        mMouseClickedAt.y = y;
    }

    @Override
    public boolean hasMouseOneClicked() {
        return mMouseOneClicked;
    }

    @Override
    public Vector2 getMouseOneClickPos() {
        return mMouseClickedAt;
    }
}
