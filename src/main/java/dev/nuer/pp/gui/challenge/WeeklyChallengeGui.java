package dev.nuer.pp.gui.challenge;

import dev.nuer.pp.enable.FileManager;
import dev.nuer.pp.enable.WeeklyChallengeManager;
import dev.nuer.pp.gui.AbstractGui;
import dev.nuer.pp.tiers.PlayerTierManager;
import dev.nuer.pp.utils.ColorUtil;
import dev.nuer.pp.utils.ItemBuilderUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WeeklyChallengeGui extends AbstractGui {

    public WeeklyChallengeGui(String week, Player player) {
        super(FileManager.get("challenges_week_" + week).getInt("gui.size"),
                ColorUtil.colorize(FileManager.get("challenges_week_" + week).getString("gui.name")));
        for (int i = 1; i < FileManager.get("challenges_week_" + week).getInt("gui.size"); i++) {
            try {
                int id = i;
                setItemInSlot(FileManager.get("challenges_week_" + week).getInt("gui." + i + ".slot"), buildItem(i, player, week), player1 -> {
                    if (FileManager.get("challenges_week_" + week).getBoolean("gui." + id + ".exit-button")) {
                        new ChallengeMenuGui(player1).open(player1);
                    }
                });
            } catch (Exception e) {
                //Do nothing, item just is not loaded for this slot
            }
        }
    }

    public ItemStack buildItem(int i, Player player, String week) {
        YamlConfiguration config = FileManager.get("challenges_week_" + week);
        //Create the item
        ItemBuilderUtil ibu = new ItemBuilderUtil(config.getString("gui." + i + ".material"),
                config.getString("gui." + i + ".data-value"));
        //Set the item name
        ibu.addName(ColorUtil.colorize(config.getString("gui." + i + ".name")));
        //Add the first section of lore, replace the relevant placeholders
        ibu.addLore(config.getStringList("gui." + i + ".lore"));
        ibu.replaceLorePlaceholder("{player}", player.getName());
        ibu.replaceLorePlaceholder("{experience-name}", FileManager.get("config").getString("experience-name"));
        ibu.replaceLorePlaceholder("{tier}", String.valueOf(PlayerTierManager.getTier(player)));
        ibu.replaceLorePlaceholder("{status}", getStatus(i, player, config, week));
        //Add item enchantments
        ibu.addEnchantments(config.getStringList("gui." + i + ".enchantments"));
        //Add item flags
        ibu.addItemFlags(config.getStringList("gui." + i + ".item-flags"));
        return ibu.getItem();
    }

    public String getStatus(int i, Player player, YamlConfiguration config, String week) {
        try {
            if (WeeklyChallengeManager.getChallenge(config.getString("gui." + i + ".challenge-id")).getProgress(player) != -1) {
                return ColorUtil.colorize(FileManager.get("challenges_week_" + week).getString("status.active"));
            } else {
                return ColorUtil.colorize(FileManager.get("challenges_week_" + week).getString("status.complete"));
            }
        } catch (Exception e) {
            return "debug";
        }
    }
}
