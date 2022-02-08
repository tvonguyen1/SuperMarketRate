package com.example.supermarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RatingActivity extends AppCompatActivity {
    int liquorRating;
    int produceRating;
    int meatRating;
    int cheeseRating;
    int checkoutRating;
    double avgRating;
    private MarketRate currentMarket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_layout);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            initMarket(extras.getInt("marketId"));
        }
        else {
            currentMarket = new MarketRate();
        }
        initRateChange();
        initBackButton();


    }

    private void initRateChange() {
        final RatingBar liquorRate = findViewById(R.id.liquorRateIntent);
        liquorRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rate, boolean b) {
                ratingBar.setRating(rate);
                liquorRating = (int) (ratingBar.getRating());
                currentMarket.setLiquorRate(liquorRating);

            }

        });

        final RatingBar produceRate = findViewById(R.id.produceRateIntent);
        produceRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rate, boolean b) {
                ratingBar.setRating(rate);
                produceRating = (int) (ratingBar.getRating());
                currentMarket.setProduceRate(produceRating);
            }

        });

        final RatingBar meatRate = findViewById(R.id.meatRateIntent);
        meatRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rate, boolean b) {
                ratingBar.setRating(rate);
                meatRating = (int) (ratingBar.getRating());
                currentMarket.setMeatRate(meatRating);
            }

        });
        final RatingBar cheeseRate = findViewById(R.id.cheeseRateIntent);
        cheeseRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rate, boolean b) {
                ratingBar.setRating(rate);
                cheeseRating = (int) (ratingBar.getRating());
                currentMarket.setCheeseRate(cheeseRating);
            }

        });


        final RatingBar checkoutRate = findViewById(R.id.checkoutRateIntent);
        checkoutRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rate, boolean b) {
                ratingBar.setRating(rate);
                checkoutRating = (int)(ratingBar.getRating());
                currentMarket.setCheckoutRate(checkoutRating);
            }

        });

        avgRating = (currentMarket.getLiquorRate() + currentMarket.getProduceRate() + currentMarket.getCheeseRate()
                + currentMarket.getMeatRate() + currentMarket.getCheckoutRate())/5.0;
        currentMarket.setAvgRate(avgRating);


    }
    private void initBackButton() {
        Button backButton = findViewById(R.id.buttonBack2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                MarketDataSource ds = new MarketDataSource(RatingActivity.this);
                try {
                    ds.open();
                    wasSuccessful = ds.updateMarketRate(currentMarket);
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }
                Intent intent = new Intent(RatingActivity.this, ListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initMarket(int id) {

        MarketDataSource ds = new MarketDataSource(RatingActivity.this);
        try {
            ds.open();
            currentMarket = ds.getSpecificMarket(id);
            ds.close();
        } catch (Exception e) {
            Toast.makeText(this, "Load Contact Failed", Toast.LENGTH_LONG).show();
        }
    }

}
