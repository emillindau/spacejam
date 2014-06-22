package se.emillindau.spacecowboy.model.entity.component;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Emil on 2014-05-29.
 * assigned to spacecowboy in se.emillindau.spacecowboy.model.entity.component
 */
public interface IMovement extends IComponent, IUpdateAble {
    public void left();
    public void right();
    public void stop();
    public SpaceMovementComponent.MoveState getMoveState();
    public void update(float delta);
    public void up();
    public void down();
    public void shoot();
    public void jump();

    public void mouseOneClick(float x, float y);
    public boolean hasMouseOneClicked();
    public Vector2 getMouseOneClickPos();
}
