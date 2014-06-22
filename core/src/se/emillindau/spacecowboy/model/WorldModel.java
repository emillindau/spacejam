package se.emillindau.spacecowboy.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.utils.Array;
import se.emillindau.spacecowboy.model.entity.GameObject;
import se.emillindau.spacecowboy.model.entity.Planetoid;
import se.emillindau.spacecowboy.model.entity.Player;
import se.emillindau.spacecowboy.model.entity.component.IUpdateAble;
import se.emillindau.spacecowboy.model.listener.CollisionListener;

/**
 * Created by Emil on 2014-05-28.
 * assigned to spacecowboy in se.emillindau.spacecowboy.model
 */
public class WorldModel {

    private Array<Body> mBodies;

    private Player mPlayer;
    private Array<Planetoid> mPlanets;

    public WorldModel() {
        PhysicsWorld.INSTANCE.world().clearForces();
        mBodies = new Array<>();
        mPlanets = new Array<>();

        mPlayer = new Player(new Vector2(1f, 1f));
        mPlanets.add(new Planetoid(new Vector2(1f, 1f)));

        PhysicsWorld.INSTANCE.world().setContactListener(new CollisionListener());
    }

    public void update(float delta) {
        PhysicsWorld.INSTANCE.world().step(1/60f, 6, 2);
        PhysicsWorld.INSTANCE.world().clearForces();

        Array<Body> debris = mPlayer.getDebris();
        for (int i = 0; i < debris.size; i++) {
            Vector2 debrisPos = debris.get(i).getWorldCenter();

            for (int j = 0; j < mPlanets.size; j++) {
                float radius = mPlanets.get(j).getGravityField().getGravityFieldRadius();
                Vector2 planetPos = mPlanets.get(j).getBody().getWorldCenter();
                Vector2 planetDis = new Vector2(0f, 0f);

                planetDis.add(debrisPos);
                planetDis.sub(planetPos);

                float finalDistance = planetDis.len();

                if(finalDistance <= radius) {
                    planetDis.x = -planetDis.x;
                    planetDis.y = -planetDis.y;
                    float sum = Math.abs(planetDis.x) + Math.abs(planetDis.y);
                    planetDis.mulAdd(planetDis, (1/sum) * radius/finalDistance);
                    debris.get(i).applyForce(planetDis, debris.get(i).getWorldCenter(), true);

                    // Adding joint
                    /* DistanceJointDef jointDef = new DistanceJointDef();
                    jointDef.initialize(mPlanets.get(j).getBody(), mPlayer.getBody(), mPlanets.get(j).getBody().getWorldCenter(), mPlayer.getBody().getWorldCenter());
                    jointDef.collideConnected = true;
                    PhysicsWorld.INSTANCE.world().createJoint(jointDef);*/
                }
            }
        }

        Vector2 playerPos = mPlayer.getBody().getWorldCenter().cpy();
        for(int i = 0; i < mPlanets.size; i++) {
            float radius = mPlanets.get(i).getGravityField().getGravityFieldRadius();
            Vector2 planetPos = mPlanets.get(i).getBody().getWorldCenter();
            Vector2 planetDis = new Vector2(0f, 0f);

            planetDis.add(playerPos);
            planetDis.sub(planetPos);

            float finalDistance = planetDis.len();

            if(finalDistance <= radius) {

                //in Step() function
                float bodyAngle = mPlayer.getBody().getAngle();
                Vector2 toTarget = mPlanets.get(i).getPosition().sub(mPlayer.getBody().getPosition());
                float desiredAngle = (float) Math.atan2(-toTarget.x, toTarget.y);
                mPlayer.getBody().setTransform(mPlayer.getBody().getPosition(), desiredAngle);
                mPlayer.getBody().setFixedRotation(true);

                planetDis.x = -planetDis.x;
                planetDis.y = -planetDis.y;
                float sum = Math.abs(planetDis.x) + Math.abs(planetDis.y);
                planetDis.mulAdd(planetDis, (1/sum) * radius/finalDistance);
                mPlayer.getBody().applyForce(planetDis, mPlayer.getBody().getWorldCenter(), true);
            }
        }




        // Update the bodies in the world
        PhysicsWorld.INSTANCE.world().getBodies(mBodies);
        for(Body b : mBodies) {
            // Do stuff..
            if(b.getUserData() instanceof GameObject) {
                GameObject gObj = (GameObject) b.getUserData();
                gObj.update(delta);
            }
        }
    }

    public Player getPlayer() {
        return mPlayer;
    }
    public Array<Planetoid> getPlanets() { return mPlanets; }
}
