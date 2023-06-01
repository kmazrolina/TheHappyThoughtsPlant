#include <SoftwareSerial.h>
SoftwareSerial android(5,6);
int buttonPin_music = 10;
int buttonPin_nature = 11;
int buttonPin_chat = 12;
boolean isHit_1 = false;
boolean isHit_2 = false;
boolean isHit_3 = false;

void setup(){
  pinMode(buttonPin_music, INPUT_PULLUP);
  pinMode(buttonPin_nature, INPUT_PULLUP);
  pinMode(buttonPin_chat, INPUT_PULLUP);
  // Bluetooth用のシリアルのポートを設定
  android.begin(9600);
  // arduinoのシリアルモニタ用
  Serial.begin(9600);
  Serial.write("init");
}
void loop(){
  int buttonState_music = digitalRead(buttonPin_music);
  int buttonState_nature = digitalRead(buttonPin_nature);
  int buttonState_chat = digitalRead(buttonPin_chat); 
  boolean isPushed = false;
  if (buttonState_music == LOW) {
    if (!isHit_1) {
      isHit_1 = true;
      isPushed = true;
      android.write("m");
      Serial.write("m");
    }
  } else {
    isHit_1 = false;
  }

  if (buttonState_nature == LOW) {
    if (!isHit_2) {
      isHit_2 = true;
      isPushed = true;
      android.write("n");
      Serial.write("n");
    }
  } else {
    isHit_2 = false;
  }

  if (buttonState_chat == LOW) {
    if (!isHit_3) {
      isHit_3 = true;
      isPushed = true;
      android.write("c");
      Serial.write("c");
    }
  } else {
    isHit_3 = false;
  }

  if(android.available()){
    Serial.println(android.read());
  }
  if(Serial.available()){
    android.write(Serial.read());
  }
}
