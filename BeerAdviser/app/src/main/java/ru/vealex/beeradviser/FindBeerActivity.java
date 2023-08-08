package ru.vealex.beeradviser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Spinner;

import android.widget.TextView;

import java.util.List;

public class FindBeerActivity extends AppCompatActivity {
    private BeerExpert expert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }

    //Called when the user clicks the button
    public void onClickFindBeer(View view) {
        //Получить ссылку на TextView
        TextView brands = (TextView) findViewById(R.id.brands);
        //Получить ссылку на Spinner
        Spinner color = (Spinner) findViewById(R.id.color);
        //Получить вариант, выбранный в Spinner
        String beerType = String.valueOf(color.getSelectedItem());
        //Получить рекомендации от класса BeerExpert
        List<String> beerList = expert.getBrands(beerType);
        StringBuilder formattedBrand = new StringBuilder();
        for (String brand: beerList) {
            formattedBrand.append(brand).append('\n');
        }
        //Вывести сорта пива
        brands.setText(formattedBrand);
    }
}