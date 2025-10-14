package pl.panszelescik.plugin;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;

public final class PanSzelescikBootstrap implements PluginBootstrap {

    private static final Map<String, String> DATAPACKS = Map.of(
            "panszelescik", "/panszelescik",
            "crafting_re_dye_concrete", "/purpurpacks/crafting/re_dye/concrete",
            "crafting_re_dye_concrete_powder", "/purpurpacks/crafting/re_dye/concrete_powder",
            "crafting_re_dye_glass", "/purpurpacks/crafting/re_dye/glass",
            "crafting_re_dye_glazed_terracotta", "/purpurpacks/crafting/re_dye/glazed_terracotta",
            "crafting_re_dye_terracotta", "/purpurpacks/crafting/re_dye/terracotta",
            "crafting_smelting_blasting_smelts_glass", "/purpurpacks/crafting/smelting/blasting_smelts_glass"
    );

    @Override
    public void bootstrap(BootstrapContext context) {
        context.getLifecycleManager().registerEventHandler(LifecycleEvents.DATAPACK_DISCOVERY.newHandler(event -> {
            try {
                var registrar = event.registrar();
                for (Map.Entry<String, String> entry : DATAPACKS.entrySet()) {
                    URI uri = Objects.requireNonNull(getClass().getResource(entry.getValue())).toURI();
                    registrar.discoverPack(uri, entry.getKey());
                }
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
