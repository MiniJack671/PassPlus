package dev.nuer.pp.papi;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.experience.PlayerExperienceManager;
import dev.nuer.pp.playerData.PlayerDataManager;
import dev.nuer.pp.tiers.PlayerTierManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PassPlusExpansion extends PlaceholderExpansion {
    private JavaPlugin instance;

    public PassPlusExpansion(JavaPlugin instance) {
        this.instance = instance;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return instance.getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier() {
        return "pass+";
    }

    @Override
    public String getVersion() {
        return instance.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equalsIgnoreCase("tier")) {
            return PassPlus.numberFormat.format(PlayerTierManager.getTier(player));
        }
        if (identifier.equalsIgnoreCase("exp")) {
            return PassPlus.numberFormat.format(PlayerExperienceManager.getExperience(player));
        }
        if (identifier.equalsIgnoreCase("challenges")) {
            return PassPlus.numberFormat.format(PlayerDataManager.getChallengesCompleted(player));
        }
        if (identifier.equalsIgnoreCase("has_copy")) {
            return String.valueOf(PlayerDataManager.hasCopy(player));
        }
        return "Invalid Placeholder";
    }
}
