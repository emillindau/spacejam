package se.emillindau.spacecowboy.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import se.emillindau.spacecowboy.model.entity.GameObject;
import se.emillindau.spacecowboy.model.entity.component.PhysicsComponent;

/**
 * Created by Emil on 2014-05-28.
 * assigned to spacecowboy in se.emillindau.spacecowboy.util
 */
public class Camera extends OrthographicCamera {

    public static final float CAMERA_WIDTH = 16f;
    public static final float CAMERA_HEIGHT = 9f;

    private Vector2 mCameraLookAt;
    private GameObject mAttatching;

    public Camera(float width, float height) {
        super(CAMERA_WIDTH, CAMERA_HEIGHT);

        mCameraLookAt = new Vector2(0f, 0f);
        mAttatching = null;
    }

    public boolean hasAttatchedEntity() {
        return mAttatching == null ? false : true;
    }

    public void setLookAt(Vector2 val) {
        mCameraLookAt.x = val.x;
        mCameraLookAt.y = val.y;
    }

    public void attatchToEntity(GameObject obj) {
        mAttatching = obj;
    }

    @Override
    public void update() {
        if(hasAttatchedEntity()) {
            setLookAt(((PhysicsComponent)mAttatching.getComponent(PhysicsComponent.class)).getPosition());
        }

        if(mCameraLookAt != null) {
            position.x = mCameraLookAt.x;
            position.y = mCameraLookAt.y;
        }

        super.update();
    }
}
