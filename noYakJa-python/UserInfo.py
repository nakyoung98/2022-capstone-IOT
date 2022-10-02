from plistlib import UID


class UserInfo:
    
    uid = ""
    name = ""
    phone = ""

    def __init__(self,uid,phone, name):
        self.name = name
        self.phone = phone
        self.uid = uid

    def toString(self):
        return 'uid: '+self.uid + ', phone: '+ self.phone + ', name: ' + self.name

    def getUid(self):
        return self.uid