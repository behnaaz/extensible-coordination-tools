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
// $ANTLR 3.2 Sep 23, 2009 12:02:23 Constraint.g 2010-02-05 15:09:27

package org.ect.codegen.ea.catparser;

import java.util.Set;
import java.util.HashSet;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class CAT_Constraint extends Parser {
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
    // delegators
    public CATParser gCAT;
    public CATParser gParent;


        public CAT_Constraint(TokenStream input, CATParser gCAT) {
            this(input, new RecognizerSharedState(), gCAT);
        }
        public CAT_Constraint(TokenStream input, RecognizerSharedState state, CATParser gCAT) {
            super(input, state);
            this.gCAT = gCAT;
             
            gParent = gCAT;
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return CATParser.tokenNames; }
    public String getGrammarFileName() { return "Constraint.g"; }


    protected static class constraint_scope {
        Set<String> inp;
        Set<String> outp;
    }
    protected Stack constraint_stack = new Stack();

    public static class constraint_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "constraint"
    // Constraint.g:8:1: constraint[Set<String> inports, Set<String> outports] : SPACER disjunction SPACER ;
    public final CAT_Constraint.constraint_return constraint(Set<String> inports, Set<String> outports) throws RecognitionException {
        constraint_stack.push(new constraint_scope());
        CAT_Constraint.constraint_return retval = new CAT_Constraint.constraint_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SPACER1=null;
        Token SPACER3=null;
        CAT_Constraint.disjunction_return disjunction2 = null;


        Object SPACER1_tree=null;
        Object SPACER3_tree=null;

        ((constraint_scope)constraint_stack.peek()).inp =inports; ((constraint_scope)constraint_stack.peek()).outp =outports;
        try {
            // Constraint.g:11:4: ( SPACER disjunction SPACER )
            // Constraint.g:11:6: SPACER disjunction SPACER
            {
            root_0 = (Object)adaptor.nil();

            SPACER1=(Token)match(input,SPACER,FOLLOW_SPACER_in_constraint43); 
            pushFollow(FOLLOW_disjunction_in_constraint46);
            disjunction2=disjunction();

            state._fsp--;

            adaptor.addChild(root_0, disjunction2.getTree());
            SPACER3=(Token)match(input,SPACER,FOLLOW_SPACER_in_constraint48); 

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
            constraint_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "constraint"

    public static class disjunction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "disjunction"
    // Constraint.g:14:1: disjunction : conjunction ( ( OR conjunction )+ -> ^( OR ( conjunction )+ ) | -> conjunction ) ;
    public final CAT_Constraint.disjunction_return disjunction() throws RecognitionException {
        CAT_Constraint.disjunction_return retval = new CAT_Constraint.disjunction_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token OR5=null;
        CAT_Constraint.conjunction_return conjunction4 = null;

        CAT_Constraint.conjunction_return conjunction6 = null;


        Object OR5_tree=null;
        RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
        RewriteRuleSubtreeStream stream_conjunction=new RewriteRuleSubtreeStream(adaptor,"rule conjunction");
        try {
            // Constraint.g:14:13: ( conjunction ( ( OR conjunction )+ -> ^( OR ( conjunction )+ ) | -> conjunction ) )
            // Constraint.g:14:15: conjunction ( ( OR conjunction )+ -> ^( OR ( conjunction )+ ) | -> conjunction )
            {
            pushFollow(FOLLOW_conjunction_in_disjunction65);
            conjunction4=conjunction();

            state._fsp--;

            stream_conjunction.add(conjunction4.getTree());
            // Constraint.g:14:27: ( ( OR conjunction )+ -> ^( OR ( conjunction )+ ) | -> conjunction )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==OR) ) {
                alt2=1;
            }
            else if ( (LA2_0==SPACER||LA2_0==RPAREN) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // Constraint.g:15:5: ( OR conjunction )+
                    {
                    // Constraint.g:15:5: ( OR conjunction )+
                    int cnt1=0;
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==OR) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // Constraint.g:15:6: OR conjunction
                    	    {
                    	    OR5=(Token)match(input,OR,FOLLOW_OR_in_disjunction74);  
                    	    stream_OR.add(OR5);

                    	    pushFollow(FOLLOW_conjunction_in_disjunction76);
                    	    conjunction6=conjunction();

                    	    state._fsp--;

                    	    stream_conjunction.add(conjunction6.getTree());

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



                    // AST REWRITE
                    // elements: OR, conjunction
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 15:23: -> ^( OR ( conjunction )+ )
                    {
                        // Constraint.g:15:26: ^( OR ( conjunction )+ )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_OR.nextNode(), root_1);

                        if ( !(stream_conjunction.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_conjunction.hasNext() ) {
                            adaptor.addChild(root_1, stream_conjunction.nextTree());

                        }
                        stream_conjunction.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // Constraint.g:16:11: 
                    {

                    // AST REWRITE
                    // elements: conjunction
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 16:11: -> conjunction
                    {
                        adaptor.addChild(root_0, stream_conjunction.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;

            }


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
    // $ANTLR end "disjunction"

    public static class conjunction_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "conjunction"
    // Constraint.g:19:1: conjunction : composite ( ( AND composite )+ -> ^( AND ( composite )+ ) | -> composite ) ;
    public final CAT_Constraint.conjunction_return conjunction() throws RecognitionException {
        CAT_Constraint.conjunction_return retval = new CAT_Constraint.conjunction_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AND8=null;
        CAT_Constraint.composite_return composite7 = null;

        CAT_Constraint.composite_return composite9 = null;


        Object AND8_tree=null;
        RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
        RewriteRuleSubtreeStream stream_composite=new RewriteRuleSubtreeStream(adaptor,"rule composite");
        try {
            // Constraint.g:19:13: ( composite ( ( AND composite )+ -> ^( AND ( composite )+ ) | -> composite ) )
            // Constraint.g:19:15: composite ( ( AND composite )+ -> ^( AND ( composite )+ ) | -> composite )
            {
            pushFollow(FOLLOW_composite_in_conjunction114);
            composite7=composite();

            state._fsp--;

            stream_composite.add(composite7.getTree());
            // Constraint.g:19:25: ( ( AND composite )+ -> ^( AND ( composite )+ ) | -> composite )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==AND) ) {
                alt4=1;
            }
            else if ( (LA4_0==SPACER||LA4_0==OR||LA4_0==RPAREN) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // Constraint.g:20:5: ( AND composite )+
                    {
                    // Constraint.g:20:5: ( AND composite )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==AND) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // Constraint.g:20:6: AND composite
                    	    {
                    	    AND8=(Token)match(input,AND,FOLLOW_AND_in_conjunction123);  
                    	    stream_AND.add(AND8);

                    	    pushFollow(FOLLOW_composite_in_conjunction125);
                    	    composite9=composite();

                    	    state._fsp--;

                    	    stream_composite.add(composite9.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt3 >= 1 ) break loop3;
                                EarlyExitException eee =
                                    new EarlyExitException(3, input);
                                throw eee;
                        }
                        cnt3++;
                    } while (true);



                    // AST REWRITE
                    // elements: composite, AND
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 20:22: -> ^( AND ( composite )+ )
                    {
                        // Constraint.g:20:25: ^( AND ( composite )+ )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot(stream_AND.nextNode(), root_1);

                        if ( !(stream_composite.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_composite.hasNext() ) {
                            adaptor.addChild(root_1, stream_composite.nextTree());

                        }
                        stream_composite.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // Constraint.g:21:11: 
                    {

                    // AST REWRITE
                    // elements: composite
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 21:11: -> composite
                    {
                        adaptor.addChild(root_0, stream_composite.nextTree());

                    }

                    retval.tree = root_0;
                    }
                    break;

            }


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
    // $ANTLR end "conjunction"

    public static class composite_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "composite"
    // Constraint.g:24:1: composite : ( LPAREN disjunction RPAREN | literal | eq_pred );
    public final CAT_Constraint.composite_return composite() throws RecognitionException {
        CAT_Constraint.composite_return retval = new CAT_Constraint.composite_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LPAREN10=null;
        Token RPAREN12=null;
        CAT_Constraint.disjunction_return disjunction11 = null;

        CAT_Constraint.literal_return literal13 = null;

        CAT_Constraint.eq_pred_return eq_pred14 = null;


        Object LPAREN10_tree=null;
        Object RPAREN12_tree=null;

        try {
            // Constraint.g:24:11: ( LPAREN disjunction RPAREN | literal | eq_pred )
            int alt5=3;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                alt5=1;
                }
                break;
            case TRUE:
                {
                int LA5_2 = input.LA(2);

                if ( (LA5_2==ASSIGN||LA5_2==RELOP) ) {
                    alt5=3;
                }
                else if ( (LA5_2==SPACER||(LA5_2>=AND && LA5_2<=OR)||LA5_2==RPAREN) ) {
                    alt5=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 2, input);

                    throw nvae;
                }
                }
                break;
            case FALSE:
                {
                int LA5_3 = input.LA(2);

                if ( (LA5_3==ASSIGN||LA5_3==RELOP) ) {
                    alt5=3;
                }
                else if ( (LA5_3==SPACER||(LA5_3>=AND && LA5_3<=OR)||LA5_3==RPAREN) ) {
                    alt5=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 3, input);

                    throw nvae;
                }
                }
                break;
            case SOURCE:
            case TARGET:
            case INT:
            case IDENT:
            case GLOB:
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // Constraint.g:24:13: LPAREN disjunction RPAREN
                    {
                    root_0 = (Object)adaptor.nil();

                    LPAREN10=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_composite166); 
                    pushFollow(FOLLOW_disjunction_in_composite169);
                    disjunction11=disjunction();

                    state._fsp--;

                    adaptor.addChild(root_0, disjunction11.getTree());
                    RPAREN12=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_composite171); 

                    }
                    break;
                case 2 :
                    // Constraint.g:25:6: literal
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_literal_in_composite180);
                    literal13=literal();

                    state._fsp--;

                    adaptor.addChild(root_0, literal13.getTree());

                    }
                    break;
                case 3 :
                    // Constraint.g:26:6: eq_pred
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_eq_pred_in_composite187);
                    eq_pred14=eq_pred();

                    state._fsp--;

                    adaptor.addChild(root_0, eq_pred14.getTree());

                    }
                    break;

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
    // $ANTLR end "composite"

    public static class eq_pred_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "eq_pred"
    // Constraint.g:29:1: eq_pred : ( output ASSIGN value | value ( ( ASSIGN output ) | ( RELOP value ) ) );
    public final CAT_Constraint.eq_pred_return eq_pred() throws RecognitionException {
        CAT_Constraint.eq_pred_return retval = new CAT_Constraint.eq_pred_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ASSIGN16=null;
        Token ASSIGN19=null;
        Token RELOP21=null;
        CAT_Constraint.output_return output15 = null;

        CAT_Constraint.value_return value17 = null;

        CAT_Constraint.value_return value18 = null;

        CAT_Constraint.output_return output20 = null;

        CAT_Constraint.value_return value22 = null;


        Object ASSIGN16_tree=null;
        Object ASSIGN19_tree=null;
        Object RELOP21_tree=null;

        try {
            // Constraint.g:29:10: ( output ASSIGN value | value ( ( ASSIGN output ) | ( RELOP value ) ) )
            int alt7=2;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==LPAREN||LA7_1==RELOP) ) {
                    alt7=2;
                }
                else if ( (LA7_1==ASSIGN) ) {
                    switch ( input.LA(3) ) {
                    case IDENT:
                        {
                        int LA7_5 = input.LA(4);

                        if ( ((((constraint_scope)constraint_stack.peek()).outp.contains(input.LT(1).getText()))) ) {
                            alt7=1;
                        }
                        else if ( ((((constraint_scope)constraint_stack.peek()).inp.contains(input.LT(1).getText()))) ) {
                            alt7=2;
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 7, 5, input);

                            throw nvae;
                        }
                        }
                        break;
                    case FALSE:
                    case TRUE:
                    case SOURCE:
                    case INT:
                    case GLOB:
                        {
                        alt7=1;
                        }
                        break;
                    case TARGET:
                        {
                        alt7=2;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 4, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;
                }
                }
                break;
            case TARGET:
                {
                alt7=1;
                }
                break;
            case FALSE:
            case TRUE:
            case SOURCE:
            case INT:
            case GLOB:
                {
                alt7=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // Constraint.g:29:12: output ASSIGN value
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_output_in_eq_pred200);
                    output15=output();

                    state._fsp--;

                    adaptor.addChild(root_0, output15.getTree());
                    ASSIGN16=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_eq_pred202); 
                    ASSIGN16_tree = (Object)adaptor.create(ASSIGN16);
                    root_0 = (Object)adaptor.becomeRoot(ASSIGN16_tree, root_0);

                    pushFollow(FOLLOW_value_in_eq_pred205);
                    value17=value();

                    state._fsp--;

                    adaptor.addChild(root_0, value17.getTree());

                    }
                    break;
                case 2 :
                    // Constraint.g:30:6: value ( ( ASSIGN output ) | ( RELOP value ) )
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_value_in_eq_pred212);
                    value18=value();

                    state._fsp--;

                    adaptor.addChild(root_0, value18.getTree());
                    // Constraint.g:30:12: ( ( ASSIGN output ) | ( RELOP value ) )
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==ASSIGN) ) {
                        alt6=1;
                    }
                    else if ( (LA6_0==RELOP) ) {
                        alt6=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 0, input);

                        throw nvae;
                    }
                    switch (alt6) {
                        case 1 :
                            // Constraint.g:30:13: ( ASSIGN output )
                            {
                            // Constraint.g:30:13: ( ASSIGN output )
                            // Constraint.g:30:14: ASSIGN output
                            {
                            ASSIGN19=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_eq_pred216); 
                            ASSIGN19_tree = (Object)adaptor.create(ASSIGN19);
                            root_0 = (Object)adaptor.becomeRoot(ASSIGN19_tree, root_0);

                            pushFollow(FOLLOW_output_in_eq_pred219);
                            output20=output();

                            state._fsp--;

                            adaptor.addChild(root_0, output20.getTree());

                            }


                            }
                            break;
                        case 2 :
                            // Constraint.g:30:31: ( RELOP value )
                            {
                            // Constraint.g:30:31: ( RELOP value )
                            // Constraint.g:30:32: RELOP value
                            {
                            RELOP21=(Token)match(input,RELOP,FOLLOW_RELOP_in_eq_pred224); 
                            RELOP21_tree = (Object)adaptor.create(RELOP21);
                            root_0 = (Object)adaptor.becomeRoot(RELOP21_tree, root_0);

                            pushFollow(FOLLOW_value_in_eq_pred227);
                            value22=value();

                            state._fsp--;

                            adaptor.addChild(root_0, value22.getTree());

                            }


                            }
                            break;

                    }


                    }
                    break;

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
    // $ANTLR end "eq_pred"

    public static class value_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "value"
    // Constraint.g:33:1: value : ( function -> ^( FUNCTION function ) | arg );
    public final CAT_Constraint.value_return value() throws RecognitionException {
        CAT_Constraint.value_return retval = new CAT_Constraint.value_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CAT_Constraint.function_return function23 = null;

        CAT_Constraint.arg_return arg24 = null;


        RewriteRuleSubtreeStream stream_function=new RewriteRuleSubtreeStream(adaptor,"rule function");
        try {
            // Constraint.g:33:8: ( function -> ^( FUNCTION function ) | arg )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==IDENT) ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==LPAREN) ) {
                    alt8=1;
                }
                else if ( (LA8_1==SPACER||(LA8_1>=AND && LA8_1<=OR)||(LA8_1>=RPAREN && LA8_1<=ASSIGN)||LA8_1==RELOP) ) {
                    alt8=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }
            }
            else if ( ((LA8_0>=FALSE && LA8_0<=TRUE)||LA8_0==SOURCE||LA8_0==INT||LA8_0==GLOB) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // Constraint.g:33:10: function
                    {
                    pushFollow(FOLLOW_function_in_value242);
                    function23=function();

                    state._fsp--;

                    stream_function.add(function23.getTree());


                    // AST REWRITE
                    // elements: function
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 33:21: -> ^( FUNCTION function )
                    {
                        // Constraint.g:33:24: ^( FUNCTION function )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(FUNCTION, "FUNCTION"), root_1);

                        adaptor.addChild(root_1, stream_function.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // Constraint.g:34:6: arg
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_arg_in_value259);
                    arg24=arg();

                    state._fsp--;

                    adaptor.addChild(root_0, arg24.getTree());

                    }
                    break;

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
    // $ANTLR end "value"

    public static class function_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "function"
    // Constraint.g:37:1: function : IDENT LPAREN ( arg -> arg IDENT | function -> function IDENT ) RPAREN ;
    public final CAT_Constraint.function_return function() throws RecognitionException {
        CAT_Constraint.function_return retval = new CAT_Constraint.function_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT25=null;
        Token LPAREN26=null;
        Token RPAREN29=null;
        CAT_Constraint.arg_return arg27 = null;

        CAT_Constraint.function_return function28 = null;


        Object IDENT25_tree=null;
        Object LPAREN26_tree=null;
        Object RPAREN29_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_arg=new RewriteRuleSubtreeStream(adaptor,"rule arg");
        RewriteRuleSubtreeStream stream_function=new RewriteRuleSubtreeStream(adaptor,"rule function");
        try {
            // Constraint.g:37:10: ( IDENT LPAREN ( arg -> arg IDENT | function -> function IDENT ) RPAREN )
            // Constraint.g:37:12: IDENT LPAREN ( arg -> arg IDENT | function -> function IDENT ) RPAREN
            {
            IDENT25=(Token)match(input,IDENT,FOLLOW_IDENT_in_function277);  
            stream_IDENT.add(IDENT25);

            LPAREN26=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_function279);  
            stream_LPAREN.add(LPAREN26);

            // Constraint.g:37:25: ( arg -> arg IDENT | function -> function IDENT )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==IDENT) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==LPAREN) ) {
                    alt9=2;
                }
                else if ( (LA9_1==RPAREN) ) {
                    alt9=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;
                }
            }
            else if ( ((LA9_0>=FALSE && LA9_0<=TRUE)||LA9_0==SOURCE||LA9_0==INT||LA9_0==GLOB) ) {
                alt9=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // Constraint.g:38:6: arg
                    {
                    pushFollow(FOLLOW_arg_in_function288);
                    arg27=arg();

                    state._fsp--;

                    stream_arg.add(arg27.getTree());


                    // AST REWRITE
                    // elements: arg, IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 38:13: -> arg IDENT
                    {
                        adaptor.addChild(root_0, stream_arg.nextTree());
                        adaptor.addChild(root_0, stream_IDENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // Constraint.g:39:7: function
                    {
                    pushFollow(FOLLOW_function_in_function306);
                    function28=function();

                    state._fsp--;

                    stream_function.add(function28.getTree());


                    // AST REWRITE
                    // elements: function, IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 39:17: -> function IDENT
                    {
                        adaptor.addChild(root_0, stream_function.nextTree());
                        adaptor.addChild(root_0, stream_IDENT.nextNode());

                    }

                    retval.tree = root_0;
                    }
                    break;

            }

            RPAREN29=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_function321);  
            stream_RPAREN.add(RPAREN29);


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
    // $ANTLR end "function"

    public static class arg_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "arg"
    // Constraint.g:43:1: arg : ( input | constant | literal );
    public final CAT_Constraint.arg_return arg() throws RecognitionException {
        CAT_Constraint.arg_return retval = new CAT_Constraint.arg_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        CAT_Constraint.input_return input30 = null;

        CAT_Constraint.constant_return constant31 = null;

        CAT_Constraint.literal_return literal32 = null;



        try {
            // Constraint.g:43:7: ( input | constant | literal )
            int alt10=3;
            switch ( input.LA(1) ) {
            case SOURCE:
            case IDENT:
                {
                alt10=1;
                }
                break;
            case INT:
            case GLOB:
                {
                alt10=2;
                }
                break;
            case FALSE:
            case TRUE:
                {
                alt10=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // Constraint.g:43:9: input
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_input_in_arg335);
                    input30=input();

                    state._fsp--;

                    adaptor.addChild(root_0, input30.getTree());

                    }
                    break;
                case 2 :
                    // Constraint.g:44:6: constant
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_constant_in_arg342);
                    constant31=constant();

                    state._fsp--;

                    adaptor.addChild(root_0, constant31.getTree());

                    }
                    break;
                case 3 :
                    // Constraint.g:45:6: literal
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_literal_in_arg350);
                    literal32=literal();

                    state._fsp--;

                    adaptor.addChild(root_0, literal32.getTree());

                    }
                    break;

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
    // $ANTLR end "arg"

    public static class input_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "input"
    // Constraint.g:48:1: input : ({...}? IDENT -> ^( PORT IDENT ) | SOURCE IDENT -> ^( CELL IDENT ) );
    public final CAT_Constraint.input_return input() throws RecognitionException {
        CAT_Constraint.input_return retval = new CAT_Constraint.input_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT33=null;
        Token SOURCE34=null;
        Token IDENT35=null;

        Object IDENT33_tree=null;
        Object SOURCE34_tree=null;
        Object IDENT35_tree=null;
        RewriteRuleTokenStream stream_SOURCE=new RewriteRuleTokenStream(adaptor,"token SOURCE");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");

        try {
            // Constraint.g:48:8: ({...}? IDENT -> ^( PORT IDENT ) | SOURCE IDENT -> ^( CELL IDENT ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==IDENT) ) {
                alt11=1;
            }
            else if ( (LA11_0==SOURCE) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // Constraint.g:48:11: {...}? IDENT
                    {
                    if ( !((((constraint_scope)constraint_stack.peek()).inp.contains(input.LT(1).getText()))) ) {
                        throw new FailedPredicateException(input, "input", "$constraint::inp.contains(input.LT(1).getText())");
                    }
                    IDENT33=(Token)match(input,IDENT,FOLLOW_IDENT_in_input370);  
                    stream_IDENT.add(IDENT33);



                    // AST REWRITE
                    // elements: IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 49:10: -> ^( PORT IDENT )
                    {
                        // Constraint.g:49:13: ^( PORT IDENT )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PORT, "PORT"), root_1);

                        adaptor.addChild(root_1, stream_IDENT.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // Constraint.g:50:8: SOURCE IDENT
                    {
                    SOURCE34=(Token)match(input,SOURCE,FOLLOW_SOURCE_in_input400);  
                    stream_SOURCE.add(SOURCE34);

                    IDENT35=(Token)match(input,IDENT,FOLLOW_IDENT_in_input402);  
                    stream_IDENT.add(IDENT35);



                    // AST REWRITE
                    // elements: IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 50:22: -> ^( CELL IDENT )
                    {
                        // Constraint.g:50:25: ^( CELL IDENT )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CELL, "CELL"), root_1);

                        adaptor.addChild(root_1, stream_IDENT.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

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
    // $ANTLR end "input"

    public static class output_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "output"
    // Constraint.g:53:1: output : ({...}? IDENT -> ^( PORT IDENT ) | TARGET IDENT -> ^( CELL IDENT ) );
    public final CAT_Constraint.output_return output() throws RecognitionException {
        CAT_Constraint.output_return retval = new CAT_Constraint.output_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IDENT36=null;
        Token TARGET37=null;
        Token IDENT38=null;

        Object IDENT36_tree=null;
        Object TARGET37_tree=null;
        Object IDENT38_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_TARGET=new RewriteRuleTokenStream(adaptor,"token TARGET");

        try {
            // Constraint.g:53:9: ({...}? IDENT -> ^( PORT IDENT ) | TARGET IDENT -> ^( CELL IDENT ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==IDENT) ) {
                alt12=1;
            }
            else if ( (LA12_0==TARGET) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // Constraint.g:53:12: {...}? IDENT
                    {
                    if ( !((((constraint_scope)constraint_stack.peek()).outp.contains(input.LT(1).getText()))) ) {
                        throw new FailedPredicateException(input, "output", "$constraint::outp.contains(input.LT(1).getText())");
                    }
                    IDENT36=(Token)match(input,IDENT,FOLLOW_IDENT_in_output433);  
                    stream_IDENT.add(IDENT36);



                    // AST REWRITE
                    // elements: IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 54:10: -> ^( PORT IDENT )
                    {
                        // Constraint.g:54:13: ^( PORT IDENT )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PORT, "PORT"), root_1);

                        adaptor.addChild(root_1, stream_IDENT.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // Constraint.g:55:8: TARGET IDENT
                    {
                    TARGET37=(Token)match(input,TARGET,FOLLOW_TARGET_in_output459);  
                    stream_TARGET.add(TARGET37);

                    IDENT38=(Token)match(input,IDENT,FOLLOW_IDENT_in_output461);  
                    stream_IDENT.add(IDENT38);



                    // AST REWRITE
                    // elements: IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 55:22: -> ^( CELL IDENT )
                    {
                        // Constraint.g:55:25: ^( CELL IDENT )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CELL, "CELL"), root_1);

                        adaptor.addChild(root_1, stream_IDENT.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

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
    // $ANTLR end "output"

    public static class constant_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "constant"
    // Constraint.g:58:1: constant : ( INT -> ^( CONSTANT INT ) | GLOB -> ^( CONSTANT GLOB ) );
    public final CAT_Constraint.constant_return constant() throws RecognitionException {
        CAT_Constraint.constant_return retval = new CAT_Constraint.constant_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token INT39=null;
        Token GLOB40=null;

        Object INT39_tree=null;
        Object GLOB40_tree=null;
        RewriteRuleTokenStream stream_GLOB=new RewriteRuleTokenStream(adaptor,"token GLOB");
        RewriteRuleTokenStream stream_INT=new RewriteRuleTokenStream(adaptor,"token INT");

        try {
            // Constraint.g:58:10: ( INT -> ^( CONSTANT INT ) | GLOB -> ^( CONSTANT GLOB ) )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==INT) ) {
                alt13=1;
            }
            else if ( (LA13_0==GLOB) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // Constraint.g:58:14: INT
                    {
                    INT39=(Token)match(input,INT,FOLLOW_INT_in_constant487);  
                    stream_INT.add(INT39);



                    // AST REWRITE
                    // elements: INT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 58:22: -> ^( CONSTANT INT )
                    {
                        // Constraint.g:58:25: ^( CONSTANT INT )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CONSTANT, "CONSTANT"), root_1);

                        adaptor.addChild(root_1, stream_INT.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // Constraint.g:59:8: GLOB
                    {
                    GLOB40=(Token)match(input,GLOB,FOLLOW_GLOB_in_constant508);  
                    stream_GLOB.add(GLOB40);



                    // AST REWRITE
                    // elements: GLOB
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 59:16: -> ^( CONSTANT GLOB )
                    {
                        // Constraint.g:59:19: ^( CONSTANT GLOB )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CONSTANT, "CONSTANT"), root_1);

                        adaptor.addChild(root_1, stream_GLOB.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

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
    // $ANTLR end "constant"

    public static class literal_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "literal"
    // Constraint.g:62:1: literal : ( TRUE -> ^( CONSTANT TRUE ) | FALSE -> ^( CONSTANT FALSE ) );
    public final CAT_Constraint.literal_return literal() throws RecognitionException {
        CAT_Constraint.literal_return retval = new CAT_Constraint.literal_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token TRUE41=null;
        Token FALSE42=null;

        Object TRUE41_tree=null;
        Object FALSE42_tree=null;
        RewriteRuleTokenStream stream_FALSE=new RewriteRuleTokenStream(adaptor,"token FALSE");
        RewriteRuleTokenStream stream_TRUE=new RewriteRuleTokenStream(adaptor,"token TRUE");

        try {
            // Constraint.g:62:10: ( TRUE -> ^( CONSTANT TRUE ) | FALSE -> ^( CONSTANT FALSE ) )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==TRUE) ) {
                alt14=1;
            }
            else if ( (LA14_0==FALSE) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // Constraint.g:62:12: TRUE
                    {
                    TRUE41=(Token)match(input,TRUE,FOLLOW_TRUE_in_literal532);  
                    stream_TRUE.add(TRUE41);



                    // AST REWRITE
                    // elements: TRUE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 62:21: -> ^( CONSTANT TRUE )
                    {
                        // Constraint.g:62:24: ^( CONSTANT TRUE )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CONSTANT, "CONSTANT"), root_1);

                        adaptor.addChild(root_1, stream_TRUE.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // Constraint.g:63:8: FALSE
                    {
                    FALSE42=(Token)match(input,FALSE,FOLLOW_FALSE_in_literal553);  
                    stream_FALSE.add(FALSE42);



                    // AST REWRITE
                    // elements: FALSE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 63:18: -> ^( CONSTANT FALSE )
                    {
                        // Constraint.g:63:21: ^( CONSTANT FALSE )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(CONSTANT, "CONSTANT"), root_1);

                        adaptor.addChild(root_1, stream_FALSE.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

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
    // $ANTLR end "literal"

    // Delegated rules


 

    public static final BitSet FOLLOW_SPACER_in_constraint43 = new BitSet(new long[]{0x00000001C0061300L});
    public static final BitSet FOLLOW_disjunction_in_constraint46 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_SPACER_in_constraint48 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conjunction_in_disjunction65 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_OR_in_disjunction74 = new BitSet(new long[]{0x00000001C0061300L});
    public static final BitSet FOLLOW_conjunction_in_disjunction76 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_composite_in_conjunction114 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_AND_in_conjunction123 = new BitSet(new long[]{0x00000001C0061300L});
    public static final BitSet FOLLOW_composite_in_conjunction125 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_LPAREN_in_composite166 = new BitSet(new long[]{0x00000001C0061300L});
    public static final BitSet FOLLOW_disjunction_in_composite169 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_composite171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_composite180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_eq_pred_in_composite187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_output_in_eq_pred200 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_ASSIGN_in_eq_pred202 = new BitSet(new long[]{0x00000001C0061300L});
    public static final BitSet FOLLOW_value_in_eq_pred205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_eq_pred212 = new BitSet(new long[]{0x0000000010004000L});
    public static final BitSet FOLLOW_ASSIGN_in_eq_pred216 = new BitSet(new long[]{0x0000000080040000L});
    public static final BitSet FOLLOW_output_in_eq_pred219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RELOP_in_eq_pred224 = new BitSet(new long[]{0x00000001C0061300L});
    public static final BitSet FOLLOW_value_in_eq_pred227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_value242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arg_in_value259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_function277 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_LPAREN_in_function279 = new BitSet(new long[]{0x00000001C0061300L});
    public static final BitSet FOLLOW_arg_in_function288 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_function_in_function306 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_function321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_input_in_arg335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constant_in_arg342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_arg350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_input370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SOURCE_in_input400 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_IDENT_in_input402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_output433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TARGET_in_output459 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_IDENT_in_output461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_constant487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GLOB_in_constant508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literal532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literal553 = new BitSet(new long[]{0x0000000000000002L});

}