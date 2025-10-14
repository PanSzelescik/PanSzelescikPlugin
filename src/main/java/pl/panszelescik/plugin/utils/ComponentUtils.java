package pl.panszelescik.plugin.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;

public final class ComponentUtils {

    private ComponentUtils() {
    }

    public static Component location(Location location) {
        return Component.text(location.getBlockX(), NamedTextColor.GREEN)
                .append(Component.text(", "))
                .append(Component.text(location.getBlockY(), NamedTextColor.GREEN))
                .append(Component.text(", "))
                .append(Component.text(location.getBlockZ(), NamedTextColor.GREEN));
    }
}
