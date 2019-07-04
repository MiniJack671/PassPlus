package dev.nuer.pp.api;

import dev.nuer.pp.tiers.PlayerTierManager;
import dev.nuer.pp.tiers.exception.BelowMinimumPlayerTierException;
import dev.nuer.pp.tiers.exception.ExceedMaxPlayerTierException;
import org.bukkit.entity.Player;

public class TierApi {

    public static int getTier(Player player) {
        return PlayerTierManager.getTier(player);
    }

    public static void setTier(Player player, int tier) throws ExceedMaxPlayerTierException, BelowMinimumPlayerTierException {
        PlayerTierManager.setTier(player, tier);
    }

    public static void incrementTier(Player player, int numberOfTiersToIncrement) {
        PlayerTierManager.incrementTier(player, numberOfTiersToIncrement);
    }

    public static void decrementTier(Player player, int numberOfTiersToDecrement) {
        PlayerTierManager.decrementTier(player, numberOfTiersToDecrement);
    }
}