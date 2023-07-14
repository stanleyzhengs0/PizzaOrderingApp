package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 This class is the activity class and interacts with activity_store_orders.xml
 provides the user with options to cancel orders
 @author Stanley Zheng, Jamie Pan
 */
public class StoreOrdersActivity extends AppCompatActivity {

    private ArrayList<String> orderNumbersList;

    private Spinner spinner;
    private ArrayAdapter<String> adapterSpinner;
    private ListView listview;
    private ArrayAdapter<Pizza> adapterListView;
    private TextView total;

    /**
     * Initializes instance variables and sets up spinner and list view
     * @param savedInstanceState saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        total = findViewById(R.id.total1);
        orderNumbersList = new ArrayList<String>();
        for (int i = 0; i < MainActivity.storeOrder.getOrderList().size(); i++) {
            orderNumbersList.add(Integer.toString(MainActivity.storeOrder.getOrderList().get(i).getOrderNum()));
        }
        setUpSpinner();
        listview = findViewById(R.id.listView1);
    }

    /**
     Method to set up the spinner
     */
    private void setUpSpinner() {
        spinner = findViewById(R.id.spinner2);
        adapterSpinner = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, orderNumbersList);
        spinner.setAdapter(adapterSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int number = Integer.parseInt(spinner.getSelectedItem().toString());
                int index = findOrderIndex(number);
                adapterListView = new ArrayAdapter<Pizza>(getApplicationContext(), android.R.layout.simple_list_item_1, MainActivity.storeOrder.getOrderList().get(index).getPizzaList());
                listview.setAdapter(adapterListView);
                total.setText("$" + MainActivity.storeOrder.getOrderList().get(index).getTotalPrice());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    /**
     * find the index of the order in a list
     * @param number order number
     * @return returns index of the order
     */
    private int findOrderIndex(int number) {
        for (int i = 0; i < MainActivity.storeOrder.getOrderList().size(); i++) {
            if (MainActivity.storeOrder.getOrderList().get(i).getOrderNum() == number) {
                return i;
            }
        }
        return -1;
    }

    /**
     * cancels the order that was selected
     * @param view view
     */
    public void onCancelOrderButtonClick(View view) {
        if (spinner.getSelectedItem() == null) {
            Toast.makeText(getApplicationContext(), "no order selected", Toast.LENGTH_LONG).show();
            return;
        }
        int number = Integer.parseInt(spinner.getSelectedItem().toString());
        int index = findOrderIndex(number);
        MainActivity.storeOrder.remove(MainActivity.storeOrder.getOrderList().get(index));
        orderNumbersList.remove(spinner.getSelectedItem().toString());
        Toast.makeText(getApplicationContext(), "order removed", Toast.LENGTH_LONG).show();
        adapterSpinner = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, orderNumbersList);
        spinner.setAdapter(adapterSpinner);
        total.setText("");
        ArrayList<Pizza> emptyList = new ArrayList<Pizza>();
        adapterListView = new ArrayAdapter<Pizza>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, emptyList);
        listview.setAdapter(adapterListView);
    }
}