lexer grammar Keywords;

ACCEPT: 'ACCEPT';
ADD: 'ADD';
ADVANCING: 'ADVANCING';
ALSO: 'ALSO';
ALTER: 'ALTER';
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
OCCURS: 'OCCURS';
OF: 'OF';
OFF: 'OFF';
ON: 'ON';
OTHER: 'OTHER';
PERFORM: 'PERFORM';
PICTURE: 'PICTURE';
PROCEDURE: 'PROCEDURE';
PROCEED: 'PROCEED';
PROGRAM_ID: 'PROGRAM-ID';
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
UNTIL: 'UNTIL';
USING: 'USING';
VALUE: 'VALUE';
VALUES: 'VALUES';
VARYING: 'VARYING';
WHEN: 'WHEN';
WHILE: 'WHILE';
WITH: 'WITH';

// Special tokens

// A valid COBOL word cannot start or end with a dash or underscore and must have at least one letter
COBOL_WORD: (DIGIT (DIGIT | DASH)*)? LETTER (DASH* ALPHANUMERIC)*;

REPRESENTATION: ([9AXZ](LPAR INT RPAR)?)+ // A valid representation string. S and V may only occur once, number in parentheses may shorthand repetition.
              | ([9AXZ](LPAR INT RPAR)?)*'S'([9AXZ](LPAR INT RPAR)?)*('V'([9AXZ](LPAR INT RPAR)?)*)?
              | ([9AXZ](LPAR INT RPAR)?)*'V'([9AXZ](LPAR INT RPAR)?)*('S'([9AXZ](LPAR INT RPAR)?)*)?
              ;

LEVEL: DIGIT DIGIT;
INT: DIGIT+;
LPAR: '(';
RPAR: ')';
DOT: '.';

// --- LITERALS ---

// A non-numeric literal can be delimited by a pair of apostrophes or quotes. Doubling a quote or apostrophe can escape it,
// and a non-numeric literal must have at least one character inside.
NONNUMERIC: '\'' (SPECIAL | LETTER | DIGIT | QUOTE | (APOST APOST) | ' ')+ '\''
          | '"' (SPECIAL | LETTER | DIGIT | APOST | (QUOTE QUOTE) | ' ')+ '"';

NUMERIC: SIGN? DIGIT+ (DOT DIGIT+)?;

ID_DIV_WORD: ID_DIV_CHAR+; // Identification Division names and values, anything but a dot

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
fragment ID_DIV_CHAR: SPECIAL | LETTER | DIGIT | APOST | QUOTE | PAR; // Anything except a DOT (.)
fragment SIGN: [+-];
fragment DIGIT: [0-9];
fragment LOWERCASE: [a-z];
fragment UPPERCASE: [A-Z];