import 'package:flutter_blue/flutter_blue.dart';

class BtDevice {
  String singleDeviceName;
  DeviceIdentifier id;
  BluetoothDeviceType type;

  BtDevice(this.singleDeviceName, this.id, this.type);

  @override
  bool operator ==(other) {
    return id.toString() == other.id.toString();
  }

  @override
  int get hashCode => id.hashCode;
}
