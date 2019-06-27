package dev.nuer.pp.tiers.listeners;

import dev.nuer.pp.tiers.events.PlayerTierUpEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerTierListener implements Listener {

    @EventHandler
    public void tierUp(PlayerTierUpEvent event) {
//        event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.FIREWORK);
    }
}