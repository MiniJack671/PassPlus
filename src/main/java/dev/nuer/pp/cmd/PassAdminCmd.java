package dev.nuer.pp.cmd;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.cmd.adminSub.GiveCmd;
import dev.nuer.pp.cmd.adminSub.TierCmd;
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
                    MessageUtil.message("messages", "permission-debug", (Player) sender, "{node}", "pass+.admin-cmd.help");
                }
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("g") || args[0].equalsIgnoreCase("give")) {
                GiveCmd.onCmd(sender, args);
            }
        } else if (args.length == 4) {
            if (args[0].equalsIgnoreCase("t") || args[0].equalsIgnoreCase("tier")) {
                TierCmd.onCmd(sender, args);
            }
        }
        return true;
    }
}
