package dev.nuer.pp.api;

import dev.nuer.pp.experience.PlayerExperienceManager;
import org.bukkit.entity.Player;

/**
 * Class that handles methods for getting, setting and manipulating player experience
 */
public class ExperienceApi {

    /**
     * Gets the experience for the specified player
     *
     * @param player Player, the player to query
     * @return double
     */
    public static double getExperience(Player player) {
        return PlayerExperienceManager.getExperience(player);
    }

    /**
     * Set the players experience to the specified amount
     *
     * @param player     Player, the player to set
     * @param experience double, the experience to set
     */
    public static void setExperience(Player player, double experience) {
        PlayerExperienceManager.setExperience(player, experience);
    }

    /**
     * Increment the players experience by the specified amount
     *
     * @param player          Player, the player being affected
     * @param experienceToAdd double, the experience to add
     */
    public static void incrementExperience(Player player, double experienceToAdd) {
        PlayerExperienceManager.incrementExperience(player, experienceToAdd);
    }

    /**
     * Decrement the players experience by the specified amount
     *
     * @param player             Player, the player being affected
     * @param experienceToRemove double, the experience to remove
     */
    public static void decrementExperience(Player player, double experienceToRemove) {
        PlayerExperienceManager.decrementExperience(player, experienceToRemove);
    }
}