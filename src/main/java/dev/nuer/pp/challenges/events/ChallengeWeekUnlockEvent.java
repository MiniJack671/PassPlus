package dev.nuer.pp.challenges.events;

import dev.nuer.pp.challenges.ChallengeWeek;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChallengeWeekUnlockEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private ChallengeWeek challengeWeek;
    private boolean cancel;


    public ChallengeWeekUnlockEvent(ChallengeWeek challengeWeek) {
        this.challengeWeek = challengeWeek;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public ChallengeWeek getChallengeWeek() {
        return challengeWeek;
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
