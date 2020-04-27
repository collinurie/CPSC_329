package com.example.curie.fairbanks_03;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;

import android.app.Activity;
import android.os.Bundle;
import java.io.IOException;

import android.util.Log;
import android.view.View;
import android.view.MenuItem;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.Toast;
import android.content.Intent;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 *
 */
public class DeviceList extends Activity {

    Button deviceSearch;
    Switch btSwitch;


    private BluetoothAdapter BA;
    private Set<BluetoothDevice>pairedDevices;
    private String instrumentName;
    private UUID instrumentUUID;
    BluetoothSocket socket;
    BluetoothConnection connector;

    ListView lv;
    public static BluetoothDevice instrument;
    PopupMenu pMenu;
    ArrayList<MenuItem> popupList;
    ArrayList<BluetoothDevice> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);

        deviceSearch = (Button) findViewById(R.id.device_search);
        deviceSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(DeviceList.this, findViewById(R.id.device_search));
                pairedDevices = BA.getBondedDevices();
                System.out.println("!!!!!!!!!!!!!!!!!!!!");
                list = new ArrayList<BluetoothDevice>();
                for (BluetoothDevice bt : pairedDevices) {

                    list.add(bt);
                    popup.getMenu().add(bt.getName());
                    popup.show();
                }// end onClick

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {


                        ArrayList<BluetoothDevice> deviceArrayList = new ArrayList<BluetoothDevice>(pairedDevices);

                        Toast.makeText(DeviceList.this, "Connecting to: " + deviceArrayList.get(0).getName(), Toast.LENGTH_SHORT).show();
                        for(BluetoothDevice device : deviceArrayList)
                        {
                            if(device.getName().equals(item.getTitle()))
                                instrument = device;
                        }
                        Intent myIntent = new Intent(DeviceList.this, InputActivity.class);
                        DeviceList.this.startActivity(myIntent);
                        return true;
                    }// end onMenuItemClick
                });

            }
        });


        btSwitch = (Switch) findViewById(R.id.switch1);

        BA =BluetoothAdapter.getDefaultAdapter();
        lv =(ListView) findViewById(R.id.listView);

        if(BA.isEnabled())
            btSwitch.setChecked(true);
        else
            btSwitch.setChecked(false);

    }// end onCreate


    /**
     * Turns on the bluetooth on the device if it is off.
     * Turns off the bluetooth on the device if it is on.
     * @param v
     */
    public void onOff(View v)
    {
        if(BA.isEnabled())
        {
            BA.disable();
            Toast.makeText(getApplicationContext(), " BT Turned off" ,Toast.LENGTH_LONG).show();
        }
        else
        {             BA.enable();
            Toast.makeText(getApplicationContext(), "BT Turned on" ,Toast.LENGTH_LONG).show();
        }
    }// end onOff


    /**
     * Returns the Bluetooth device that was selected in the ListView.
     * @return
     */
    public static BluetoothDevice getInstrument() { return instrument; }// end getInstrument

}
