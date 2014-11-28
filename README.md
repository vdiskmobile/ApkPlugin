ApkPlugin
=========

ApkPlugin，Host Apk，calling plugin Apk，support Android 4.x and 5.0

How to Use

1、run TestHall project on your device;
2、export TestRoomA.apk TestRoomB.apk TestRoomC.apk one by one;
3、then switch shell,and execute:
adb  push /xxx/TestRoomB.apk /mnt/sdcard/test/TestRoomB.apk
adb  push /xxx/TestRoomA.apk /mnt/sdcard/test/TestRoomA.apk
adb  push /xxx/TestRoomC.apk /mnt/sdcard/test/TestRoomC.apk

by the way, xxx is my local dir

4、restart the Host Project named TestHall

