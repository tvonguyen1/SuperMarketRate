package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements MarketRateDialog.SaveRateListener {
    private MarketRate currentMarket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initChangeRateButton();
        initTextChangedEvents();
        initSaveButton();

        currentMarket = new MarketRate();
    }


    @Override
    public void didFinishMarketRateDialog (int rate1, int rate2, int rate3, int rate4, int rate5) {
        TextView rating = findViewById(R.id.resultView);
        rating.setText ((rate1 + rate2 + rate3 + rate4 + rate5)/5.0 + " stars");
        currentMarket.setLiquorRate(rate1);
        currentMarket.setProduceRate(rate2);
        currentMarket.setMeatRate(rate3);
        currentMarket.setCheeseRate(rate4);
        currentMarket.setCheckoutRate(rate5);


    }

    private void initChangeRateButton() {
        Button changeRate = findViewById (R.id.buttonRate);
        changeRate.setOnClickListener (new View.OnClickListener() {

            public void onClick (View view) {
                FragmentManager fm = getSupportFragmentManager();
                MarketRateDialog marketRateDialog = new MarketRateDialog();
                marketRateDialog.show(fm,"MarketRate");
            }
        });
    }

    private void initTextChangedEvents() {
        final EditText etMarketName = findViewById(R.id.nameEdit);
        etMarketName.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentMarket.setMarketName(etMarketName.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        final EditText etStreetAddress = findViewById(R.id.streetEdit);
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentMarket.setStreetAddress(etStreetAddress.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        final EditText etCity = findViewById(R.id.cityEdit);
        etCity.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void afterTextChanged(Editable editable) {
                currentMarket.setCity(etCity.getText().toString());
            }
        });

        final EditText etState = findViewById(R.id.editState);
        etState.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void afterTextChanged(Editable editable) {
                currentMarket.setState(etState.getText().toString());
            }
        });

        final EditText etZip = findViewById(R.id.zipcodeEdit);
        etZip.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void afterTextChanged(Editable editable) {
                currentMarket.setZipCode(etZip.getText().toString());
            }
        });
    }
    private void initSaveButton() {
        Button saveButton = findViewById(R.id.saveMarket);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                MarketDataSource ds = new MarketDataSource(MainActivity.this);
                try {
                    ds.open();
                    if (currentMarket.getMarketID() == -1) {
                        wasSuccessful = ds.insertMarketRate(currentMarket);
                        if (wasSuccessful) {
                            int newId = ds.getLastMarketId();
                            currentMarket.setMarketID(newId);

                        }

                    } else {
                        wasSuccessful = ds.updateMarketRate(currentMarket);
                    }
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }
            }
        });


    }
}





