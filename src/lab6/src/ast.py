import math


class Number():
    def __init__(self, value):
        self.value = value

    def eval(self):
        return int(self.value)


class BinaryOp():
    def __init__(self, left, right):
        self.left = left
        self.right = right


class Pow(BinaryOp):
    def eval(self):
        return self.left.eval() ** self.right.eval()


class Percent(BinaryOp):
    def eval(self):
        if self.right.eval() != 0:
            return str(int((self.left.eval() / self.right.eval())*100))+"%"
        else:
            return 0


class Max(BinaryOp):
    def eval(self):
        return max(self.left.eval(),self.right.eval())


class Error(BinaryOp):
    def eval(self):
        return "UNKNOWN OPERATION"

class Print():
    def __init__(self, value):
        self.value = value

    def eval(self):
        print(self.value.eval())
