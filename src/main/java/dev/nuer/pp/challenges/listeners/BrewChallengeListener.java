package dev.nuer.pp.challenges.listeners;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.challenges.Challenge;
import dev.nuer.pp.challenges.ChallengeType;
import dev.nuer.pp.challenges.ChallengeWeek;
import dev.nuer.pp.enable.WeeklyChallengeManager;
import dev.nuer.pp.playerData.PlayerDataManager;
import dev.nuer.pp.utils.SecondCountdownUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class BrewChallengeListener implements Listener {
    private static HashMap<Location, UUID> playersBrewingLocation = new HashMap<>();

    @EventHandler
    public void playerStartBrew(InventoryClickEvent event) {
        if (event.isCancelled()) return;
        if (SecondCountdownUtil.containsTimer(event.getWhoClicked().getUniqueId())) return;
        try {
            if (!event.getClickedInventory().getType().equals(InventoryType.BREWING)) return;
        } catch (NullPointerException e) {
            return;
        }
        if (!isBrewingChallenge()) return;
        if (event.getClickedInventory().getContents().length <= 0) return;
//        playersBrewingLocation.put(event.getWhoClicked().getEntityId(), event.getWhoClicked().getUniqueId());
        SecondCountdownUtil.addTimer(event.getWhoClicked().getUniqueId(), 60);
    }

    @EventHandler
    public void playerBrew(BrewEvent event) {
        for (ItemStack s : event.getContents()) {
            if (s == null) return;
            PassPlus.log.info(s.getType().toString());
            PassPlus.log.info("" + s.getDurability());
        }
        boolean doGain = false;
        Player player = null;
        if (event.isCancelled()) return;
        for (Location location : playersBrewingLocation.keySet()) {
            if (location.equals(event.getBlock().getLocation())) {
                player = Bukkit.getPlayer(playersBrewingLocation.get(location));
                doGain = true;
            }
        }
        if (!doGain) return;
        //Do experience
        for (ChallengeWeek challengeWeek : WeeklyChallengeManager.weeks.values()) {
            if (challengeWeek.isUnlocked()) {
                for (Challenge challenge : challengeWeek.challenges) {
                    if (challenge.isPremium() && !PlayerDataManager.hasCopy(player)) continue;
                    if (!challenge.getType().equals(ChallengeType.PLAYER_BREW)) continue;
                    if (!challenge.getElement().equalsIgnoreCase("")) {
//                        if (!event.getContents().getContents())
//                        if (!event.getContents().getHolder().getType().toString().equalsIgnoreCase(challenge.getElement()))
//                            continue;
//                        if (challenge.getDataValue() != -1) {
//                            if (event.getContents().get.getDurability() != challenge.getDataValue())
//                                continue;
//                        }
                    }
                    if (challenge.getProgress(player) == -1) continue;
                    challenge.progress(player);
                }
            }
        }
    }

    public boolean isBrewingChallenge() {
        for (ChallengeWeek challengeWeek : WeeklyChallengeManager.weeks.values()) {
            if (challengeWeek.isUnlocked()) {
                for (Challenge challenge : challengeWeek.challenges) {
                    if (challenge.getType().equals(ChallengeType.PLAYER_BREW)) return true;
                }
            }
        }
        return false;
    }

    public static HashMap<Location, UUID> getPlayersBrewingLocation() {
        return playersBrewingLocation;
    }
}