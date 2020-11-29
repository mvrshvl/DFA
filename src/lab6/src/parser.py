from rply import ParserGenerator

from src.ast import Print, Pow, Number, Percent, Max, Error


class Parser():
    def __init__(self):
        self.pg = ParserGenerator(
            # Список всех токенов, принятых парсером.
            ['NUMBER', 'PRINT', 'OPEN_PARENS', 'CLOSE_PARENS',
             'SEMI_COLON', 'POW', 'PERCENT', 'MAX', 'NEGATIVE'],
            precedence=[
                ('left', ['POW', 'PERCENT', 'MAX']),
            ]
        )

    def get_parser(self):
        return self.pg.build()

    def parse(self):
        @self.pg.production('program : PRINT OPEN_PARENS expression CLOSE_PARENS SEMI_COLON')
        def program(p):
            return Print(p[2])

        @self.pg.production('expression : expression MAX expression')
        @self.pg.production('expression : expression PERCENT expression')
        @self.pg.production('expression : expression POW expression')
        def expression(p):
            left = p[0]
            right = p[2]
            operator = p[1]
            if operator.gettokentype() == 'POW':
                return Pow(left, right)
            elif operator.gettokentype() == 'PERCENT':
                return Percent(left, right)
            elif operator.gettokentype() == 'MAX':
                return Max(left, right)
            else:
                return Error()

        @self.pg.production('expression : NEGATIVE')
        @self.pg.production('expression : NUMBER')
        def number(p):
            return Number(p[0].value)

        @self.pg.error
        def error_handle(token):
            raise ValueError(token)
