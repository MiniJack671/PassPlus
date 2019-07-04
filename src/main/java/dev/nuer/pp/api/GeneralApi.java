package dev.nuer.pp.api;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.playerData.PlayerDataManager;
import dev.nuer.pp.playerData.utils.PlayerFileUtil;
import org.bukkit.entity.Player;

/**
 * Class that contains general API methods, I wasn't sure what to call the class
 */
public class GeneralApi {

    /**
     * Gets the main plugin instance
     *
     * @return PassPlus instance
     */
    public static PassPlus getInstance() {
        return PassPlus.instance;
    }

    /**
     * Returns true if the player has a copy of Pass+
     *
     * @param player Player, the player to query
     * @return boolean
     */
    public static boolean hasPassCopy(Player player) {
        return PlayerDataManager.hasCopy(player);
    }

    /**
     * Gives the player a copy of Pass+
     *
     * @param player Player, the player to give a copy to
     */
    public static void givePassCopy(Player player) {
        PlayerDataManager.giveCopy(player);
    }

    /**
     * Returns that players .yml data file, careful you can corrupt their file using this method incorrectly
     *
     * @param player Player, get the file for this player
     * @return PlayerFileUtil
     */
    public static PlayerFileUtil getPlayerFile(Player player) {
        return PlayerDataManager.getPlayerFile(player);
    }
}