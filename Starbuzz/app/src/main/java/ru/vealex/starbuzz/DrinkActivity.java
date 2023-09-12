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
                    new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?", new String[] {Integer.toString(drinkid)},
                    null, null, null);

            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);

                TextView name = (TextView) findViewById(R.id.name);
                name.setText(/*drink.getName()*/nameText);

                TextView description = (TextView) findViewById(R.id.description);
                description.setText(/*drink.getDescription()*/descriptionText);

                ImageView photo = (ImageView) findViewById(R.id.photo);
                photo.setImageResource(/*drink.getImageResourceId()*/photoId);
                photo.setContentDescription(/*drink.getName()*/nameText);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException ex) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}