package ru.vealex.bitsandpizzas;

import android.os.Bundle;

//import androidx.fragment.app.ListFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

//import android.widget.ArrayAdapter;

import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

public class PizzaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pizzas));
//        setListAdapter(adapter);
//        return super.onCreateView(inflater, container, savedInstanceState);
        RecyclerView pizzaRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_pizza, container, false);

        String[] pizzaNames = new String[Pizza.pizzas.length];
        for (int i = 0; i < pizzaNames.length; i++) {
            pizzaNames[i] = Pizza.pizzas[i].getName();
        }

        int[] pizzaImages = new int[Pizza.pizzas.length];
        for (int i = 0; i < pizzaImages.length; i++) {
            pizzaImages[i] = Pizza.pizzas[i].getImageResourceId();
        }

        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pizzaNames, pizzaImages);
        pizzaRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        pizzaRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onCLick(int position) {
                Intent intent = new Intent(getActivity(), PizzaDetailActivity.class);
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_ID, position);
                getActivity().startActivity(intent);
            }
        });

        return pizzaRecycler;
    }
}