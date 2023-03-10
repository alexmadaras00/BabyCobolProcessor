grammar Cobol;
import Tokens;

// Root rule
program: id_div (data_div)? proc_div? EOF;

// --- IDENTIFICATION DIVISION ---
id_div: NOINDENT IDENTIFICATION DIVISION DOT
        (ID_CLAUSE DOT)+;

ID_CLAUSE: ID_DIV_CHAR+ DOT ' ' ID_DIV_CHAR+;


// --- DATA DIVISION ---
data_div: NOINDENT DATA DIVISION DOT (data_entry)+;

data_entry: LEVEL identifier picture_clause OCCURS_CLAUSE? DOT #pictureData
          | LEVEL identifier like_clause OCCURS_CLAUSE? DOT    #likeData
          | LEVEL identifier OCCURS_CLAUSE? DOT                #recordData
          ;

picture_clause: PICTURE IS REPRESENTATION;

like_clause: LIKE identifier;

OCCURS_CLAUSE: OCCURS INT TIMES;

// --- PROCEDURE DIVISION ---
proc_div: NOINDENT PROCEDURE DIVISION (using_clause)? DOT
          (sentence+ | paragraph) // first paragraph may be nameless
          paragraph*
        ;

paragraph: NOINDENT procedure_name DOT sentence+;

sentence: statement+ DOT;

statement: ACCEPT identifier+                                                                                #acceptStat
         | ADD add+=atomic+ TO to=atomic giving_clause*                                                       #addStat
         | ALTER alter=procedure_name tpt to=procedure_name                                                  #alterStat
//         | CALL (ID_DIV_WORD) using_clause? #callStat // This one was breaking the parser every time, probably not worth doing
         | DISPLAY display_clause+ wna?                                                                      #displayStat
         | DIVIDE div+=atomic INTO into+=atomic+ (giving_clause+ | giving_clause REMAINDER rem=identifier)?   #divideStat
         | EVALUATE any_expression also_clause* when_block+ END                                              #evaluateStat
         | goto procedure_name                                                                               #gotoStat
         | IF boolean_expression THEN then+=statement+ (ELSE else+=statement+)? END?                         #ifStat
         | LOOP loop_statement* END                                                                          #loopStat
         | MOVE move_expression TO to+=identifier+                                                           #moveStat
         | MULTIPLY mul+=atomic BY by+=atomic+ giving_clause?                                                 #multiplyStat
         | ns                                                                                                #nextStat
         | PERFORM procedure_name (THROUGH procedure_name)? (atomic TIMES)?                                  #performStat
         | SIGNAL signal_expression ON ERROR                                                                 #signalStat
         | STOP                                                                                              #stopStat
         | SUBTRACT sub+=atomic+ FROM (from+=atomic+ | from+=atomic giving_clause+)                             #subtractStat
         ;

// Used in ADD, DIVIDE, MULTIPLY, SUBTRACT
giving_clause: GIVING identifier;

signal_expression: OFF            #offSignal
                 | procedure_name #procedureSignal
                 ;

also_clause: ALSO any_expression;

display_clause: atomic (delimited_expression)?;

delimited_expression : delim SPACE   #spaceDelimited
                     | delim SIZE    #sizeDelimited
                     | delim literal #literalDelimited
                     ;

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

// --- Basic expressions and atoms -------------------------------------
any_expression: arithmetic_expression #arithmeticExpr
              | string_expression     #stringExpr
              | boolean_expression    #booleanExpr
              ;

arithmetic_expression: atomic
                     | left=arithmetic_expression ARITHMETIC_OP right=arithmetic_expression
                     ;

string_expression: atomic
                 | left=string_expression PLUS right=string_expression;

boolean_expression: TRUE                                                                 #trueBool
                  | FALSE                                                                #falseBool
                  | NOT boolean_expression                                               #notBool
                  | left=boolean_expression BOOLEAN_OP right=boolean_expression          #boolBool
                  | left=arithmetic_expression COMPARISON_OP right=arithmetic_expression #compBool
                  ;

procedure_name: COBOL_WORD;

atomic: literal    #literalAtom
      | identifier #identifierAtom
      ;

literal: NUMERIC    #numericLit
       | NONNUMERIC #nonnumericLit
       ;

identifier: COBOL_WORD                        #primitiveId
          | identifier (OF parent=identifier) #qualifiedId // Using the brackets enforces right-associativity, probably easier to work with
          | identifier INDEX                  #indexId
          ;

// Shorthands for keyword phrases
wna: WITH NO ADVANCING;
ns: NEXT SENTENCE;
goto: GO TO;
tpt: TO PROCEED TO;
delim: DELIMITED BY;

// Handling whitespace, indents and line continuations. Must be located here and not in Tokens.g4
NOINDENT: '~'; // Used if no ident was found
fragment LC: '\u00ac' WS?; // \u00ac is ¬, a marker for a continued line
fragment WS: [ \t\r\n]+;
SKIP_: (LC | WS) -> skip;