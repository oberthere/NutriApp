package edu.rit.swen262.workout;

import java.util.Date;

public class Workout{
    private int durationMin;
    private IntensityStrategy intensity;
    private Date recordedDate;
    private String workoutName;
    
    public Workout(String workoutName, int durationMin, IntensityStrategy intensity){
        this.workoutName = workoutName;
        this.durationMin = durationMin;
        this.intensity = intensity;
    }

    public IntensityStrategy getCaloriesBurned() {return this.intensity;}

    public Date getRecordedDate() {return this.recordedDate;}

    public int getDurationMin() {return this.durationMin;}

    public IntensityStrategy getIntensity() {return this.intensity;}

    public String getName() {return this.workoutName;}
}