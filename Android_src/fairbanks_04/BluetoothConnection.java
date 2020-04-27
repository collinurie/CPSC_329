package com.example.curie.fairbanks_04;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class BluetoothConnection extends Thread {

    public static  BluetoothSocket mSocket;

    private InputStream mInStream;
    private OutputStream mOutStream;
    private byte[] buffer;
    private BluetoothAdapter mAdapter;
    private Handler mHandler;
    private String output;
    private String sendString;
    private String tempTester = "";

    static UUID MY_UUID;


    /**
     * Constructor initializes all necessary variables.
     * @param device the device that the constructor will connect to
     */
    public BluetoothConnection(BluetoothDevice device){
        MY_UUID = device.getUuids()[0].getUuid();
        mAdapter = null;
        mSocket = createMSocket(device);
        mSocket = connectSocket(mSocket);

        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        try{
            tmpIn = mSocket.getInputStream();
            tmpOut = mSocket.getOutputStream();
        }catch (IOException e){
            e.printStackTrace();
        }
        mInStream = tmpIn;
        mOutStream = tmpOut;
        buffer = new byte[25];
    }// end constructor


    /**
     * Creates the main socket that will be used in connection with device.
     * @param device a BluetoothDevice
     * @return a BluetoothSocket mSocket.
     */
    private BluetoothSocket createMSocket(BluetoothDevice device) {
        BluetoothSocket tmp = null;
        try {
            tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;
    }// end createMSocket


    /**
     * Socket makes connection to device then returns back the updated socket.
     * @param socket BluetoothSocket
     * @return an updated version of the parameter socket.
     */
    private BluetoothSocket connectSocket(BluetoothSocket socket){
        try {
            // This is a blocking call and will only return on a
            // successful connection or an exception
            socket.connect();
            System.out.println("$$$$$$$$$$$$$$$$****** socket connected ******$$$$$$$$$$$$$$$$");
        } catch (IOException e) {
            //connection to device failed so close the socket
            try {
                socket.close();
                System.out.println("$$$$$$$$$$$$$$$$****** socket closed ******$$$$$$$$$$$$$$$$");
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return socket;
    }// end connectSocket


    /**
     * This is a method that is not being used.
     */
    public void run1(){
        mHandler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run () {
                mHandler.post(new Runnable() {
                    @Override
                    public void run () {
                        // place your action here
                        int counter = 0;
                        while (true) {
                            counter++;
                            try {
                                output = "";
                                if (mInStream != null) {
                                    mInStream.read(buffer);
                                    for (byte b : buffer) {
                                        char c = (char) b;
                                        if (c >= ' ' && c < 'z') {
                                            System.out.print(c);
                                            output += c;
                                        }
                                    }
                                    System.out.println();
                                    Intent intent = new Intent();
                                    intent.setAction("com.curie.WEIGHT_RECEIVED");
                                    intent.putExtra("Output",output);

                                    if (counter % 1 == 0) {
                                        System.out.println(counter);
                                        //LocalBroadcastManager.getInstance(InputActivity.getContext()).sendBroadcastSync(intent);
                                    }
                                }
                                // Send the obtained bytes to the UI Activity
                            } catch (IOException e) {
                                //an exception here marks connection loss
                                //send message to UI Activity
                                break;
                            }
                        }
                    }
                });
            }
        }).start();
    }// end run1


    /**
     * Creates a thread that receives signal from the scale and then broadcasts the
     * weight to the UI to be displayed.
     */
    public void run2()
    {
        new Thread(new Runnable() {
            @Override
            public void run () {
                int counter = 0;
                // always waiting for signal
                while (true) {
                    counter++;
                    try {
                        output = "";
                        if (mInStream != null) {
                            mInStream.read(buffer);
                            // adds necessary bytes to output
                            for (byte b : buffer) {

                                char c = (char) b;
                                // gets only the symbols that are necessary
                                if (c >= ' ' && c < 'z') {
                                    //System.out.print(c);
                                    output += c;
                                }// end iff
                            }// end for loop
                             //System.out.println();
                           // System.out.println(output);
                            output = output.trim();
                            if(output.matches(".*-*[0-9]+\\.[0-9]+.*")){
                                //System.out.println(output);
                                sendString = convertToFinalForm(output);
                                tempTester = output;
                             }// end if

                            Intent intent = new Intent();
                            intent.setAction("com.curie.WEIGHT_RECEIVED");
                            intent.putExtra("Output",sendString);
                            // only broadcasts every other signal received from scale
                            if (counter % 1 == 0) {
                                //System.out.println(counter);
                                if(!tempTester.equals(""))
                                    //System.out.println(tempTester);
                                    LocalBroadcastManager.getInstance(InputActivity.getContext()).sendBroadcastSync(intent);
                            }// end if
                        }// end if
                    } catch (IOException e) {
                        e.printStackTrace();
                        //send message to UI Activity
                        break;
                    }// end catch
                }// end while loop
            }// end run
        }).start();


    }// end run2


    /**
     * Sends message back to device in the form of a byte[].
     * @param buffer byte[]
     */
    public void write(byte[] buffer){
        try{
            mOutStream.write(buffer);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }// end write


    /**
     * Closes the connection with the device
     */
    public void cancel(){
        try{
            mSocket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }// end cancel


    /**
     * Takes the raw string from the scale and the converts it into a readable
     * form ('weight.weight unit)
     * @param input raw string of char from inputStream
     * @return the string of the weight, in final form to be sent to InputActivity
     */
    private String convertToFinalForm(String input){
        boolean lb = true;
        double temp;
        System.out.println(input);

        if(input.indexOf('l') < 0)
            lb = false;

        String finalForm = "";
        // pattern would need to be ajusted depending on the stream the device is sending
        //Pattern p = Pattern.compile("(\\s[-]*[0-9]+)"); // "([-]*[0-9]+[\\.]*[0-9]*[\\s][a-z]+)"
        Pattern p = Pattern.compile("(-*[0-9]+[\\.]*[0-9]*)");
        Matcher m = p.matcher(input);
        if(m.find()){
            finalForm += m.group();
        }
        //System.out.println(finalForm);
        finalForm = finalForm.trim();
        temp = Double.parseDouble(finalForm);
        if(lb) {
            //temp = temp *0.01;
            //temp = round(temp,2);
            finalForm = ""+temp;
            finalForm += "l";
        }
        else {
           // temp = temp * 0.001;
           // temp = round(temp,3);
            finalForm = ""+temp;
            finalForm += "kg";
        }
        //System.out.println(finalForm);
        return finalForm;
    }// end convertToFinalForm


    /**
     * NOT BEING USED
     * takes a double and the places cuts the double down to the place limit
     * @param value the double
     * @param places mow many decimal places that the new double should have
     * @return the double that is cut down to the place limit
     */
    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
