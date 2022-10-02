from logging import captureWarnings
import cv2
from cv2 import imwrite
from datetime import datetime
import os

class Check:
    
    capture = None
    path = "E:\develop\2022-capstone-IOT\noYakJa-python\image"

    def __init__(self):
        self.capture = cv2.VideoCapture(0)
        self.capture.set(cv2.CAP_PROP_FRAME_WIDTH, 640)
        self.capture.set(cv2.CAP_PROP_FRAME_WIDTH, 480)


    def captures(self):
        while True:
            ret, frame = self.capture.read()

            if ret:
                cv2.imshow("videoFrame",frame)
                if cv2.waitKey(33) != -1: 
                    now = datetime.now()
                    now = now.strftime('%Y_%m_%d_%H_%M_%S')
                    filename = "image"+now+".png"
                    print(filename)
                    #imwrite(os.path.join(self.path,"image"+now+".png"),frame)
                    cv2.imwrite("noYakJa-python/image/"+now+".png",frame)
                    #cv2.imwrite(filename,frame)


        self.capture.release()
        cv2.destroyAllWindows()




camera = Check()
camera.captures()

