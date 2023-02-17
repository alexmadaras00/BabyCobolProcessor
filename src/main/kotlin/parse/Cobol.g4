grammar Cobol;

// Root rule
program: id_div env_div? data_div? proc_div? EOF;

// --- IDENTIFICATION DIVISION ---
id_div: IDENTIFICATION_DIVISION DOT
        program_id;

program_id: PROGRAM_ID COBOL_WORD DOT;

// --- ENVIRONMENT DIVISION (tbi) --- not yet relevant
env_div: ;

// --- DATA DIVISION ---
data_div: DATA_DIVISION DOT
        file_section? ws_section? ls_section? linkage_section?;

file_section: ; // tbi, not yet relevant

ws_section: WORKING_STORAGE_SECTION DOT
            ws_sentence+;
ws_sentence: DIGIT DIGIT COBOL_WORD PICTURE ('A' | '9') LPAR DIGIT+ RPAR DOT;

ls_section: LOCAL_STORAGE_SECTION DOT; // tbi, working storage should do for now

linkage_section: ; // tbi, not yet relevant

// --- PROCEDURE DIVISION ---
proc_div: PROCEDURE_DIVISION DOT
          (statement DOT)+
          stop_statement DOT;

statement: DISPLAY (atomic /*(DELIMITED_BY (SPACE | SIZE | NONNUMERIC | NUMERIC))?*/)+ WITH_NO_ADVANCING? #displayStat
         | ACCEPT COBOL_WORD+                                                                             #acceptStat
         | ADD (atomic)+ TO (atomic)+ (GIVING COBOL_WORD)? (ROUNDED)?                                     #addStat
         | DIVIDE atomic INTO COBOL_WORD (GIVING COBOL_WORD)? (REMINDER COBOL_WORD)?/*TODO finish*/ #divideStat
         | EVALUATE  /* this one looks absolutely horrendous TODO finish? */                              #evaluateStat
         | IF boolean_expression THEN statement+ (ELSE statement+) END                                    #ifStat
         | MOVE (atomic /*| HIGH_VALUES | LOW_VALUES | SPACES*/) TO (COBOL_WORD)+                         #moveStat
         | MULTIPLY atomic BY atomic+ (GIVING COBOL_WORD)?                                                #multiplyStat
         | PERFORM /*TODO finish*/                                                                        #performStat
         | stop_statement                                                                                 #stopStat
         | SUBTRACT /*TODO finish*/                                                                       #subtractStat
         ;

stop_statement: STOP;

// Anything smaller than a statement
boolean_expression: ;

atomic: NUMERIC    #numeric
      | NONNUMERIC #nonnumeric
      | COBOL_WORD #identifier
      ;

// --- Keywords ---
ACCEPT: 'ACCEPT';
ADD: 'ADD';
ALTER:'ALTER';
BY: 'BY';
CALL:'CALL';
COPY:'COPY';
CORR:'CORR';
CORRESPONDING:'CORRESPONDING';
DATA_DIVISION:'DATA DIVISION';
DELIMITED_BY: 'DELIMITED BY';
DISPLAY:'DISPLAY';
DIVIDE:'DIVIDE';
DIVISION:'DIVISION';
ELSE: 'ELSE';
END: 'END';
EVALUATE:'EVALUATE';
GIVING:'GIVING';
GO_TO:'GO TO';
IDENTIFICATION_DIVISION: 'IDENTIFICATION DIVISION';
IF:'IF';
INTO:'INTO';
LOOP:'LOOP';
LOCAL_STORAGE_SECTION : 'LOCAL-STORAGE SECTION';
MOVE:'MOVE';
MULTIPLY:'MULTIPLY';
NEXT_SENTENCE:'NEXT SENTENCE';
PERFORM: 'PERFORM';
PICTURE: 'PICTURE';
PROCEDURE_DIVISION: 'PROCEDURE DIVISION';
PROGRAM_ID: 'PROGRAM-ID';
REMINDER:'REMINDER';
ROUNDED: 'ROUNDED';
SIGNAL:'SIGNAL';
SIZE: 'SIZE';
SPACE: 'SPACE';
STOP: 'STOP';
SUBTRACT: 'SUBTRACT';
THEN: 'THEN';
TO: 'TO';
WITH_NO_ADVANCING: 'WITH NO ADVANCING';
WORKING_STORAGE_SECTION: 'WORKING-STORAGE SECTION';


// TODO double-check that these are still synced;
Keywords: ACCEPT|ADD|ALTER|CALL|COPY|DATA_DIVISION|DISPLAY|DIVIDE|DIVISION|EVALUATE|
GO_TO|IDENTIFICATION_DIVISION|IF|LOOP|MOVE|MULTIPLY|NEXT_SENTENCE|SIGNAL|STOP|SUBTRACT|
WORKING_STORAGE_SECTION|LOCAL_STORAGE_SECTION;

// --- LITERALS ---
// A valid COBOL word cannot start or end with a dash or underscore and must have at least one letter
COBOL_WORD: (DIGIT (DIGIT | [-_])*)? LETTER ([-_]* ALPHANUMERIC)*;

// A non-numeric literal can be delimited by a pair of apostrophes or quotes. Doubling a quote or apostrophe can escape it,
// and a non-numeric literal must have at least one character inside.
NONNUMERIC: '\'' (SPECIAL | LETTER | DIGIT | QUOTE | (APOST APOST) | ' ')+ '\''
          | '"' (SPECIAL | LETTER | DIGIT | APOST | (QUOTE QUOTE) | ' ')+ '"';

FIXEDPOINT: SIGN? DIGIT+ (DOT DIGIT+)?;
FLOATINGPOINT: SIGN? DIGIT+ DOT DIGIT+ 'E' SIGN? DIGIT+;
NUMERIC: FIXEDPOINT | FLOATINGPOINT;

// --- Character classes and sets ---
ALPHANUMERIC: LETTER | DIGIT;
LETTER: LOWERCASE | UPPERCASE;
PAR: LPAR | RPAR;

SPECIAL: [+\-*/=$,;><:_];
DOT: '.';
APOST: '\'';
QUOTE: '"';
LPAR: '(';
RPAR: ')';
// All valid characters in COBOL.
CHARACTER: SPECIAL | LETTER | DIGIT | DOT | APOST | QUOTE | PAR;
SIGN: [+-];
DIGIT: [0-9];
LOWERCASE: [a-z];
UPPERCASE: [A-Z];
WS: [ \r\n]+ -> skip;


