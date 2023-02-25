grammar Cobol;
import Keywords;

// Root rule
program: id_div data_div? proc_div? EOF;

// --- IDENTIFICATION DIVISION ---
id_div: IDENTIFICATION DIVISION DOT
        INDENT program_id (INDENT id_clause)*;

program_id: PROGRAM_ID DOT ID_DIV_VALUE DOT;

id_clause: ID_DIV_NAME DOT ID_DIV_VALUE DOT;

// --- DATA DIVISION ---
data_div: DATA DIVISION DOT data_entry+;

data_entry: LEVEL identifier (picture_clause | like_clause)? occurs_clause?;

picture_clause: PICTURE IS REPRESENTATION;

like_clause: LIKE identifier;

occurs_clause: OCCURS INT TIMES;

// --- PROCEDURE DIVISION ---
proc_div: PROCEDURE DIVISION (using_clause)? DOT
          (INDENT sentence)*
          paragraph+;

paragraph: procedure_name DOT (INDENT sentence)+;

sentence: statement+ DOT;

statement: ACCEPT identifier+                                                                #acceptStat
         | ADD (atomic)+ TO atomic giving_clause*                                            #addStat
         | ALTER procedure_name TO PROCEED TO procedure_name                                 #alterStat
         | CALL ID_DIV_NAME using_clause?                                                    #callStat
         | DISPLAY display_clause+ (WITH NO ADVANCING)?                                      #displayStat
         | DIVIDE atomic INTO atomic+ (giving_clause+ | giving_clause REMAINDER identifier)? #divideStat
         | EVALUATE any_expression also_clause* when_block+ END                              #evaluateStat
         | GO TO procedure_name                                                              #gotoStat
         | IF boolean_expression THEN statement+ (ELSE statement+)? END                      #ifStat
         | LOOP loop_statement* END                                                          #loopStat
         | MOVE move_expression TO identifier+                                               #moveStat
         | MULTIPLY atomic BY atomic+ giving_clause?                                         #multiplyStat
         | NEXT SENTENCE                                                                     #nextStat
         | PERFORM procedure_name (THROUGH procedure_name)? (atomic TIMES)?                  #performStat
         | SIGNAL signal_expression ON ERROR                                                 #signalStat
         | STOP                                                                              #stopStat
         | SUBTRACT (atomic)+ FROM (atomic+ | atomic giving_clause+)                         #subtractStat
         ;

signal_expression: OFF            #offSignal
                 | procedure_name #procedureSignal
                 ;

also_clause: ALSO any_expression;

display_clause: atomic (delimited_expression)?;

delimited_expression : DELIMITED BY SPACE   #spaceDelimited
                     | DELIMITED BY SIZE    #sizeDelimited
                     | DELIMITED BY literal #literalDelimited
                     ;

giving_clause: GIVING identifier;

loop_statement: VARYING identifier? (FROM atomic)? (TO atomic)? (BY atomic)? #varyingLoop
              | WHILE boolean_expression                                     #whileLoop
              | UNTIL boolean_expression                                     #untilLoop
              | statement                                                    #statLoop
              ;

move_expression: atomic      #atomicMove
               | HIGH VALUES #highMove
               | LOW VALUES  #lowMove
               | SPACES      #spacesMove
               ;

using_clause: USING (by_clause)+;

by_clause: BY REFERENCE identifier #referenceBy
         | BY CONTENT atomic       #contentBy
         | BY VALUE atomic         #valueBy
         ;

when_block: when_clause statement+;

when_clause: WHEN (when_expression (ALSO when_expression)*) #exprWhen
           | WHEN OTHER                                     #otherWhen
           ;

when_expression: atomic (THROUGH atomic)*;

any_expression: arithmetic_expression #arithmeticExpr
              | string_expression     #stringExpr
              | boolean_expression    #booleanExpr
              ;

arithmetic_expression: ;

string_expression: ;

boolean_expression: ;

procedure_name: ;

atomic: literal    #literalAtom
      | identifier #identifierAtom
      ;

literal: NUMERIC    #numericLit
       | NONNUMERIC #nonnumericLit
       ;

identifier: COBOL_WORD               #primitiveId
          | identifier OF identifier #qualifiedId // Maybe this works better with (OF identifier)+
          | identifier LPAR INT RPAR #indexId
          ;

// Special tokens

ID_DIV_NAME: ID_DIV_CHAR+; // Identification Division names and values, anything but a dot
ID_DIV_VALUE: ID_DIV_CHAR+;

REPRESENTATION: ([9AXZ](LPAR INT RPAR)?)+ // A valid representation string. S and V may only occur once, number in parentheses may shorthand repetition.
              | ([9AXZ](LPAR INT RPAR)?)*'S'([9AXZ](LPAR INT RPAR)?)*('V'([9AXZ](LPAR INT RPAR)?)*)?
              | ([9AXZ](LPAR INT RPAR)?)*'V'([9AXZ](LPAR INT RPAR)?)*('S'([9AXZ](LPAR INT RPAR)?)*)?
              ;

LEVEL: DIGIT DIGIT;
INT: DIGIT+;
LPAR: '(';
RPAR: ')';
INDENT: '\u00b7\u00b7\u00b7\u00b7'; // \u00b7 is ·, a 'fake space' inserted by the preprocessor
DOT: '.';

// --- LITERALS ---
// A valid COBOL word cannot start or end with a dash or underscore and must have at least one letter
COBOL_WORD: (DIGIT (DIGIT | [-_])*)? LETTER ([-_]* ALPHANUMERIC)*;

// A non-numeric literal can be delimited by a pair of apostrophes or quotes. Doubling a quote or apostrophe can escape it,
// and a non-numeric literal must have at least one character inside.
NONNUMERIC: '\'' (SPECIAL | LETTER | DIGIT | QUOTE | (APOST APOST) | ' ')+ '\''
          | '"' (SPECIAL | LETTER | DIGIT | APOST | (QUOTE QUOTE) | ' ')+ '"';

NUMERIC: SIGN? DIGIT+ (DOT DIGIT+)?;

// --- Character classes and sets ---
fragment ALPHANUMERIC: LETTER | DIGIT;
fragment LETTER: LOWERCASE | UPPERCASE;
fragment PAR: LPAR | RPAR;

fragment SPECIAL: [+\-*/=$,;><:_];
fragment APOST: '\'';
fragment QUOTE: '"';
// All valid characters in COBOL.
fragment CHARACTER: SPECIAL | LETTER | DIGIT | DOT | APOST | QUOTE | PAR;
fragment ID_DIV_CHAR: SPECIAL | LETTER | DIGIT | APOST | QUOTE | PAR; // Anything except a DOT (.)
fragment SIGN: [+-];
fragment DIGIT: [0-9];
fragment LOWERCASE: [a-z];
fragment UPPERCASE: [A-Z];
WS: [ \r\n]+ -> skip;
