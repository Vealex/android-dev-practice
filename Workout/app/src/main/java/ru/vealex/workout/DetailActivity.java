package ru.vealex.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        WorkoutDetailFragment fragment = (WorkoutDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
        int workoutid = (int)getIntent().getExtras().get(EXTRA_WORKOUT_ID);
        fragment.setWorkout(workoutid);
    }
}