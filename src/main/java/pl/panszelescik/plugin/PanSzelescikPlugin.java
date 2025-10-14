package pl.panszelescik.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import pl.panszelescik.plugin.listeners.EntityTransformListener;

public final class PanSzelescikPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new EntityTransformListener(this), this);
    }
}
