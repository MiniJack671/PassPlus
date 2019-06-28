package dev.nuer.pp.experience.listeners;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.enable.FileManager;
import dev.nuer.pp.experience.PlayerExperienceManager;
import dev.nuer.pp.experience.events.PlayerExperienceGainEvent;
import dev.nuer.pp.tiers.PlayerTierManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ExperienceTierListener implements Listener {

    @EventHandler
    public void experienceGain(PlayerExperienceGainEvent event) {
        if (PlayerExperienceManager.getExperience(event.getPlayer()) >=
                FileManager.get("tier_config").getDouble((PlayerTierManager.getTier(event.getPlayer()) + 1) + ".experience-to-level") - 0.1) {
            Bukkit.getScheduler().runTaskLater(PassPlus.instance, () -> {
                PlayerTierManager.incrementTier(event.getPlayer(), 1);
            }, 1L);
        }
    }
}