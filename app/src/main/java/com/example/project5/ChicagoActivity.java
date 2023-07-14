package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 This class is the activity class that interacts with activity_chicago.xml
 Provides the user with options when placing an order for a Chicago style pizza
 @author Stanley Zheng, Jamie Pan
 */
public class ChicagoActivity extends AppCompatActivity {

    private String size;
    public static double price;
    private String[] pizzaTypes = {"Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own"};
    public static ArrayList<Topping> toppingList;

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final double DELUXE_S = 14.99;
    private static final double DELUXE_M = 16.99;
    private static final double DELUXE_L = 18.99;
    private static final double BBQ_S = 13.99;
    private static final double BBQ_M = 15.99;
    private static final double BBQ_L = 17.99;
    private static final double MEATZZA_S = 15.99;
    private static final double MEATZZA_M = 17.99;
    private static final double MEATZZA_L = 19.99;
    private static final double BUILD_OWN_S = 8.99;
    private static final double BUILD_OWN_M = 10.99;
    private static final double BUILD_OWN_L = 12.99;
    public static final double TOPPING_COST = 1.59;

    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private TextView crust;
    public static TextView pizzaPrice;
    private ImageView imageView;
    public static TextView toppingsTextView;
    private TextView toppingsText;


    /**
     Initializes instance variables and sets up spinner
     @param savedInstanceState saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicago);
        size = "small"; price = 0; toppingList = new ArrayList<Topping>();
        crust = findViewById(R.id.crust);
        pizzaPrice = findViewById(R.id.pizzaPrice);
        imageView = findViewById(R.id.pizzaImage);
        toppingsTextView = findViewById(R.id.toppingsTextView);
        toppingsText = findViewById(R.id.toppingsText);
        setUpSpinner();
    }

    /**
     Method to set up the spinner
     */
    private void setUpSpinner() {
        spinner = findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, pizzaTypes);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinner.getSelectedItem().toString().equals("Deluxe")) {
                    setAsDeluxe(); return;
                }
                if (spinner.getSelectedItem().toString().equals("BBQ Chicken")) {
                    setAsBBQChicken(); return;
                }
                if (spinner.getSelectedItem().toString().equals("Meatzza")) {
                    setAsMeatzza(); return;
                }
                setAsBuildYourOwn();
                Intent intent = new Intent(ChicagoActivity.this, ToppingsSelectedActivity.class);
                intent.putExtra("calling class", "ChicagoActivity");
                startActivity(intent);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    /**
     * Adds the desired pizza created by the user to an order
     * @param view view
     */
    public void onAddToOrderButtonClick(View view) {
        PizzaFactory pizzaFactory = new ChicagoPizza();
        Pizza pizza = pizzaFactory.createDeluxe();
        String flavor = spinner.getSelectedItem().toString();
        if (flavor.equals("Deluxe")) {
            pizza = pizzaFactory.createDeluxe();
        }
        else if (flavor.equals("BBQ Chicken")) {
            pizza = pizzaFactory.createBBQChicken();
        }
        else if (flavor.equals("Meatzza")) {
            pizza = pizzaFactory.createMeatzza();
        }
        else if (flavor.equals("Build Your Own")) {
            pizza = pizzaFactory.createBuildYourOwn();
            for (int i = 0; i < toppingList.size(); i++) {
                pizza.getToppingArrayList().add(toppingList.get(i));
            }
        }
        pizza.setSize(size);
        MainActivity.storeOrder.getOrder().add(pizza);
        Toast.makeText(this, "pizza added to order", Toast.LENGTH_LONG).show();
    }

    /**
     * A method that loads the presets of a Deluxe pizza
     * including price, crust, toppings, and image
     */
    private void setAsDeluxe() {
        crust.setText("Deep dish");
        pizzaPrice.setText("$" + calculatePrice("Deluxe", size));
        imageView.setImageResource(R.drawable.deluxe_pizza);
        toppingsText.setText("");
        toppingsTextView.setText("");
    }

    /**
     * A method that loads the presets of a BBQChicken pizza
     * including price, crust, toppings, and image
     */
    private void setAsBBQChicken() {
        crust.setText("Pan");
        pizzaPrice.setText("$" + calculatePrice("BBQ Chicken", size));
        imageView.setImageResource(R.drawable.bbq_chicken);
        toppingsText.setText("");
        toppingsTextView.setText("");
    }

    /**
     * A method that loads the presets of a Meatzza pizza
     * including price, crust, toppings, and image
     */
    private void setAsMeatzza() {
        crust.setText("Stuffed");
        pizzaPrice.setText("$" + calculatePrice("Meatzza", size));
        imageView.setImageResource(R.drawable.meatzza);
        toppingsText.setText("");
        toppingsTextView.setText("");
    }

    /**
     * A method that loads the presets of a Build your own pizza
     * including price, crust, toppings, and image
     */
    private void setAsBuildYourOwn() {
        toppingList = new ArrayList<Topping>();
        crust.setText("Pan");
        pizzaPrice.setText("$" + calculatePrice("Build Your Own", size));
        imageView.setImageResource(R.drawable.build_your_own_pizza);
        toppingsText.setText("Toppings: ");
        toppingsTextView.setText("");
    }

    /**
     * Caculates the prices of the pizza based on the flavor and size selected
     * @param flavor the flavor selected for the pizza
     * @param size the size selected for the pizza
     * @return returns the price of the pizza selected
     */
    private double calculatePrice(String flavor, String size) {
        if (size.equals("small")) {
            switch(flavor) {
                case "Deluxe": price = DELUXE_S; break;
                case "BBQ Chicken": price = BBQ_S; break;
                case "Meatzza": price = MEATZZA_S; break;
                case "Build Your Own": price = BUILD_OWN_S + (TOPPING_COST * toppingList.size()); break;
            }
            return price;
        }
        if (size.equals("medium")) {
            switch(flavor) {
                case "Deluxe": price = DELUXE_M; break;
                case "BBQ Chicken": price = BBQ_M; break;
                case "Meatzza": price = MEATZZA_M; break;
                case "Build Your Own": price = BUILD_OWN_M + (TOPPING_COST * toppingList.size()); break;
            }
            return price;
        }
        switch(flavor) {
            case "Deluxe": price = DELUXE_L; break;
            case "BBQ Chicken": price = BBQ_L; break;
            case "Meatzza": price = MEATZZA_L; break;
            case "Build Your Own": price = BUILD_OWN_L + (TOPPING_COST * toppingList.size()); break;
        }
        return price;
    }

    /**
     * calculates and set the price of a small pizza
     * @param view view
     */
    public void onSmallButtonClick(View view) {
        size = "small";
        pizzaPrice.setText("$" + df.format(calculatePrice(spinner.getSelectedItem().toString(), size)));
    }

    /**
     * calculates and set the price of a medium pizza
     * @param view view
     */
    public void onMediumButtonClick(View view) {
        size = "medium";
        pizzaPrice.setText("$" + df.format(calculatePrice(spinner.getSelectedItem().toString(), size)));
    }

    /**
     * calculates and set the price of a large pizza
     * @param view view
     */
    public void onLargeButtonClick(View view) {
        size = "large";
        pizzaPrice.setText("$" + df.format(calculatePrice(spinner.getSelectedItem().toString(), size)));
    }

    /**
     * method that shows the added toppings on build your own pizza to the user interface
     * @return build your own pizza's added toppings
     */
    public static String printAddedToppings() {
        String string = "";
        for (int i = 0; i < toppingList.size(); i++) {
            if (i == toppingList.size() - 1) {
                string += toppingList.get(i);
            }
            else {
                string += toppingList.get(i) + ", ";
            }
        }
        return string;
    }
}
