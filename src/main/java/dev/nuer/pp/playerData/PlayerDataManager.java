package dev.nuer.pp.playerData;

import dev.nuer.pp.playerData.utils.PlayerFileUtil;
import org.bukkit.entity.Player;

import java.io.File;

public class PlayerDataManager {

    public static PlayerFileUtil getPlayerFile(Player player) {
        return new PlayerFileUtil("player-data" + File.separator + player.getUniqueId().toString() + ".yml");
    }
}
