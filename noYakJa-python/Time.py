class Time:
    hour=0
    min=0

    def __init__(self,dic) -> None:
        self.hour = dic['hour']
        self.min = dic['min']

    def toString(self):
        if self.min < 10:
            return str(self.hour)+":0"+str(self.min)
        else:
            return str(self.hour)+":"+str(self.min)
