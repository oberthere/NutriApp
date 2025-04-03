package edu.rit.swen262.workout;

public class MediumIntensityStrategy implements IntensityStrategy{
    @Override
    public double caloriesBurned(Workout workout) {
        int duration = workout.getDurationMin();

        double caloriesBurned = duration * 7.5;

        return caloriesBurned;
    }   
}