package dev.nuer.pp.experience;

import dev.nuer.pp.experience.events.PlayerExperienceGainEvent;
import dev.nuer.pp.playerData.PlayerDataManager;
import dev.nuer.pp.playerData.utils.PlayerFileUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerExperienceManager {

    public static void incrementExperience(Player player, double experienceToAdd) {
        setExperience(player, getExperience(player) + experienceToAdd);
    }

    public static void decrementExperience(Player player, double experienceToRemove) {
        setExperience(player, getExperience(player) - experienceToRemove);
    }

    public static void setExperience(Player player, double experience) {
        PlayerFileUtil pfu = PlayerDataManager.getPlayerFile(player);
        //If not, then set the players tier
        pfu.get().set("pass-info.experience", experience);
        pfu.save();
        //Then check if they should tier up
        if (experience > getExperience(player)) {
            Bukkit.getPluginManager().callEvent(new PlayerExperienceGainEvent(player, experience - getExperience(player)));
        }
    }

    public static double getExperience(Player player) {
        PlayerFileUtil pfu = PlayerDataManager.getPlayerFile(player);
        return pfu.get().getDouble("pass-info.experience");
    }
}
