from src.lexer import Lexer
from src.parser import Parser

text_input = """
print(4 ** 2 ^ 2);
"""
print("***********ANSWER***********")
lexer = Lexer().get_lexer()
tokens = lexer.lex(text_input)

pg = Parser()
pg.parse()
parser = pg.get_parser()
parser.parse(tokens).eval()
print("****************************")
