package dev.nuer.pp.utils;

import dev.nuer.pp.enable.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TierCommandUtil {

    public static void execute(String directory, String path, Player player) {
        for (String command : FileManager.get(directory).getStringList(path)) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", player.getName()));
        }
    }
}
