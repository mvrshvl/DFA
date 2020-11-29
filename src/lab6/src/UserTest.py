import unittest

from src.lexer import Lexer
from src.parser import Parser

class MyTestCase(unittest.TestCase):
    def test_a(self):
        expression = """4 ** 2 ^ 2"""
        test(expression)

    def test_b(self):
        expression = """1 # 3"""
        test(expression)

    def test_с(self):
        expression = """1 ? 3"""
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
