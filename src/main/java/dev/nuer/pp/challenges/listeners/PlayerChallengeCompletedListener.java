package dev.nuer.pp.challenges.listeners;

import dev.nuer.pp.challenges.events.PlayerChallengeCompletionEvent;
import dev.nuer.pp.enable.FileManager;
import dev.nuer.pp.utils.MessageUtil;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerChallengeCompletedListener implements Listener {

    @EventHandler
    public void challengeCompletion(PlayerChallengeCompletionEvent event) {
        if (event.isCancelled()) return;
        event.getChallenge().setComplete(event.getPlayer());
        //Send the player the message
        if (FileManager.get("challenge_config").getBoolean("completion-notification.message.enabled")) {
            MessageUtil.message("challenge_config", "completion-notification.message.text", event.getPlayer(),
                    "{challenge-id}", event.getChallenge().getId(),
                    "{experience-name}", FileManager.get("config").getString("experience-name"));
        }
        //Play a sound
        if (FileManager.get("challenge_config").getBoolean("completion-notification.sound.enabled")) {
            event.getPlayer().playSound(event.getPlayer().getLocation(),
                    Sound.valueOf(FileManager.get("challenge_config").getString("completion-notification.sound.type").toUpperCase()),
                    FileManager.get("challenge_config").getInt("completion-notification.sound.volume"),
                    FileManager.get("challenge_config").getInt("completion-notification.sound.pitch"));
        }
        //Shoot a firework
        if (FileManager.get("challenge_config").getBoolean("completion-notification.firework.enabled")) {
            event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.FIREWORK);
        }
    }
}
