bluetoothRC
===========

Remote Control your PC using mobile phone via bluetooth

Add bluecove.jar and bluecove-gpl.jar to the java class path.
You need to have the package libbluetooth-dev package installed.

Currently , bluetoothrc works only on linux as it uses a third party java bluetooth library called bluecove
which does not support L2CAP connection on winsock bluetooth stack.

1. First run :
  java RCserver
2. Install the client jar package to your mobile and conect to the server.
