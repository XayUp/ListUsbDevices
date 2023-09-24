package com.xayup.listusbdevices;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

public class DeviceInfo {
    @IntDef({DeviceReturnType.DEVICE_LIST_MIDI, DeviceReturnType.DEVICE_LIST_USB})
    @Retention(RetentionPolicy.SOURCE)
    @interface DeviceReturnType { byte DEVICE_LIST_USB = 0; byte DEVICE_LIST_MIDI = 1; }

    @DeviceReturnType
    protected byte TYPE_RETURN;

    public Object device;
    public byte device_type;

    public DeviceInfo(UsbDevice usbDevice){
        this.device = usbDevice;
        this.device_type = DeviceReturnType.DEVICE_LIST_USB;
    }

    public DeviceInfo(MidiDeviceInfo midiDeviceInfo){
        this.device = midiDeviceInfo;
        this.device_type = DeviceReturnType.DEVICE_LIST_MIDI;
    }
    public String getDeviceSubclass(){
        if (device_type == DeviceReturnType.DEVICE_LIST_MIDI){
            UsbDevice usb = ((MidiDeviceInfo) device).getProperties().getParcelable(MidiDeviceInfo.PROPERTY_USB_DEVICE);
            if(usb != null) return String.valueOf(usb.getDeviceProtocol());
        } else if (device_type == DeviceReturnType.DEVICE_LIST_USB) {
            return String.valueOf(((UsbDevice) device).getDeviceProtocol());
        }
        return null;
    }
    public String getDeviceClass() {
        if (device_type == DeviceReturnType.DEVICE_LIST_MIDI){
            UsbDevice usb = ((MidiDeviceInfo) device).getProperties().getParcelable(MidiDeviceInfo.PROPERTY_USB_DEVICE);
            if(usb != null) return String.valueOf(usb.getDeviceClass());
        } else if (device_type == DeviceReturnType.DEVICE_LIST_USB) {
            return String.valueOf(((UsbDevice) device).getDeviceClass());
        }
        return null;
    }
    public String getVendorId(){
        if (device_type == DeviceReturnType.DEVICE_LIST_MIDI){
            UsbDevice usb = ((MidiDeviceInfo) device).getProperties().getParcelable(MidiDeviceInfo.PROPERTY_USB_DEVICE);
            if(usb != null) return String.valueOf(usb.getVendorId());
        } else if (device_type == DeviceReturnType.DEVICE_LIST_USB) {
            return String.valueOf(((UsbDevice) device).getVendorId());
        }
        return null;
    }
    public String getDeviceName() {
        return (device_type == DeviceReturnType.DEVICE_LIST_MIDI)
                ? ((MidiDeviceInfo) device).getProperties().getString(MidiDeviceInfo.PROPERTY_PRODUCT)
                : (device_type == DeviceReturnType.DEVICE_LIST_USB)
                ? ((UsbDevice) device).getProductName()
                : null;
    }

    public String getDeviceProtocol(){
        if (device_type == DeviceReturnType.DEVICE_LIST_MIDI){
            UsbDevice usb = ((MidiDeviceInfo) device).getProperties().getParcelable(MidiDeviceInfo.PROPERTY_USB_DEVICE);
            if(usb != null) return String.valueOf(usb.getDeviceProtocol());
        } else if (device_type == DeviceReturnType.DEVICE_LIST_USB) {
            return String.valueOf(((UsbDevice) device).getDeviceProtocol());
        }
        return null;
    }
    public String getManufacturerName(){
        return (device_type == DeviceReturnType.DEVICE_LIST_MIDI)
                ? ((MidiDeviceInfo) device).getProperties().getString(MidiDeviceInfo.PROPERTY_MANUFACTURER)
                : (device_type == DeviceReturnType.DEVICE_LIST_USB)
                ? ((UsbDevice) device).getManufacturerName()
                : null;
    }
    public String getProductId(){
        return (device_type == DeviceReturnType.DEVICE_LIST_MIDI)
                ? String.valueOf(((MidiDeviceInfo) device).getId())
                : (device_type == DeviceReturnType.DEVICE_LIST_USB)
                ? String.valueOf(((UsbDevice) device).getProductId())
                : null;
    }
    public String getProductName(){
        return (device_type == DeviceReturnType.DEVICE_LIST_MIDI)
                ? ((MidiDeviceInfo) device).getProperties().getString(MidiDeviceInfo.PROPERTY_NAME)
                : (device_type == DeviceReturnType.DEVICE_LIST_USB)
                ? ((UsbDevice) device).getProductName()
                : null;
    }
    public String getSerialNumber(){
        return (device_type == DeviceReturnType.DEVICE_LIST_MIDI)
                ? ((MidiDeviceInfo) device).getProperties().getString(MidiDeviceInfo.PROPERTY_SERIAL_NUMBER)
                : (device_type == DeviceReturnType.DEVICE_LIST_USB)
                ? ((UsbDevice) device).getSerialNumber()
                : null;
    }
    public String getVersion(){
        return (device_type == DeviceReturnType.DEVICE_LIST_MIDI)
                ? ((MidiDeviceInfo) device).getProperties().getString(MidiDeviceInfo.PROPERTY_VERSION)
                : (device_type == DeviceReturnType.DEVICE_LIST_USB)
                ? ((UsbDevice) device).getVersion()
                : null;
    }
}
