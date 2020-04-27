package com.example.curie.fairbanks_03;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class InputActivity extends AppCompatActivity {

    private static Context context;

    BluetoothDevice connectedDevice;
    TextView inputWindow;
    TextView unitView;
    Button getWeightButton;
    Button zero;
    Button units;
    String weight;
    BroadcastReceiver resultReceiver;
    BluetoothConnection connection;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // initialize objects used in the activity
        inputWindow = (TextView) findViewById(R.id.input_window);
        //unitView = (TextView) findViewById(R.id.unit_view);
        getWeightButton = (Button) findViewById(R.id.get_weight);
        zero = (Button) findViewById(R.id.zero_scale);
        units = (Button) findViewById(R.id.change_units);
        InputActivity.context = getApplicationContext();
        resultReceiver = createBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(resultReceiver, new IntentFilter("com.curie.WEIGHT_RECEIVED"));

        // connect with device selected in DeviceList.java
        connectedDevice = DeviceList.getInstrument();
        connection = new BluetoothConnection(connectedDevice);
        if(BluetoothConnection.mSocket.isConnected())
            connection.run2();

        else {
            Toast.makeText(InputActivity.this, "Unable to make connection with " + connectedDevice.getName(), Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(InputActivity.this, DeviceList.class);
            InputActivity.this.startActivity(myIntent);
        }

        // set listeners
        getWeightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //connection.run2();
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                zeroScale();
            }
        });
        units.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeUnits();
            }
        });


    }


    /**
     * Creates a BroadcastReceiver that will receive the weight being broadcast
     * from BluetoothConnection.java
     * @return
     */
    private BroadcastReceiver createBroadcastReceiver() {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                weight = intent.getStringExtra("Output");
               // System.out.println("******"+weight+"*******");
                //inputWindow.setText(weight);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        inputWindow.setText(getWeight(weight)+" "+getUnit(weight));
                    }
                });
            }
        };
    }


    /**
     * Is callable from other classes,
     * returns the context of the activity.
     * @return
     */
    public static Context getContext()
    {
        return InputActivity.context ;
    }


    /**
     * Sends a signal to the device to change the units on the scale.
     */
     public void changeUnits(){
         char u = 'U';
         byte b = (byte)u;
         connection.write(new byte[]{b});
         System.out.println("BitSent");
     }


    /**
     * Sends a signal to the device zeroing the scale.
     */
    public void zeroScale()
    {
        char z = 'Z';
        byte b = (byte)z;
        connection.write(new byte[]{b});
        System.out.println("BitSent");
    }


    /**
     * Takes the input and determines/returns the unit of the input.
     * @param weight
     * @return
     */
    public String getUnit(String weight){
        String unit = "";
        if(weight.contains("l"))
            unit = "lb";
        else if(weight.contains("o"))
            unit = "oz";
        else if(weight.contains("kg"))
            unit = "kg";
        else if(weight.contains("g"))
            unit = "g";
        return unit;
    }


    /**
     * Takes the input and determines/returns the numerical weight value.
     * @param weight
     * @return
     */
    public String getWeight(String weight){
        String weightNum = "";
        Pattern p = Pattern.compile("([-]*[0-9]+[\\.]*[0-9]*)");
        Matcher m = p.matcher(weight);
        if(m.find()){
            weightNum += m.group();
        }
        return weightNum;
    }


}


