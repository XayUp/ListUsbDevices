package com.xayup.listusbdevices;

import android.app.*;
import android.os.*;

import android.view.View;
import android.widget.*;

public class MainActivity extends Activity  {
    protected int device_list_current_tab;

    private DevicesList deviceList;

    //Interface
    private AlertDialog devicesListWindow;
    private ListView viewDevices;
    private TextView infoDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        View devicesListLayout = getLayoutInflater().inflate(R.layout.devices_layout, null);

        this.devicesListWindow = new AlertDialog.Builder(this).setView(devicesListLayout).create();

        this.viewDevices = devicesListLayout.findViewById(R.id.devices_list);
        this.infoDevice = findViewById(R.id.main_text_devices_info);

        this.deviceList = new DevicesList(this);

        devicesListLayout.findViewById(R.id.devices_list_close).setOnClickListener((view) -> devicesListWindow.dismiss());
        devicesListLayout.findViewById(R.id.devices_list_refresh).setOnClickListener((view) -> viewDevices.requestLayout());
        devicesListLayout.findViewById(R.id.devices_list_usb).setOnClickListener((view) -> {
            viewDevices.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.simple_list_item_1, android.R.id.text1, deviceList.getDevicesList(DeviceInfo.DeviceReturnType.DEVICE_LIST_USB, true).keySet().toArray()));
        });
        devicesListLayout.findViewById(R.id.devices_list_midi).setOnClickListener((view) -> {
            viewDevices.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.simple_list_item_1, android.R.id.text1, deviceList.getDevicesList(DeviceInfo.DeviceReturnType.DEVICE_LIST_MIDI, true).keySet().toArray()));
        });
        viewDevices.setOnItemClickListener((adapterView, view, position, id) -> {
            DeviceInfo device =  deviceList.getDevicesList(deviceList.getCurrentListType(), false).get((String) adapterView.getItemAtPosition(position));
            if (device != null) {
                String infoDevice_ =
                                "\nDevice Name: " + device.getDeviceName() +
                                "\nProduct Name: " + device.getProductName() +
                                "\nManufacturer: " + device.getManufacturerName() +
                                "\nProduct Id: " + device.getProductId() +
                                "\nSerial Number: " + device.getSerialNumber() +
                                "\nVendor Id: " + device.getVendorId() +
                                "\nVersion: " + device.getVersion() +
                                "\nDevice Class: " + device.getDeviceClass() +
                                "\nDevice Protocol: " + device.getDeviceProtocol() +
                                "\nDevice Subclass: " + device.getDeviceSubclass()
                        ;

                infoDevice.setText(infoDevice_);
            } else { infoDevice.setText("?"); }
            devicesListWindow.dismiss();
        });
        //Open devices list
        findViewById(R.id.main_button_open_devices_list).setOnClickListener((view) -> {
            viewDevices.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.simple_list_item_1, android.R.id.text1, deviceList.getDevicesList(DeviceInfo.DeviceReturnType.DEVICE_LIST_USB, true).keySet().toArray()));
            devicesListWindow.show();
        });
    }
}
