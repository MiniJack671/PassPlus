package dev.nuer.pp.challenges.listeners;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.challenges.Challenge;
import dev.nuer.pp.challenges.ChallengeType;
import dev.nuer.pp.challenges.ChallengeWeek;
import dev.nuer.pp.enable.WeeklyChallengeManager;
import dev.nuer.pp.playerData.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FishChallengeListener implements Listener {
    private Map<UUID, Long> playersFishing = new HashMap<>();
    private Challenge challenge;

    @EventHandler
    public void playerFish(PlayerFishEvent event) {
        if (event.getCaught() == null) return;
        for (ChallengeWeek challengeWeek : WeeklyChallengeManager.weeks.values()) {
            if (challengeWeek.isUnlocked()) {
                for (Challenge challenge : challengeWeek.challenges) {
                    if (challenge.isPremium() && !PlayerDataManager.hasCopy(event.getPlayer())) continue;
                    if (!challenge.getType().equals(ChallengeType.PLAYER_FISH)) continue;
//                    if (!challenge.getElement().equalsIgnoreCase("")) {
//                        if (!event.getHook().getType().toString().equalsIgnoreCase(challenge.getElement()))
//                            continue;
//                        if (challenge.getDataValue() != -1) {
//                            PassPlus.log.info("" + event.getCaught().getType().getTypeId());
//                            if (event.getCaught().getType().getTypeId() != challenge.getDataValue()) continue;
//                        }
//                    }
                    if (challenge.getProgress(event.getPlayer()) == -1) continue;
                    this.challenge = challenge;
                    if (challenge.getElement().equalsIgnoreCase("")) {
                        challenge.progress(event.getPlayer());
                    } else {
                        playersFishing.put(event.getPlayer().getUniqueId(), System.currentTimeMillis());
                    }
                }
            }
        }
    }

    @EventHandler
    public void itemAcquire(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (!playersFishing.containsKey(player.getUniqueId())) return;
        if (this.challenge == null) return;
        if (playersFishing.get(player.getUniqueId()) + 1000 >= System.currentTimeMillis()) {
            if (!event.getItem().getType().toString().equalsIgnoreCase(this.challenge.getElement())) return;
            if (this.challenge.getDataValue() != -1) {
                PassPlus.log.info("" + event.getItem().getType().getTypeId());
                if (event.getItem().getType().getTypeId() != this.challenge.getDataValue()) return;
            }
            this.challenge.progress(player);
            playersFishing.remove(player.getUniqueId());
        }
    }
}
