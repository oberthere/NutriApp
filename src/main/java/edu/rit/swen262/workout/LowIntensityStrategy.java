package edu.rit.swen262.workout;

public class LowIntensityStrategy implements IntensityStrategy {

    @Override
    public double calorieBurnAlgorithm(Workout workout) {
        int duration = workout.getDurationMin();

        double caloriesBurned = duration * 5;

        return caloriesBurned;
    }   
    
}
