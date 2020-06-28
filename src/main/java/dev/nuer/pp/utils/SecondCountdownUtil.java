package dev.nuer.pp.utils;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.challenges.listeners.BrewChallengeListener;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

public class SecondCountdownUtil {
    private static HashMap<UUID, Integer> timers;

    public static void countdown() {
        timers = new HashMap<>();
        Bukkit.getScheduler().runTaskTimerAsynchronously(PassPlus.instance, () -> {
            for (UUID uuid : timers.keySet()) {
                if (timers.get(uuid) <= 0) {
                    timers.remove(uuid);
                    BrewChallengeListener.getPlayersBrewingLocation().remove(uuid);
                } else {
                    timers.put(uuid, timers.get(uuid) - 1);
                }
            }
        }, 0L, 20L);
    }

    public static void addTimer(UUID uuid, int time) {
        timers.put(uuid, time);
    }

    public static void removeTimer(UUID uuid) {
        timers.remove(uuid);
    }

    public static boolean containsTimer(UUID uuid) {
        return timers.containsKey(uuid);
    }
}
