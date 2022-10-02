import collections
import firebase_admin
from firebase_admin import auth
from firebase_admin import credentials
from firebase_admin import db
from firebase_admin import firestore

from UserInfo import UserInfo
from Medicine import Medicine

class FirebaseServer:

    userInfo = ""
    
    config = {
        "apiKey": "AIzaSyCEP6BSpyJCzIh-O7GvdoFlBigoEtL3_DM",
        "authDomain": "noyakja-829d0.firebaseapp.com",
        "databaseURL": "https://noyakja-829d0-default-rtdb.asia-southeast1.firebasedatabase.app",
        "storageBucket": "noyakja-829d0.appspot.com",
        "serviceAccount": "E://develop/2022-capstone-IOT/noYakJa-python/noyakja-829d0-firebase-adminsdk-kthvb-3c2b33640b.json"
    }

    default_app = None
    database = None
    datastore = None

    def __init__(self, phone):

        #self.firebase = pyrebase.initialize_app(self.config)
        cred = credentials.Certificate("E://develop/2022-capstone-IOT/noYakJa-python/noyakja-829d0-firebase-adminsdk-kthvb-3c2b33640b.json")
        self.default_app = firebase_admin.initialize_app(cred, {'databaseURL': "https://noyakja-829d0-default-rtdb.asia-southeast1.firebasedatabase.app"})
        print(self.default_app.name)

        #self.default_app.auth = firebase_admin.get_app
        user = auth.get_user_by_phone_number(phone)

        #pyrebase.initialize_app()
        print('Successfully fetched user data: {0}'.format(user.uid))

        self.database = db.reference('user/'+user.uid)
        snapshot = self.database.get()

        self.userInfo  = UserInfo(user.uid, snapshot['phone'], snapshot['name'])
        print(self.userInfo.toString())
        
        #user 불러오기 및 db 접근 완료



    def getMedicine(self):

        #doc_ref = db.collections('SettingMedicine').document(self.userInfo.getUid)
        self.datastore = firestore.client()
        print(self.userInfo.uid)
        doc_ref = self.datastore.collection(u'SettingMedicine').document(u''.join(self.userInfo.uid))
        docs = doc_ref.get()
        docs = docs.to_dict()

        medicine = Medicine(docs['medicine'])

        return medicine


