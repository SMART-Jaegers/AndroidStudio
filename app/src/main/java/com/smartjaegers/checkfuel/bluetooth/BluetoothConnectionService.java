package com.smartjaegers.checkfuel.bluetooth;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.UUID;

public class BluetoothConnectionService {
    private static final String TAG = "BTConnectionService";

    private static final String appName = "MYAPP";

    private static final UUID MY_UUID_INSECURE =
            UUID.fromString("bdafb6cc-d68a-4b6b-819a-796d30674b9c");

    private final BluetoothAdapter bluetoothAdapter;

    private Context context;

    private AcceptThread insecureAcceptThread;

    private ConnectThread connectThread;

    private BluetoothDevice bluetoothDevice;

    private UUID deviceUUID;

    private ProgressDialog progressDialog;

    private ConnectedThread connectedThread;

    public BluetoothConnectionService(Context context) {
        this.context = context;
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        start();
    }

    /**
     * This thread runs while listening for incoming connections. It behaves
     * like server-side client. It runs until a connection is accepted
     * (or until cancelled).
     */
    private class AcceptThread extends Thread {
        private final BluetoothServerSocket serverSocket;

        public AcceptThread() {
            BluetoothServerSocket tmp = null;

            // Create a new listening server socket.
            try {
                tmp = bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(appName, MY_UUID_INSECURE);
                Log.d(TAG, "AcceptThread: Setting up Server using: " + MY_UUID_INSECURE);
            } catch (IOException e) {
                Log.e(TAG, "AcceptThread: IOException: " + e.getMessage());
            }
            serverSocket = tmp;
        }

        public void run() {
            Log.d(TAG, "run: AcceptThread running");
            BluetoothSocket socket = null;

            // This is a blocking call and will only return on a
            // successful connection or an exception.
            Log.d(TAG, "run: RFCOMM server socket start .......");
            try {
                socket = serverSocket.accept();
                Log.d(TAG, "run: RFCOMM server socket accepted connection.");
            } catch (IOException e) {
                Log.e(TAG, "AcceptThread: IOException: " + e.getMessage());
            }

            // to do
            if (socket != null) {
                connected(socket, bluetoothDevice);
            }
            Log.i(TAG, "run: END AcceptThread");
        }

        public void cancel() {
            Log.d(TAG, "cancel: Canceling AcceptThread");
            try {
                serverSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "cancel: Close of AcceptThread Server Socket failed. " + e.getMessage());
            }
        }
    }

    /**
     * This thread runs while attempting to make an outgoing connection
     * with a device. It runs straight through; the connection either
     * succeeds or fails.
     */
    private class ConnectThread extends Thread {
        BluetoothSocket bluetoothSocket;

        public ConnectThread(BluetoothDevice device, UUID uuid) {
            Log.d(TAG, "ConnectThread: started");
            bluetoothDevice = device;
            deviceUUID = uuid;
        }

        public void run() {
            BluetoothSocket tmp = null;
            Log.i(TAG, "ConnectThread: RUN connectedThread");

            // Get a BluetoothSocket for connection with a
            // given BluetoothDevice
            try {
                Log.d(TAG, "ConnectThread: Trying to create InsecureRfcommSocket using UUID: " +
                        MY_UUID_INSECURE);
                tmp = bluetoothDevice.createRfcommSocketToServiceRecord(deviceUUID);
            } catch (IOException e) {
                Log.e(TAG, "ConnectTread: Could not create InsecureRfcommSocket " + e.getMessage());
            }

            bluetoothSocket = tmp;

            // Always cancel discovery because it will slow down a connection.
            bluetoothAdapter.cancelDiscovery();

            // Make a connection to the BluetoothSocket

            try {
                // This is a blocking call and will only return on a
                // successful connection or an exception.
                bluetoothSocket.connect();
                Log.d(TAG, "run: ConnectThread connected.");
            } catch (IOException e) {
                try {
                    bluetoothSocket.close();
                    Log.d(TAG, "run: Closed Socket");
                } catch (IOException ex) {
                    Log.e(TAG, "connectThread: run: Unable to close connection in socket " + ex.getMessage());
                }
                Log.d(TAG, "run: ConnectThread: Could not connect to UUID: " + MY_UUID_INSECURE);
            }

            // to do
            connected(bluetoothSocket, bluetoothDevice);
        }

        public void cancel() {
            try {
                Log.d(TAG, "cancel: Closing Client Socket");
                bluetoothSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "cancel: close() of bluetoothSocket in ConnectThread failed. " + e.getMessage());
            }
        }
    }

    /**
     * Finally the ConnectedThread which is responsible for maintaining the BTConnection, Sending the data,
     * and receiving incoming data through input/output streams respectively
     */
    private class ConnectedThread extends Thread {
        private final BluetoothSocket socket;
        private final InputStream inputStream;
        private final OutputStream outputStream;

        ConnectedThread(BluetoothSocket socket) {
            Log.d(TAG, "ConnectedThread: Starting");
            
            this.socket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // dismiss the progress dialog when connection is established
            try {
                progressDialog.dismiss();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            inputStream = tmpIn;
            outputStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024]; // buffer store for the stream

            int bytes; // bytes returned from read()

            // keep listening to the InputStream until an exception occurs
            while(true) {
                // Read from the InputStream
                try {
                    bytes = inputStream.read(buffer);
                    String incomingMessage = new String(buffer, 0, bytes);
                    Log.d(TAG, "InputStream: " + incomingMessage);
                    Intent incomingMessageIntent = new Intent("incomingMessage");
                    incomingMessageIntent.putExtra("theMessage", incomingMessage);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(incomingMessageIntent);

                } catch (IOException e) {
                    Log.e(TAG, "run: Error reading InputStream");
                    break;
                }
            }
        }

        /*Call this form the main activity to send data to the remote device*/
        public void write(byte[] bytes) {
            String text = new String(bytes, Charset.defaultCharset());
            Log.d(TAG, "write: Writing to OutputStream: " + text);
            try {
                outputStream.write(bytes);
            } catch (IOException e) {
                Log.e(TAG, "write: Error writing to OutputStream. " + e.getMessage());
            }
        }

        /*Call this form the main activity to shutdown the connection*/
        public void cancel() {
            try {
                socket.close();
            } catch (IOException e) {

            }
        }
    }

    /**
     * Start the chat service. Specifically start AcceptThread to begin a
     * session in listening(server) mode. Called by the Activity onResume()
     */
    public synchronized void start() {
        Log.d(TAG, "start");

        // Cancel any thread attempting to make connection
        if (connectThread != null) {
            connectThread.cancel();
            connectThread = null;
        }
        if (insecureAcceptThread == null) {
            insecureAcceptThread = new AcceptThread();
            insecureAcceptThread.start();
        }
    }

    public void startClient(BluetoothDevice device, UUID uuid) {
        Log.d(TAG, "startClient: Started");

        //initprogress dialog
        progressDialog = ProgressDialog.show(context, "Connecting bluetooth", "Please wait...", true);
        connectThread = new ConnectThread(device, uuid);
        connectThread.start();

    }

    private void connected(BluetoothSocket bluetoothSocket, BluetoothDevice bluetoothDevice) {
        Log.d(TAG, "connected: Starting");

        // Start the thread to manage the connection and perform transmissions
        connectedThread = new ConnectedThread(bluetoothSocket);
        connectedThread.start();
    }

    /**
     * Write to the ConnectedThread in an unsynchronized manner
     * @param out The bytes to write
     * @see ConnectedThread#write(byte[])
     */
    public void write(byte[] out) {
        // Create temporary object
        ConnectedThread r;

        // Synchronize a copy of ConnectedThread
        Log.d(TAG, "write: Write called");
        // perform the write
        connectedThread.write(out);
    }
}