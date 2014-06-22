package se.emillindau.spacecowboy.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Emil on 2014-05-28.
 * assigned to spacecowboy in se.emillindau.spacecowboy.util
 */
public enum Resources {
    INSTANCE;

    // Actual res
    public Texture player;
    public Texture planet;

    private static AssetManager manager;
    static {
        manager = new AssetManager();
    };

    public AssetManager getAssetManager() {
        return manager;
    }

    public void loadGameResources() {
        manager.load("player.png", Texture.class);
        manager.load("planet.png", Texture.class);
    }

    public boolean setResourceInstances() {
        player = manager.get("player.png", Texture.class);
        planet = manager.get("planet.png", Texture.class);

        return true;
    }
}
