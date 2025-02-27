package edu.rit.swen262.workout;

public class MediumIntensityStrategy implements IntensityStrategy{
    
    @Override
    public double calorieBurnAlgorithm(Workout workout) {
        int duration = workout.getDurationMin();

        double caloriesBurned = duration * 7.5;

        return caloriesBurned;
    }   
}
