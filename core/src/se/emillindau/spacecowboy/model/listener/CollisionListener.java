package se.emillindau.spacecowboy.model.listener;

import com.badlogic.gdx.physics.box2d.*;
import se.emillindau.spacecowboy.model.entity.Planetoid;
import se.emillindau.spacecowboy.model.entity.Player;
import se.emillindau.spacecowboy.model.entity.component.PlanetMovementComponent;

/**
 * Created by Emil on 2014-05-31.
 * assigned to spacecowboy in se.emillindau.spacecowboy.model.listener
 */
public class CollisionListener implements ContactListener {

    private Body mBodyA, mBodyB;
    private Fixture mFixA, mFixB;

    @Override
    public void beginContact(Contact contact) {
        mBodyA = contact.getFixtureA().getBody();
        mBodyB = contact.getFixtureB().getBody();

        // Case: Player has touched ground
        if(mBodyA.getUserData() instanceof Player) {
            if(mBodyB.getUserData() instanceof Planetoid) {
                Player p = (Player) mBodyA.getUserData();
                p.changeMovementComponent(new PlanetMovementComponent());
            }
        }

        if(mBodyB.getUserData() instanceof Player) {
            if(mBodyA.getUserData() instanceof Planetoid) {
                Player p = (Player) mBodyB.getUserData();
                p.changeMovementComponent(new PlanetMovementComponent());
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

}
