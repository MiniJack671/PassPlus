package dev.nuer.pp.cmd.adminSub;

import dev.nuer.pp.enable.FileManager;
import dev.nuer.pp.experience.PlayerExperienceManager;
import dev.nuer.pp.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExperienceCmd {

    public static void onCmd(CommandSender sender, String[] args) {
        if (args[1].equalsIgnoreCase("set")
                || args[1].equalsIgnoreCase("=")) {
            //Set the players experience
            PlayerExperienceManager.setExperience(Bukkit.getPlayer(args[2]), Double.parseDouble(args[3]));
        } else if (args[1].equalsIgnoreCase("inc")
                || args[1].equalsIgnoreCase("increment")
                || args[1].equalsIgnoreCase("+")) {
            //Increment the players experience
            PlayerExperienceManager.incrementExperience(Bukkit.getPlayer(args[2]), Double.parseDouble(args[3]));
        } else if (args[1].equalsIgnoreCase("dec")
                || args[1].equalsIgnoreCase("decrement")
                || args[1].equalsIgnoreCase("-")) {
            //Decrement the players experience
            PlayerExperienceManager.decrementExperience(Bukkit.getPlayer(args[2]), Double.parseDouble(args[3]));
        }
        //Send the admin a message so that they know their command worked
        if (sender instanceof Player) {
            MessageUtil.message("messages", "admin-cmd-experience-manipulation", (Player) sender,
                    "{player}", Bukkit.getPlayer(args[2]).getName(),
                    "{exp}", String.valueOf(PlayerExperienceManager.getExperience(Bukkit.getPlayer(args[2]))),
                    "{experience-name}", FileManager.get("config").getString("experience-name"));
        }
    }
}
