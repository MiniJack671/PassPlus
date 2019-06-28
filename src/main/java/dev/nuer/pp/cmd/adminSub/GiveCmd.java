package dev.nuer.pp.cmd.adminSub;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.tiers.PlayerTierManager;
import dev.nuer.pp.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

public class GiveCmd {

    public static void onCmd(CommandSender sender, String[] args) {
        Player target = null;
        try {
            target = Bukkit.getPlayer(args[1]);
        } catch (Exception e) {
            if (sender instanceof Player) {
                MessageUtil.message("messages", "command-debug", (Player) sender,
                        "{reason}", "The player you tried to give Pass+ cannot be found, please review the players name.");
            } else {
                PassPlus.log.severe("The player you tried to give Pass+ cannot be found, please review the players name.");
            }
        }
        //Add the permission node to the player
        HashMap<UUID, PermissionAttachment> perms = new HashMap<>();
        PermissionAttachment attachment = target.addAttachment(PassPlus.instance);
        perms.put(target.getUniqueId(), attachment);
        PermissionAttachment node = perms.get(target.getUniqueId());
        node.setPermission("pass+.pass", true);
        //Set their new tier
        if (PlayerTierManager.getTier(target) == 0) {
            PlayerTierManager.incrementTier(target, 1);
        }
        if (sender instanceof Player) {
            MessageUtil.message("messages", "give-success", (Player) sender, "{player}", target.getName());
        }
        PassPlus.log.info(target.getName() + " has received a copy of Pass+, the executor was: " + sender.getName() + ".");
    }
}
