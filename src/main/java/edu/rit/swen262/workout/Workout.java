package edu.rit.swen262.workout;

import java.util.Date;

public class Workout{
    private int durationMin;
    private IntensityStrategy intensity;
    private Date recordedDate;
    
    public Workout(int durationMin, IntensityStrategy intensity){
        
        this.durationMin = durationMin;
        this.intensity = intensity;
    }

    public IntensityStrategy getCaloriesBurned() {
        return intensity;
    }

    public Date getRecordedDate(){
        return recordedDate;
    }

    public int getDurationMin(){
        return durationMin;
    }

    public IntensityStrategy getIntensity(){
        return intensity;
    }
}
