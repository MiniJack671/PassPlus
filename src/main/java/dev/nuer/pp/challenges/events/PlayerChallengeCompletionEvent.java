package dev.nuer.pp.challenges.events;

import dev.nuer.pp.challenges.Challenge;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerChallengeCompletionEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private Challenge challenge;
    private Player player;
    private boolean cancel;

    public PlayerChallengeCompletionEvent(Challenge challenge, Player player) {
        this.challenge = challenge;
        this.player = player;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
