package se.emillindau.spacecowboy.model.entity;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import se.emillindau.spacecowboy.model.entity.component.*;

/**
 * Created by Emil on 2014-05-28.
 * assigned to spacecowboy in se.emillindau.spacecowboy.model.entity
 */
public class GameObject implements IUpdateAble {

    public Array<IComponent> mComponents = new Array<>();

    public GameObject() {
    }

    public boolean hasComponent(Class<?> classType) {
        for(IComponent component : mComponents) {
            if(classType.isInstance(component)) return true;
        }
        return false;
    }

    /**
     * Returns a component that either implements a interface or are of the class type that param is.
     * Don't put a generic interface e.g IComponent, then You are a tard.
     *
     * @param componentClass - Class of component
     * @return
     */
    public IComponent getComponent(Class<?> componentClass) {
        for(IComponent component : mComponents) {
            if(componentClass.equals(component.getClass())) {
                return component;
            }

            for(Class classinterface : component.getClass().getInterfaces()) {
                if(classinterface.equals(componentClass))
                    return component;
            }
        }

        return null;
    }

    public void addComponent(IComponent component) {
        if(!hasComponent(component.getClass()))
            mComponents.add(component);
    }

    public IPhysics createPhysicsComponent(Body b) {
        PhysicsComponent pc = new PhysicsComponent(b);
        addComponent(pc);
        return pc;
    }

    public IMovement createControlledComponent(Body b) {
        SpaceMovementComponent cc = new SpaceMovementComponent(b);
        addComponent(cc);
        return cc;
    }

    @Override
    public void update(float delta) {
    }
}
