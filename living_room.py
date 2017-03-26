import json
import RPi.GPIO as GPIO
from time import sleep
LIGHT_1=0
LIGHT_2=0
LIGHT_3=0
FAN_1=0
FAN_2=0
TV=0
DOOR=0
WIFI=0
PIN_LIGHT_1=3
PIN_LIGHT_2=5
PIN_LIGHT_3=7
PIN_FAN_1=11
PIN_FAN_2=13
PIN_TV=15
PIN_DOOR=19
PIN_WIFI=21
GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)
GPIO.setup(PIN_LIGHT_1,GPIO.OUT)
GPIO.setup(PIN_LIGHT_2,GPIO.OUT)
GPIO.setup(PIN_LIGHT_3,GPIO.OUT)
GPIO.setup(PIN_FAN_1,GPIO.OUT)
GPIO.setup(PIN_FAN_2,GPIO.OUT)
GPIO.setup(PIN_TV,GPIO.OUT)
GPIO.setup(PIN_DOOR,GPIO.OUT)
GPIO.setup(PIN_WIFI,GPIO.OUT)
p1 = GPIO.PWM(PIN_LIGHT_1,50)
p2 = GPIO.PWM(PIN_LIGHT_2,50)
p3 = GPIO.PWM(PIN_LIGHT_3,50)
p4 = GPIO.PWM(PIN_FAN_1,50)
p5 = GPIO.PWM(PIN_FAN_1,50)
p6 = GPIO.PWM(PIN_TV,50)
p7 = GPIO.PWM(PIN_DOOR,50)
p8 = GPIO.PWM(PIN_WIFI,50)
p1.start(1)
p2.start(1)
p3.start(1)
p4.start(1)
p5.start(1)
p6.start(1)
p7.start(1)
p8.start(1)

def send():
    obj = json.dumps({'ROOM': "Living_Room", 'LIGHT_1': LIGHT_1, 'LIGHT_2': LIGHT_2, 'LIGHT_3': LIGHT_3, 'FAN_1': FAN_1,
                      'FAN_2': FAN_2, 'TV': TV, 'DOOR': DOOR,'WIFI':WIFI}, indent=2, separators=(',', ': '))
    print obj
    return obj
    
def rec(data):
    global LIGHT_1, LIGHT_2, LIGHT_3, FAN_1, FAN_2, TV, DOOR, WIFI
    a = json.loads(data)
    for key,value in a.items():
        if key == "LIGHT_1":
            LIGHT_1 = value
        if key == "LIGHT_2":
            LIGHT_2 = value
        if key == "LIGHT_3":
            LIGHT_3 = value
        if key == "FAN_1":
            FAN_1 = value
        if key == "FAN_2":
            FAN_2 = value
        if key == "TV":
            TV = value
        if key == "DOOR":
            DOOR = value
     
    p1.ChangeDutyCycle(LIGHT_1)
    p2.ChangeDutyCycle(LIGHT_2)
    p3.ChangeDutyCycle(LIGHT_3)
    p4.ChangeDutyCycle(FAN_1)
    p5.ChangeDutyCycle(FAN_2)
    p6.ChangeDutyCycle(TV)
    p7.ChangeDutyCycle(DOOR)
    p8.ChangeDutyCycle(WIFI)
