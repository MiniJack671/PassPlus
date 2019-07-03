package dev.nuer.pp.challenges.listeners;

import dev.nuer.pp.challenges.events.PlayerChallengeCompletionEvent;
import dev.nuer.pp.enable.FileManager;
import dev.nuer.pp.utils.MessageUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerChallengeCompletedListener implements Listener {

    @EventHandler
    public void challengeCompletion(PlayerChallengeCompletionEvent event) {
        if (event.isCancelled()) return;
        event.getChallenge().setComplete(event.getPlayer());
        MessageUtil.message("messages", "challenge-completion", event.getPlayer(),
                "{challenge-id}", event.getChallenge().getId(),
                "{experience-name}", FileManager.get("config").getString("experience-name"));
    }
}
