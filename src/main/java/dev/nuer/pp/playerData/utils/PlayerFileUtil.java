package dev.nuer.pp.playerData.utils;

import dev.nuer.pp.PassPlus;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Class that handles creating and loading player files
 */
public class PlayerFileUtil {
    //Store the file name string
    private String fileName;
    //Store the player file
    private File file;
    //Store the yaml config
    private YamlConfiguration config;

    public PlayerFileUtil(String fileName) {
        //Set instance variable
        this.fileName = fileName;
        //Get the player file
        file = new File(PassPlus.instance.getDataFolder(), fileName);
        //Load the configuration for the file
        config = YamlConfiguration.loadConfiguration(file);
        //If the file doesn't exist then set the defaults
        if (!file.exists()) {
            setupPlayerFileDefaults(config);
        }
        save();
        reload();
    }

    private void setupPlayerFileDefaults(YamlConfiguration config) {
        //Set defaults for the information about the players tiers and currency
        config.createSection("pass-info");
        config.set("pass-info.tier", 0);
        config.set("pass-info.experience", 0);
        //Set defaults for the information about the players current & completed challenges
        config.createSection("pass-challenges");
        config.set("pass-challenges.active", "[ ]");
        config.set("pass-challenges.complete", "[ ]");
        //Send a nice message
        PassPlus.log.info("Successfully created a new player-data file: " + fileName + ", all defaults have been created.");
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            PassPlus.log.severe("Critical error saving the file: " + fileName + ", please contact nbdSteve#0583 on discord.");
        }
    }

    public void reload() {
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            PassPlus.log.severe("Critical error loading the file: " + fileName + ", please contact nbdSteve#0583 on discord.");
        }
    }

    public YamlConfiguration get() {
        return config;
    }
}
