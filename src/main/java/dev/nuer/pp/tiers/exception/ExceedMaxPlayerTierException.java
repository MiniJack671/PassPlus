package dev.nuer.pp.tiers.exception;

import dev.nuer.pp.PassPlus;

public class ExceedMaxPlayerTierException extends Exception {

    public ExceedMaxPlayerTierException() {
        PassPlus.log.severe("You cannot set a players tier higher than the max tier.");
    }
}