package dev.nuer.pp.challenges.listeners;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.challenges.Challenge;
import dev.nuer.pp.challenges.ChallengeWeek;
import dev.nuer.pp.enable.WeeklyChallengeManager;
import dev.nuer.pp.playerData.PlayerDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

/**
 * Listener class for all of the challenges
 */
public class ChallengeListener implements Listener {

    //Player block mine challenge
    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        if (!PlayerDataManager.hasCopy(event.getPlayer())) return;
        for (ChallengeWeek challengeWeek : WeeklyChallengeManager.weeks.values()) {
            if (challengeWeek.isUnlocked()) {
                for (Challenge challenge : challengeWeek.challenges) {
                    if (!challenge.getType().equalsIgnoreCase("player_mine")) continue;
                    if (!challenge.getElement().equalsIgnoreCase("")) {
                        if (!event.getBlock().getType().toString().equalsIgnoreCase(challenge.getElement()))
                            continue;
                        if (challenge.getDataValue() != -1) {
                            if (event.getBlock().getType().getMaxDurability() != challenge.getDataValue())
                                continue;
                        }
                    }
                    if (challenge.getProgress(event.getPlayer()) == -1) continue;
                    challenge.progress(event.getPlayer());
                }
            }
        }
    }

    //Player block place challenge
    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        if (event.isCancelled()) return;
        if (!PlayerDataManager.hasCopy(event.getPlayer())) return;
        for (ChallengeWeek challengeWeek : WeeklyChallengeManager.weeks.values()) {
            if (challengeWeek.isUnlocked()) {
                for (Challenge challenge : challengeWeek.challenges) {
                    if (!challenge.getType().equalsIgnoreCase("player_place")) continue;
                    if (!challenge.getElement().equalsIgnoreCase("")) {
                        if (!event.getBlockPlaced().getType().toString().equalsIgnoreCase(challenge.getElement()))
                            continue;
                        if (challenge.getDataValue() != -1) {
                            if (event.getBlockPlaced().getType().getMaxDurability() != challenge.getDataValue())
                                continue;
                        }
                    }
                    if (challenge.getProgress(event.getPlayer()) == -1) continue;
                    challenge.progress(event.getPlayer());
                }
            }
        }
    }

    //Player kills challenge
    @EventHandler
    public void playerKill(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() == null) return;
        if (!PlayerDataManager.hasCopy(event.getEntity().getKiller())) return;
        for (ChallengeWeek challengeWeek : WeeklyChallengeManager.weeks.values()) {
            if (challengeWeek.isUnlocked()) {
                for (Challenge challenge : challengeWeek.challenges) {
                    if (!challenge.getType().equalsIgnoreCase("player_kills")) continue;
                    if (challenge.getProgress(event.getEntity().getKiller()) == -1) continue;
                    challenge.progress(event.getEntity().getKiller());
                }
            }
        }
    }

    //Player deaths challenge
    @EventHandler
    public void playerDeath(PlayerDeathEvent event) {
        if (!PlayerDataManager.hasCopy(event.getEntity())) return;
        for (ChallengeWeek challengeWeek : WeeklyChallengeManager.weeks.values()) {
            if (challengeWeek.isUnlocked()) {
                for (Challenge challenge : challengeWeek.challenges) {
                    if (!challenge.getType().equalsIgnoreCase("player_deaths")) continue;
                    if (challenge.getProgress(event.getEntity()) == -1) continue;
                    challenge.progress(event.getEntity());
                }
            }
        }
    }

    //Player kill mob challenge
    @EventHandler
    public void mobDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) return;
        if (!PlayerDataManager.hasCopy(event.getEntity().getKiller())) return;
        for (ChallengeWeek challengeWeek : WeeklyChallengeManager.weeks.values()) {
            if (challengeWeek.isUnlocked()) {
                for (Challenge challenge : challengeWeek.challenges) {
                    if (!challenge.getType().equalsIgnoreCase("player_kill_mob")) continue;
                    if (!challenge.getElement().equalsIgnoreCase("")) {
                        if (!event.getEntity().getType().toString().equalsIgnoreCase(challenge.getElement()))
                            continue;
                    }
                    if (challenge.getProgress(event.getEntity().getKiller()) == -1) continue;
                    challenge.progress(event.getEntity().getKiller());
                }
            }
        }
    }

    //Player chat challenge
    @EventHandler
    public void chat(AsyncPlayerChatEvent event) {
        if (event.isCancelled()) return;
        if (!PlayerDataManager.hasCopy(event.getPlayer())) return;
        for (ChallengeWeek challengeWeek : WeeklyChallengeManager.weeks.values()) {
            if (challengeWeek.isUnlocked()) {
                for (Challenge challenge : challengeWeek.challenges) {
                    if (!challenge.getType().equalsIgnoreCase("player_chat")) continue;
                    if (challenge.getProgress(event.getPlayer()) == -1) continue;
                    challenge.progress(event.getPlayer());
                }
            }
        }
    }

    //Player fish challenge
    @EventHandler
    public void playerFish(PlayerFishEvent event) {
        if (event.getCaught() == null) return;
        PassPlus.log.info("reaching -1");
        if (!PlayerDataManager.hasCopy(event.getPlayer())) return;
        PassPlus.log.info("reaching 0");
        for (ChallengeWeek challengeWeek : WeeklyChallengeManager.weeks.values()) {
            PassPlus.log.info("reaching 1");
            if (challengeWeek.isUnlocked()) {
                PassPlus.log.info("reaching 2");
                for (Challenge challenge : challengeWeek.challenges) {
                    if (!challenge.getType().equalsIgnoreCase("player_fish")) continue;
                    PassPlus.log.info("reaching 3");
//                    if (!challenge.getElement().equalsIgnoreCase("")) {
//                        if (!event.getHook().getType().toString().equalsIgnoreCase(challenge.getElement()))
//                            continue;
//                        if (challenge.getDataValue() != -1) {
//                            PassPlus.log.info("" + event.getCaught().getType().getTypeId());
//                            if (event.getCaught().getType().getTypeId() != challenge.getDataValue()) continue;
//                        }
//                    }
                    if (challenge.getProgress(event.getPlayer()) == -1) continue;
                    challenge.progress(event.getPlayer());
                }
            }
        }
    }

    //Player consume food event
    @EventHandler
    public void playerEat(PlayerItemConsumeEvent event) {
        if (event.isCancelled()) return;
        if (!PlayerDataManager.hasCopy(event.getPlayer())) return;
        for (ChallengeWeek challengeWeek : WeeklyChallengeManager.weeks.values()) {
            if (challengeWeek.isUnlocked()) {
                for (Challenge challenge : challengeWeek.challenges) {
                    if (!challenge.getType().equalsIgnoreCase("player_consume")) continue;
                    if (!challenge.getElement().equalsIgnoreCase("")) {
                        if (!event.getItem().getType().toString().equalsIgnoreCase(challenge.getElement()))
                            continue;
                        if (challenge.getDataValue() != -1) {
                            if (event.getItem().getDurability() != challenge.getDataValue())
                                continue;
                        }
                    }
                    if (challenge.getProgress(event.getPlayer()) == -1) continue;
                    challenge.progress(event.getPlayer());
                }
            }
        }
    }
}