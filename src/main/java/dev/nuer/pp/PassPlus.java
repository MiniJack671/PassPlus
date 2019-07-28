package dev.nuer.pp;

import dev.nuer.pp.cmd.PassAdminCmd;
import dev.nuer.pp.cmd.PassCmd;
import dev.nuer.pp.enable.FileManager;
import dev.nuer.pp.enable.SetupManager;
import dev.nuer.pp.utils.VersionUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.logging.Logger;

/**
 * Main class for the Pass+ plugin, developed by Stephen Goodhill (Nuer)
 */
public final class PassPlus extends JavaPlugin {
    //Store the main instance of the plugin
    public static PassPlus instance;
    //Store the plugins logger provided by Bukkit
    public static Logger log;
    //Static way to format price placeholders
    public static DecimalFormat numberFormat = new DecimalFormat("#,###.##");
    //Store the version string
    public static String version = "0.9.2.1";

    /**
     * Method called on plugin start up
     */
    @Override
    public void onEnable() {
        //Initialise the instance
        instance = this;
        //Initialise the logger
        log = getLogger();
        //Set up plugin files
        SetupManager.setupFiles(new FileManager(instance));
        //Register the plugins commands
        registerCommads();
        //Register the plugins events
        SetupManager.registerEvents(instance);
        //Check that the plugin is in the latest version
        if (FileManager.get("config").getBoolean("update-version-message")) {
            VersionUtil.checkVersion(null);
            getServer().getPluginManager().registerEvents(new VersionUtil(), this);
        }
        //Send a nice message
        log.info("Thank you for using Pass+! If you find any bugs please contact nbdSteve#0583 on discord.");
    }

    /**
     * Method called on plugin shutdown
     */
    @Override
    public void onDisable() {
        //Send a nice message
        log.info("Thank you for using Pass+! If you find any bugs please contact nbdSteve#0583 on discord.");
    }

    /**
     * Registers all of the plugin commands
     */
    private void registerCommads() {
        getCommand("pass-plus").setExecutor(new PassCmd());
        getCommand("pass-plus-admin").setExecutor(new PassAdminCmd());
    }
}
