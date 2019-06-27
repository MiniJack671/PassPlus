package dev.nuer.pp.cmd;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.tiers.PlayerTierManager;
import dev.nuer.pp.tiers.exception.BelowMinimumPlayerTierException;
import dev.nuer.pp.tiers.exception.ExceedMaxPlayerTierException;
import dev.nuer.pp.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PassAdminCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender.hasPermission("pass+.admin-cmd.help")) {
                if (sender instanceof Player) {
                    MessageUtil.message("messages", "admin-cmd-help", (Player) sender);
                } else {
                    PassPlus.log.info("The admin help command message can only be view in game");
                }
            } else {
                if (sender instanceof Player) {
                    MessageUtil.message("messages", "no-permission", (Player) sender, "{node}", "pass+.admin-cmd.help");
                }
            }
            // passa tier set nuer 10
        } else if (args.length == 4) {
            if (args[0].equalsIgnoreCase("tier")) {
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
        return true;
    }
}
