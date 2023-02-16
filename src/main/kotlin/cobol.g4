grammar cobol;

// A valid COBOL word cannot start or end with a dash or underscore and must have at least one letter
COBOL_WORD: (DIGIT (DIGIT | [-_])*)? LETTER ([-_]? ALPHANUMERIC)*;

// A non-numeric literal can be delimited by a pair of apostrophes or quotes. Doubling a quote or apostrophe can escape it,
// and a non-numeric literal must have at least one character inside.
NONNUMERIC: '\'' (SPECIAL | LETTER | DIGIT | QUOTE | (APOST APOST))+ '\''
          | '"' (SPECIAL | LETTER | DIGIT | APOST | (QUOTE QUOTE))+ '"';

NUMERIC: FIXEDPOINT | FLOATINGPOINT;
FIXEDPOINT: SIGN? DIGIT+ ('.' DIGIT+)?;
FLOATINGPOINT: SIGN? DIGIT+ ('.' DIGIT+)? 'E' SIGN? DIGIT+;

// All valid characters in COBOL.
CHARACTER: SPECIAL | LETTER | DIGIT | APOST | QUOTE;
ALPHANUMERIC: LETTER | DIGIT;
LETTER: LOWERCASE | UPPERCASE;

SPECIAL: [ +\-*/=$,;.()><:_];
APOST: '\'';
QUOTE: '"';
SIGN: [+-];
DIGIT: [0-9];
LOWERCASE: [a-z];
UPPERCASE: [A-Z];