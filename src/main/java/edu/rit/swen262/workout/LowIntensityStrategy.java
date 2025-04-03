package edu.rit.swen262.workout;

public class LowIntensityStrategy implements IntensityStrategy {
    @Override
    public double caloriesBurned(Workout workout) {
        int duration = workout.getDurationMin();

        double caloriesBurned = duration * 5;

        return caloriesBurned;
    }   
}