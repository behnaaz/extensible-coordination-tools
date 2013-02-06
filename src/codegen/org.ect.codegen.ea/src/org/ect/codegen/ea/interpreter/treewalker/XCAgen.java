/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
// $ANTLR 3.2 Sep 23, 2009 12:02:23 /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g 2010-03-01 16:21:39

package org.ect.codegen.ea.interpreter.treewalker;

import java.util.Map;
import java.util.HashMap;

import org.ect.codegen.ea.interpreter.*;
import org.ect.codegen.ea.runtime.*;
import org.ect.codegen.ea.runtime.conditions.*;
import org.ect.codegen.ea.runtime.constraints.*;
import org.ect.codegen.ea.runtime.primitives.*;

import static org.ect.codegen.ea.runtime.conditions.Predicate.Rel;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class XCAgen extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "INITIALSTATE", "BEGIN", "END", "SPACER", "FALSE", "TRUE", "AND", "OR", "LPAREN", "RPAREN", "ASSIGN", "QUOTE", "SEPARATOR", "SOURCE", "TARGET", "CA", "PORT", "STATE", "TRANS", "CELL", "PTYPE", "ISSTART", "FUNCTION", "CONSTANT", "RELOP", "IO", "INT", "IDENT", "GLOB", "WS"
    };
    public static final int FUNCTION=26;
    public static final int STATE=21;
    public static final int GLOB=32;
    public static final int CA=19;
    public static final int PORT=20;
    public static final int SOURCE=17;
    public static final int CELL=23;
    public static final int INT=30;
    public static final int SEPARATOR=16;
    public static final int INITIALSTATE=4;
    public static final int TARGET=18;
    public static final int AND=10;
    public static final int EOF=-1;
    public static final int TRUE=9;
    public static final int LPAREN=12;
    public static final int TRANS=22;
    public static final int QUOTE=15;
    public static final int RPAREN=13;
    public static final int WS=33;
    public static final int IO=29;
    public static final int SPACER=7;
    public static final int BEGIN=5;
    public static final int OR=11;
    public static final int ASSIGN=14;
    public static final int CONSTANT=27;
    public static final int IDENT=31;
    public static final int PTYPE=24;
    public static final int ISSTART=25;
    public static final int END=6;
    public static final int FALSE=8;
    public static final int RELOP=28;

    // delegates
    // delegators


        public XCAgen(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public XCAgen(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return XCAgen.tokenNames; }
    public String getGrammarFileName() { return "/ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g"; }


    Map<String, TransactionalIO> synchronisers;
    Map<String, XState> states = new HashMap<String, XState>();

    private ChannelOperation getFunc(String id) {
    	try {
    		return (ChannelOperation)Class.forName(id).newInstance();
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;		
    	}
    }



    // $ANTLR start "module"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:36:1: module returns [List<ExecutableCA> val] : ( automaton[new HashMap()] )+ ;
    public final List<ExecutableCA> module() throws RecognitionException {
        List<ExecutableCA> val = null;

        ExecutableCA automaton1 = null;


        val = new ArrayList<ExecutableCA>();
        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:38:4: ( ( automaton[new HashMap()] )+ )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:38:5: ( automaton[new HashMap()] )+
            {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:38:5: ( automaton[new HashMap()] )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==CA) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:38:6: automaton[new HashMap()]
            	    {
            	    pushFollow(FOLLOW_automaton_in_module52);
            	    automaton1=automaton(new HashMap());

            	    state._fsp--;

            	    val.add(automaton1);

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return val;
    }
    // $ANTLR end "module"


    // $ANTLR start "automaton"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:42:1: automaton[Map synchronisers] returns [ExecutableCA val] : ^( CA GLOB ( port )* ( state )* ) ;
    public final ExecutableCA automaton(Map synchronisers) throws RecognitionException {
        ExecutableCA val = null;

        CommonTree GLOB2=null;
        TransactionalIO port3 = null;

        XState state4 = null;


        this.synchronisers = synchronisers;
        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:44:4: ( ^( CA GLOB ( port )* ( state )* ) )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:44:5: ^( CA GLOB ( port )* ( state )* )
            {
            match(input,CA,FOLLOW_CA_in_automaton87); 

            match(input, Token.DOWN, null); 
            GLOB2=(CommonTree)match(input,GLOB,FOLLOW_GLOB_in_automaton89); 
            val = new ExecutableCA((GLOB2!=null?GLOB2.getText():null));
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:45:4: ( port )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==PORT) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:45:5: port
            	    {
            	    pushFollow(FOLLOW_port_in_automaton101);
            	    port3=port();

            	    state._fsp--;

            	    val.addPort(port3);

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:47:4: ( state )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==STATE) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:47:5: state
            	    {
            	    pushFollow(FOLLOW_state_in_automaton121);
            	    state4=state();

            	    state._fsp--;

            	    val.addState(state4);

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return val;
    }
    // $ANTLR end "automaton"


    // $ANTLR start "port"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:51:1: port returns [TransactionalIO val] : ^( PORT IDENT ( IO )? ) ;
    public final TransactionalIO port() throws RecognitionException {
        TransactionalIO val = null;

        CommonTree IDENT5=null;

        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:52:4: ( ^( PORT IDENT ( IO )? ) )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:52:5: ^( PORT IDENT ( IO )? )
            {
            match(input,PORT,FOLLOW_PORT_in_port154); 

            match(input, Token.DOWN, null); 
            IDENT5=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_port156); 
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:52:18: ( IO )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==IO) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:52:18: IO
                    {
                    match(input,IO,FOLLOW_IO_in_port158); 

                    }
                    break;

            }


            match(input, Token.UP, null); 

            	if (!synchronisers.containsKey((IDENT5!=null?IDENT5.getText():null)))
            		synchronisers.put((IDENT5!=null?IDENT5.getText():null), new TimeoutPort((IDENT5!=null?IDENT5.getText():null)));
            	
            	val = synchronisers.get((IDENT5!=null?IDENT5.getText():null));


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return val;
    }
    // $ANTLR end "port"


    // $ANTLR start "state"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:61:1: state returns [XState val] : ^( STATE IDENT ( INITIALSTATE )? ( cell )* ( transition )* ) ;
    public final XState state() throws RecognitionException {
        XState val = null;

        CommonTree IDENT6=null;
        Cell cell7 = null;

        XTransition transition8 = null;


        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:62:4: ( ^( STATE IDENT ( INITIALSTATE )? ( cell )* ( transition )* ) )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:62:5: ^( STATE IDENT ( INITIALSTATE )? ( cell )* ( transition )* )
            {
            match(input,STATE,FOLLOW_STATE_in_state185); 

            match(input, Token.DOWN, null); 
            IDENT6=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_state187); 

            	if (!states.containsKey((IDENT6!=null?IDENT6.getText():null))) 
            		states.put((IDENT6!=null?IDENT6.getText():null), new XState((IDENT6!=null?IDENT6.getText():null)));

            	val = states.get((IDENT6!=null?IDENT6.getText():null));

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:69:4: ( INITIALSTATE )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==INITIALSTATE) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:69:5: INITIALSTATE
                    {
                    match(input,INITIALSTATE,FOLLOW_INITIALSTATE_in_state199); 
                    val.setStart(true);

                    }
                    break;

            }

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:71:4: ( cell )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==CELL) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:71:5: cell
            	    {
            	    pushFollow(FOLLOW_cell_in_state218);
            	    cell7=cell();

            	    state._fsp--;

            	    val.addCell(cell7);

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:73:4: ( transition )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==TRANS) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:73:5: transition
            	    {
            	    pushFollow(FOLLOW_transition_in_state238);
            	    transition8=transition();

            	    state._fsp--;

            	    val.addOutgoing(transition8);

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return val;
    }
    // $ANTLR end "state"


    // $ANTLR start "cell"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:77:1: cell returns [Cell val] : ^( CELL IDENT (init= GLOB | init= INT | init= TRUE | init= FALSE )? ) ;
    public final Cell cell() throws RecognitionException {
        Cell val = null;

        CommonTree init=null;
        CommonTree IDENT9=null;

        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:78:4: ( ^( CELL IDENT (init= GLOB | init= INT | init= TRUE | init= FALSE )? ) )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:78:5: ^( CELL IDENT (init= GLOB | init= INT | init= TRUE | init= FALSE )? )
            {
            match(input,CELL,FOLLOW_CELL_in_cell271); 

            match(input, Token.DOWN, null); 
            IDENT9=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_cell273); 
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:78:18: (init= GLOB | init= INT | init= TRUE | init= FALSE )?
            int alt8=5;
            switch ( input.LA(1) ) {
                case GLOB:
                    {
                    alt8=1;
                    }
                    break;
                case INT:
                    {
                    alt8=2;
                    }
                    break;
                case TRUE:
                    {
                    alt8=3;
                    }
                    break;
                case FALSE:
                    {
                    alt8=4;
                    }
                    break;
            }

            switch (alt8) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:78:19: init= GLOB
                    {
                    init=(CommonTree)match(input,GLOB,FOLLOW_GLOB_in_cell278); 

                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:78:29: init= INT
                    {
                    init=(CommonTree)match(input,INT,FOLLOW_INT_in_cell282); 

                    }
                    break;
                case 3 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:78:38: init= TRUE
                    {
                    init=(CommonTree)match(input,TRUE,FOLLOW_TRUE_in_cell286); 

                    }
                    break;
                case 4 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:78:48: init= FALSE
                    {
                    init=(CommonTree)match(input,FALSE,FOLLOW_FALSE_in_cell290); 

                    }
                    break;

            }


            match(input, Token.UP, null); 

            	if (!synchronisers.containsKey((IDENT9!=null?IDENT9.getText():null))) 
            		synchronisers.put((IDENT9!=null?IDENT9.getText():null), new Cell((IDENT9!=null?IDENT9.getText():null), (init!=null?init.getText():null)));
            	
            	val = (Cell)synchronisers.get((IDENT9!=null?IDENT9.getText():null));	


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return val;
    }
    // $ANTLR end "cell"


    // $ANTLR start "transition"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:87:1: transition returns [XTransition val] : ^( TRANS ( port )* constraint state ) ;
    public final XTransition transition() throws RecognitionException {
        XTransition val = null;

        TransactionalIO port10 = null;

        TransCon constraint11 = null;

        XState state12 = null;


        val = new XTransition();
        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:89:4: ( ^( TRANS ( port )* constraint state ) )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:89:5: ^( TRANS ( port )* constraint state )
            {
            match(input,TRANS,FOLLOW_TRANS_in_transition319); 

            match(input, Token.DOWN, null); 
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:89:13: ( port )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==PORT) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:89:14: port
            	    {
            	    pushFollow(FOLLOW_port_in_transition322);
            	    port10=port();

            	    state._fsp--;

            	    val.addPort(port10);

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            pushFollow(FOLLOW_constraint_in_transition340);
            constraint11=constraint();

            state._fsp--;

            val.setConstraint(constraint11);
            pushFollow(FOLLOW_state_in_transition353);
            state12=state();

            state._fsp--;

            val.setTarget(state12);

            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return val;
    }
    // $ANTLR end "transition"


    // $ANTLR start "constraint"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:96:1: constraint returns [TransCon val] : ( ^( OR (m= constraint )+ ) | ^( AND (m= constraint )+ ) | equation | predicate | ^( CONSTANT ( TRUE | FALSE ) ) );
    public final TransCon constraint() throws RecognitionException {
        TransCon val = null;

        TransCon m = null;

        Assignment equation13 = null;

        Predicate predicate14 = null;


        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:97:4: ( ^( OR (m= constraint )+ ) | ^( AND (m= constraint )+ ) | equation | predicate | ^( CONSTANT ( TRUE | FALSE ) ) )
            int alt12=5;
            switch ( input.LA(1) ) {
            case OR:
                {
                alt12=1;
                }
                break;
            case AND:
                {
                alt12=2;
                }
                break;
            case ASSIGN:
                {
                alt12=3;
                }
                break;
            case RELOP:
                {
                alt12=4;
                }
                break;
            case CONSTANT:
                {
                alt12=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:97:5: ^( OR (m= constraint )+ )
                    {
                    match(input,OR,FOLLOW_OR_in_constraint384); 

                    val = new Or();

                    match(input, Token.DOWN, null); 
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:98:4: (m= constraint )+
                    int cnt10=0;
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0>=AND && LA10_0<=OR)||LA10_0==ASSIGN||(LA10_0>=CONSTANT && LA10_0<=RELOP)) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:98:5: m= constraint
                    	    {
                    	    pushFollow(FOLLOW_constraint_in_constraint399);
                    	    m=constraint();

                    	    state._fsp--;

                    	    ((Or)val).add(m);

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt10 >= 1 ) break loop10;
                                EarlyExitException eee =
                                    new EarlyExitException(10, input);
                                throw eee;
                        }
                        cnt10++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:100:5: ^( AND (m= constraint )+ )
                    {
                    match(input,AND,FOLLOW_AND_in_constraint420); 

                    val = new And();

                    match(input, Token.DOWN, null); 
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:101:5: (m= constraint )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0>=AND && LA11_0<=OR)||LA11_0==ASSIGN||(LA11_0>=CONSTANT && LA11_0<=RELOP)) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:101:6: m= constraint
                    	    {
                    	    pushFollow(FOLLOW_constraint_in_constraint437);
                    	    m=constraint();

                    	    state._fsp--;

                    	    ((And)val).add(m);

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt11 >= 1 ) break loop11;
                                EarlyExitException eee =
                                    new EarlyExitException(11, input);
                                throw eee;
                        }
                        cnt11++;
                    } while (true);


                    match(input, Token.UP, null); 

                    }
                    break;
                case 3 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:103:6: equation
                    {
                    pushFollow(FOLLOW_equation_in_constraint457);
                    equation13=equation();

                    state._fsp--;

                    val = equation13;

                    }
                    break;
                case 4 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:104:6: predicate
                    {
                    pushFollow(FOLLOW_predicate_in_constraint470);
                    predicate14=predicate();

                    state._fsp--;

                    val = predicate14;

                    }
                    break;
                case 5 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:105:6: ^( CONSTANT ( TRUE | FALSE ) )
                    {
                    match(input,CONSTANT,FOLLOW_CONSTANT_in_constraint484); 

                    match(input, Token.DOWN, null); 
                    if ( (input.LA(1)>=FALSE && input.LA(1)<=TRUE) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    match(input, Token.UP, null); 
                    val = Literal.TRUE; /*XXX:FIXME*/

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return val;
    }
    // $ANTLR end "constraint"


    // $ANTLR start "equation"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:108:1: equation returns [Assignment val] : ^( ASSIGN l= element r= param ) ;
    public final Assignment equation() throws RecognitionException {
        Assignment val = null;

        TransactionalIO l = null;

        TransactionalIO r = null;


        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:109:4: ( ^( ASSIGN l= element r= param ) )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:109:5: ^( ASSIGN l= element r= param )
            {
            match(input,ASSIGN,FOLLOW_ASSIGN_in_equation515); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_element_in_equation519);
            l=element();

            state._fsp--;

            pushFollow(FOLLOW_param_in_equation523);
            r=param();

            state._fsp--;


            match(input, Token.UP, null); 
            val = new Assignment(l, r);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return val;
    }
    // $ANTLR end "equation"


    // $ANTLR start "predicate"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:112:1: predicate returns [Predicate val] : ^( RELOP l= param r= param ) ;
    public final Predicate predicate() throws RecognitionException {
        Predicate val = null;

        CommonTree RELOP15=null;
        TransactionalIO l = null;

        TransactionalIO r = null;


        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:113:4: ( ^( RELOP l= param r= param ) )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:113:5: ^( RELOP l= param r= param )
            {
            RELOP15=(CommonTree)match(input,RELOP,FOLLOW_RELOP_in_predicate550); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_param_in_predicate554);
            l=param();

            state._fsp--;

            pushFollow(FOLLOW_param_in_predicate558);
            r=param();

            state._fsp--;


            match(input, Token.UP, null); 
            val = new Predicate(l, r, Predicate.operator((RELOP15!=null?RELOP15.getText():null)));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return val;
    }
    // $ANTLR end "predicate"


    // $ANTLR start "param"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:116:1: param returns [TransactionalIO val] : ( function | element );
    public final TransactionalIO param() throws RecognitionException {
        TransactionalIO val = null;

        FunComposition function16 = null;

        TransactionalIO element17 = null;


        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:117:4: ( function | element )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==FUNCTION) ) {
                alt13=1;
            }
            else if ( (LA13_0==PORT||LA13_0==CELL||LA13_0==CONSTANT) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:117:5: function
                    {
                    pushFollow(FOLLOW_function_in_param584);
                    function16=function();

                    state._fsp--;

                    val = function16;

                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:118:5: element
                    {
                    pushFollow(FOLLOW_element_in_param597);
                    element17=element();

                    state._fsp--;

                    val = element17;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return val;
    }
    // $ANTLR end "param"


    // $ANTLR start "function"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:121:1: function returns [FunComposition val] : ^( FUNCTION ( element ) ( IDENT )+ ) ;
    public final FunComposition function() throws RecognitionException {
        FunComposition val = null;

        CommonTree IDENT19=null;
        TransactionalIO element18 = null;


        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:122:4: ( ^( FUNCTION ( element ) ( IDENT )+ ) )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:122:5: ^( FUNCTION ( element ) ( IDENT )+ )
            {
            match(input,FUNCTION,FOLLOW_FUNCTION_in_function626); 

            match(input, Token.DOWN, null); 
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:122:16: ( element )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:123:5: element
            {
            pushFollow(FOLLOW_element_in_function634);
            element18=element();

            state._fsp--;

            val = new FunComposition(element18);

            }

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:124:6: ( IDENT )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==IDENT) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:124:7: IDENT
            	    {
            	    IDENT19=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_function648); 
            	    val.addComposition(getFunc((IDENT19!=null?IDENT19.getText():null)));

            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return val;
    }
    // $ANTLR end "function"


    // $ANTLR start "element"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:128:1: element returns [TransactionalIO val] : ( port | cell | ^( CONSTANT INT ) | ^( CONSTANT GLOB ) | ^( CONSTANT TRUE ) | ^( CONSTANT FALSE ) );
    public final TransactionalIO element() throws RecognitionException {
        TransactionalIO val = null;

        CommonTree INT22=null;
        CommonTree GLOB23=null;
        TransactionalIO port20 = null;

        Cell cell21 = null;


        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:129:4: ( port | cell | ^( CONSTANT INT ) | ^( CONSTANT GLOB ) | ^( CONSTANT TRUE ) | ^( CONSTANT FALSE ) )
            int alt15=6;
            switch ( input.LA(1) ) {
            case PORT:
                {
                alt15=1;
                }
                break;
            case CELL:
                {
                alt15=2;
                }
                break;
            case CONSTANT:
                {
                int LA15_3 = input.LA(2);

                if ( (LA15_3==DOWN) ) {
                    switch ( input.LA(3) ) {
                    case INT:
                        {
                        alt15=3;
                        }
                        break;
                    case GLOB:
                        {
                        alt15=4;
                        }
                        break;
                    case TRUE:
                        {
                        alt15=5;
                        }
                        break;
                    case FALSE:
                        {
                        alt15=6;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 15, 4, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:129:5: port
                    {
                    pushFollow(FOLLOW_port_in_element691);
                    port20=port();

                    state._fsp--;

                    val = port20;

                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:130:5: cell
                    {
                    pushFollow(FOLLOW_cell_in_element704);
                    cell21=cell();

                    state._fsp--;

                    val = cell21;

                    }
                    break;
                case 3 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:131:5: ^( CONSTANT INT )
                    {
                    match(input,CONSTANT,FOLLOW_CONSTANT_in_element718); 

                    match(input, Token.DOWN, null); 
                    INT22=(CommonTree)match(input,INT,FOLLOW_INT_in_element720); 

                    match(input, Token.UP, null); 
                    val = new Constant((INT22!=null?INT22.getText():null));

                    }
                    break;
                case 4 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:132:5: ^( CONSTANT GLOB )
                    {
                    match(input,CONSTANT,FOLLOW_CONSTANT_in_element732); 

                    match(input, Token.DOWN, null); 
                    GLOB23=(CommonTree)match(input,GLOB,FOLLOW_GLOB_in_element734); 

                    match(input, Token.UP, null); 
                    val = new Constant((GLOB23!=null?GLOB23.getText():null));

                    }
                    break;
                case 5 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:133:5: ^( CONSTANT TRUE )
                    {
                    match(input,CONSTANT,FOLLOW_CONSTANT_in_element747); 

                    match(input, Token.DOWN, null); 
                    match(input,TRUE,FOLLOW_TRUE_in_element749); 

                    match(input, Token.UP, null); 
                    val = Literal.TRUE;

                    }
                    break;
                case 6 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/interpreter/treewalker/XCAgen.g:134:5: ^( CONSTANT FALSE )
                    {
                    match(input,CONSTANT,FOLLOW_CONSTANT_in_element762); 

                    match(input, Token.DOWN, null); 
                    match(input,FALSE,FOLLOW_FALSE_in_element764); 

                    match(input, Token.UP, null); 
                    val = Literal.FALSE;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return val;
    }
    // $ANTLR end "element"

    // Delegated rules


 

    public static final BitSet FOLLOW_automaton_in_module52 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_CA_in_automaton87 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_GLOB_in_automaton89 = new BitSet(new long[]{0x0000000000300008L});
    public static final BitSet FOLLOW_port_in_automaton101 = new BitSet(new long[]{0x0000000000300008L});
    public static final BitSet FOLLOW_state_in_automaton121 = new BitSet(new long[]{0x0000000000200008L});
    public static final BitSet FOLLOW_PORT_in_port154 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_port156 = new BitSet(new long[]{0x0000000020000008L});
    public static final BitSet FOLLOW_IO_in_port158 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STATE_in_state185 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_state187 = new BitSet(new long[]{0x0000000000C00018L});
    public static final BitSet FOLLOW_INITIALSTATE_in_state199 = new BitSet(new long[]{0x0000000000C00008L});
    public static final BitSet FOLLOW_cell_in_state218 = new BitSet(new long[]{0x0000000000C00008L});
    public static final BitSet FOLLOW_transition_in_state238 = new BitSet(new long[]{0x0000000000400008L});
    public static final BitSet FOLLOW_CELL_in_cell271 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_cell273 = new BitSet(new long[]{0x0000000140000308L});
    public static final BitSet FOLLOW_GLOB_in_cell278 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INT_in_cell282 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TRUE_in_cell286 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FALSE_in_cell290 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TRANS_in_transition319 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_port_in_transition322 = new BitSet(new long[]{0x0000000018104C00L});
    public static final BitSet FOLLOW_constraint_in_transition340 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_state_in_transition353 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OR_in_constraint384 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_constraint_in_constraint399 = new BitSet(new long[]{0x0000000018004C08L});
    public static final BitSet FOLLOW_AND_in_constraint420 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_constraint_in_constraint437 = new BitSet(new long[]{0x0000000018004C08L});
    public static final BitSet FOLLOW_equation_in_constraint457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_predicate_in_constraint470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONSTANT_in_constraint484 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_set_in_constraint486 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ASSIGN_in_equation515 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_element_in_equation519 = new BitSet(new long[]{0x000000000C900000L});
    public static final BitSet FOLLOW_param_in_equation523 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RELOP_in_predicate550 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_param_in_predicate554 = new BitSet(new long[]{0x000000000C900000L});
    public static final BitSet FOLLOW_param_in_predicate558 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_function_in_param584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_in_param597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_function626 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_element_in_function634 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_IDENT_in_function648 = new BitSet(new long[]{0x0000000080000008L});
    public static final BitSet FOLLOW_port_in_element691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_cell_in_element704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONSTANT_in_element718 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INT_in_element720 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONSTANT_in_element732 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_GLOB_in_element734 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONSTANT_in_element747 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TRUE_in_element749 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONSTANT_in_element762 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_FALSE_in_element764 = new BitSet(new long[]{0x0000000000000008L});

}