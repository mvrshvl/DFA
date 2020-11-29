from src.lexer import Lexer
from src.parser import Parser

while (True):
    expr = input("ENTER EXPRESSION - ")
    if str.lower(expr) == "quit":
        break

    text_input = """
        print(""" + expr + """);
        """

    lexer = Lexer().get_lexer()
    tokens = lexer.lex(text_input)

    error = False

    for token in tokens:
        if token.name == 'ERROR':
            print("ERROR : INCORRECT EXPRESSION\n")
            error = True
            break

    if not error:
        pg = Parser()
        pg.parse()
        parser = pg.get_parser()
        parser.parse(tokens).eval()

print("BYE")
