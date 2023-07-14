package com.example.project5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 This is the adapter class for toppings to implement recyclerview
 @author Stanley Zheng, Jamie Pan
 */
public class ToppingsAdapter extends RecyclerView.Adapter<ToppingsAdapter.ToppingsHolder> {

    private Context context;
    private ArrayList<Topping> toppings;
    private static String callingClass;
    private static int count;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     Constructor.
     Intializes instance variables.
     */
    public ToppingsAdapter(Context context, ArrayList<Topping> toppings, String callingClass) {
        this.context = context;
        this.toppings = toppings;
        this.callingClass = callingClass;
        count = 0;
    }

    /**
     Creates toppings view holder
     @param parent parent
     @param viewType type of view
     @return toppings view holder
     */
    @NonNull
    @Override
    public ToppingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new ToppingsHolder(view);
    }

    /**
     Bind view holder.
     @param holder toppings holder
     @param position index
     */
    @Override
    public void onBindViewHolder(@NonNull ToppingsHolder holder, int position) {
        //assign values for each row
        holder.tView.setText(toppings.get(position).getIngredient());
        holder.imView.setImageResource(toppings.get(position).getImage());
    }

    /**
     Gets the number of menu item in the array list
     @return toppings array list size
     */
    @Override
    public int getItemCount() {
        return toppings.size();
    }


    /**
     Toppings holder inner class
     @author Stanley Zheng, Jamie Pan
     */
    public static class ToppingsHolder extends RecyclerView.ViewHolder {

        private TextView tView;
        private ImageView imView;
        private Button btn;
        private Button btn2;

        /**
         Constructor.
         Initializes instance variables and calls methods to set up add/remove buttons
         @param itemView item view
         */
        public ToppingsHolder(@NonNull View itemView) {
            super(itemView);
            tView = itemView.findViewById(R.id.textView);
            imView = itemView.findViewById(R.id.imageView);
            btn = itemView.findViewById(R.id.addButton);
            btn2 = itemView.findViewById(R.id.removeButton);
            setAddButtonOnClick(itemView); //register the onClicklistener for the button on each row.
            setRemoveButtonOnClick(itemView);
        }

        /**
         Method to set up add topping button
         @param itemView item view
         */
        private void setAddButtonOnClick(@NonNull View itemView) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Add to pizza");
                    alert.setMessage(tView.getText().toString());
                    //handle the "YES" click
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (callingClass.equals("ChicagoActivity")) {
                                addUsingChicago(itemView);
                            }
                            else {
                                addUsingNewYork(itemView);
                            }
                        }
                        //handle the "NO" click
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }

        /**
         Method to set up remove topping button
         @param itemView item view
         */
        private void setRemoveButtonOnClick(@NonNull View itemView) {
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Remove from pizza");
                    alert.setMessage(tView.getText().toString());
                    //handle the "YES" click
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (callingClass.equals("ChicagoActivity")) {
                                removeUsingChicago(itemView);
                            }
                            else {
                                removeUsingNewYork(itemView);
                            }
                        }
                        //handle the "NO" click
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }

        /**
         Adds topping to topping list array located in chicago activity class
         @param itemView item view
         */
        private void addUsingChicago(@NonNull View itemView) {
            Topping topping = new Topping(tView.getText().toString());
            if (count >= 7) {
                Toast.makeText(itemView.getContext(), "cannot add more than 7 toppings", Toast.LENGTH_LONG).show();
            }
            else if (ChicagoActivity.toppingList.contains(topping)) {
                Toast.makeText(itemView.getContext(), tView.getText().toString() + " is already added", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(itemView.getContext(),
                        tView.getText().toString() + " added.", Toast.LENGTH_LONG).show();
                ChicagoActivity.toppingList.add(topping);
                count++;
                ChicagoActivity.price += ChicagoActivity.TOPPING_COST;
                ChicagoActivity.pizzaPrice.setText("$" + df.format(ChicagoActivity.price));
                ChicagoActivity.toppingsTextView.setText(ChicagoActivity.printAddedToppings());
            }
        }

        /**
         Adds topping to topping list array located in new york activity class
         @param itemView item view
         */
        private void addUsingNewYork(@NonNull View itemView) {
            Topping topping = new Topping(tView.getText().toString());
            if (count >= 7) {
                Toast.makeText(itemView.getContext(), "cannot add more than 7 toppings", Toast.LENGTH_LONG).show();
            }
            else if (NewYorkActivity.toppingList.contains(topping)) {
                Toast.makeText(itemView.getContext(), tView.getText().toString() + " is already added", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(itemView.getContext(),
                        tView.getText().toString() + " added.", Toast.LENGTH_LONG).show();
                NewYorkActivity.toppingList.add(topping);
                count++;
                NewYorkActivity.price += NewYorkActivity.TOPPING_COST;
                NewYorkActivity.pizzaPrice.setText("$" + df.format(NewYorkActivity.price));
                NewYorkActivity.toppingsTextView.setText(NewYorkActivity.printAddedToppings());
            }
        }

        /**
         Removes topping from topping list array located in chicago activity class
         @param itemView item view
         */
        private void removeUsingChicago(@NonNull View itemView) {
            Topping topping = new Topping(tView.getText().toString());
            if (!ChicagoActivity.toppingList.contains(topping)) {
                Toast.makeText(itemView.getContext(), tView.getText().toString() + " has not been added", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(itemView.getContext(),
                        tView.getText().toString() + " removed.", Toast.LENGTH_LONG).show();
                ChicagoActivity.toppingList.remove(topping);
                count--;
                ChicagoActivity.price -= ChicagoActivity.TOPPING_COST;
                ChicagoActivity.pizzaPrice.setText("$" + df.format(ChicagoActivity.price));
                ChicagoActivity.toppingsTextView.setText(ChicagoActivity.printAddedToppings());
            }
        }

        /**
         Removes topping from topping list array located in new york activity class
         @param itemView item view
         */
        private void removeUsingNewYork(@NonNull View itemView) {
            Topping topping = new Topping(tView.getText().toString());
            if (!NewYorkActivity.toppingList.contains(topping)) {
                Toast.makeText(itemView.getContext(), tView.getText().toString() + " has not been added", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(itemView.getContext(),
                        tView.getText().toString() + " removed.", Toast.LENGTH_LONG).show();
                NewYorkActivity.toppingList.remove(topping);
                count--;
                NewYorkActivity.price -= ChicagoActivity.TOPPING_COST;
                NewYorkActivity.pizzaPrice.setText("$" + df.format(ChicagoActivity.price));
                NewYorkActivity.toppingsTextView.setText(NewYorkActivity.printAddedToppings());
            }
        }
    }
}




