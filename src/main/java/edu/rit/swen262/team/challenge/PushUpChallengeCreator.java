package edu.rit.swen262.team.challenge;

import java.util.Date;

public class PushUpChallengeCreator implements ChallengeCreator{
    private static String pushUpChallengeName = "Pushups";
    private static String instructions = "Do 100 Pushups";

    @Override
    public Challenge createChallenge(Date endDate) {
        return new Challenge(pushUpChallengeName, endDate, instructions);
    }
    
}
