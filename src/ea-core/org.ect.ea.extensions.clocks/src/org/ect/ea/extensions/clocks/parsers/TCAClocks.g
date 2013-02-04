grammar TCAClocks;

tokens {
  TRUE = 'true';
}

@lexer::header {
  package org.ect.ea.extensions.clocks.parsers;
}

@parser::header {
  package org.ect.ea.extensions.clocks.parsers;

	import org.eclipse.emf.common.util.BasicEList;
	import org.eclipse.emf.common.util.EList;
	
	import org.ect.ea.extensions.clocks.ClockUtils;
	import org.ect.ea.extensions.clocks.codegen.CodegenUtils;
	 
}
 
@members{

  /** Convenience Constructor. Construct a new TCAClocksParser on the given 
    * String. The new TCAClocksParser is built using classes CommonTokenStream, 
    * TCAClocksLexer and ANTLRStringStream.
    */
  public TCAClocksParser(String input) {
    this(new CommonTokenStream(new TCAClocksLexer(new ANTLRStringStream(input))));
  }
}

/* ******************************************************** */
/* ******************** Parser section ******************** */
/* ******************************************************** */

/**
 * Parse a (comma seperated) list of clocks on automaton level.  
 * To avoid confusion, all whitespaces in between clocks and the
 * comma are removed.
 * @internal If the whitespaces are not removed, StringListExtension objects
 * are not able to properly identify duplicate entries, for example as in
 * 'x,y,z' and 'x, y, z'.
 */
automaton_clocks returns [String ret = ""] : 
    s1=clock 
      { if (s1 != "") $ret = s1; }
    (COMMA s1=clock
      { if (s1 != "") $ret = $ret.concat(",").concat(s1); }
    )*
    ; 

private clock returns [String ret = ""] : 
    i=IDENT 
      { $ret = i.getText(); }
    ;

/** Clock constraint. All clock constraints have to be convex, so we need not
  * distinguish between location invariants and transition guards. A clock 
  * constraint is a conjunction of clock comparisons. We assume they do not 
  * contain any parenthesis (using conjunction as only propositional operator, 
  * no ambiguities can arise). */
clock_constraint returns [String ret = ""] :
    c=cc
      { $ret = c; } 
    (AND s1=cc 
      { if (s1!="") $ret = $ret + "&" + s1; }
    )*
  ;

/** Shape a clock constraint in interstep representation, adding a specified 
  * index to all clocks.
  * @note Shaping here means to replace clocks by differences with the absolute 
  * time reference. The absolute time reference gets index+1 as index. */
shape_clock_constraint_interstep [String index] returns [String ret = ""] :
    c=shape_cc_interstep[index]
      { $ret = c;}
    (AND s1=shape_cc_interstep[index] 
      { if (s1!="") $ret = $ret + "&" + s1; }
    )*
  ;

/** Shape an atomic clock comparison in interstep representation, adding a  
  * specified index to all clocks. 
  * @note Shaping here means to replace clocks by differences with the absolute 
  * time reference. The absolute time reference gets index+1 as index. */
private shape_cc_interstep [String index] returns [String ret = ""] :
    (c=shape_comparison_interstep[index] 
      { $ret = "(" + c + ")";})
    |
    (t=TRUE 
      { $ret=t.getText();} 
    )
  ;  

/** Shape an atomic clock comparison != true in interstep representation,   
  * adding a specified index to all clocks. If the index is the empty string, 
  * no index is added. 
  * @note Shaping here means to replace clocks by differences with the absolute 
  * time reference. The absolute time reference gets index+1 as index. */
private shape_comparison_interstep [String index] returns [String ret = ""] 
@init {String add = (index.equals("")) ? "" : "_" + index; }:
        c=clock 
          { $ret = "(" + CodegenUtils.GLOBAL_CLOCK + add + "+1 - " + c + add + ")"; }
        (LT 
          { $ret = $ret.concat(" < "); }
        |LEQ 
          { $ret = $ret.concat(" <= "); }
        |EQUALS 
          { $ret = $ret.concat(" = "); }
        |GEQ 
          { $ret = $ret.concat(" >= "); }
        |GT 
          { $ret = $ret.concat(" > "); }
        ) 
        n=NUMBER { $ret = $ret.concat(n.getText()); }
    ;
    
/** Shape a clock constraint, adding a specified index to all clocks. 
  * @note Shaping here means to replace clocks by differences with the absolute 
  * time reference. */
shape_clock_constraint [String index] returns [String ret = ""] :
    c=shape_cc[index]
      { $ret = c; }
    (AND s=shape_cc[index] 
      { if (!s.equals("")) $ret = $ret + "&" + s; }
    )*
  ;

/** Shape an atomic clock constraint, adding a specified index to all clocks. 
  * @note Shaping here means to replace clocks by differences with the absolute 
  * time reference. */
private shape_cc[String index] returns [String ret = ""] :
    (c=shape_comparison[index] 
      { $ret = "(" + c + ")";})
    |
    (t=TRUE 
      { $ret=t.getText(); }
    )
  ;  

/** Shape a clock comparison != true, adding a specified index to all clocks. 
  * If the index is the empty String, no index is added.
  * @note Shaping here means to replace clocks by differences with the absolute 
  * time reference. */
private shape_comparison [String index] returns [String ret = ""] 
@init {String add = (index.equals("")) ? "" : "_" + index; } :
        c=clock 
          { $ret = "(" + CodegenUtils.GLOBAL_CLOCK + add + " - " + c + add + ")";}
        (LT 
          { $ret = $ret.concat(" < "); }
        |LEQ 
          { $ret = $ret.concat(" <= "); }
        |EQUALS 
          { $ret = $ret.concat(" = "); }
        |GEQ 
          { $ret = $ret.concat(" >= "); }
        |GT 
          { $ret = $ret.concat(" > "); }
        ) 
        n=NUMBER { $ret = $ret.concat(n.getText()); }
    ;

private cc returns [String ret = ""] :
    (c=comparison 
      { if (c == "") $ret = "true"; else $ret = "(" + c + ")";})
    |
    (TRUE 
      { $ret = "true"; }
    )
  ;  

/** Clock comparison. A clock comparison is a simple arithmetic constraint x~c.*/
private comparison returns [String ret = ""] :
        c=clock
        (o=LT 
        |o=LEQ
        |o=EQUALS
        |o=GEQ 
        |o=GT 
        ) 
        n=NUMBER { $ret = c + o.getText() + n.getText(); }
    ;
    


/* Data constraint. */


/** Get the clock names. */
clock_names returns [EList<String> names = new BasicEList<String>()] :
		(~IDENT)* 
		( i=IDENT { $names.add(i.getText()); } 
		  (~IDENT)*
		)*
	;
	
/***/
clock_names_left returns [EList<String> names = new BasicEList<String>()] :
    c=IDENT EQUALS (IDENT | NUMBER) 
      {names.add(c.getText());}
    (COMMA c=IDENT EQUALS (IDENT | NUMBER)
      {names.add(c.getText());}
    )*
  ; 

/** An update is a comma seperated list of clock assignments. An assignment can 
  * be of the form 'x=n' or 'x=y', with x,y clocks, and n a natural number. */
//update returns [String ret = ""] :
//    s1=clock { $ret = s1; }
//    (COMMA s1=clock
//      { if (s1 != "") $ret = $ret.concat(",").concat(s1); }
//    )*
//  ;
update returns [String ret = ""] :
    c1=IDENT e=EQUALS (c2=IDENT | c2=NUMBER) 
      { $ret = c1.getText() + e.getText() + c2.getText();}
    (COMMA c1=IDENT e=EQUALS (c2=IDENT | c2=NUMBER)
      { $ret = $ret + $COMMA.text + c1.getText() + e.getText() + c2.getText();}
    )*
    ;

        

subscript [final String index] returns [String result = ""] :
    (ni=~IDENT
      {
      	$result = $result + " " + ni.getText();
      }
    )*
    (i=IDENT 
      {
      	$result = $result + " " + i.getText();
      	if (!(i.getText().equals(CodegenUtils.NO_FLOW_NAME))) $result = $result + "_" + index;
      }
      (nii=~IDENT
        {
          $result = $result + " " + nii.getText();
        }
      )*
    )*
    ;

/* Make tokens know to the parser. This trick is needed for rules using wildcards. */
private getToKnowTokens :
    NOT | LPARAN | RPARAN 
    ;

/* ******************************************************* */
/* ******************** Lexer section ******************** */
/* ******************************************************* */

/** Identifier. */
IDENT : LETTER (LETTER | DIGIT)* ;
GEQ : '>=' | '=>' ;
LEQ : '<=' | '=<' ;
EQUALS : ('==')=> '==' | '=' ;
GT : '>' ;
LT : '<' ;
AND : ('&&')=> '&&'| '&' ;
OR  : ('||')=> '||' | '|' ;
NUMBER  : DIGIT (DIGIT)* ;
COMMA   : ',' ;
RPARAN  : ')';
LPARAN  : '(';
NOT     : '!';
MINUS   : '-';
DOT     : '.';
MEMCELL : '$' ('s'|'t') DOT LETTER (LETTER | DIGIT)* ; 


/** Ignore white space. */
WS : (' ' //blank
    | '\t' //tab
    | '\u0000' //null
    | '\r' //cr
    )+ { skip(); }
    ;

fragment DIGIT : ('0'..'9') ;
fragment LETTER : ('a'..'z'|'A'..'Z') ;

/*DEPRECATED*/
/*
data_constraint returns [String ret = ""] {String s1 = "";} :
    ret = dc
    (
      (AND s1 = dc {if (s1!="") ret = ret + "&" + s1;})
      |
      (OR s1 = dc {if (s1!="") ret = ret + "|" + s1;})
    )*
    ;
*/
/* Data sub-constraint, i.e. single conjunct or disjunct. 
  * Note that negation is not supported by the constraint extension. */
/*
private dc returns [String ret = ""] :
    (i1:IDENT EQUALS
      {
        ret = i1.getText() + "=";
      } 
      (i2:IDENT
        {
          ret = ret + i2.getText();
        } 
      | 
      n:NUMBER
        {
          ret = ret + n.getText();
        }
      )
    )
    |
    TRUE
    ;
*/