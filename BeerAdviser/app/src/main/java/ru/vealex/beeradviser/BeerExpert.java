package ru.vealex.beeradviser;

import java.util.ArrayList;
import java.util.List;

public class BeerExpert {
    List<String> getBrands(String color) {
        List<String> brands = new ArrayList<>();
        switch (color) {
            case "light":
                brands.add("Amstel");
                brands.add("Stella Artos");
                brands.add("Haineken");
                brands.add("Три медведя");
                break;
            case "amber":
                brands.add("Жигулевское 80 год Юбилейное");
                brands.add("Maisels Weisse Original");
                brands.add("Ayinger Urweisse");
                brands.add("Букет Чувашии Породистый крафт IPA");
                break;
            case "brown":
                brands.add("Василеостровская Пивоварня");
                brands.add("\"Newcastle\"");
                break;
            case "dark":
                brands.add("Leffe Brune");
                brands.add("Velkopopovicky Kozel");
                brands.add("Bud");
        }
        return brands;
    }
}
