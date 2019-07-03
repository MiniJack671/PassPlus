package dev.nuer.pp.experience.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerExperienceGainEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private double experienceGained;
    private double totalExperience;
    private boolean cancel;

    public PlayerExperienceGainEvent(Player player, double experienceGained, double totalExperience) {
        this.player = player;
        this.experienceGained = experienceGained;
        this.totalExperience = totalExperience;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public double getExperienceGained() {
        return experienceGained;
    }

    public double getTotalExperience() {
        return totalExperience;
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
