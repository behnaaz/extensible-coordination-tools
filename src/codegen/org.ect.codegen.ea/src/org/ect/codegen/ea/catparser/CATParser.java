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
// $ANTLR 3.2 Sep 23, 2009 12:02:23 /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g 2010-02-05 15:09:26

package org.ect.codegen.ea.catparser;

import java.util.Set;
import java.util.HashSet;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class CATParser extends Parser {
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
    public static final int RPAREN=13;
    public static final int QUOTE=15;
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
    public CAT_Constraint gConstraint;
    // delegators


        public CATParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public CATParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            gConstraint = new CAT_Constraint(input, state, this);         
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
        gConstraint.setTreeAdaptor(this.adaptor);
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return CATParser.tokenNames; }
    public String getGrammarFileName() { return "/ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g"; }


    private void categorisePort(Token ident, Token io) {
    	if (io==null || ident==null) throw new RuntimeException();
    	
    	String id = ident.getText(), type = io.getText(); 
    	if (type.equals("IN")) ((ca_scope)ca_stack.peek()).inports.add(id); 
    	else if (type.equals("OUT")) ((ca_scope)ca_stack.peek()).outports.add(id);
    }


    public static class module_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "module"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:26:1: module : ( ca )+ ;
    public final CATParser.module_return module() throws RecognitionException {
        CATParser.module_return retval = new CATParser.module_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CATParser.ca_return ca1 = null;



        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:26:8: ( ( ca )+ )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:26:10: ( ca )+
            {
            root_0 = (Object)adaptor.nil();

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:26:10: ( ca )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==GLOB) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:26:10: ca
            	    {
            	    pushFollow(FOLLOW_ca_in_module49);
            	    ca1=ca();

            	    state._fsp--;

            	    adaptor.addChild(root_0, ca1.getTree());

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

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "module"

    protected static class ca_scope {
        Set<String> inports;
        Set<String> outports;
    }
    protected Stack ca_stack = new Stack();

    public static class ca_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ca"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:28:1: ca : GLOB ports ( state )? ( SEPARATOR state )* -> ^( CA GLOB ports ( state )* ) ;
    public final CATParser.ca_return ca() throws RecognitionException {
        ca_stack.push(new ca_scope());
        CATParser.ca_return retval = new CATParser.ca_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token GLOB2=null;
        Token SEPARATOR5=null;
        CATParser.ports_return ports3 = null;

        CATParser.state_return state4 = null;

        CATParser.state_return state6 = null;


        Object GLOB2_tree=null;
        Object SEPARATOR5_tree=null;
        RewriteRuleTokenStream stream_GLOB=new RewriteRuleTokenStream(adaptor,"token GLOB");
        RewriteRuleTokenStream stream_SEPARATOR=new RewriteRuleTokenStream(adaptor,"token SEPARATOR");
        RewriteRuleSubtreeStream stream_ports=new RewriteRuleSubtreeStream(adaptor,"rule ports");
        RewriteRuleSubtreeStream stream_state=new RewriteRuleSubtreeStream(adaptor,"rule state");
        ((ca_scope)ca_stack.peek()).inports = new HashSet<String>(); ((ca_scope)ca_stack.peek()).outports = new HashSet<String>();
        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:31:3: ( GLOB ports ( state )? ( SEPARATOR state )* -> ^( CA GLOB ports ( state )* ) )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:31:5: GLOB ports ( state )? ( SEPARATOR state )*
            {
            GLOB2=(Token)match(input,GLOB,FOLLOW_GLOB_in_ca70);  
            stream_GLOB.add(GLOB2);

            pushFollow(FOLLOW_ports_in_ca72);
            ports3=ports();

            state._fsp--;

            stream_ports.add(ports3.getTree());
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:32:4: ( state )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==IDENT) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:32:5: state
                    {
                    pushFollow(FOLLOW_state_in_ca80);
                    state4=state();

                    state._fsp--;

                    stream_state.add(state4.getTree());

                    }
                    break;

            }

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:32:13: ( SEPARATOR state )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==SEPARATOR) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:32:14: SEPARATOR state
            	    {
            	    SEPARATOR5=(Token)match(input,SEPARATOR,FOLLOW_SEPARATOR_in_ca85);  
            	    stream_SEPARATOR.add(SEPARATOR5);

            	    pushFollow(FOLLOW_state_in_ca87);
            	    state6=state();

            	    state._fsp--;

            	    stream_state.add(state6.getTree());

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);



            // AST REWRITE
            // elements: state, GLOB, ports
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 33:9: -> ^( CA GLOB ports ( state )* )
            {
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:33:12: ^( CA GLOB ports ( state )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CA, "CA"), root_1);

                adaptor.addChild(root_1, stream_GLOB.nextNode());
                adaptor.addChild(root_1, stream_ports.nextTree());
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:33:29: ( state )*
                while ( stream_state.hasNext() ) {
                    adaptor.addChild(root_1, stream_state.nextTree());

                }
                stream_state.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            ca_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "ca"

    public static class ports_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ports"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:36:1: ports : BEGIN ( port )? ( SEPARATOR port )* END -> ( port )* ;
    public final CATParser.ports_return ports() throws RecognitionException {
        CATParser.ports_return retval = new CATParser.ports_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token BEGIN7=null;
        Token SEPARATOR9=null;
        Token END11=null;
        CATParser.port_return port8 = null;

        CATParser.port_return port10 = null;


        Object BEGIN7_tree=null;
        Object SEPARATOR9_tree=null;
        Object END11_tree=null;
        RewriteRuleTokenStream stream_SEPARATOR=new RewriteRuleTokenStream(adaptor,"token SEPARATOR");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleTokenStream stream_BEGIN=new RewriteRuleTokenStream(adaptor,"token BEGIN");
        RewriteRuleSubtreeStream stream_port=new RewriteRuleSubtreeStream(adaptor,"rule port");
        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:36:7: ( BEGIN ( port )? ( SEPARATOR port )* END -> ( port )* )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:36:9: BEGIN ( port )? ( SEPARATOR port )* END
            {
            BEGIN7=(Token)match(input,BEGIN,FOLLOW_BEGIN_in_ports125);  
            stream_BEGIN.add(BEGIN7);

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:36:15: ( port )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==IDENT) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:36:16: port
                    {
                    pushFollow(FOLLOW_port_in_ports128);
                    port8=port();

                    state._fsp--;

                    stream_port.add(port8.getTree());

                    }
                    break;

            }

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:36:23: ( SEPARATOR port )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==SEPARATOR) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:36:24: SEPARATOR port
            	    {
            	    SEPARATOR9=(Token)match(input,SEPARATOR,FOLLOW_SEPARATOR_in_ports133);  
            	    stream_SEPARATOR.add(SEPARATOR9);

            	    pushFollow(FOLLOW_port_in_ports135);
            	    port10=port();

            	    state._fsp--;

            	    stream_port.add(port10.getTree());

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            END11=(Token)match(input,END,FOLLOW_END_in_ports139);  
            stream_END.add(END11);



            // AST REWRITE
            // elements: port
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 37:9: -> ( port )*
            {
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:37:13: ( port )*
                while ( stream_port.hasNext() ) {
                    adaptor.addChild(root_0, stream_port.nextTree());

                }
                stream_port.reset();

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ports"

    public static class port_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "port"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:40:1: port : ident= IDENT ( SPACER io= IO )? -> ^( PORT IDENT ( IO )? ) ;
    public final CATParser.port_return port() throws RecognitionException {
        CATParser.port_return retval = new CATParser.port_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ident=null;
        Token io=null;
        Token SPACER12=null;

        Object ident_tree=null;
        Object io_tree=null;
        Object SPACER12_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_IO=new RewriteRuleTokenStream(adaptor,"token IO");
        RewriteRuleTokenStream stream_SPACER=new RewriteRuleTokenStream(adaptor,"token SPACER");

        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:40:6: (ident= IDENT ( SPACER io= IO )? -> ^( PORT IDENT ( IO )? ) )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:40:8: ident= IDENT ( SPACER io= IO )?
            {
            ident=(Token)match(input,IDENT,FOLLOW_IDENT_in_port173);  
            stream_IDENT.add(ident);

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:40:20: ( SPACER io= IO )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==SPACER) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:40:21: SPACER io= IO
                    {
                    SPACER12=(Token)match(input,SPACER,FOLLOW_SPACER_in_port176);  
                    stream_SPACER.add(SPACER12);

                    io=(Token)match(input,IO,FOLLOW_IO_in_port180);  
                    stream_IO.add(io);

                    categorisePort(ident, io);

                    }
                    break;

            }



            // AST REWRITE
            // elements: IO, IDENT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 41:9: -> ^( PORT IDENT ( IO )? )
            {
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:41:12: ^( PORT IDENT ( IO )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PORT, "PORT"), root_1);

                adaptor.addChild(root_1, stream_IDENT.nextNode());
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:41:25: ( IO )?
                if ( stream_IO.hasNext() ) {
                    adaptor.addChild(root_1, stream_IO.nextNode());

                }
                stream_IO.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "port"

    public static class state_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "state"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:44:1: state : IDENT ( INITIALSTATE )? ( BEGIN cell ( SEPARATOR cell )* END )? ( BEGIN trans ( SEPARATOR trans )* END )? -> ^( STATE IDENT ( INITIALSTATE )? ( cell )* ( trans )* ) ;
    public final CATParser.state_return state() throws RecognitionException {
        CATParser.state_return retval = new CATParser.state_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT13=null;
        Token INITIALSTATE14=null;
        Token BEGIN15=null;
        Token SEPARATOR17=null;
        Token END19=null;
        Token BEGIN20=null;
        Token SEPARATOR22=null;
        Token END24=null;
        CATParser.cell_return cell16 = null;

        CATParser.cell_return cell18 = null;

        CATParser.trans_return trans21 = null;

        CATParser.trans_return trans23 = null;


        Object IDENT13_tree=null;
        Object INITIALSTATE14_tree=null;
        Object BEGIN15_tree=null;
        Object SEPARATOR17_tree=null;
        Object END19_tree=null;
        Object BEGIN20_tree=null;
        Object SEPARATOR22_tree=null;
        Object END24_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_SEPARATOR=new RewriteRuleTokenStream(adaptor,"token SEPARATOR");
        RewriteRuleTokenStream stream_INITIALSTATE=new RewriteRuleTokenStream(adaptor,"token INITIALSTATE");
        RewriteRuleTokenStream stream_END=new RewriteRuleTokenStream(adaptor,"token END");
        RewriteRuleTokenStream stream_BEGIN=new RewriteRuleTokenStream(adaptor,"token BEGIN");
        RewriteRuleSubtreeStream stream_trans=new RewriteRuleSubtreeStream(adaptor,"rule trans");
        RewriteRuleSubtreeStream stream_cell=new RewriteRuleSubtreeStream(adaptor,"rule cell");
        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:44:8: ( IDENT ( INITIALSTATE )? ( BEGIN cell ( SEPARATOR cell )* END )? ( BEGIN trans ( SEPARATOR trans )* END )? -> ^( STATE IDENT ( INITIALSTATE )? ( cell )* ( trans )* ) )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:44:11: IDENT ( INITIALSTATE )? ( BEGIN cell ( SEPARATOR cell )* END )? ( BEGIN trans ( SEPARATOR trans )* END )?
            {
            IDENT13=(Token)match(input,IDENT,FOLLOW_IDENT_in_state217);  
            stream_IDENT.add(IDENT13);

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:44:17: ( INITIALSTATE )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==INITIALSTATE) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:44:18: INITIALSTATE
                    {
                    INITIALSTATE14=(Token)match(input,INITIALSTATE,FOLLOW_INITIALSTATE_in_state220);  
                    stream_INITIALSTATE.add(INITIALSTATE14);


                    }
                    break;

            }

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:45:4: ( BEGIN cell ( SEPARATOR cell )* END )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==BEGIN) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==IDENT) ) {
                    alt9=1;
                }
            }
            switch (alt9) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:45:5: BEGIN cell ( SEPARATOR cell )* END
                    {
                    BEGIN15=(Token)match(input,BEGIN,FOLLOW_BEGIN_in_state229);  
                    stream_BEGIN.add(BEGIN15);

                    pushFollow(FOLLOW_cell_in_state231);
                    cell16=cell();

                    state._fsp--;

                    stream_cell.add(cell16.getTree());
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:45:16: ( SEPARATOR cell )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==SEPARATOR) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:45:17: SEPARATOR cell
                    	    {
                    	    SEPARATOR17=(Token)match(input,SEPARATOR,FOLLOW_SEPARATOR_in_state234);  
                    	    stream_SEPARATOR.add(SEPARATOR17);

                    	    pushFollow(FOLLOW_cell_in_state236);
                    	    cell18=cell();

                    	    state._fsp--;

                    	    stream_cell.add(cell18.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);

                    END19=(Token)match(input,END,FOLLOW_END_in_state240);  
                    stream_END.add(END19);


                    }
                    break;

            }

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:46:4: ( BEGIN trans ( SEPARATOR trans )* END )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==BEGIN) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:46:5: BEGIN trans ( SEPARATOR trans )* END
                    {
                    BEGIN20=(Token)match(input,BEGIN,FOLLOW_BEGIN_in_state248);  
                    stream_BEGIN.add(BEGIN20);

                    pushFollow(FOLLOW_trans_in_state250);
                    trans21=trans();

                    state._fsp--;

                    stream_trans.add(trans21.getTree());
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:46:17: ( SEPARATOR trans )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==SEPARATOR) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:46:18: SEPARATOR trans
                    	    {
                    	    SEPARATOR22=(Token)match(input,SEPARATOR,FOLLOW_SEPARATOR_in_state253);  
                    	    stream_SEPARATOR.add(SEPARATOR22);

                    	    pushFollow(FOLLOW_trans_in_state255);
                    	    trans23=trans();

                    	    state._fsp--;

                    	    stream_trans.add(trans23.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    END24=(Token)match(input,END,FOLLOW_END_in_state259);  
                    stream_END.add(END24);


                    }
                    break;

            }



            // AST REWRITE
            // elements: INITIALSTATE, trans, IDENT, cell
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 47:9: -> ^( STATE IDENT ( INITIALSTATE )? ( cell )* ( trans )* )
            {
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:47:13: ^( STATE IDENT ( INITIALSTATE )? ( cell )* ( trans )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(STATE, "STATE"), root_1);

                adaptor.addChild(root_1, stream_IDENT.nextNode());
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:47:27: ( INITIALSTATE )?
                if ( stream_INITIALSTATE.hasNext() ) {
                    adaptor.addChild(root_1, stream_INITIALSTATE.nextNode());

                }
                stream_INITIALSTATE.reset();
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:47:41: ( cell )*
                while ( stream_cell.hasNext() ) {
                    adaptor.addChild(root_1, stream_cell.nextTree());

                }
                stream_cell.reset();
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:47:47: ( trans )*
                while ( stream_trans.hasNext() ) {
                    adaptor.addChild(root_1, stream_trans.nextTree());

                }
                stream_trans.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "state"

    public static class cell_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "cell"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:50:1: cell : IDENT ( SPACER (val= GLOB | val= INT | val= TRUE | val= FALSE ) )? -> ^( CELL IDENT ( $val)? ) ;
    public final CATParser.cell_return cell() throws RecognitionException {
        CATParser.cell_return retval = new CATParser.cell_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token val=null;
        Token IDENT25=null;
        Token SPACER26=null;

        Object val_tree=null;
        Object IDENT25_tree=null;
        Object SPACER26_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_GLOB=new RewriteRuleTokenStream(adaptor,"token GLOB");
        RewriteRuleTokenStream stream_INT=new RewriteRuleTokenStream(adaptor,"token INT");
        RewriteRuleTokenStream stream_FALSE=new RewriteRuleTokenStream(adaptor,"token FALSE");
        RewriteRuleTokenStream stream_TRUE=new RewriteRuleTokenStream(adaptor,"token TRUE");
        RewriteRuleTokenStream stream_SPACER=new RewriteRuleTokenStream(adaptor,"token SPACER");

        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:50:6: ( IDENT ( SPACER (val= GLOB | val= INT | val= TRUE | val= FALSE ) )? -> ^( CELL IDENT ( $val)? ) )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:50:8: IDENT ( SPACER (val= GLOB | val= INT | val= TRUE | val= FALSE ) )?
            {
            IDENT25=(Token)match(input,IDENT,FOLLOW_IDENT_in_cell299);  
            stream_IDENT.add(IDENT25);

            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:50:14: ( SPACER (val= GLOB | val= INT | val= TRUE | val= FALSE ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==SPACER) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:50:15: SPACER (val= GLOB | val= INT | val= TRUE | val= FALSE )
                    {
                    SPACER26=(Token)match(input,SPACER,FOLLOW_SPACER_in_cell302);  
                    stream_SPACER.add(SPACER26);

                    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:50:22: (val= GLOB | val= INT | val= TRUE | val= FALSE )
                    int alt12=4;
                    switch ( input.LA(1) ) {
                    case GLOB:
                        {
                        alt12=1;
                        }
                        break;
                    case INT:
                        {
                        alt12=2;
                        }
                        break;
                    case TRUE:
                        {
                        alt12=3;
                        }
                        break;
                    case FALSE:
                        {
                        alt12=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 12, 0, input);

                        throw nvae;
                    }

                    switch (alt12) {
                        case 1 :
                            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:50:23: val= GLOB
                            {
                            val=(Token)match(input,GLOB,FOLLOW_GLOB_in_cell307);  
                            stream_GLOB.add(val);


                            }
                            break;
                        case 2 :
                            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:50:32: val= INT
                            {
                            val=(Token)match(input,INT,FOLLOW_INT_in_cell311);  
                            stream_INT.add(val);


                            }
                            break;
                        case 3 :
                            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:50:40: val= TRUE
                            {
                            val=(Token)match(input,TRUE,FOLLOW_TRUE_in_cell315);  
                            stream_TRUE.add(val);


                            }
                            break;
                        case 4 :
                            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:50:49: val= FALSE
                            {
                            val=(Token)match(input,FALSE,FOLLOW_FALSE_in_cell319);  
                            stream_FALSE.add(val);


                            }
                            break;

                    }


                    }
                    break;

            }



            // AST REWRITE
            // elements: val, IDENT
            // token labels: val
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleTokenStream stream_val=new RewriteRuleTokenStream(adaptor,"token val",val);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 51:9: -> ^( CELL IDENT ( $val)? )
            {
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:51:12: ^( CELL IDENT ( $val)? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CELL, "CELL"), root_1);

                adaptor.addChild(root_1, stream_IDENT.nextNode());
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:51:25: ( $val)?
                if ( stream_val.hasNext() ) {
                    adaptor.addChild(root_1, stream_val.nextNode());

                }
                stream_val.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "cell"

    public static class trans_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "trans"
    // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:54:1: trans : ports constraint[$ca::inports,$ca::outports] state -> ^( TRANS ports constraint state ) ;
    public final CATParser.trans_return trans() throws RecognitionException {
        CATParser.trans_return retval = new CATParser.trans_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CATParser.ports_return ports27 = null;

        CAT_Constraint.constraint_return constraint28 = null;

        CATParser.state_return state29 = null;


        RewriteRuleSubtreeStream stream_ports=new RewriteRuleSubtreeStream(adaptor,"rule ports");
        RewriteRuleSubtreeStream stream_state=new RewriteRuleSubtreeStream(adaptor,"rule state");
        RewriteRuleSubtreeStream stream_constraint=new RewriteRuleSubtreeStream(adaptor,"rule constraint");
        try {
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:54:7: ( ports constraint[$ca::inports,$ca::outports] state -> ^( TRANS ports constraint state ) )
            // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:54:9: ports constraint[$ca::inports,$ca::outports] state
            {
            pushFollow(FOLLOW_ports_in_trans362);
            ports27=ports();

            state._fsp--;

            stream_ports.add(ports27.getTree());
            pushFollow(FOLLOW_constraint_in_trans364);
            constraint28=constraint(((ca_scope)ca_stack.peek()).inports, ((ca_scope)ca_stack.peek()).outports);

            state._fsp--;

            stream_constraint.add(constraint28.getTree());
            pushFollow(FOLLOW_state_in_trans367);
            state29=state();

            state._fsp--;

            stream_state.add(state29.getTree());


            // AST REWRITE
            // elements: state, ports, constraint
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 55:9: -> ^( TRANS ports constraint state )
            {
                // /ufs/maraikar/workspace/cwi.ea.runtime.codegen/src/cwi/ea/runtime/catparser/CAT.g:55:12: ^( TRANS ports constraint state )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(TRANS, "TRANS"), root_1);

                adaptor.addChild(root_1, stream_ports.nextTree());
                adaptor.addChild(root_1, stream_constraint.nextTree());
                adaptor.addChild(root_1, stream_state.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "trans"

    // Delegated rules
    public CAT_Constraint.function_return function() throws RecognitionException { return gConstraint.function(); }
    public CAT_Constraint.eq_pred_return eq_pred() throws RecognitionException { return gConstraint.eq_pred(); }
    public CAT_Constraint.value_return value() throws RecognitionException { return gConstraint.value(); }
    public CAT_Constraint.arg_return arg() throws RecognitionException { return gConstraint.arg(); }
    public CAT_Constraint.conjunction_return conjunction() throws RecognitionException { return gConstraint.conjunction(); }
    public CAT_Constraint.output_return output() throws RecognitionException { return gConstraint.output(); }
    public CAT_Constraint.constant_return constant() throws RecognitionException { return gConstraint.constant(); }
    public CAT_Constraint.literal_return literal() throws RecognitionException { return gConstraint.literal(); }
    public CAT_Constraint.composite_return composite() throws RecognitionException { return gConstraint.composite(); }
    public CAT_Constraint.input_return input() throws RecognitionException { return gConstraint.input(); }
    public CAT_Constraint.constraint_return constraint(Set<String> inports, Set<String> outports) throws RecognitionException { return gConstraint.constraint(inports, outports); }
    public CAT_Constraint.disjunction_return disjunction() throws RecognitionException { return gConstraint.disjunction(); }


 

    public static final BitSet FOLLOW_ca_in_module49 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_GLOB_in_ca70 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ports_in_ca72 = new BitSet(new long[]{0x0000000080010002L});
    public static final BitSet FOLLOW_state_in_ca80 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_SEPARATOR_in_ca85 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_state_in_ca87 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_BEGIN_in_ports125 = new BitSet(new long[]{0x0000000080010040L});
    public static final BitSet FOLLOW_port_in_ports128 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_SEPARATOR_in_ports133 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_port_in_ports135 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_END_in_ports139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_port173 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_SPACER_in_port176 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_IO_in_port180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_state217 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_INITIALSTATE_in_state220 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_BEGIN_in_state229 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_cell_in_state231 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_SEPARATOR_in_state234 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_cell_in_state236 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_END_in_state240 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_BEGIN_in_state248 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_trans_in_state250 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_SEPARATOR_in_state253 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_trans_in_state255 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_END_in_state259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_cell299 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_SPACER_in_cell302 = new BitSet(new long[]{0x0000000140000300L});
    public static final BitSet FOLLOW_GLOB_in_cell307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_cell311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_cell315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_cell319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ports_in_trans362 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_constraint_in_trans364 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_state_in_trans367 = new BitSet(new long[]{0x0000000000000002L});

}