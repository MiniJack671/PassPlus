package dev.nuer.pp.challenges;

import dev.nuer.pp.challenges.events.PlayerChallengeCompletionEvent;
import dev.nuer.pp.experience.PlayerExperienceManager;
import dev.nuer.pp.playerData.PlayerDataManager;
import dev.nuer.pp.playerData.utils.PlayerFileUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Challenge class, this is useful for parsing in a challenge from the configuration
 */
public class Challenge {
    //Store the week that the challenge belongs to
    private String week;
    //Store the type of challenge
    private ChallengeType type;
    //Store the challenge id
    private String challengeId;
    //Store the active element for the challenge
    private String element;
    //Store the data value of the active element
    private short dataValue;
    //Store the total amount of experience acquirable
    private double total;
    //Store the experience per unit completed
    private double expPer;
    //Store if the challenge is premium
    private boolean premium;

    /**
     *
     * @param week
     * @param type
     * @param challengeId
     * @param element
     * @param dataValue
     * @param total
     * @param expPer
     * @param premium
     */
    public Challenge(String week, ChallengeType type, String challengeId, String element, short dataValue, double total, double expPer, boolean premium) {
        this.week = week;
        this.type = type;
        this.challengeId = challengeId;
        this.element = element;
        this.dataValue = dataValue;
        this.total = total;
        this.expPer = expPer;
        this.premium = premium;
    }

    public double getProgress(Player player) {
        if (PlayerDataManager.getPlayerFile(player).get().get("pass-challenges.week-" + week + "." + challengeId) == null)
            return 0.0;
        return PlayerDataManager.getPlayerFile(player).get().getDouble("pass-challenges.week-" + week + "." + challengeId);
    }

    public void progress(Player player) {
        PlayerFileUtil pfu = PlayerDataManager.getPlayerFile(player);
        if (getProgress(player) + getExpPer() >= total - 0.1) {
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
        pfu.get().set("pass-info.challenges-completed", pfu.get().getInt("pass-info.challenges-completed") + 1);
        pfu.save();
    }

    public String getId() {
        return challengeId;
    }

    public ChallengeType getType() {
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

    public boolean isPremium() {
        return premium;
    }
}