package dev.nuer.pp.api;

import dev.nuer.pp.experience.PlayerExperienceManager;
import org.bukkit.entity.Player;

public class ExperienceApi {

    public static double getExperience(Player player) {
        return PlayerExperienceManager.getExperience(player);
    }

    public static void setExperience(Player player, double experience) {
        PlayerExperienceManager.setExperience(player, experience);
    }

    public static void incrementExperience(Player player, double experienceToAdd) {
        PlayerExperienceManager.incrementExperience(player, experienceToAdd);
    }

    public static void decrementExperience(Player player, double experienceToRemove) {
        PlayerExperienceManager.decrementExperience(player, experienceToRemove);
    }
}