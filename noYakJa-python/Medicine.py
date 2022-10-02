from distutils.errors import DistutilsInternalError


from Eat import Eat

class Medicine:

    morning=None
    lunch=None
    dinner=None

    def __init__(self,medicine) -> None:
        self.morning = Eat(medicine['morning'])
        self.lunch = Eat(medicine['lunch'])
        self.dinner = Eat(medicine['dinner'])




