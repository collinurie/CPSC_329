package com.example.curie.fairbanks_04;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalibrateOne extends AppCompatActivity {

    BluetoothConnection connection;
    BluetoothDevice connectedDevice;
    Button unitsButton,trackingButton,motionButton,divisionButton,filterButton,tareButton,ntepButton, calibrateButton;
    EditText capacityText;
    PopupMenu unitsPopup, trackingPopup, motionPopup, divisionsPopup,filterPopup, tarePopup, ntepPopup;
    String[] unitsArray, trackingArray,motionArray, divisionArray,filterArray,tareArray,ntepArray;
    byte[] sendPackage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibrate_one);

        // initialize bluetooth stuff
        connectedDevice = DeviceList.getInstrument();
        connection = new BluetoothConnection(connectedDevice);

        // initialize buttons/ editText
        unitsButton = (Button) findViewById(R.id.UnitsButton);
        trackingButton =  (Button) findViewById(R.id.TrackingButton);
        motionButton = (Button) findViewById(R.id.MotionButton);
        divisionButton = (Button) findViewById(R.id.DivisionButton);
        filterButton = (Button) findViewById(R.id.FilterButton);
        tareButton = (Button) findViewById(R.id.TareButton);
        ntepButton = (Button) findViewById(R.id.NTEPButton);
        calibrateButton = (Button)findViewById(R.id.CalibrationButton);
        capacityText = (EditText) findViewById(R.id.CapacityEditText);

        // initialize popups
        unitsPopup = new PopupMenu(CalibrateOne.this,findViewById(R.id.UnitsButton));
        trackingPopup = new PopupMenu(CalibrateOne.this,findViewById(R.id.TrackingButton));
        motionPopup = new PopupMenu(CalibrateOne.this,findViewById(R.id.MotionButton));
        divisionsPopup = new PopupMenu(CalibrateOne.this,findViewById(R.id.DivisionButton));
        filterPopup = new PopupMenu(CalibrateOne.this,findViewById(R.id.FilterButton));
        tarePopup = new PopupMenu(CalibrateOne.this,findViewById(R.id.TareButton));
        ntepPopup = new PopupMenu(CalibrateOne.this,findViewById(R.id.NTEPButton));

        // add elements to unitsPopup
        addElements();
        // add onclickListeners
        onClicks();


        if(BluetoothConnection.mSocket.isConnected())
        {
            Toast.makeText(CalibrateOne.this, "Connected to " + connectedDevice.getName(), Toast.LENGTH_SHORT).show();

        }// end if
        else {
            Toast.makeText(CalibrateOne.this, "Unable to make connection with " + connectedDevice.getName(), Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(CalibrateOne.this, DeviceList.class);
            CalibrateOne.this.startActivity(myIntent);
        }// end else
    }// end onCreate
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**********************************************************************
     * All of the onClick methods for teh view
     **********************************************************************/
    public void onClicks(){
        //units button/menu listeners
        unitsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                unitsPopup.show();
            }

        });
        unitsPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                unitsButton.setText(item.getTitle());
                return true;
            }
        });
        //tracking button/menu listeners
        trackingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                trackingPopup.show();
            }
        });
        trackingPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                trackingButton.setText(item.getTitle());
                return true;
            }
        });
        //motion button/menu listeners
        motionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                motionPopup.show();
            }
        });
        motionPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                motionButton.setText(item.getTitle());
                return true;
            }
        });
        //division button/menu listeners
        divisionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                divisionsPopup.show();
            }
        });
        divisionsPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                divisionButton.setText(item.getTitle());
                return true;
            }
        });
        //filter button/menu listeners
        filterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                filterPopup.show();
            }
        });
        filterPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                filterButton.setText(item.getTitle());
                return true;
            }
        });
        //tare button/menu listeners
        tareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tarePopup.show();
            }
        });
        tarePopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                tareButton.setText(item.getTitle());
                return true;
            }
        });
        // ntep button/ menu listeners
        ntepButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ntepPopup.show();
            }
        });
        ntepPopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                ntepButton.setText(item.getTitle());
                return true;
            }
        });


        calibrateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // send all data here
                sendPackage();
            }
        });
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**********************************************************************
     * Adds the elements to the popup menus
     **********************************************************************/
    public void addElements(){
        unitsArray = new String[] {"lb","kg","oz","g"};
        for(String s : unitsArray){
            unitsPopup.getMenu().add(s);
        }

        // add elements to trackingPopup
        trackingArray = new String[] {"OFF","0.5","1","3"};
        for(String s : trackingArray){
            trackingPopup.getMenu().add(s);
        }

        // add elements to motionPopup
        motionArray = new String[] {"OFF","0.5","1","3"};
        for(String s : motionArray){
            motionPopup.getMenu().add(s);
        }

        // add elements to divisionPopup
        divisionArray = new String[] {"0.0001","0.0002","0.0005","0.001","0.002","0.005","0.01","0.02","0.05","0.1","0.2","0.5","1","2","5","10","20","50"};
        for(String s : divisionArray){
            divisionsPopup.getMenu().add(s);
        }

        // add elements to filterPopup
        filterArray = new String[] {"STANDARD","FAST","SLOW","CATTLE"};
        for(String s : filterArray){
            filterPopup.getMenu().add(s);
        }

        // add elements to tarePopup
        tareArray = new String[] {"OFF","ON","ON-CLR"};
        for(String s : tareArray){
            tarePopup.getMenu().add(s);
        }

        //add elements to ntepPopup
        ntepArray = new String[] {"ON","OFF"};
        for(String s : ntepArray){
            ntepPopup.getMenu().add(s);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * gathers data from the view and arranges them into a package that can be sent to teh scale
     */
    public void gatherPackage(){
        String cfg = "[CFG],1,2,1,1,0,0,0,0,\"Barrels\",1.0,1.0,0,1,3,3,1,2,04,3,1,1,2,2,3,3,1,1,2,\r\n";
        String rest = "[ANY],1,1,1,1,0,\r\n[420],0,5000,1,1,0.0000,0.0000,\r\n[TKT],t,10,8,d,12,8,G,14,8,T,16,8,N,18,8,\r\n[PRN],1,1,12,1,\r\n[CST],<A><B><C><G><T>,\r\n" +
                "[CFM],0,0,0,6,0,0,0,0,1,0,0,4,0D,W<D>,2,02,3,0D,0,20,lb,kg,M,O,GR,TA,NT,,I,\r\n[TAD],08,56,10,07,25,18,\r\n";

        int units = getUnitsLocation();
        int tracking = getTrackingLocation();
        int motion = getMotionLocation();
        String division = divisionButton.getText().toString();
        int filter = getFilterLocation();
        int tare  = getTareLocation();
        int ntep = getNtepLocation();
        String cal = "[CAL],"+capacityText.getText()+","+units+","+tracking+","+motion+","+division+","+filter+","+tare+","+ntep+",\r\n";

        String totPac = "d\002 "+cfg+cal+rest;
        sendPackage = totPac.getBytes();

    }

    /**
     * Sends a succession of packages to the scale
     *
     */
    public void sendPackage(){
        gatherPackage();
        connection.write("pcmode".getBytes());

        try{Thread.sleep(250);} catch(InterruptedException e) {
            Thread.currentThread().interrupt();
            // code for stopping current task so thread stops
        }
        //connection.write("d\002".getBytes());


        connection.write(sendPackage);

        connection.write("\004".getBytes());


    }

    /**
     * finds what number needs to go in the package in regards to the Units
     * @return
     */
    int getUnitsLocation(){
        String strU = unitsButton.getText().toString();
        int spot = 0;
        for(int i = 0; i < unitsArray.length; i++){
            if(unitsArray[i].equals(strU)){
                spot = i + 1;
            }
        }
        return spot;
    }

    /**
     *finds what number needs to go in the package in regards to the Tracking
     * @return
     */
    int getTrackingLocation(){
        String strU = trackingButton.getText().toString();
        int spot = 0;
        for(int i = 0; i < trackingArray.length; i++){
            if(trackingArray[i].equals(strU)){
                spot = i + 1;
            }
        }
        return spot;
    }

    /**
     * finds what number needs to go in the package in regards to the Motion
     * @return
     */
    int getMotionLocation(){
        String strU = motionButton.getText().toString();
        int spot = 0;
        for(int i = 0; i < motionArray.length; i++){
            if(motionArray[i].equals(strU)){
                spot = i + 1;
            }
        }
        return spot;
    }

    /**
     * finds what number needs to go in the package in regards to the Filter
     * @return
     */
    int getFilterLocation(){
        String strU = filterButton.getText().toString();
        int spot = 0;
        for(int i = 0; i < filterArray.length; i++){
            if(filterArray[i].toLowerCase().equals(strU.toLowerCase())){
                spot = i + 1;
            }
        }
        return spot;
    }

    /**
     * finds what number needs to go in the package in regards to the Tare
     * @return
     */
    int getTareLocation(){
        String strU = tareButton.getText().toString();
        int spot = 0;
        for(int i = 0; i < tareArray.length; i++){
            if(tareArray[i].equals(strU)){
                spot = i + 1;
            }
        }
        return spot;
    }

    /** finds what number needs to go in the package in regards to the NTEP mode
     *
     * @return
     */
    int getNtepLocation(){
        if(ntepButton.getText().toString().toLowerCase().equals("off"))
            return 0;

        return 1;
    }

}
