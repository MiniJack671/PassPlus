package dev.nuer.pp.playerData.listeners;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.enable.FileManager;
import dev.nuer.pp.experience.PlayerExperienceManager;
import dev.nuer.pp.playerData.PlayerDataManager;
import dev.nuer.pp.tiers.PlayerTierManager;
import dev.nuer.pp.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Class that handles generating and loading a players data file when they join the server
 */
public class DataCreationOnJoin implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        PlayerDataManager.getPlayerFile(event.getPlayer());
        if (!FileManager.get("config").getBoolean("enable-welcome-message")) return;
        Bukkit.getScheduler().runTaskLater(PassPlus.instance, () -> {
            MessageUtil.message("messages", "player-welcome", event.getPlayer(),
                    "{player}", event.getPlayer().getName(),
                    "{tier}", String.valueOf(PlayerTierManager.getTier(event.getPlayer())),
                    "{experience-name}", FileManager.get("config").getString("experience-name"),
                    "{exp}", PassPlus.numberFormat.format(PlayerExperienceManager.getExperience(event.getPlayer())));
        }, 10L);
    }
}
