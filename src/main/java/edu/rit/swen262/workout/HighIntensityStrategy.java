package edu.rit.swen262.workout;

public class HighIntensityStrategy implements IntensityStrategy{
    
    @Override
    public double calorieBurnAlgorithm(Workout workout) {
        int duration = workout.getDurationMin();

        double caloriesBurned = duration * 10;

        return caloriesBurned;
    }   
}
