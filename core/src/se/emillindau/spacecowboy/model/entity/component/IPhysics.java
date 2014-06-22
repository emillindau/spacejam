package se.emillindau.spacecowboy.model.entity.component;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Emil on 2014-05-28.
 * assigned to spacecowboy in se.emillindau.spacecowboy.model.entity.component
 */
public interface IPhysics extends IComponent {
    public Vector2 getPosition();
    public Vector2 getVelocity();
    public float getRotation();
    public void setPosition(Vector2 newPos);
    public Body getBody();
}
