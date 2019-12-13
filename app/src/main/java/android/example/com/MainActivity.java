package android.example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSpinner();
        createMap();
        userNameEditText = findViewById(R.id.editText);
    }

    void createMap(){
        goodsMap = new HashMap();
        goodsMap.put("guitar", 500.);
        goodsMap.put("drums", 1500.);
        goodsMap.put("keyboard", 1000.);
        goodsMap.put("microphone", 300.);
    }

    void createSpinner(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");
        spinnerArrayList.add("microphone");
        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public void minus(View view) {
        if (quantity > 0) {
            quantity = quantity - 1;
            TextView quantityTextView = findViewById(R.id.qiantityTextView);
            quantityTextView.setText("" + quantity);
            TextView priceTextView = findViewById(R.id.priceTextView);
            priceTextView.setText("" + quantity * price);
        }
    }
    public void plus(View view) {
        quantity = quantity + 1 ;
        TextView quantityTextView = findViewById(R.id.qiantityTextView);
        quantityTextView.setText(""+ quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double)goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
        ImageView goodsImageView = findViewById(R.id.imageView2);

        switch (goodsName){
            case "guitar":
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
            case "drums":
                goodsImageView.setImageResource(R.drawable.drums);
                break;
            case "keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
            case "microphone":
                goodsImageView.setImageResource(R.drawable.microphone);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        Log.d("username", order.userName);

        order.goodsName = goodsName;
        Log.d("goodsName", order.goodsName);

        order.quantity = quantity;
        Log.d("quantity", "" + order.quantity);

        order.orderPrice = quantity * price;
        Log.d("orderPrice", "" + order.orderPrice);
        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("username", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        startActivity(orderIntent);


    }
}
