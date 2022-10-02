from sqlalchemy import false


from Time import Time

class Eat:
    isEat=false
    time=None

    def __init__(self,dic) -> None:
        self.isEat = dic['isEat']
        self.time = Time(dic['time'])