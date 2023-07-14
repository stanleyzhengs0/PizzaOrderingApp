package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 This class is the activity class and interacts with activity_current_order.xml.
 provides the user with options when managing the current order
 @author Stanley Zheng, Jamie Pan
 */
public class CurrentOrderActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final double SALES_TAX = 0.06625;

    private ListView listview;
    private ArrayAdapter<Pizza> adapter;
    private TextView orderNumber;
    private TextView subtotal;
    private TextView salesTax;
    private TextView total;


    /**
     * Initializes instance variables and sets up list view.
     * @param savedInstanceState saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        orderNumber = findViewById(R.id.orderNumber);
        subtotal = findViewById(R.id.subtotal);
        salesTax = findViewById(R.id.salesTax);
        total = findViewById(R.id.total);
        adapter = new ArrayAdapter<Pizza>(this, android.R.layout.simple_list_item_1, MainActivity.storeOrder.getOrder().getPizzaList());
        listview = findViewById(R.id.listView);
        listview.setOnItemClickListener(this);
        listview.setAdapter(adapter);
        orderNumber.setText(MainActivity.storeOrder.getOrderNumber() + "");
        calculatePrices();
    }

    /**
     * Sets up click method for listview
     * @param adapterView adapter view
     * @param view view
     * @param i index of item clicked
     * @param l long
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("remove pizza?");
        alert.setMessage(adapterView.getAdapter().getItem(i).toString());
        //anonymous inner class to handle the onClick event of YES or NO.
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Pizza pizza = adapter.getItem(i);
                MainActivity.storeOrder.getOrder().remove(pizza);
                listview.setAdapter(adapter);
                Toast.makeText(getApplicationContext(), "YES", Toast.LENGTH_LONG).show();
                calculatePrices();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * clears all the pizzas in the current order
     * @param view view
     */
    public void onClearOrderButtonClick(View view) {
        while (MainActivity.storeOrder.getOrder().getPizzaList().size() != 0) {
            Pizza pizza = MainActivity.storeOrder.getOrder().getPizzaList().get(0);
            MainActivity.storeOrder.getOrder().remove(pizza);
        }
        listview.setAdapter(adapter);
        calculatePrices();
        Toast.makeText(getApplicationContext(), "order cleared", Toast.LENGTH_LONG).show();
    }

    /**
     * adds the order to store order in the main activity class
     * @param view view
     */
    public void onPlaceOrderButtonClick(View view) {
        if (MainActivity.storeOrder.getOrder().getPizzaList().isEmpty()) {
            Toast.makeText(getApplicationContext(), "order is empty", Toast.LENGTH_LONG).show();
            return;
        }
        MainActivity.storeOrder.add(MainActivity.storeOrder.getOrder());
        Toast.makeText(getApplicationContext(), "order placed", Toast.LENGTH_LONG).show();
        orderNumber.setText("");
        adapter = new ArrayAdapter<Pizza>(this, android.R.layout.simple_list_item_1, MainActivity.storeOrder.getOrder().getPizzaList());
        listview.setAdapter(adapter);
        calculatePrices();
    }

    /**
     * calculates the subtotal, sales tax, and total price of the pizzas in the order
     */
    private void calculatePrices() {
        subtotal.setText("$" + df.format(MainActivity.storeOrder.getOrder().getPrice()));
        salesTax.setText("$" + df.format(MainActivity.storeOrder.getOrder().getPrice() * SALES_TAX));
        total.setText("$" + MainActivity.storeOrder.getOrder().getTotalPrice());
    }
}