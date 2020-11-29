import unittest

from src.lexer import Lexer
from src.parser import Parser

class MyTestCase(unittest.TestCase):
    def test_a1(self):
        expression = """4 ** 2 ^ 2"""
        test(expression)

    def test_a2(self):
        expression = """4 ** 0"""
        test(expression)


    def test_a3(self):
        expression = """0 ** 2"""
        test(expression)

    def test_a4(self):
        expression = """0 ** 0"""
        test(expression)

    def test_a5(self):
        expression = """5 ** -2"""
        test(expression)

    def test_b1(self):
        expression = """1 # 3"""
        test(expression)

    def test_b2(self):
        expression = """0 # 2"""
        test(expression)

    def test_b3(self):
        expression = """2 # 0"""
        test(expression)

    def test_b4(self):
        expression = """0 # 0"""
        test(expression)

    def test_b5(self):
        expression = """2 # -2"""
        test(expression)

    def test_с1(self):
        expression = """1 ? 3"""
        test(expression)

    def test_с2(self):
        expression = """2 ? 0"""
        test(expression)

    def test_с3(self):
        expression = """0 ? 0"""
        test(expression)

    def test_с4(self):
        expression = """-10 ? 5"""
        test(expression)

    def test_с5(self):
        expression = """-10 ? -20"""
        test(expression)

if __name__ == '__main__':
    unittest.main()

def test(expr):
    text_input = """
        print("""+expr+""");
        """
    print("****************************")
    print("EXPRESSION " + expr)
    lexer = Lexer().get_lexer()
    tokens = lexer.lex(text_input)

    pg = Parser()
    pg.parse()
    parser = pg.get_parser()
    parser.parse(tokens).eval()

