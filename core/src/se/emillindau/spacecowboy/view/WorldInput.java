package se.emillindau.spacecowboy.view;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;
import se.emillindau.spacecowboy.model.entity.GameObject;
import se.emillindau.spacecowboy.model.entity.component.IMovement;
import se.emillindau.spacecowboy.model.entity.component.SpaceMovementComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Emil on 2014-05-28.
 * assigned to spacecowboy in se.emillindau.spacecowboy.view
 */
public class WorldInput implements InputProcessor {

    private GameObject mGameObject;
    private Camera mCamera;

    public enum MapKeys {
        LEFT, RIGHT, UP, DOWN, JUMP, SHOOT;
    }

    private static final Map<MapKeys, Boolean> mKeys = new HashMap<MapKeys, Boolean>();
    static {
        mKeys.put(MapKeys.LEFT, false);
        mKeys.put(MapKeys.RIGHT, false);
        mKeys.put(MapKeys.DOWN, false);
        mKeys.put(MapKeys.UP, false);
        mKeys.put(MapKeys.JUMP, false);
        mKeys.put(MapKeys.SHOOT, false);
    }

    public WorldInput(GameObject obj, Camera cam) {
        mGameObject = obj;
        mCamera = cam;
    }

    public void processCurrentInput() {
        if(mKeys.get(MapKeys.RIGHT)) {
            if(!mKeys.get(MapKeys.LEFT)) {
                ((IMovement)mGameObject.getComponent(IMovement.class)).right();
            }
        } else if(mKeys.get(MapKeys.LEFT)) {
            if(!mKeys.get(MapKeys.RIGHT)) {
                ((IMovement)mGameObject.getComponent(IMovement.class)).left();
            }
        } else {
            ((IMovement)mGameObject.getComponent(IMovement.class)).stop();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Keys.LEFT:
            case Keys.A:
                mKeys.get(mKeys.put(MapKeys.LEFT, true));
                break;
            case Keys.RIGHT:
            case Keys.D:
                mKeys.get(mKeys.put(MapKeys.RIGHT, true));
                break;
            case Keys.UP:
            case Keys.W:
                mKeys.get(mKeys.put(MapKeys.UP, true));
                break;
            case Keys.DOWN:
            case Keys.S:
                mKeys.get(mKeys.put(MapKeys.DOWN, true));
                break;
            case Keys.SPACE:
                mKeys.get(mKeys.put(MapKeys.JUMP, true));
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Keys.LEFT:
            case Keys.A:
                mKeys.get(mKeys.put(MapKeys.LEFT, false));
                break;
            case Keys.RIGHT:
            case Keys.D:
                mKeys.get(mKeys.put(MapKeys.RIGHT, false));
                break;
            case Keys.UP:
            case Keys.W:
                mKeys.get(mKeys.put(MapKeys.UP, false));
                break;
            case Keys.DOWN:
            case Keys.S:
                mKeys.get(mKeys.put(MapKeys.DOWN, false));
                break;
            case Keys.SPACE:
                mKeys.get(mKeys.put(MapKeys.JUMP, false));
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 point = mCamera.unproject(new Vector3(screenX, screenY, 0f));
        ((IMovement)mGameObject.getComponent(IMovement.class)).mouseOneClick(point.x, point.y);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
