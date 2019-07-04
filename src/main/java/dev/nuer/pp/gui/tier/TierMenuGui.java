package dev.nuer.pp.gui.tier;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.enable.FileManager;
import dev.nuer.pp.experience.PlayerExperienceManager;
import dev.nuer.pp.gui.AbstractGui;
import dev.nuer.pp.gui.menu.MainMenuGui;
import dev.nuer.pp.tiers.PlayerTierManager;
import dev.nuer.pp.utils.ColorUtil;
import dev.nuer.pp.utils.ItemBuilderUtil;
import dev.nuer.pp.utils.ProgressBarUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TierMenuGui extends AbstractGui {

    /**
     * Constructor the create a new Gui
     *
     * @param player Player, the person who open the gui
     */
    public TierMenuGui(Player player, int page) {
        super(FileManager.get("tier_gui").getInt("gui-page-" + page + ".size"),
                ColorUtil.colorize(FileManager.get("tier_gui").getString("gui-page-" + page + ".name")));
        for (int i = 1; i <= FileManager.get("tier_gui").getInt("gui-page-" + page + ".size"); i++) {
            try {
                int id = i;
                setItemInSlot(FileManager.get("tier_gui").getInt("gui-page-" + page + "." + i + ".slot"), buildItem(i, player, page), player1 -> {
                    if (FileManager.get("tier_gui").getBoolean("gui-page-" + page + "." + id + ".exit-button")) {
                        new MainMenuGui(player1).open(player1);
                    }
                    if (FileManager.get("tier_gui").getBoolean("gui-page-" + page + "." + id + ".previous-page.enabled")) {
                        new TierMenuGui(player1, FileManager.get("tier_gui").getInt("gui-page-" + page + "." + id + ".previous-page.page")).open(player1);
                    }
                    if (FileManager.get("tier_gui").getBoolean("gui-page-" + page + "." + id + ".next-page.enabled")) {
                        new TierMenuGui(player1, FileManager.get("tier_gui").getInt("gui-page-" + page + "." + id + ".next-page.page")).open(player1);
                    }
                });
            } catch (Exception e) {
                //Do nothing, item just is not loaded for this slot
            }
        }
    }

    public ItemStack buildItem(int i, Player player, int page) {
        YamlConfiguration config = FileManager.get("tier_gui");
        //Create the item
        ItemBuilderUtil ibu = new ItemBuilderUtil(config.getString("gui-page-" + page + "." + i + ".material"),
                config.getString("gui-page-" + page + "." + i + ".data-value"));
        //Set the item name
        ibu.addName(ColorUtil.colorize(config.getString("gui-page-" + page + "." + i + ".name")));
        //Add the first section of lore, replace the relevant placeholders
        ibu.addLore(config.getStringList("gui-page-" + page + "." + i + ".lore"));
        ibu.replaceLorePlaceholder("{player}", player.getName());
        ibu.replaceLorePlaceholder("{experience-name}", FileManager.get("config").getString("experience-name"));
        ibu.replaceLorePlaceholder("{tier}", String.valueOf(PlayerTierManager.getTier(player)));
        if (completed(config.getInt("gui-page-" + page + "." + i + ".tier"), PlayerTierManager.getTier(player))) {
            ibu.replaceLorePlaceholder("{exp}", "MAX");
        } else {
            ibu.replaceLorePlaceholder("{exp}", PassPlus.numberFormat.format(PlayerExperienceManager.getExperience(player)));
        }
        try {
            ibu.replaceLorePlaceholder("{progress-bar}",
                    ProgressBarUtil.bar(PlayerExperienceManager.getExperience(player),
                            FileManager.get("tier_config").getDouble(i + ".experience-to-level")));
        } catch (Exception e) {
            ibu.replaceLorePlaceholder("{progress-bar}", "debug");
        }
        try {
            ibu.replaceLorePlaceholder("{status}", getStatus(config.getInt("gui-page-" + page + "." + i + ".tier"), PlayerTierManager.getTier(player)));
        } catch (Exception e) {
            ibu.replaceLorePlaceholder("{status}", "debug");
        }
        //Add item enchantments
        ibu.addEnchantments(config.getStringList("gui-page-" + page + "." + i + ".enchantments"));
        //Add item flags
        ibu.addItemFlags(config.getStringList("gui-page-" + page + "." + i + ".item-flags"));
        return ibu.getItem();
    }

    public String getStatus(int i, int tier) {
        if (i - 1 < tier) {
            return ColorUtil.colorize(FileManager.get("tier_gui").getString("status.complete"));
        } else if (i == tier + 1) {
            return ColorUtil.colorize(FileManager.get("tier_gui").getString("status.active"));
        } else {
            return ColorUtil.colorize(FileManager.get("tier_gui").getString("status.locked"));
        }
    }

    public boolean completed(int i, int tier) {
        return i - 1 < tier;
    }
}
