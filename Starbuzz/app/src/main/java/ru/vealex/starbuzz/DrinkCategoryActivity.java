package ru.vealex.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.ListView;

import android.content.Intent;

public class DrinkCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);
        ArrayAdapter<Drink> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Drink.drinks);
        ListView listDrinks = (ListView) findViewById(R.id.list_drink);
        listDrinks.setAdapter(listAdapter);

        //Создание слушателя
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int)l);
                startActivity(intent);
            }
        };

        //Назначение случшателя для спискового представления
        listDrinks.setOnItemClickListener(itemClickListener);
    }
}