package dev.nuer.pp.tiers.listeners;

import dev.nuer.pp.tiers.events.PlayerTierUpEvent;
import dev.nuer.pp.utils.MessageUtil;
import dev.nuer.pp.utils.TierCommandUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerTierListener implements Listener {

    @EventHandler
    public void tierUp(PlayerTierUpEvent event) {
        TierCommandUtil.execute("tier_config", event.getNewPlayerTier() + ".rewards.commands", event.getPlayer());
        MessageUtil.message("tier_config", event.getNewPlayerTier() + ".rewards.message", event.getPlayer());
    }
}