package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

/**
 This class is the activity class and interacts with activity_toppings_selected.xml
 Shows the topping names and their images in the recyclerview.
 @author Stanley Zheng, Jamie Pan
 */
public class ToppingsSelectedActivity extends AppCompatActivity {

    private ToppingsAdapter adapter;
    private ArrayList<Topping> toppings = new ArrayList<>();
    private int [] toppingImages = {R.drawable.topping_bbq_chicken, R.drawable.topping_beef, R.drawable.topping_cheddar,
            R.drawable.topping_black_olive, R.drawable.topping_ham, R.drawable.topping_green_pepper, R.drawable.topping_onion,
            R.drawable.topping_pineapple, R.drawable.topping_provolone, R.drawable.topping_sausages, R.drawable.topping_spinach,
            R.drawable.topping_pepperoni, R.drawable.topping_mushroom};


    /**
     Initializes instance variables and sets up recycler view
     @param savedInstanceState saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppings_selected);
        String callingClass = getIntent().getStringExtra("calling class");
        RecyclerView rView = findViewById(R.id.recyclerview);
        setupMenuItems();
        adapter = new ToppingsAdapter(this, toppings, callingClass);
        rView.setAdapter(adapter);
        rView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     Sets up the topping choices into the recycler view.
     */
    private void setupMenuItems() {
        /*
         * Item names are defined in a String array under res/string.xml.
         * Your item names might come from other places, such as an external file, or the database
         * from the backend.
         */
        String [] toppingName = getResources().getStringArray(R.array.toppingNames);
        /* Add the items to the ArrayList.
         * Note that I use hardcoded prices for demo purpose. This should be replace by other
         * data sources.
         */
        for (int i = 0; i < toppingName.length; i++) {
            toppings.add(new Topping(toppingName[i], toppingImages[i]));
        }
    }
}
