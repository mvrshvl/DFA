from src.lexer import Lexer
from src.parser import Parser
import copy

while (True):
    expr = input("ENTER EXPRESSION - ")
    if str.lower(expr) == "quit":
        break

    text_input = """
        print(""" + expr + """);
        """

    lexer = Lexer().get_lexer()
    tokens = lexer.lex(text_input)

    tokens_copy = copy.copy(tokens)

    if not Lexer.Check_tokens(tokens_copy):
        pg = Parser()
        pg.parse()
        parser = pg.get_parser()
        parser.parse(tokens).eval()

print("BYE")

