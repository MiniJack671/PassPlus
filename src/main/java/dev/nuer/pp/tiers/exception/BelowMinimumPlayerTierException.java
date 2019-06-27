package dev.nuer.pp.tiers.exception;

import dev.nuer.pp.PassPlus;

public class BelowMinimumPlayerTierException extends Exception {

    public BelowMinimumPlayerTierException() {
        PassPlus.log.severe("Players must have a positive number of tiers, you cannot set a players tier below 0.");
    }
}