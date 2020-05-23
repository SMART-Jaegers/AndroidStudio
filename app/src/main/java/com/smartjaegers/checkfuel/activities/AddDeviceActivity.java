package com.smartjaegers.checkfuel.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.smartjaegers.checkfuel.R;
import com.smartjaegers.checkfuel.adapters.DeviceListAdapter;
import com.smartjaegers.checkfuel.bluetooth.BluetoothConnectionService;

import java.util.ArrayList;
import java.util.UUID;

public class AddDeviceActivity extends AppCompatActivity implements AdapterView.OnItemClickListener  {

    private static final String TAG = "MainActivity";

    BluetoothAdapter bluetoothAdapter;
    BluetoothConnectionService bluetoothConnectionService;
    private static final UUID MY_UUID_INSECURE =
            UUID.fromString("bdafb6cc-d68a-4b6b-819a-796d30674b9c");
    BluetoothDevice bluetoothDevice;
    StringBuilder messages;
    public ArrayList<BluetoothDevice> bluetoothDevices = new ArrayList<>();
    public DeviceListAdapter deviceListAdapter;
    ListView lvNewDevices;

    private BroadcastReceiver broadcastReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Log.d(TAG, "onReceive: ACTION_FOUND");

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if(!bluetoothDevices.contains(device)){
                    bluetoothDevices.add(device);
                }
                if (device != null) {
                    Log.d(TAG, "onReceive: " + device.getName() + ": " + device.getAddress());
                }
                deviceListAdapter = new DeviceListAdapter(context, R.layout.device_adapter_view, bluetoothDevices);
                Log.d(TAG, "onReceive: " + deviceListAdapter);
                lvNewDevices.setAdapter(deviceListAdapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_activity);

        Switch switchOnOff = findViewById(R.id.switch1);
        lvNewDevices = findViewById(R.id.lvNewDevices);
        bluetoothDevices = new ArrayList<>();
        messages = new StringBuilder();


        //Broadcasts when bond state changes
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        //registerReceiver(broadcastReceiver4, filter);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        lvNewDevices.setOnItemClickListener(AddDeviceActivity.this);

        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            switchOnOff.setChecked(true);
        }
        switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onClick: enabling/disabling bluetooth");
                enableDisableBT();
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: called.");
        super.onDestroy();
        unregisterReceiver(broadcastReceiver1);
    }

    /**
     * Create method for starting connection
     * Remember: the connection will fail and app will crash if you haven't paired first
     */
    public void startConnection() {
        startBTConnection(bluetoothDevice, MY_UUID_INSECURE);
    }

    /**
     * Starting chat service method
     */
    public void startBTConnection(BluetoothDevice bluetoothDevice, UUID uuid) {
        Log.d(TAG, "startBTConnection: Initializing RFCOMM Connection");
        bluetoothConnectionService.startClient(bluetoothDevice, uuid);
        startActivity(new Intent(this, MainActivity.class));
    }

    public void enableDisableBT() {
        if (bluetoothAdapter == null) {
            Log.d(TAG, "enableDisableBT: Does not have BT capabilities.");
        } else if (!bluetoothAdapter.isEnabled()) {
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBTIntent);
        }
        if (bluetoothAdapter.isEnabled()) {
            Log.d(TAG, "enableDisableBT: disabling BT");
            bluetoothAdapter.disable();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void discover(View view) {
        Log.d(TAG, "btnDiscover: Looking for unpaired devices");
        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
            Log.d(TAG, "btnDiscover: Canceling discovery");
        }
        // check BT permission in manifest
        checkBTPermissions();
        bluetoothAdapter.startDiscovery();
        IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(broadcastReceiver1, discoverDevicesIntent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkBTPermissions() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            int permissionCheck = this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            permissionCheck += this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
            if (permissionCheck != 0) {
                this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001);
            }
        } else {
            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // first cancel discovery because it is very memory intensive.
        bluetoothAdapter.cancelDiscovery();
        Log.d(TAG, "onItemClick: You Clicked on device.");
        String deviceName = bluetoothDevices.get(position).getName();
        String deviceAddress = bluetoothDevices.get(position).getAddress();

        Log.d(TAG, "onItemClick: deviceName = " + deviceName);
        Log.d(TAG, "onItemClick: deviceAddress = " + deviceAddress);

        //create the bond
        //NOTE: Requires API 17+?
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Log.d(TAG, "onItemClick: Trying to pair with " + deviceName);
            bluetoothDevices.get(position).createBond();
            bluetoothDevice = bluetoothDevices.get(position);
            bluetoothConnectionService = BluetoothConnectionService.getInstance(AddDeviceActivity.this);
            startConnection();

        }
    }

}