package dev.nuer.pp.experience.listeners;

import dev.nuer.pp.enable.FileManager;
import dev.nuer.pp.experience.events.PlayerExperienceGainEvent;
import dev.nuer.pp.tiers.PlayerTierManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ExperienceTierListener implements Listener {

    @EventHandler
    public void experienceGain(PlayerExperienceGainEvent event) {
        if (event.getTotalExperience() >=
                FileManager.get("tier_config").getDouble((PlayerTierManager.getTier(event.getPlayer()) + 1) + ".experience-to-level") - 0.01) {
            PlayerTierManager.incrementTier(event.getPlayer(), 1);
        }
    }
}