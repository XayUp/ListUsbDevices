package com.xayup.listusbdevices;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;

import java.util.HashMap;
import java.util.Map;

import com.xayup.listusbdevices.DeviceInfo.DeviceReturnType;

public class DevicesList {

    private final UsbManager usbManager;
    private final MidiManager midiManager;

    private final HashMap<String, DeviceInfo> devices;
    private byte current_list_type;

    public DevicesList(Context context){
        this.usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        this.midiManager = (MidiManager) context.getSystemService(Context.MIDI_SERVICE);
        this.current_list_type = 127;
        this.devices = new HashMap<>();
    }

    @DeviceReturnType
    public byte getCurrentListType(){ return current_list_type; }

    public HashMap<String, DeviceInfo> getDevicesList(@DeviceReturnType byte DEVICE_TYPE, boolean update){
        if(update) {
            devices.clear();
            if (DEVICE_TYPE == DeviceInfo.DeviceReturnType.DEVICE_LIST_USB)
                for (UsbDevice usb : usbManager.getDeviceList().values())
                    devices.put(usb.getProductName(), new DeviceInfo(usb));
            else if (DEVICE_TYPE == DeviceInfo.DeviceReturnType.DEVICE_LIST_MIDI)
                for (MidiDeviceInfo midi : midiManager.getDevices())
                    devices.put(midi.getProperties().getString(MidiDeviceInfo.PROPERTY_PRODUCT), new DeviceInfo(midi));
            current_list_type = DeviceReturnType.DEVICE_LIST_USB;
        } else if(DEVICE_TYPE != current_list_type){ return new HashMap<>(); }
        return devices;
    }
}
