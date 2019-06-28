package dev.nuer.pp.cmd.adminSub;

import dev.nuer.pp.tiers.PlayerTierManager;
import dev.nuer.pp.tiers.exception.BelowMinimumPlayerTierException;
import dev.nuer.pp.tiers.exception.ExceedMaxPlayerTierException;
import dev.nuer.pp.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TierCmd {

    public static void onCmd(CommandSender sender, String[] args) {
        if (!sender.hasPermission("pass+.admin-cmd.tier")) {
            if (sender instanceof Player) {
                MessageUtil.message("messages", "permission-debug", (Player) sender,
                        "{node}", "pass+.admin-cmd.tier");
            }
            return;
        }
        if (args[1].equalsIgnoreCase("set")
                || args[1].equalsIgnoreCase("=")) {
            try {
                PlayerTierManager.setTier(Bukkit.getPlayer(args[2]), Integer.parseInt(args[3]));
            } catch (ExceedMaxPlayerTierException | BelowMinimumPlayerTierException e) {
                //Do nothing, exception classes will handle the exception
            }
        } else if (args[1].equalsIgnoreCase("inc")
                || args[1].equalsIgnoreCase("increment")
                || args[1].equalsIgnoreCase("+")) {
            //Increment the players tiers
            PlayerTierManager.incrementTier(Bukkit.getPlayer(args[2]), Integer.parseInt(args[3]));
        } else if (args[1].equalsIgnoreCase("dec")
                || args[1].equalsIgnoreCase("decrement")
                || args[1].equalsIgnoreCase("-")) {
            //Decrement the players tiers
            PlayerTierManager.decrementTier(Bukkit.getPlayer(args[2]), Integer.parseInt(args[3]));
        }
        //Send the admin a message so that they know their command worked
        if (sender instanceof Player) {
            MessageUtil.message("messages", "admin-cmd-tier-manipulation", (Player) sender,
                    "{player}", Bukkit.getPlayer(args[2]).getName(),
                    "{tier}", String.valueOf(PlayerTierManager.getTier(Bukkit.getPlayer(args[2]))));
        }
    }
}
