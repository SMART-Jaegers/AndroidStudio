package com.smartjaegers.checkfuel.adapters;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smartjaegers.checkfuel.R;

import java.util.ArrayList;

public class DeviceListAdapter extends ArrayAdapter<BluetoothDevice> {

    private static final String TAG = "Device list adapter----";
    private LayoutInflater layoutInflater;
    private ArrayList<BluetoothDevice> devices;
    private int viewResourceId;

    public DeviceListAdapter(Context context, int btResourceId, ArrayList<BluetoothDevice> devices) {
        super(context, btResourceId);
        this.devices = devices;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceId = btResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(viewResourceId, null);

        BluetoothDevice device = devices.get(position);
        if (device != null) {
            TextView deviceName = convertView.findViewById(R.id.btDeviceName);
            TextView deviceAddress = convertView.findViewById(R.id.btDeviceAddress);

            if (deviceName != null) {
                deviceName.setText(device.getName());
            }
            if (deviceAddress != null) {
                deviceAddress.setText(device.getAddress());
            }
        }
        return  convertView;
    }

    @Override
    public int getCount() {
        return devices.size();
    }
}
