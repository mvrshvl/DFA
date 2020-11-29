from rply import LexerGenerator

class Lexer():
    def __init__(self):
        self.lexer = LexerGenerator()

    def _add_tokens(self):
        # Print
        self.lexer.add('PRINT', r'print')
        # Скобки
        self.lexer.add('OPEN_PARENS', r'\(')
        self.lexer.add('CLOSE_PARENS', r'\)')
        # Точка с запятой
        self.lexer.add('SEMI_COLON', r'\;')
        # Операторы
        self.lexer.add('POW', r'\*\*')
        self.lexer.add('POW', r'\^')
        self.lexer.add('PERCENT', r'\#')
        self.lexer.add('MAX', r'\?')
        # Числа
        self.lexer.add('NUMBER', r'\d+')
        self.lexer.add('NEGATIVE', r'\-\d+')
        self.lexer.add('ERROR', r'\D+')

    # Игнорируем пробелы
        self.lexer.ignore('\s+')

    def get_lexer(self):
        self._add_tokens()
        return self.lexer.build()

    def Check_tokens(tokens):
        for token in tokens:
            if token.name == 'ERROR':
                print("ERROR : INCORRECT EXPRESSION\n")
                return True