package dev.nuer.pp.challenges.listeners;

import dev.nuer.pp.challenges.Challenge;
import dev.nuer.pp.challenges.ChallengeWeek;
import dev.nuer.pp.enable.WeeklyChallengeManager;
import dev.nuer.pp.challenges.events.ChallengeWeekUnlockEvent;
import dev.nuer.pp.enable.FileManager;
import dev.nuer.pp.utils.BroadcastUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

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

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        for (ChallengeWeek challengeWeek : WeeklyChallengeManager.weeks.values()) {
            if (!challengeWeek.isLocked()) {
                for (Challenge challenge : challengeWeek.challenges) {
                    if (!challenge.getType().equalsIgnoreCase("player_mine")) continue;
                    if (!event.getBlock().getType().toString().equalsIgnoreCase(challenge.getElement()))
                        continue;
                    if (event.getBlock().getType().getMaxDurability() != challenge.getDataValue()) continue;
                    if (challenge.getProgress(event.getPlayer()) == -1) continue;
                    challenge.progress(event.getPlayer());
                }
            }
        }
    }
}
