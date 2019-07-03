package dev.nuer.pp.cmd.adminSub;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.challenges.ChallengeWeek;
import dev.nuer.pp.enable.FileManager;
import dev.nuer.pp.enable.WeeklyChallengeManager;
import dev.nuer.pp.utils.MessageUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCmd {

    public static void onCmd(CommandSender sender, String[] args) {
        FileManager.reload();
        for (ChallengeWeek challengeWeek : WeeklyChallengeManager.weeks.values()) {
            challengeWeek.reload();
        }
        if (sender instanceof Player) {
            MessageUtil.message("messages", "admin-cmd-reload", (Player) sender);
        } else {
            PassPlus.log.info("Successfully reloaded all configuration files.");
        }
    }
}
