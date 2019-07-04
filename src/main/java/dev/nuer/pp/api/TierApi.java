package dev.nuer.pp.api;

import dev.nuer.pp.tiers.PlayerTierManager;
import dev.nuer.pp.tiers.exception.BelowMinimumPlayerTierException;
import dev.nuer.pp.tiers.exception.ExceedMaxPlayerTierException;
import org.bukkit.entity.Player;

/**
 * Class that handles all tier manipulation methods
 */
public class TierApi {

    /**
     * Gets the players tier
     *
     * @param player Player, the player to query
     * @return int
     */
    public static int getTier(Player player) {
        return PlayerTierManager.getTier(player);
    }

    /**
     * Sets the players tier to the specified tier
     *
     * @param player Player, the player affected
     * @param tier   int, the tier to set
     * @throws ExceedMaxPlayerTierException
     * @throws BelowMinimumPlayerTierException
     */
    public static void setTier(Player player, int tier) throws ExceedMaxPlayerTierException, BelowMinimumPlayerTierException {
        PlayerTierManager.setTier(player, tier);
    }

    /**
     * Increments the players tier by the specified amount
     *
     * @param player                   Player, the player being affected
     * @param numberOfTiersToIncrement int, the number of tiers to add
     */
    public static void incrementTier(Player player, int numberOfTiersToIncrement) {
        PlayerTierManager.incrementTier(player, numberOfTiersToIncrement);
    }

    /**
     * Decrements the players tier by the specified amount
     *
     * @param player                   Player, the player being affected
     * @param numberOfTiersToDecrement int, the number of tiers to remove
     */
    public static void decrementTier(Player player, int numberOfTiersToDecrement) {
        PlayerTierManager.decrementTier(player, numberOfTiersToDecrement);
    }
}