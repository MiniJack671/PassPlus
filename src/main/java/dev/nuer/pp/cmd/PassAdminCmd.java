package dev.nuer.pp.cmd;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.cmd.adminSub.ExperienceCmd;
import dev.nuer.pp.cmd.adminSub.GiveCmd;
import dev.nuer.pp.cmd.adminSub.ReloadCmd;
import dev.nuer.pp.cmd.adminSub.TierCmd;
import dev.nuer.pp.utils.MessageUtil;
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
        }else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("pass+.admin-cmd.reload")) {
                    if (sender instanceof Player) {
                        MessageUtil.message("messages", "permission-debug", (Player) sender,
                                "{node}", "pass+.admin-cmd.give");
                    }
                    return true;
                }
                ReloadCmd.onCmd(sender, args);
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("g") || args[0].equalsIgnoreCase("give")) {
                if (!sender.hasPermission("pass+.admin-cmd.give")) {
                    if (sender instanceof Player) {
                        MessageUtil.message("messages", "permission-debug", (Player) sender,
                                "{node}", "pass+.admin-cmd.give");
                    }
                    return true;
                }
                GiveCmd.onCmd(sender, args);
            }
        } else if (args.length == 4) {
            if (args[0].equalsIgnoreCase("t") || args[0].equalsIgnoreCase("tier")) {
                if (!sender.hasPermission("pass+.admin-cmd.tier")) {
                    if (sender instanceof Player) {
                        MessageUtil.message("messages", "permission-debug", (Player) sender,
                                "{node}", "pass+.admin-cmd.tier");
                    }
                    return true;
                }
                TierCmd.onCmd(sender, args);
            } else if (args[0].equalsIgnoreCase("e") || args[0].equalsIgnoreCase("exp")) {
                if (!sender.hasPermission("pass+.admin-cmd.exp")) {
                    if (sender instanceof Player) {
                        MessageUtil.message("messages", "permission-debug", (Player) sender,
                                "{node}", "pass+.admin-cmd.exp");
                    }
                    return true;
                }
                ExperienceCmd.onCmd(sender, args);
            }
        } else {
            if (sender instanceof Player) {
                MessageUtil.message("messages", "command-debug", (Player) sender,
                        "{reason}", "The command arguments are not valid, please see the GitHub page for command help");
            } else {
                PassPlus.log.info("The command you entered is invalid.");
            }
        }
        return true;
    }
}