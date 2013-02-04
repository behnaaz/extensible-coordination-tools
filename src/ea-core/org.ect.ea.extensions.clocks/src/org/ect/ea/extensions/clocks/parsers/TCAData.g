grammar TCAData;

tokens {
  NOT       = '!';

  LPAREN    = '(' ;
  RPAREN    = ')' ;
  AND       = '&' ;
  OR        = '|' ;
  LEQ       = '<=';
  
  PLUS      = '+';
    
  TRUE      = 'true' ;
  FALSE     = 'false' ;

}

@lexer::header{
package org.ect.ea.extensions.clocks.parsers; 
}

@parser::header{
package org.ect.ea.extensions.clocks.parsers;

import java.lang.Integer;
import java.lang.Math;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.BasicEList;

import org.ect.ea.extensions.clocks.ClockUtils;
import org.ect.ea.extensions.clocks.codegen.CodegenUtils;
}

@members{

  /** Convenience constructor. Construct a new TCADataParser on the given 
    * String. The new TCADataParser is built using classes CommonTokenStream,
    * TCADataLexer and ANTLRStringStream. Parsing is done to check syntax. 
    */ 
    public TCADataParser(String input) {
      this(new CommonTokenStream(new TCADataLexer(new ANTLRStringStream(input))));
    }
    
    /** Convenience constructor. Construct a new TCADataParser on the given 
    * String. The new TCADataParser is built using classes CommonTokenStream,
    * TCADataLexer and ANTLRStringStream. 
    * @param codegen Whether parsing is done with the aim of code generation 
    * (as opposed to parsing with the aim of editing or syntax checking, for 
    * example). If this is false, the parser just checks for syntactical 
    * wellformedness. If true, the output may be different, for example, 
    * additional information/constraints may be added. 
    */
    public TCADataParser(String input, boolean codegen) {
      this(input);
      this.parseForCodeGen = codegen;
    }

    private boolean parseForCodeGen = false;

    private final static String DATA_PREFIX = CodegenUtils.DATA_PREFIX;  
}

/* **************************************************************************** */
/* ****************************** Parser section ****************************** */
/* **************************************************************************** */

/** Top-level data constraint. */
data_constraint returns [String dc = ""]
    : (
        b=boolatom {if (b != "") $dc = b;} 
        | n=NOT l=LPAREN b=boolatom r=RPAREN {if (b != "") $dc = $dc + n.getText() + l.getText() + b + r.getText();}
      ) 
      ( 
        (op=AND|op=OR) (b=boolatom {if (b != "") $dc = $dc + op.getText() + b;}
        | n=NOT l=LPAREN b=boolatom r=RPAREN {if (b != "") $dc = $dc + op.getText() + n.getText() + l.getText() + b + r.getText();})
      )*
    ; 

/** Boolean atom. Either true or a comparison of arithmetic expressions. */
boolatom returns [String at =""] 
    : TRUE {$at = $TRUE.text;} 
    | comparison {if ($comparison.comp != "") $at = $comparison.comp;}  
  ;
  
/** Comparison of arithmetic expressions. */
comparison returns [String comp = ""] 
    : a1=expression
      (ASSIGN a2=expression {if (a1 != "") {$comp = a1; if (a2 != "") $comp = $comp + $ASSIGN.text + a2;}}
      | LEQ a2=expression {if (a1 != "") {$comp = a1; if (a2 != "") $comp = $comp + $LEQ.text + a2;}}) 
    ; 
      
/** Arithmetic expression. */
expression returns [String arith = ""]
    : (MINUS element exp)=> MINUS element exp                                   //negative element (unary minus), followed by more
      {if ($element.elem != "") 
        {$arith = $MINUS.text + $element.elem; 
         if ($exp.e != "") 
          $arith = $arith + $exp.e;
        }
      }
    | (element exp)=> element exp                                               //positive element, followed by more
      {if ($element.elem != "") 
        {$arith = $element.elem; 
         if ($exp.e != "") 
           $arith = $arith + $exp.e;
         }
      }
    | (MINUS element)=> MINUS element                                           //negative element 
      {if ($element.elem != "") 
        $arith = $MINUS.text + $element.elem;
      }
    | (element)=> element                                                       //positive element
      {if ($element.elem != "") 
        $arith = $element.elem;
      }    
    | (LPAREN expression MINUS expression RPAREN exp)=> LPAREN e1=expression MINUS e2=expression RPAREN exp 
      {if (e1 != "") { 
        $arith = $LPAREN.text + e1; 
        if (e2 != "") {
          $arith = $arith + $MINUS.text + e2;
        } 
        $arith = $arith + $RPAREN.text; 
        if ($exp.e != "") {
          $arith = $arith + $exp.e;
        }
      }
      }
    | LPAREN e1=expression MINUS e2=expression RPAREN 
      {if (e1 != "") { 
        $arith = $LPAREN.text + e1; 
        if (e2 != "") {
          $arith = $arith + $MINUS.text + e2;
        } 
        $arith = $arith + $RPAREN.text; 
      }
      }
    ;

/** Helper rule to eliminate left-recursion. */
exp returns [String e = ""]
    : (PLUS expression exp)=> PLUS expression e1=exp {if ($expression.arith != "") {$e = $PLUS.text + $expression.arith; if (e1 != "") $e = $e + e1;}}
    | PLUS expression {if ($expression.arith != "") $e = $PLUS.text + $expression.arith;} 
    ;

/** An element in an arithmetic expression. 
  * @note Data constraints may contain the following 'variables': port names 
  * (matched by IDENT = first alternative), source memory cell names (matched
  * by SOURCE IDENT = fourth alternative), and target memory cell names (matched
  * by TARGET IDENT = fifth alternative). All port names in a data constraint are
  * prefixed with CodegenUtils.DATA_PREFIX (ports in data constraints always 
  * reason about data values. */
element returns [String elem = ""]
    : IDENT {if ($IDENT.text != "") {$elem = $IDENT.text;}}
    | (MINUS INT)=> MINUS INT {if ($INT.text != "") $elem = '-' + $INT.text;} //TODO comment this back in and remove MINUS in INT rule to get back to previous state
    | INT {if ($INT.text != "") $elem = $INT.text; }
    | (SOURCE IDENT) {if (($IDENT.text != "") && ($SOURCE.text != "")) $elem = $SOURCE.text + $IDENT.text;}
    | (TARGET IDENT) {if (($IDENT.text != "") && ($TARGET.text != "")) $elem = $TARGET.text + $IDENT.text;}
  ; 


/* ******************************************************** */
/* ******* Helper rules for extracting information ******** */
/* ******************************************************** */

/** Get the port names in a data constraint. Actually, this works for any String,
  * and returns the identifier names which are not preceeded by SOURCE or TARGET. 
  */
get_port_names returns [EList<String> names = new BasicEList<String>()] :
    (~(IDENT|SOURCE|TARGET))* 
    ( (i=IDENT { $names.add(i.getText()); }
      | SOURCE IDENT
      | TARGET IDENT 
      ) 
      (~(IDENT|SOURCE|TARGET))*
    )*
  ;
  
/** Get the source memory cell names in a data constraint, i.e., the identifier
  * names which are preceeded by the SOURCE token. */
get_sourceloc_memcell_names returns [EList<String> names = new BasicEList<String>()] :
    (~(IDENT|SOURCE|TARGET))* 
    ( (IDENT 
      | SOURCE i=IDENT { $names.add(i.getText()); }
      | TARGET IDENT 
      ) 
      (~(IDENT|SOURCE|TARGET))*
    )*
  ;

/** Get the target memory cell names in a data constraint, i.e., the identifier
  * names which are preceeded by the TARGET token. */  
get_targetloc_memcell_names returns [EList<String> names = new BasicEList<String>()] :
    (~(IDENT|SOURCE|TARGET))* 
    ( (IDENT
      | SOURCE IDENT
      | TARGET i=IDENT { $names.add(i.getText()); }
      ) 
      (~(IDENT|SOURCE|TARGET))*
    )*
  ;
  
/** Get all integer values used in a data constraint. 
  * @return An EList with two elements: [min,max].*/
get_minmax_int_values returns [EList<String> result]
@init{
  int min = -1;
  int max = 0;
  int temp;
} 
@after {
  result = new BasicEList();
  result.add(String.valueOf(min));
  result.add(String.valueOf(max));
} :
    (~(INT|MINUS))*
    ( ( (MINUS INT)=> m=MINUS i=INT 
        { //MINUS is unary if input.LT(-3) is any of <=, =, +, -, (, &, !, null
          if (input.LT(-3) == null) {                                           //unary minus at the beginning
            temp = Integer.parseInt(m.getText() + i.getText());
            min = Math.min(min,temp);
            max = Math.max(max,temp);
          } else if ((input.LT(-3).getType() == LEQ) ||
                     (input.LT(-3).getType() == ASSIGN) ||
                     (input.LT(-3).getType() == PLUS) ||
                     (input.LT(-3).getType() == MINUS) || 
                     (input.LT(-3).getType() == LPAREN) ||
                     (input.LT(-3).getType() == AND) ||
                     (input.LT(-3).getType() == NOT)) {                         //unary minus elsewhere
            temp = Integer.parseInt(m.getText() + i.getText());
            min = Math.min(min,temp);
            max = Math.max(max,temp);
          } else {                                                              //binary minus
            temp = Integer.parseInt(i.getText());
            min = Math.min(min,temp);
            max = Math.max(max,temp);
          }
        } 
        //System.out.println("Text is: " + (input.LT(-3) == null)); /*System.out.println("Number found: " + m + i);*/} //compare with min or max, depending on whether MINUS is unary or binary
      | i=INT 
        {
          temp = Integer.parseInt(i.getText());
          min = Math.min(min,temp);
          max = Math.max(max,temp);
        }
      | MINUS //do nothing (binary minus) 
      )
    (~(INT|MINUS))*
    )*
    ;
  
/* ******************************************************** */

/* ******************************************************** */
/* *********** Helper rules for code generation *********** */
/* ******************************************************** */

/** Add an index to ports and memory cells. Source location memory cells get the
  * current index, ports and target location memory cells get the index of the 
  * next step.  
  * @param index String representation of the index of the current step, without '_'
  * @param nextindex String representation of the index of the next step, without '_'
  * @note If parseForCodeGen is true (determined by the constructor), 
  * {@link CodegenUtils.DATA_PREFIX} is added to all identifiers that are not
  * identified as source or target memory cell, since these must be ports then. 
  * @note This rule/method only works correctly when called on a data constraint. 
  * It will work for (i.e., parse) any String, but the result is not wellformed 
  * in that case.
  * @note Every identifier name which is not preceeded by the SOURCE or TARGET 
  * token is assumed to be a port name. The only exception is 
  * CodegenUtils.NO_FLOW_NAME. This should not be subscripted, since it has the 
  * same value in all steps.     
  */
addIndexRemoveMemcellPrefix [String index, String nextindex] returns [String ret = ""] :
    (s=~(IDENT|SOURCE|TARGET) {$ret = $ret + s.getText();}  
    )* //ignore, just append to result
    ( 
      (i=IDENT {
        if (!i.getText().equals(CodegenUtils.NO_FLOW_NAME)) {
          $ret = $ret + DATA_PREFIX + i.getText() + "_" + nextindex + " "; //add space after name, since otherwise idents followed by arithmetic addition are not parsed/translated correctly
        } else {
          $ret = $ret + i.getText();
        }
      }
      | SOURCE i=IDENT {$ret = $ret + i.getText() + "_" + index + " ";}
      | TARGET i=IDENT {$ret = $ret + i.getText() + "_" + nextindex + " ";}
      ) 
      (s=~(IDENT|SOURCE|TARGET) {$ret = $ret + s.getText();} )* 
    )*
    ;



/* ******************************************************* */
/* ******************** Lexer section ******************** */
/* ******************************************************* */

IDENT : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
  
INT   : ('0'..'9')+;



MINUS : '-';


SOURCE : '$s.' ;
TARGET : '$t.' ;

ASSIGN : ('==')=> '==' | '=' ;

WS : (' ' //blank
    | '\t' //tab
    | '\u0000' //null
    | '\r' //cr
    | '\n'
    )+ { skip(); }
    ;


///** Shape a data constraint for use in visible transitions. Namely, prefix each 
//  * port name with ClockUtils.DATA_PREFIX to obtain port data variables,
//  * and remove the \'$s.\' respectively \'$t.\' from memory cell names (note: 
//  * using only the name of the memory cell should not cause problems with 
//  * respect to uniqueness, since SMTFormulaGenerator checks for unique memory 
//  * cell names already). 
//  * @note This methods takes over parenthesis exactly as they appear in the
//  * constraint, assuming the constraint is correctly bracketed. */
//shape_data_constraint returns [String ret = ""] 
//@init {String temp = "";} :
//    (LPAREN 
//      { $ret = $ret + "("; }
//    )* 
//    s1=shape_dc 
//      { $ret = $ret + s1; } 
//    (RPAREN 
//      { $ret = $ret + ")"; }
//    )*
//    ( 
//      { temp = ""; }
//      (AND 
//        (LPAREN 
//          { temp = temp + "("; }
//        )* 
//        s1=shape_dc 
//          { if (s1!="") $ret = $ret + " & " + temp + s1; }
//        (RPAREN 
//          { $ret = $ret + ")"; }
//        )*
//      )
//      |
//      (OR 
//        (LPAREN 
//          { temp = temp + "("; }
//        )*  
//        s1=shape_dc 
//          { if (s1!="") $ret = $ret + " | " + temp + s1; }
//        (RPAREN   
//          { $ret = $ret + ")"; }
//        )*
//      )
//    )*
//    ;
///** Shape a data constraint which contains memory cells, and add subscripts to 
//  * the variables afterwards.
//  * @note This method does not handle port data variables. For these, method 
//  * shape_data_constraint needs to be called.  
//*/
//dc_shape_memcell_and_subscript [final String index] returns [String result = ""] 
//@init {
//  String prevIndex = index.equals("t+1") ? "t" : String.valueOf(Integer.valueOf(index)-1);
//  String actIndex = "";
//}   :
//    (n1=~(IDENT | SOURCE | TARGET)
//      {
//        $result = $result + " " + n1.getText();
//      }
//    )*
//    ((i=IDENT 
//      {
//        $result = $result + " " + i.getText() + "_" + index;
//      }
//    | (SOURCE {actIndex = prevIndex;} | TARGET {actIndex = index;}) i2=IDENT
//      {
//        $result = $result + " " + i2 + "_" + actIndex;
//      } 
//    )
//      (n2=~(IDENT | SOURCE | TARGET) 
//        {
//          $result = $result + " " + n2.getText();
//        }
//      )*
//    )*
//    ;

///** Shape a data sub-constraint, i.e. single conjunct or disjunct. 
//  * Note that negation is not supported by the constraint extension. 
//  * At the moment, constraints allowed here are Port=Port, Port=Number,
//  * Port=MemCell, and MemCell=MemCell. */
//private shape_dc returns [String ret = ""] :
//    ((i1=IDENT
//      {
//        $ret = ClockUtils.DATA_PREFIX + i1.getText();
//      } 
//    |
//    (m=SOURCE | m=TARGET) i=IDENT 
//      {
//        $ret = $ret + m.getText() + i.getText(); //do nothing for now
//      }
//    ) ASSIGN
//    {
//      $ret = $ret + "=";
//    }
//      (i2=IDENT
//        {
//          $ret = $ret + ClockUtils.DATA_PREFIX + i2.getText();
//        } 
//      | 
//      n=INT
//        {
//          $ret = $ret + n.getText();
//        }
//      | 
//      (m=SOURCE | m=TARGET) i=IDENT 
//        {
//          $ret = $ret + m.getText() + i.getText(); //do nothing for now
//        }
//      )
//    )
//    |
//    TRUE
//    ;
