package com.prothesbrand.electricitybillcalculate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edText;
    private Button calculateBtn,resetBtn;
    private TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES,WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES);
        this.getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        this.getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_main);

        edText = findViewById(R.id.edText);
        calculateBtn = findViewById(R.id.calculateBtn);
        resetBtn = findViewById(R.id.resetBtn);
        tvDisplay = findViewById(R.id.tvDisplay);

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUserInput = edText.getText().toString();
                if (getUserInput.length() > 0){
                    float unit = Float.parseFloat(getUserInput);
                    float bill = 0,demandCharge = 42,totalBill,vat;
                    if (unit <= 50){
                        bill = unit * 4.633f;
                    }else if (unit <= 75){
                        bill = unit * 5.26f;
                    }else if(unit <= 200){
                        bill = (394.50f + ((unit - 75) * 7.20f));
                    }else if(unit <= 300){
                        bill = (394.50f + 900 +((unit - 200) * 7.59f));
                    }else if(unit <= 400){
                        bill = (394.50f + 900 + 759 +((unit - 300) * 8.02f));
                    }else if(unit <= 600){
                        bill = (394.50f + 900 + 759 + 802 +((unit - 400) * 12.67f));
                    }else if(unit > 600){
                        bill = (394.50f + 900 + 759 + 802 + 2534 +((unit - 600) * 14.61f));
                    }

                    // Add surCharge and Bill
                    demandCharge = demandCharge * 2;
                    // Add bill with 5% vat;
                    vat = ((bill+demandCharge) * 0.05f);
                    totalBill = vat + demandCharge + bill;
                    totalBill = (float) Math.ceil(totalBill);
                    tvDisplay.setText("Demand Charge : "+demandCharge+" BDT"+"\nVAT 5% : "+vat+" BDT"+"\nElectricity Bill : "+bill+" BDT"+"\n----------------"+"\nTotal Bill : "+totalBill+" BDT");
                    tvDisplay.setGravity(Gravity.CENTER_VERTICAL);
                }else if (getUserInput.length() <= 0){
                    edText.setError("Empty Field");
                }
            }
        });



        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edText.setText(null);
                edText.setError(null);
                tvDisplay.setText(null);
            }
        });




    }
}