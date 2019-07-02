package dev.nuer.pp.challenges.listeners;

import dev.nuer.pp.challenges.events.PlayerChallengeCompletionEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerChallengeCompletedListener implements Listener {

    @EventHandler
    public void challengeCompletion(PlayerChallengeCompletionEvent event) {
        if (event.isCancelled()) return;
        event.getChallenge().setComplete(event.getPlayer());
    }
}
