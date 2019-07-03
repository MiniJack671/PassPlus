package dev.nuer.pp.challenges.listeners;

import dev.nuer.pp.challenges.events.ChallengeWeekUnlockEvent;
import dev.nuer.pp.enable.FileManager;
import dev.nuer.pp.utils.BroadcastUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChallengeWeekUnlockListener implements Listener {

    @EventHandler
    public void unlock(ChallengeWeekUnlockEvent event) {
        if (event.isCancelled()) return;
        //Set the boolean to be unlocked
        event.getChallengeWeek().setUnlocked(true);
        //Do the broadcast
        if (FileManager.get("config").getBoolean("weekly-challenge-unlock-broadcast.enabled")) {
            BroadcastUtil.broadcast("weekly-challenge-unlock-broadcast.message", "{week}", event.getChallengeWeek().getWeek());
        }
    }
}
