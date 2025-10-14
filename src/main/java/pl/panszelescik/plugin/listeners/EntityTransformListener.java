package pl.panszelescik.plugin.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTransformEvent;
import pl.panszelescik.plugin.PanSzelescikPlugin;

public final class EntityTransformListener implements Listener {

    private final PanSzelescikPlugin plugin;

    public EntityTransformListener(PanSzelescikPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityTransform(EntityTransformEvent event) {
        if (event.getTransformReason() == EntityTransformEvent.TransformReason.INFECTION && event.getEntity() instanceof Villager villager) {
            event.getTransformedEntities()
                    .stream()
                    .filter(entity -> entity instanceof ZombieVillager)
                    .map(entity -> (ZombieVillager) entity)
                    .forEach(zombieVillager -> {
                        var message = Component.text("Villager (")
                                .append(Component.text(villager.getProfession().key().asMinimalString(), NamedTextColor.GOLD))
                                .append(Component.text(", level: "))
                                .append(Component.text(villager.getVillagerLevel(), NamedTextColor.AQUA))
                                .append(Component.text(") at: "))
                                .append(Component.text(zombieVillager.getLocation().getBlockX(), NamedTextColor.GREEN))
                                .append(Component.text(", "))
                                .append(Component.text(zombieVillager.getLocation().getBlockY(), NamedTextColor.GREEN))
                                .append(Component.text(", "))
                                .append(Component.text(zombieVillager.getLocation().getBlockZ(), NamedTextColor.GREEN))
                                .append(Component.text(" was infected into a Zombie Villager."));

                        Bukkit.broadcast(message);

                        var consoleMessage = Component.text("[Infection] ").append(message);
                        this.plugin.getComponentLogger().info(consoleMessage);
                    });
        } else if (event.getTransformReason() == EntityTransformEvent.TransformReason.CURED && event.getEntity() instanceof ZombieVillager zombieVillager) {
            event.getTransformedEntities()
                    .stream()
                    .filter(entity -> entity instanceof Villager)
                    .map(entity -> (Villager) entity)
                    .forEach(villager -> {
                        var message = Component.text("Zombie Villager at: ")
                                .append(Component.text(zombieVillager.getLocation().getBlockX(), NamedTextColor.GREEN))
                                .append(Component.text(", "))
                                .append(Component.text(zombieVillager.getLocation().getBlockY(), NamedTextColor.GREEN))
                                .append(Component.text(", "))
                                .append(Component.text(zombieVillager.getLocation().getBlockZ(), NamedTextColor.GREEN))
                                .append(Component.text(" was cured into a Villager ("))
                                .append(Component.text(villager.getProfession().key().asMinimalString(), NamedTextColor.GOLD))
                                .append(Component.text(", level: "))
                                .append(Component.text(villager.getVillagerLevel(), NamedTextColor.AQUA))
                                .append(Component.text(")."));

                        Bukkit.broadcast(message);

                        var consoleMessage = Component.text("[Cure] ").append(message);
                        this.plugin.getComponentLogger().info(consoleMessage);
                    });
        }
    }
}
