package dev.nuer.pp.cmd;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.enable.FileManager;
import dev.nuer.pp.experience.PlayerExperienceManager;
import dev.nuer.pp.gui.menu.MainMenuGui;
import dev.nuer.pp.tiers.PlayerTierManager;
import dev.nuer.pp.utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PassCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            new MainMenuGui((Player) sender).open((Player) sender);
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("t") || args[0].equalsIgnoreCase("tier")) {
                if (sender instanceof Player) {
                    MessageUtil.message("messages", "tier-query", (Player) sender,
                            "{tier}", String.valueOf(PlayerTierManager.getTier((Player) sender)));
                } else {
                    PassPlus.log.info("Only players can view their current tier.");
                }
            }
            if (args[0].equalsIgnoreCase("e") || args[0].equalsIgnoreCase("exp")) {
                if (sender instanceof Player) {
                    MessageUtil.message("messages", "experience-query", (Player) sender,
                            "{exp}", String.valueOf(PlayerExperienceManager.getExperience((Player) sender)),
                            "{experience-name}", FileManager.get("config").getString("experience-name"));
                } else {
                    PassPlus.log.info("Only players can view their current experience.");
                }
            }
        }
        return true;
    }
}