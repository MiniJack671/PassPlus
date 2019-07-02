package dev.nuer.pp.challenges;

import dev.nuer.pp.challenges.events.PlayerChallengeCompletionEvent;
import dev.nuer.pp.experience.PlayerExperienceManager;
import dev.nuer.pp.playerData.PlayerDataManager;
import dev.nuer.pp.playerData.utils.PlayerFileUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Challenge {
    private String week;
    private String type;
    private String challengeId;
    private String element;
    private short dataValue;
    private double total;
    private double expPer;

    public Challenge(String week, String type, String challengeId, String element, short dataValue, double total, double expPer) {
        this.week = week;
        this.type = type;
        this.challengeId = challengeId;
        this.element = element;
        this.dataValue = dataValue;
        this.total = total;
        this.expPer = expPer;
    }

    public double getProgress(Player player) {
        return PlayerDataManager.getPlayerFile(player).get().getDouble("pass-challenges.week-" + week + "." + challengeId);
    }

    public void progress(Player player) {
        PlayerFileUtil pfu = PlayerDataManager.getPlayerFile(player);
        if (getProgress(player) + getExpPer() >= total) {
            Bukkit.getPluginManager().callEvent(new PlayerChallengeCompletionEvent(this, player));
        } else {
            pfu.get().set("pass-challenges.week-" + week + "." + challengeId, (getProgress(player) + getExpPer()));
            pfu.save();
        }
        PlayerExperienceManager.incrementExperience(player, getExpPer());
    }

    public void setComplete(Player player) {
        PlayerFileUtil pfu = PlayerDataManager.getPlayerFile(player);
        if (pfu.get().get("pass-challenges.week-" + week + "." + challengeId) == null)
            return;
        pfu.get().set("pass-challenges.week-" + week + "." + challengeId, -1);
        pfu.save();
    }

    public String getId() {
        return challengeId;
    }

    public String getType() {
        return type;
    }

    public String getElement() {
        return element;
    }

    public short getDataValue() {
        return dataValue;
    }

    public double getTotal() {
        return total;
    }

    public double getExpPer() {
        return expPer;
    }
}