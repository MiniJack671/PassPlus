package dev.nuer.pp.challenges;

import org.bukkit.event.Event;

public abstract class AbstractChallenge {

    private String name;
    private String challengeId;
    private String activeType;
    private Event event;
    private int dataValue;
    private int total;
    private int expPer;

    public AbstractChallenge(String name, String challengeId, String activeType, Event event, int dataValue, int total, int expPer) {
        this.name = name;
        this.challengeId = challengeId;
        this.activeType = activeType;
        this.event = event;
        this.dataValue = dataValue;
        this.total = total;
        this.expPer = expPer;
    }


}