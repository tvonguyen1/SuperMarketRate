package com.example.supermarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MarketRateDialog.SaveRateListener {
    private MarketRate currentMarket;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initChangeRateButton();
        initTextChangedEvents();
        initSaveButton();
        initListButton();

        currentMarket = new MarketRate();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            initMarket(extras.getInt("marketId"));
        }
        else {
            currentMarket = new MarketRate();
        }
    }

    private void initListButton() {
        Button buttonList = findViewById(R.id.list);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

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
        currentMarket.setAvgRate((rate1 + rate2 + rate3 + rate4 + rate5)/5.0);


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
    private void initMarket(int id) {

        MarketDataSource ds = new MarketDataSource(MainActivity.this);
        try {
            ds.open();
            currentMarket = ds.getSpecificMarket(id);
            ds.close();
        }
        catch (Exception e) {
            Toast.makeText(this, "Load Contact Failed", Toast.LENGTH_LONG).show();
        }

        EditText editName = findViewById(R.id.nameEdit);
        EditText editAddress = findViewById(R.id.streetEdit);
        EditText editCity = findViewById(R.id.cityEdit);
        EditText editState = findViewById(R.id.editState);
        EditText editZipCode = findViewById(R.id.zipcodeEdit);
        TextView rate = findViewById(R.id.resultView);


        editName.setText(currentMarket.getMarketName());
        editAddress.setText(currentMarket.getStreetAddress());
        editCity.setText(currentMarket.getCity());
        editState.setText(currentMarket.getState());
        editZipCode.setText(currentMarket.getZipCode());
        rate.setText(currentMarket.getAvgRate()+"");

    }
}





