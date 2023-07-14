package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

/**
 This class is the main activity class and interacts with activity_main.xml
 provides the user with options to select Chicago and NY style pizzas
 and view them in current order tab or store order tab
 @author Stanley Zheng, Jamie Pan
 */
public class MainActivity extends AppCompatActivity {

    public static StoreOrder storeOrder;


    /**
     * initializes store order
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storeOrder = new StoreOrder();
    }

    /**
     * Button that loads/opens up the chicago pizza menu
     * @param view view
     */
    public void onChicagoButtonClick(View view) {
        Intent intent = new Intent(this, ChicagoActivity.class);
        startActivity(intent);
    }

    /**
     * Button that loads/opens up the New York pizza menue
     * @param view view
     */
    public void onNewYorkButtonClick(View view) {
        Intent intent = new Intent(this, NewYorkActivity.class);
        startActivity(intent);
    }

    /**
     * Button that loads/opens up the current order window
     * @param view view
     */
    public void onCurrentOrderButtonClick(View view) {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    /**
     * Button that loads/opens up the store order window
     * @param view view
     */
    public void onStoreOrderButtonClick(View view) {
        Intent intent = new Intent(this, StoreOrdersActivity.class);
        startActivity(intent);
    }
}