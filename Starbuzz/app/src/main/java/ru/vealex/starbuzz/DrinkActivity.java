package ru.vealex.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ImageView;

import android.widget.TextView;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteException;

import android.database.sqlite.SQLiteOpenHelper;

import android.widget.Toast;

import android.view.View;

import android.widget.CheckBox;

import android.content.ContentValues;

import android.os.AsyncTask;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINKID = "drinkid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        int drinkid = (Integer)getIntent().getExtras().get(EXTRA_DRINKID);
//        Drink drink = Drink.drinks[drinkid];

        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("DRINK",
                    new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                    "_id = ?", new String[] {Integer.toString(drinkid)},
                    null, null, null);

            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3) == 1);

                TextView name = (TextView) findViewById(R.id.name);
                name.setText(/*drink.getName()*/nameText);

                TextView description = (TextView) findViewById(R.id.description);
                description.setText(/*drink.getDescription()*/descriptionText);

                ImageView photo = (ImageView) findViewById(R.id.photo);
                photo.setImageResource(/*drink.getImageResourceId()*/photoId);
                photo.setContentDescription(/*drink.getName()*/nameText);

                CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException ex) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onFavoriteClicked(View view) {
        int drinkId = (Integer)getIntent().getExtras().getInt(EXTRA_DRINKID);

//        CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
//        ContentValues drinkValues = new ContentValues();
//        drinkValues.put("FAVORITE", favorite.isChecked());
//
//        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
//        try {
//            SQLiteDatabase db = starbuzzDatabaseHelper.getWritableDatabase();
//            db.update("DRINK", drinkValues, "_id = ?", new String[] {Integer.toString(drinkId)});
//            db.close();
//        } catch (SQLiteException ex) {
//            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
//            toast.show();
//        }

        new UpdateDrinkTask().execute(drinkId);
    }

    private class UpdateDrinkTask extends AsyncTask<Integer, Void, Boolean> {
        private ContentValues drinkValues;

        @Override
        protected void onPreExecute() {
            CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
            drinkValues = new ContentValues();
            drinkValues.put("FAVORITE", favorite.isChecked());
        }

        @Override
        protected Boolean doInBackground(Integer... drinks) {
            int drinkId = drinks[0];
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(DrinkActivity.this);
            try {
                SQLiteDatabase db = starbuzzDatabaseHelper.getWritableDatabase();
                db.update("DRINK",
                        drinkValues,
                        "_id = ?",
                        new String[] {Integer.toString(drinkId)});
                db.close();
                return true;
            } catch (SQLiteException ex) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (!success) {
                Toast toast = Toast.makeText(DrinkActivity.this, "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}