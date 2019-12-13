package android.example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"gavrila@gero.in"};
    String subject = "Order from Music Shop";
    String emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent recieveOrderIntent = getIntent();
        String userName = recieveOrderIntent.getStringExtra("username");
        String goodsName = recieveOrderIntent.getStringExtra("goodsName");
        int quantity = recieveOrderIntent.getIntExtra("quantity", 0);
        double orderPrice = recieveOrderIntent.getDoubleExtra("orderPrice", 0);
        TextView priceOrderTotal = findViewById(R.id.priceOrderTotal);
        priceOrderTotal.setText("" + orderPrice);
        TextView userNameCart = findViewById(R.id.userNameCartValue);
        userNameCart.setText(userName);
        TextView goodsNameCart = findViewById(R.id.goodsNameCartValue);
        goodsNameCart.setText(goodsName);
        TextView quantityCart = findViewById(R.id.quantityCartValue);
        quantityCart.setText("" + quantity);
        emailText = "Hello, dear " + userName + "! Your order with "
                + quantity
                + " " + goodsName
                + " is confirmed. Total order price is "
                + orderPrice
                + " $. Thanks for your purchase.";

    }

    public void insertOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
