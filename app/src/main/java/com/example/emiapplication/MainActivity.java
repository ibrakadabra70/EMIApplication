package com.example.emiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import static android.view.View.*;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText principleEdit, rateEdit;
    Spinner spin, spin2;

    //method to calculate mortgage
    public double calculateMortgage(String paymentFreq, double amortizationPeriod,double principle, double rate)
    {
        //change rate from percent to decimal
        rate /= 100;
        rate /=12;
        double amortizationPeriodMonths = amortizationPeriod * 12;

        double monthlyPayment = (principle*rate) / (1-Math.pow(1+rate, -amortizationPeriodMonths));

        //divide rate by how many yearly payments
        if (paymentFreq.equals("Bi-weekly")){
            monthlyPayment /= 2;
        }
        else if(paymentFreq.equals("weekly"))
        {
            monthlyPayment /=4;
        }



        return monthlyPayment;


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // adding a drop down menu
        String[] users = {
                "Monthly",
                "Bi-weekly",
                "weekly"
        };
        spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter < String > adapter = new ArrayAdapter < String > (this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);


        String[] users2 = {
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15",
                "16",
                "17",
                "18",
                "19",
                "20",
                "21",
                "22",
                "23",
                "24",
                "25",
                "26",
                "27",
                "28",
                "29",
                "30"
        };
        spin2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter < String > adapter2 = new ArrayAdapter < String > (this, android.R.layout.simple_spinner_item, users2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adapter2);

        //fetching form data
        principleEdit = (EditText) findViewById(R.id.principle_amount_edit);
        rateEdit = (EditText) findViewById(R.id.interest_rate_edit);

        //initializing button to make calcualtions
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
                                      @Override
                                      public void onClick(View view) {
                                          //converting input data to double and string
                                          String paymentFreq = spin.getSelectedItem().toString();

                                          String amortizationPeriodTemp = spin2.getSelectedItem().toString();
                                          double amortizationPeriod = Double.parseDouble(amortizationPeriodTemp);

                                          String principleTemp = principleEdit.getText().toString();
                                          double principle = Double.parseDouble(principleTemp);

                                          String rateTemp = rateEdit.getText().toString();
                                          double rate = Double.parseDouble(rateTemp);

                                          double monthlyPaymentsTemp = calculateMortgage(paymentFreq,amortizationPeriod , principle,rate);
                                          double monthlyPayments = Math.round(monthlyPaymentsTemp * 100.0) / 100.0;
                                          String monthlyPaymentsString = String.valueOf(monthlyPayments);

                                          String output = "Your " +paymentFreq+ " payment is: " +monthlyPaymentsString;

                                          //Using intent to display results on next page
                                          Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                                          intent.putExtra("Output", output);
                                          startActivity(intent);

                                          //To Display on current page
                                          //(TextView) MainActivity.this.findViewById(R.id.result)).setText("Your " +paymentFreq+ " payment is: " +monthlyPaymentsString);




                                      }
                                      
        }

        );


    }

}