package dev.nuer.pp.gui.menu;

import dev.nuer.pp.enable.FileManager;
import dev.nuer.pp.experience.PlayerExperienceManager;
import dev.nuer.pp.gui.AbstractGui;
import dev.nuer.pp.gui.tier.TierMenuGui;
import dev.nuer.pp.tiers.PlayerTierManager;
import dev.nuer.pp.utils.ColorUtil;
import dev.nuer.pp.utils.ItemBuilderUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MainMenuGui extends AbstractGui {

    /**
     * Constructor the create a new Gui
     */
    public MainMenuGui(Player player) {
        super(FileManager.get("config").getInt("main-menu.size"),
                ColorUtil.colorize(FileManager.get("config").getString("main-menu.name")));
        for (int i = 0; i < FileManager.get("config").getInt("main-menu.size"); i++) {
            try {
                int id = i;
                setItemInSlot(FileManager.get("config").getInt("main-menu." + i + ".slot"), buildItem(i, player), player1 -> {
                    if (FileManager.get("config").getBoolean("main-menu." + id + ".open-tiers")) {
                        new TierMenuGui(player1).open(player1);
                    }
                    if (FileManager.get("config").getBoolean("main-menu." + id + ".open-challenges")) {

                    }
                    if (FileManager.get("config").getBoolean("main-menu." + id + ".exit-button")) {
                        player1.closeInventory();
                    }
                });
            } catch (Exception e) {
                //Do nothing, item just is not loaded for this slot
            }
        }
    }

    public ItemStack buildItem(int i, Player player) {
        YamlConfiguration config = FileManager.get("config");
        //Create the item
        ItemBuilderUtil ibu = new ItemBuilderUtil(config.getString("main-menu." + i + ".material"),
                config.getString("main-menu." + i + ".data-value"));
        //Set the item name
        ibu.addName(ColorUtil.colorize(config.getString("main-menu." + i + ".name")));
        //Add the first section of lore, replace the relevant placeholders
        ibu.addLore(config.getStringList("main-menu." + i + ".lore"));
        ibu.replaceLorePlaceholder("{player}", player.getName());
        ibu.replaceLorePlaceholder("{experience-name}", config.getString("experience-name"));
        ibu.replaceLorePlaceholder("{tier}", String.valueOf(PlayerTierManager.getTier(player)));
        ibu.replaceLorePlaceholder("{exp}", String.valueOf(PlayerExperienceManager.getExperience(player)));
        //Check to see if the item has status lore
        if (config.getBoolean("main-menu." + i + ".status.add-lore")) {
            if (player.hasPermission(config.getString("main-menu." + i + ".status.node-required"))) {
                ibu.addLore(config.getStringList("main-menu." + i + ".status.unlocked-lore"));
                ibu.replaceLorePlaceholder("{player}", player.getName());
                ibu.replaceLorePlaceholder("{experience-name}", config.getString("experience-name"));
                ibu.replaceLorePlaceholder("{tier}", String.valueOf(PlayerTierManager.getTier(player)));
                ibu.replaceLorePlaceholder("{exp}", String.valueOf(PlayerExperienceManager.getExperience(player)));
            } else {
                ibu.addLore(config.getStringList("main-menu." + i + ".status.locked-lore"));
                ibu.replaceLorePlaceholder("{player}", player.getName());
                ibu.replaceLorePlaceholder("{experience-name}", config.getString("experience-name"));
                ibu.replaceLorePlaceholder("{tier}", String.valueOf(PlayerTierManager.getTier(player)));
                ibu.replaceLorePlaceholder("{exp}", String.valueOf(PlayerExperienceManager.getExperience(player)));
            }
        }
        //Add item enchantments
        ibu.addEnchantments(config.getStringList("main-menu." + i + ".enchantments"));
        //Add item flags
        ibu.addItemFlags(config.getStringList("main-menu." + i + ".item-flags"));
        return ibu.getItem();
    }
}
