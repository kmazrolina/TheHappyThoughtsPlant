# The Happy Thoughts Plant
A simple system using Android and Ardiuno for a plant-shaped device that can help release stress and anxiety.


![The Happy Thoughts Plant](https://github.com/kmazrolina/TheHappyThoughtsPlant/assets/121491288/620b030f-f661-475a-a2c3-23bf4a5352d2)

## Description
This system consists of an Android application and an Arduino circuit. The Android app provides various activities such as Landing Activity, Calming Chat (uses ChatGPT API), Music Therapy, and Nature Sounds. The Arduino circuit, connected to the Android device via Bluetooth, allows you to control the app activities using three buttons.

## Android App

### Requirements
- Android device with Bluetooth enabled
- Internet connection

### Installation
1. Navigate to the 'Android' directory.

2. Install the required dependencies for the Android app.

3. Navigate to Android/app/src/main/java/com/example/plantchat/ChatActivity.java. In line 124 enter your ChatGPT API key. 

4. Navigate to Android/app/src/main/java/com/example/plantchat/LandingActivity.java. In line 42 Enter UUID. In line 45 enter your device name. 

5. Build and install the Android app on your device.

### Running the App
1. Make sure your Android device is connected to the internet and Bluetooth is enabled.

2. Launch the app on your Android device.

3. The app will start with the Landing Activity.

4. To access other activities, use the buttons on the Arduino circuit. Each button corresponds to a specific activity.

## Arduino Circuit

### Requirements
- Arduino board
- Bluetooth module (compatible with your Arduino board)
- 3 buttons
- Wiring connections

### Wiring
1. Connect the Bluetooth module to your Arduino board following the appropriate wiring instructions.

2. Connect the 3 buttons to the Arduino board, ensuring the proper connections.

### Arduino Program
1. Open the Arduino IDE.

2. Navigate to the 'Arduino' directory.

3. Open the Arduino program file.

4. Upload the program to your Arduino board.

5. Ensure that your Arduino board is powered and properly connected to the Android device via Bluetooth.

6. Press the buttons on the Arduino circuit to send commands to the Android app and switch between activities.


## Credits
- The Calming Chat activity with ChatGPT API was created based on the tutorial by youtube creator [Easy Tuto](https://www.youtube.com/@EasyTuto1). You can find the tutorial [here](https://www.youtube.com/watch?v=ahhze_u5ZUs&t=1s).


