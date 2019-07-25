package dev.nuer.pp.api;

import dev.nuer.pp.challenges.Challenge;
import dev.nuer.pp.enable.WeeklyChallengeManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Class that contains all of the Challenge methods for the plugin
 */
public class ChallengeApi {

    /**
     * Get a challenge based on its id
     *
     * @param challengeId String, id of the challenge to get
     * @return Challenge
     */
    public static Challenge getChallenge(String challengeId) {
        return WeeklyChallengeManager.getChallenge(challengeId);
    }

    /**
     * Gets the list of challenges for that week
     *
     * @param week int, the week to get
     * @return ArrayList<Challenge>
     */
    public static ArrayList<Challenge> getWeeklyChallenges(int week) {
        return WeeklyChallengeManager.getWeek(week).challenges;
    }

    /**
     * Gets the players progress for the respective challenge based on its id
     *
     * @param challengeId String, id of the challenge to query
     * @param player      Player, the player to query
     * @return double
     */
    public static double getChallengeProgress(String challengeId, Player player) {
        return WeeklyChallengeManager.getChallenge(challengeId).getProgress(player);
    }

    /**
     * Sets the players progress for the respective challenge based on its id
     *
     * @param challengeId String, id of the challenge being affected
     * @param player      Player, the player who's progress is being set
     */
    public static void setChallengeProgress(String challengeId, Player player) {
        WeeklyChallengeManager.getChallenge(challengeId).progress(player);
    }

    /**
     * Sets a challenge complete for the player based on the challenge id
     *
     * @param challengeId String, id of the challenge to complete
     * @param player      Player, the player being affected
     */
    public static void setChallengeComplete(String challengeId, Player player) {
        WeeklyChallengeManager.getChallenge(challengeId).setComplete(player);
    }

    /**
     * Returns true if the challenges for the respective week are unlocked
     *
     * @param week int, the week to query
     * @return boolean
     */
    public static boolean isChallengeWeekUnlocked(int week) {
        return WeeklyChallengeManager.getWeek(week).isUnlocked();
    }

    /**
     * Unlocks the challenges for the specified week
     *
     * @param week Integer, the week to unlock
     */
    public static void unlockChallenges(int week) {
        WeeklyChallengeManager.getWeek(week).setUnlocked(true);
    }

    /**
     * Locks that challenges for the specified week
     *
     * @param week Integer, the week to lock
     */
    public static void lockChallenges(int week) {
        WeeklyChallengeManager.getWeek(week).setUnlocked(false);
    }

    /**
     * Reloads the weekly challenges for that week, good if you made configuration changes
     *
     * @param week Integer, the week to reload
     */
    public static void reloadWeeklyChallenges(int week) {
        WeeklyChallengeManager.getWeek(week).reload();
    }
}