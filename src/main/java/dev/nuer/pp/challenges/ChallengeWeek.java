package dev.nuer.pp.challenges;

import dev.nuer.pp.PassPlus;
import dev.nuer.pp.challenges.events.ChallengeWeekUnlockEvent;
import dev.nuer.pp.enable.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class ChallengeWeek {
    //Store a list of the weeks challenges
    public ArrayList<Challenge> challenges;
    //Store if the week is unlocked
    private boolean unlocked;
    //Store the task id
    private BukkitTask counterTaskId;
    //Store the week
    private String week;

    public ChallengeWeek(String week) {
        this.week = week;
        //Init the challenges array for this week
        challenges = new ArrayList<>();
        //Set the boolean
        unlocked = false;
        //Load each challenge for the week into the array
        loadChallenges();
        //Start the countdown
        countdown();
    }

    private void loadChallenges() {
        PassPlus.log.info("Loading challenges for week: " + week);
        for (int i = 1; i <= 100; i++) {
            if (FileManager.get("challenges_week_" + week).getString("challenges." + i + ".challenge-id") == null)
                break;
            YamlConfiguration config = FileManager.get("challenges_week_" + week);
            Challenge challenge = new Challenge(week,
                    config.getString("challenges." + i + ".type"),
                    config.getString("challenges." + i + ".challenge-id"),
                    config.getString("challenges." + i + ".active.element"),
                    Short.parseShort(config.getString("challenges." + i + ".active.data-value")),
                    config.getDouble("challenges." + i + ".completion-requirements.total"),
                    config.getDouble("challenges." + i + ".completion-requirements.experience-per"),
                    config.getBoolean("challenges." + i + ".premium"));
            challenges.add(challenge);
        }
    }

    public void reload() {
        //Load each challenge for the week into the array
        loadChallenges();
        //Start the countdown
        countdown();
    }

    public void countdown() {
        if (FileManager.get("unlock_timers").getInt("timers.week-" + week) <= 0) {
            setUnlocked(true);
            return;
        }
        if (unlocked) {
            return;
        }
        if (!FileManager.get("config").getBoolean("challenge-countdown-enabled")) return;
        //Run the countdown timer
        counterTaskId = Bukkit.getScheduler().runTaskTimer(PassPlus.instance, () -> {
            //Check that the week is still locked
            if (unlocked) {
                //Fire the custom event
                Bukkit.getPluginManager().callEvent(new ChallengeWeekUnlockEvent(this));
                //Cancel the task
                counterTaskId.cancel();
            }
            //Store the time remaining
            int timeRemaining = FileManager.get("unlock_timers").getInt("timers.week-" + week) - 1;
            //Check that the time remaining is greater than 0
            if (timeRemaining >= 0) {
                FileManager.get("unlock_timers").set("timers.week-" + week, timeRemaining);
                FileManager.save("unlock_timers");
            } else {
                //Fire the custom event
                Bukkit.getPluginManager().callEvent(new ChallengeWeekUnlockEvent(this));
                //Cancel the task
                counterTaskId.cancel();
            }
        }, 0L, 20L);
    }

    public String getWeek() {
        return week;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public boolean isUnlocked() {
        return unlocked;
    }
}