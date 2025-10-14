package pl.panszelescik.plugin;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public final class PanSzelescikBootstrap implements PluginBootstrap {

    @Override
    public void bootstrap(BootstrapContext context) {
        context.getLifecycleManager().registerEventHandler(LifecycleEvents.DATAPACK_DISCOVERY.newHandler(event -> {
            try {
                URI uri = Objects.requireNonNull(getClass().getResource("/panszelescik")).toURI();
                event.registrar().discoverPack(uri, "panszelescik");
            } catch (URISyntaxException | IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    @Override
    public JavaPlugin createPlugin(PluginProviderContext context) {
        return new PanSzelescikPlugin();
    }
}
