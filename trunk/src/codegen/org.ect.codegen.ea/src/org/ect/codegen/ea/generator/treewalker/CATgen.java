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
// $ANTLR 3.2 Sep 23, 2009 12:02:23 /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g 2010-02-16 17:23:43

package org.ect.codegen.ea.generator.treewalker;

import java.util.Set;
import java.util.HashSet;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;
import java.util.HashMap;
public class CATgen extends TreeParser {
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


        public CATgen(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public CATgen(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected StringTemplateGroup templateLib =
      new StringTemplateGroup("CATgenTemplates", AngleBracketTemplateLexer.class);

    public void setTemplateLib(StringTemplateGroup templateLib) {
      this.templateLib = templateLib;
    }
    public StringTemplateGroup getTemplateLib() {
      return templateLib;
    }
    /** allows convenient multi-value initialization:
     *  "new STAttrMap().put(...).put(...)"
     */
    public static class STAttrMap extends HashMap {
      public STAttrMap put(String attrName, Object value) {
        super.put(attrName, value);
        return this;
      }
      public STAttrMap put(String attrName, int value) {
        super.put(attrName, new Integer(value));
        return this;
      }
    }

    public String[] getTokenNames() { return CATgen.tokenNames; }
    public String getGrammarFileName() { return "/ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g"; }


    	int eq, fn, pred, con, dis;
    	Set<String> cells = new HashSet<String>();


    public static class module_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "module"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:21:1: module : ( automaton )+ ;
    public final CATgen.module_return module() throws RecognitionException {
        CATgen.module_return retval = new CATgen.module_return();
        retval.start = input.LT(1);

        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:21:11: ( ( automaton )+ )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:21:13: ( automaton )+
            {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:21:13: ( automaton )+
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
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:21:13: automaton
            	    {
            	    pushFollow(FOLLOW_automaton_in_module49);
            	    automaton();

            	    state._fsp--;


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
        return retval;
    }
    // $ANTLR end "module"

    public static class automaton_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "automaton"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:24:1: automaton : ^( CA GLOB (p+= port )* (s+= state )* ) -> automaton(id=$GLOBports=$pstates=$s);
    public final CATgen.automaton_return automaton() throws RecognitionException {
        CATgen.automaton_return retval = new CATgen.automaton_return();
        retval.start = input.LT(1);

        CommonTree GLOB1=null;
        List list_p=null;
        List list_s=null;
        RuleReturnScope p = null;
        RuleReturnScope s = null;
        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:24:11: ( ^( CA GLOB (p+= port )* (s+= state )* ) -> automaton(id=$GLOBports=$pstates=$s))
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:24:12: ^( CA GLOB (p+= port )* (s+= state )* )
            {
            match(input,CA,FOLLOW_CA_in_automaton70); 

            match(input, Token.DOWN, null); 
            GLOB1=(CommonTree)match(input,GLOB,FOLLOW_GLOB_in_automaton72); 
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:24:24: (p+= port )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==PORT) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:24:24: p+= port
            	    {
            	    pushFollow(FOLLOW_port_in_automaton77);
            	    p=port();

            	    state._fsp--;

            	    if (list_p==null) list_p=new ArrayList();
            	    list_p.add(p.getTemplate());


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:24:33: (s+= state )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==STATE) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:24:33: s+= state
            	    {
            	    pushFollow(FOLLOW_state_in_automaton82);
            	    s=state();

            	    state._fsp--;

            	    if (list_s==null) list_s=new ArrayList();
            	    list_s.add(s.getTemplate());


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            match(input, Token.UP, null); 


            // TEMPLATE REWRITE
            // 25:8: -> automaton(id=$GLOBports=$pstates=$s)
            {
                retval.st = templateLib.getInstanceOf("automaton",
              new STAttrMap().put("id", GLOB1).put("ports", list_p).put("states", list_s));
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "automaton"

    public static class port_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "port"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:28:1: port : ^( PORT IDENT ( IO )? ) -> port(id=$IDENTtype=$IO);
    public final CATgen.port_return port() throws RecognitionException {
        CATgen.port_return retval = new CATgen.port_return();
        retval.start = input.LT(1);

        CommonTree IDENT2=null;
        CommonTree IO3=null;

        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:28:11: ( ^( PORT IDENT ( IO )? ) -> port(id=$IDENTtype=$IO))
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:28:12: ^( PORT IDENT ( IO )? )
            {
            match(input,PORT,FOLLOW_PORT_in_port134); 

            match(input, Token.DOWN, null); 
            IDENT2=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_port136); 
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:28:25: ( IO )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==IO) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:28:25: IO
                    {
                    IO3=(CommonTree)match(input,IO,FOLLOW_IO_in_port138); 

                    }
                    break;

            }


            match(input, Token.UP, null); 


            // TEMPLATE REWRITE
            // 29:8: -> port(id=$IDENTtype=$IO)
            {
                retval.st = templateLib.getInstanceOf("port",
              new STAttrMap().put("id", IDENT2).put("type", IO3));
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "port"

    public static class state_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "state"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:32:1: state : ^( STATE IDENT ( INITIALSTATE )? (c+= cell )* (t+= transition )* ) -> state(name=$IDENTisstart=$INITIALSTATEcells=$ctrans=$t);
    public final CATgen.state_return state() throws RecognitionException {
        CATgen.state_return retval = new CATgen.state_return();
        retval.start = input.LT(1);

        CommonTree IDENT4=null;
        CommonTree INITIALSTATE5=null;
        List list_c=null;
        List list_t=null;
        RuleReturnScope c = null;
        RuleReturnScope t = null;
        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:32:11: ( ^( STATE IDENT ( INITIALSTATE )? (c+= cell )* (t+= transition )* ) -> state(name=$IDENTisstart=$INITIALSTATEcells=$ctrans=$t))
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:32:12: ^( STATE IDENT ( INITIALSTATE )? (c+= cell )* (t+= transition )* )
            {
            match(input,STATE,FOLLOW_STATE_in_state187); 

            match(input, Token.DOWN, null); 
            IDENT4=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_state189); 
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:32:26: ( INITIALSTATE )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==INITIALSTATE) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:32:26: INITIALSTATE
                    {
                    INITIALSTATE5=(CommonTree)match(input,INITIALSTATE,FOLLOW_INITIALSTATE_in_state191); 

                    }
                    break;

            }

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:32:40: (c+= cell )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==CELL) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:32:41: c+= cell
            	    {
            	    pushFollow(FOLLOW_cell_in_state197);
            	    c=cell();

            	    state._fsp--;

            	    if (list_c==null) list_c=new ArrayList();
            	    list_c.add(c.getTemplate());


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:32:51: (t+= transition )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==TRANS) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:32:52: t+= transition
            	    {
            	    pushFollow(FOLLOW_transition_in_state204);
            	    t=transition();

            	    state._fsp--;

            	    if (list_t==null) list_t=new ArrayList();
            	    list_t.add(t.getTemplate());


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            match(input, Token.UP, null); 


            // TEMPLATE REWRITE
            // 33:8: -> state(name=$IDENTisstart=$INITIALSTATEcells=$ctrans=$t)
            {
                retval.st = templateLib.getInstanceOf("state",
              new STAttrMap().put("name", IDENT4).put("isstart", INITIALSTATE5).put("cells", list_c).put("trans", list_t));
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "state"

    public static class cell_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "cell"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:36:1: cell : ^( CELL IDENT (val= GLOB | val= INT | val= TRUE | val= FALSE )? ) -> {cells.add($IDENT.text)}? cell(id=$IDENTinit=$val) -> {$st=null;};
    public final CATgen.cell_return cell() throws RecognitionException {
        CATgen.cell_return retval = new CATgen.cell_return();
        retval.start = input.LT(1);

        CommonTree val=null;
        CommonTree IDENT6=null;

        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:36:11: ( ^( CELL IDENT (val= GLOB | val= INT | val= TRUE | val= FALSE )? ) -> {cells.add($IDENT.text)}? cell(id=$IDENTinit=$val) -> {$st=null;})
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:36:12: ^( CELL IDENT (val= GLOB | val= INT | val= TRUE | val= FALSE )? )
            {
            match(input,CELL,FOLLOW_CELL_in_cell263); 

            match(input, Token.DOWN, null); 
            IDENT6=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_cell265); 
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:36:25: (val= GLOB | val= INT | val= TRUE | val= FALSE )?
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
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:36:26: val= GLOB
                    {
                    val=(CommonTree)match(input,GLOB,FOLLOW_GLOB_in_cell270); 

                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:36:35: val= INT
                    {
                    val=(CommonTree)match(input,INT,FOLLOW_INT_in_cell274); 

                    }
                    break;
                case 3 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:36:43: val= TRUE
                    {
                    val=(CommonTree)match(input,TRUE,FOLLOW_TRUE_in_cell278); 

                    }
                    break;
                case 4 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:36:52: val= FALSE
                    {
                    val=(CommonTree)match(input,FALSE,FOLLOW_FALSE_in_cell282); 

                    }
                    break;

            }


            match(input, Token.UP, null); 


            // TEMPLATE REWRITE
            // 37:8: -> {cells.add($IDENT.text)}? cell(id=$IDENTinit=$val)
            if (cells.add((IDENT6!=null?IDENT6.getText():null))) {
                retval.st = templateLib.getInstanceOf("cell",
              new STAttrMap().put("id", IDENT6).put("init", val));
            }
            else // 38:8: -> {$st=null;}
            {
                retval.st = retval.st =null;;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "cell"

    public static class transition_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "transition"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:41:1: transition : ^( TRANS (p+= port )* constraint state ) -> transition(dest=$state.stports=$pcons=$constraint.st);
    public final CATgen.transition_return transition() throws RecognitionException {
        CATgen.transition_return retval = new CATgen.transition_return();
        retval.start = input.LT(1);

        List list_p=null;
        CATgen.state_return state7 = null;

        CATgen.constraint_return constraint8 = null;

        RuleReturnScope p = null;
        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:41:11: ( ^( TRANS (p+= port )* constraint state ) -> transition(dest=$state.stports=$pcons=$constraint.st))
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:41:12: ^( TRANS (p+= port )* constraint state )
            {
            match(input,TRANS,FOLLOW_TRANS_in_transition345); 

            match(input, Token.DOWN, null); 
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:41:20: (p+= port )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==PORT) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:41:21: p+= port
            	    {
            	    pushFollow(FOLLOW_port_in_transition350);
            	    p=port();

            	    state._fsp--;

            	    if (list_p==null) list_p=new ArrayList();
            	    list_p.add(p.getTemplate());


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            pushFollow(FOLLOW_constraint_in_transition354);
            constraint8=constraint();

            state._fsp--;

            pushFollow(FOLLOW_state_in_transition356);
            state7=state();

            state._fsp--;


            match(input, Token.UP, null); 


            // TEMPLATE REWRITE
            // 42:8: -> transition(dest=$state.stports=$pcons=$constraint.st)
            {
                retval.st = templateLib.getInstanceOf("transition",
              new STAttrMap().put("dest", (state7!=null?state7.st:null)).put("ports", list_p).put("cons", (constraint8!=null?constraint8.st:null)));
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "transition"

    public static class constraint_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "constraint"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:45:1: constraint : ( ^( OR (m+= constraint )+ ) -> disjunction(id=\"dis\"+(++dis)members=$m) | ^( AND (m+= constraint )+ ) -> conjunction(id=\"con\"+(++con)members=$m) | ^( ASSIGN l= parameter r= parameter ) -> equation(id=\"eqn\"+(++eq)lhs=$l.strhs=$r.st) | ^( RELOP l= parameter r= parameter ) -> predicate(id=\"pred\"+(++pred)lhs=$l.strhs=$r.stop=$RELOP.getText()) | literal -> {$literal.st});
    public final CATgen.constraint_return constraint() throws RecognitionException {
        CATgen.constraint_return retval = new CATgen.constraint_return();
        retval.start = input.LT(1);

        CommonTree RELOP9=null;
        List list_m=null;
        CATgen.parameter_return l = null;

        CATgen.parameter_return r = null;

        CATgen.literal_return literal10 = null;

        RuleReturnScope m = null;
        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:45:11: ( ^( OR (m+= constraint )+ ) -> disjunction(id=\"dis\"+(++dis)members=$m) | ^( AND (m+= constraint )+ ) -> conjunction(id=\"con\"+(++con)members=$m) | ^( ASSIGN l= parameter r= parameter ) -> equation(id=\"eqn\"+(++eq)lhs=$l.strhs=$r.st) | ^( RELOP l= parameter r= parameter ) -> predicate(id=\"pred\"+(++pred)lhs=$l.strhs=$r.stop=$RELOP.getText()) | literal -> {$literal.st})
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
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:45:12: ^( OR (m+= constraint )+ )
                    {
                    match(input,OR,FOLLOW_OR_in_constraint402); 

                    match(input, Token.DOWN, null); 
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:45:18: (m+= constraint )+
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
                    	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:45:19: m+= constraint
                    	    {
                    	    pushFollow(FOLLOW_constraint_in_constraint408);
                    	    m=constraint();

                    	    state._fsp--;

                    	    if (list_m==null) list_m=new ArrayList();
                    	    list_m.add(m.getTemplate());


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


                    // TEMPLATE REWRITE
                    // 46:8: -> disjunction(id=\"dis\"+(++dis)members=$m)
                    {
                        retval.st = templateLib.getInstanceOf("disjunction",
                      new STAttrMap().put("id", "dis"+(++dis)).put("members", list_m));
                    }


                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:47:6: ^( AND (m+= constraint )+ )
                    {
                    match(input,AND,FOLLOW_AND_in_constraint440); 

                    match(input, Token.DOWN, null); 
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:47:12: (m+= constraint )+
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
                    	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:47:13: m+= constraint
                    	    {
                    	    pushFollow(FOLLOW_constraint_in_constraint445);
                    	    m=constraint();

                    	    state._fsp--;

                    	    if (list_m==null) list_m=new ArrayList();
                    	    list_m.add(m.getTemplate());


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


                    // TEMPLATE REWRITE
                    // 48:8: -> conjunction(id=\"con\"+(++con)members=$m)
                    {
                        retval.st = templateLib.getInstanceOf("conjunction",
                      new STAttrMap().put("id", "con"+(++con)).put("members", list_m));
                    }


                    }
                    break;
                case 3 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:49:12: ^( ASSIGN l= parameter r= parameter )
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_constraint483); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_parameter_in_constraint487);
                    l=parameter();

                    state._fsp--;

                    pushFollow(FOLLOW_parameter_in_constraint491);
                    r=parameter();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 50:8: -> equation(id=\"eqn\"+(++eq)lhs=$l.strhs=$r.st)
                    {
                        retval.st = templateLib.getInstanceOf("equation",
                      new STAttrMap().put("id", "eqn"+(++eq)).put("lhs", (l!=null?l.st:null)).put("rhs", (r!=null?r.st:null)));
                    }


                    }
                    break;
                case 4 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:51:12: ^( RELOP l= parameter r= parameter )
                    {
                    RELOP9=(CommonTree)match(input,RELOP,FOLLOW_RELOP_in_constraint531); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_parameter_in_constraint535);
                    l=parameter();

                    state._fsp--;

                    pushFollow(FOLLOW_parameter_in_constraint539);
                    r=parameter();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 52:16: -> predicate(id=\"pred\"+(++pred)lhs=$l.strhs=$r.stop=$RELOP.getText())
                    {
                        retval.st = templateLib.getInstanceOf("predicate",
                      new STAttrMap().put("id", "pred"+(++pred)).put("lhs", (l!=null?l.st:null)).put("rhs", (r!=null?r.st:null)).put("op", RELOP9.getText()));
                    }


                    }
                    break;
                case 5 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:53:13: literal
                    {
                    pushFollow(FOLLOW_literal_in_constraint596);
                    literal10=literal();

                    state._fsp--;



                    // TEMPLATE REWRITE
                    // 53:23: -> {$literal.st}
                    {
                        retval.st = (literal10!=null?literal10.st:null);
                    }


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
        return retval;
    }
    // $ANTLR end "constraint"

    public static class function_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "function"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:56:1: function : ( ^( FUNCTION element (f+= IDENT )+ ) -> function(id=\"func\"+(++fn)arg=$element.stfuncs=$f) | ^( FUNCTION literal (f+= IDENT )+ ) -> function(id=\"func\"+(++fn)arg=$literal.stfuncs=$f));
    public final CATgen.function_return function() throws RecognitionException {
        CATgen.function_return retval = new CATgen.function_return();
        retval.start = input.LT(1);

        CommonTree f=null;
        List list_f=null;
        CATgen.element_return element11 = null;

        CATgen.literal_return literal12 = null;


        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:56:11: ( ^( FUNCTION element (f+= IDENT )+ ) -> function(id=\"func\"+(++fn)arg=$element.stfuncs=$f) | ^( FUNCTION literal (f+= IDENT )+ ) -> function(id=\"func\"+(++fn)arg=$literal.stfuncs=$f))
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==FUNCTION) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==DOWN) ) {
                    int LA15_2 = input.LA(3);

                    if ( (LA15_2==CONSTANT) ) {
                        int LA15_3 = input.LA(4);

                        if ( (LA15_3==DOWN) ) {
                            int LA15_5 = input.LA(5);

                            if ( ((LA15_5>=FALSE && LA15_5<=TRUE)) ) {
                                alt15=2;
                            }
                            else if ( (LA15_5==INT||LA15_5==GLOB) ) {
                                alt15=1;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 15, 5, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 15, 3, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA15_2==PORT||LA15_2==CELL) ) {
                        alt15=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 15, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:56:12: ^( FUNCTION element (f+= IDENT )+ )
                    {
                    match(input,FUNCTION,FOLLOW_FUNCTION_in_function623); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_element_in_function625);
                    element11=element();

                    state._fsp--;

                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:56:31: (f+= IDENT )+
                    int cnt13=0;
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==IDENT) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:56:32: f+= IDENT
                    	    {
                    	    f=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_function630); 
                    	    if (list_f==null) list_f=new ArrayList();
                    	    list_f.add(f);


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt13 >= 1 ) break loop13;
                                EarlyExitException eee =
                                    new EarlyExitException(13, input);
                                throw eee;
                        }
                        cnt13++;
                    } while (true);


                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 57:8: -> function(id=\"func\"+(++fn)arg=$element.stfuncs=$f)
                    {
                        retval.st = templateLib.getInstanceOf("function",
                      new STAttrMap().put("id", "func"+(++fn)).put("arg", (element11!=null?element11.st:null)).put("funcs", list_f));
                    }


                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:58:12: ^( FUNCTION literal (f+= IDENT )+ )
                    {
                    match(input,FUNCTION,FOLLOW_FUNCTION_in_function674); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_literal_in_function676);
                    literal12=literal();

                    state._fsp--;

                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:58:31: (f+= IDENT )+
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
                    	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:58:32: f+= IDENT
                    	    {
                    	    f=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_function681); 
                    	    if (list_f==null) list_f=new ArrayList();
                    	    list_f.add(f);


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


                    // TEMPLATE REWRITE
                    // 59:8: -> function(id=\"func\"+(++fn)arg=$literal.stfuncs=$f)
                    {
                        retval.st = templateLib.getInstanceOf("function",
                      new STAttrMap().put("id", "func"+(++fn)).put("arg", (literal12!=null?literal12.st:null)).put("funcs", list_f));
                    }


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
        return retval;
    }
    // $ANTLR end "function"

    public static class parameter_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "parameter"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:62:1: parameter : ( literal -> {$literal.st} | element -> {$element.st} | function -> {$function.st});
    public final CATgen.parameter_return parameter() throws RecognitionException {
        CATgen.parameter_return retval = new CATgen.parameter_return();
        retval.start = input.LT(1);

        CATgen.literal_return literal13 = null;

        CATgen.element_return element14 = null;

        CATgen.function_return function15 = null;


        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:62:11: ( literal -> {$literal.st} | element -> {$element.st} | function -> {$function.st})
            int alt16=3;
            switch ( input.LA(1) ) {
            case CONSTANT:
                {
                int LA16_1 = input.LA(2);

                if ( (LA16_1==DOWN) ) {
                    int LA16_4 = input.LA(3);

                    if ( ((LA16_4>=FALSE && LA16_4<=TRUE)) ) {
                        alt16=1;
                    }
                    else if ( (LA16_4==INT||LA16_4==GLOB) ) {
                        alt16=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 4, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 1, input);

                    throw nvae;
                }
                }
                break;
            case PORT:
            case CELL:
                {
                alt16=2;
                }
                break;
            case FUNCTION:
                {
                alt16=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:62:12: literal
                    {
                    pushFollow(FOLLOW_literal_in_parameter732);
                    literal13=literal();

                    state._fsp--;



                    // TEMPLATE REWRITE
                    // 62:22: -> {$literal.st}
                    {
                        retval.st = (literal13!=null?literal13.st:null);
                    }


                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:63:12: element
                    {
                    pushFollow(FOLLOW_element_in_parameter759);
                    element14=element();

                    state._fsp--;



                    // TEMPLATE REWRITE
                    // 63:22: -> {$element.st}
                    {
                        retval.st = (element14!=null?element14.st:null);
                    }


                    }
                    break;
                case 3 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:64:12: function
                    {
                    pushFollow(FOLLOW_function_in_parameter778);
                    function15=function();

                    state._fsp--;



                    // TEMPLATE REWRITE
                    // 64:23: -> {$function.st}
                    {
                        retval.st = (function15!=null?function15.st:null);
                    }


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
        return retval;
    }
    // $ANTLR end "parameter"

    public static class element_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "element"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:67:1: element : ( ^( PORT IDENT ) -> filler(id=$IDENT) | ^( CELL IDENT ) -> filler(id=$IDENT) | ^( CONSTANT INT ) -> const(id=\"new Constant(\"+$INT.token.getText()+\")\") | ^( CONSTANT GLOB ) -> const(id=\"new Constant(\"+$GLOB.token.getText()+\")\"));
    public final CATgen.element_return element() throws RecognitionException {
        CATgen.element_return retval = new CATgen.element_return();
        retval.start = input.LT(1);

        CommonTree IDENT16=null;
        CommonTree IDENT17=null;
        CommonTree INT18=null;
        CommonTree GLOB19=null;

        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:67:11: ( ^( PORT IDENT ) -> filler(id=$IDENT) | ^( CELL IDENT ) -> filler(id=$IDENT) | ^( CONSTANT INT ) -> const(id=\"new Constant(\"+$INT.token.getText()+\")\") | ^( CONSTANT GLOB ) -> const(id=\"new Constant(\"+$GLOB.token.getText()+\")\"))
            int alt17=4;
            switch ( input.LA(1) ) {
            case PORT:
                {
                alt17=1;
                }
                break;
            case CELL:
                {
                alt17=2;
                }
                break;
            case CONSTANT:
                {
                int LA17_3 = input.LA(2);

                if ( (LA17_3==DOWN) ) {
                    int LA17_4 = input.LA(3);

                    if ( (LA17_4==INT) ) {
                        alt17=3;
                    }
                    else if ( (LA17_4==GLOB) ) {
                        alt17=4;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 17, 4, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:67:12: ^( PORT IDENT )
                    {
                    match(input,PORT,FOLLOW_PORT_in_element818); 

                    match(input, Token.DOWN, null); 
                    IDENT16=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_element820); 

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 67:26: -> filler(id=$IDENT)
                    {
                        retval.st = templateLib.getInstanceOf("filler",
                      new STAttrMap().put("id", IDENT16));
                    }


                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:68:12: ^( CELL IDENT )
                    {
                    match(input,CELL,FOLLOW_CELL_in_element845); 

                    match(input, Token.DOWN, null); 
                    IDENT17=(CommonTree)match(input,IDENT,FOLLOW_IDENT_in_element847); 

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 68:26: -> filler(id=$IDENT)
                    {
                        retval.st = templateLib.getInstanceOf("filler",
                      new STAttrMap().put("id", IDENT17));
                    }


                    }
                    break;
                case 3 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:69:12: ^( CONSTANT INT )
                    {
                    match(input,CONSTANT,FOLLOW_CONSTANT_in_element872); 

                    match(input, Token.DOWN, null); 
                    INT18=(CommonTree)match(input,INT,FOLLOW_INT_in_element874); 

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 69:28: -> const(id=\"new Constant(\"+$INT.token.getText()+\")\")
                    {
                        retval.st = templateLib.getInstanceOf("const",
                      new STAttrMap().put("id", "new Constant("+INT18.token.getText()+")"));
                    }


                    }
                    break;
                case 4 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:70:12: ^( CONSTANT GLOB )
                    {
                    match(input,CONSTANT,FOLLOW_CONSTANT_in_element902); 

                    match(input, Token.DOWN, null); 
                    GLOB19=(CommonTree)match(input,GLOB,FOLLOW_GLOB_in_element904); 

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 70:29: -> const(id=\"new Constant(\"+$GLOB.token.getText()+\")\")
                    {
                        retval.st = templateLib.getInstanceOf("const",
                      new STAttrMap().put("id", "new Constant("+GLOB19.token.getText()+")"));
                    }


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
        return retval;
    }
    // $ANTLR end "element"

    public static class literal_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };

    // $ANTLR start "literal"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:73:1: literal : ( ^( CONSTANT TRUE ) -> filler(id=\"Literal.TRUE\") | ^( CONSTANT FALSE ) -> filler(id=\"Literal.FALSE\"));
    public final CATgen.literal_return literal() throws RecognitionException {
        CATgen.literal_return retval = new CATgen.literal_return();
        retval.start = input.LT(1);

        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:73:11: ( ^( CONSTANT TRUE ) -> filler(id=\"Literal.TRUE\") | ^( CONSTANT FALSE ) -> filler(id=\"Literal.FALSE\"))
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==CONSTANT) ) {
                int LA18_1 = input.LA(2);

                if ( (LA18_1==DOWN) ) {
                    int LA18_2 = input.LA(3);

                    if ( (LA18_2==TRUE) ) {
                        alt18=1;
                    }
                    else if ( (LA18_2==FALSE) ) {
                        alt18=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 18, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:73:12: ^( CONSTANT TRUE )
                    {
                    match(input,CONSTANT,FOLLOW_CONSTANT_in_literal937); 

                    match(input, Token.DOWN, null); 
                    match(input,TRUE,FOLLOW_TRUE_in_literal939); 

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 73:29: -> filler(id=\"Literal.TRUE\")
                    {
                        retval.st = templateLib.getInstanceOf("filler",
                      new STAttrMap().put("id", "Literal.TRUE"));
                    }


                    }
                    break;
                case 2 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/codegen/treewalker/CATgen.g:74:12: ^( CONSTANT FALSE )
                    {
                    match(input,CONSTANT,FOLLOW_CONSTANT_in_literal966); 

                    match(input, Token.DOWN, null); 
                    match(input,FALSE,FOLLOW_FALSE_in_literal968); 

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 74:29: -> filler(id=\"Literal.FALSE\")
                    {
                        retval.st = templateLib.getInstanceOf("filler",
                      new STAttrMap().put("id", "Literal.FALSE"));
                    }


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
        return retval;
    }
    // $ANTLR end "literal"

    // Delegated rules


 

    public static final BitSet FOLLOW_automaton_in_module49 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_CA_in_automaton70 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_GLOB_in_automaton72 = new BitSet(new long[]{0x0000000000300008L});
    public static final BitSet FOLLOW_port_in_automaton77 = new BitSet(new long[]{0x0000000000300008L});
    public static final BitSet FOLLOW_state_in_automaton82 = new BitSet(new long[]{0x0000000000200008L});
    public static final BitSet FOLLOW_PORT_in_port134 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_port136 = new BitSet(new long[]{0x0000000020000008L});
    public static final BitSet FOLLOW_IO_in_port138 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STATE_in_state187 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_state189 = new BitSet(new long[]{0x0000000000C00018L});
    public static final BitSet FOLLOW_INITIALSTATE_in_state191 = new BitSet(new long[]{0x0000000000C00008L});
    public static final BitSet FOLLOW_cell_in_state197 = new BitSet(new long[]{0x0000000000C00008L});
    public static final BitSet FOLLOW_transition_in_state204 = new BitSet(new long[]{0x0000000000400008L});
    public static final BitSet FOLLOW_CELL_in_cell263 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_cell265 = new BitSet(new long[]{0x0000000140000308L});
    public static final BitSet FOLLOW_GLOB_in_cell270 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INT_in_cell274 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TRUE_in_cell278 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FALSE_in_cell282 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TRANS_in_transition345 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_port_in_transition350 = new BitSet(new long[]{0x0000000018104C00L});
    public static final BitSet FOLLOW_constraint_in_transition354 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_state_in_transition356 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_OR_in_constraint402 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_constraint_in_constraint408 = new BitSet(new long[]{0x0000000018004C08L});
    public static final BitSet FOLLOW_AND_in_constraint440 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_constraint_in_constraint445 = new BitSet(new long[]{0x0000000018004C08L});
    public static final BitSet FOLLOW_ASSIGN_in_constraint483 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_parameter_in_constraint487 = new BitSet(new long[]{0x000000001C904C00L});
    public static final BitSet FOLLOW_parameter_in_constraint491 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RELOP_in_constraint531 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_parameter_in_constraint535 = new BitSet(new long[]{0x000000001C904C00L});
    public static final BitSet FOLLOW_parameter_in_constraint539 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_literal_in_constraint596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_function623 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_element_in_function625 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_IDENT_in_function630 = new BitSet(new long[]{0x0000000080000008L});
    public static final BitSet FOLLOW_FUNCTION_in_function674 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_literal_in_function676 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_IDENT_in_function681 = new BitSet(new long[]{0x0000000080000008L});
    public static final BitSet FOLLOW_literal_in_parameter732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_in_parameter759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_parameter778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PORT_in_element818 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_element820 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CELL_in_element845 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENT_in_element847 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONSTANT_in_element872 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_INT_in_element874 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONSTANT_in_element902 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_GLOB_in_element904 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONSTANT_in_literal937 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TRUE_in_literal939 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_CONSTANT_in_literal966 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_FALSE_in_literal968 = new BitSet(new long[]{0x0000000000000008L});

}