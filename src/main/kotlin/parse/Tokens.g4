lexer grammar Tokens;
/*
 This is the Lexxer
*/
// --- Keywords (reserved) ---------------------------------------------
ACCEPT: 'ACCEPT';
ADD: 'ADD';
ADVANCING: 'ADVANCING';
ALSO: 'ALSO';
ALTER: 'ALTER';
AND: 'AND';
BY: 'BY';
CALL: 'CALL';
CONTENT: 'CONTENT';
DATA: 'DATA';
DELIMITED: 'DELIMITED';
DISPLAY: 'DISPLAY';
DIVIDE: 'DIVIDE';
DIVISION: 'DIVISION';
ELSE: 'ELSE';
END: 'END';
ERROR: 'ERROR';
EVALUATE: 'EVALUATE';
FALSE: 'FALSE';
FROM: 'FROM';
GIVING: 'GIVING';
GO: 'GO';
HIGH: 'HIGH';
IDENTIFICATION: 'IDENTIFICATION';
IF: 'IF';
INTO: 'INTO';
IS: 'IS';
LIKE: 'LIKE';
LOOP: 'LOOP';
LOW: 'LOW';
MOVE: 'MOVE';
MULTIPLY: 'MULTIPLY';
NEXT: 'NEXT';
NO: 'NO';
NOT: 'NOT';
OCCURS: 'OCCURS';
OF: 'OF';
OFF: 'OFF';
ON: 'ON';
OR: 'OR';
OTHER: 'OTHER';
PERFORM: 'PERFORM';
PICTURE: 'PICTURE';
PROCEDURE: 'PROCEDURE';
PROCEED: 'PROCEED';
//PROGRAM_ID: 'PROGRAM-ID';
REFERENCE: 'REFERENCE';
REMAINDER: 'REMAINDER';
SENTENCE: 'SENTENCE';
SIGNAL: 'SIGNAL';
SIZE: 'SIZE';
SPACE: 'SPACE';
SPACES: 'SPACES';
STOP: 'STOP';
SUBTRACT: 'SUBTRACT';
THEN: 'THEN';
THROUGH: 'THROUGH';
TIMES: 'TIMES';
TO: 'TO';
TRUE: 'TRUE';
UNTIL: 'UNTIL';
USING: 'USING';
VALUE: 'VALUE';
VALUES: 'VALUES';
VARYING: 'VARYING';
WHEN: 'WHEN';
WHILE: 'WHILE';
WITH: 'WITH';
XOR: 'XOR';
// --- END Keywords ----------------------------------------------------

// A valid COBOL word cannot start or end with a dash or underscore and must have at least one letter
COBOL_WORD: (DIGIT (DIGIT | DASH)*)? LETTER (DASH* ALPHANUMERIC)*;

REPRESENTATION: ([9AXZ](LPAR INT RPAR)?)+ // A valid representation string. S and V may only occur once, number in parentheses may shorthand repetition.
              | ([9AXZ](LPAR INT RPAR)?)*'S'([9AXZ](LPAR INT RPAR)?)*('V'([9AXZ](LPAR INT RPAR)?)*)?
              | ([9AXZ](LPAR INT RPAR)?)*'V'([9AXZ](LPAR INT RPAR)?)*('S'([9AXZ](LPAR INT RPAR)?)*)?
              ;

// Special tokens
INDEX: LPAR INT RPAR;
LEVEL: DIGIT DIGIT;
fragment INT: DIGIT+;
fragment LPAR: '(';
fragment RPAR: ')';
DOT: '.';

// --- LITERALS ---

// A non-numeric literal can be delimited by a pair of apostrophes or quotes. Doubling a quote or apostrophe can escape it,
// and a non-numeric literal must have at least one character inside.
NONNUMERIC: '\'' (SPECIAL | LETTER | DIGIT | QUOTE | (APOST APOST) | ' ')+ '\''
          | '"' (SPECIAL | LETTER | DIGIT | APOST | (QUOTE QUOTE) | ' ')+ '"';

NUMERIC: S? INT (V INT)?;

// Operators
ARITHMETIC_OP: PLUS | MINUS | MULT | DIV | POW;
PLUS: '+' ; // Must be a token instead of fragment, as it is also used in string_expression
fragment MINUS: '-' ;
fragment MULT: '*' ;
fragment DIV: '/' ;
fragment POW: '**' ;

COMPARISON_OP: EQ | GT | LT | GE | LE;
fragment EQ: '=';
fragment GT: '>';
fragment LT: '<';
fragment GE: '>=';
fragment LE: '<=';

BOOLEAN_OP: OR | AND | XOR;

// --- Character classes and sets ---
fragment ALPHANUMERIC: LETTER | DIGIT;
fragment LETTER: LOWERCASE | UPPERCASE;
fragment PAR: LPAR | RPAR;

fragment SPECIAL: [+*/=$,;><:];
fragment DASH: [-_];
fragment APOST: '\'';
fragment QUOTE: '"';
// All valid characters in COBOL.
fragment CHARACTER: SPECIAL | LETTER | DIGIT | DOT | APOST | QUOTE | PAR | DASH;
fragment ID_DIV_CHAR: SPECIAL | LETTER | DIGIT | APOST | QUOTE | PAR | DASH | [ \t]; // Anything except a DOT (.) or space
fragment V: [.,]; // Decimal separator
fragment S: [+-]; // Sign
fragment DIGIT: [0-9];
fragment LOWERCASE: [a-z];
fragment UPPERCASE: [A-Z];