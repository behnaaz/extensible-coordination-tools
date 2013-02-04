// $ANTLR 3.1 /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g 2009-05-28 12:46:19

package org.ect.ea.util.text.parse;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.eclipse.emf.ecore.EReference;
import org.ect.ea.automata.*;
import org.ect.ea.extensions.constraints.*;
import org.ect.ea.util.cacompat.*;
import org.ect.ea.util.cacompat.CA;

import static org.ect.ea.util.cacompat.CA.PortType;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CatParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "OR", "AND", "LPAREN", "RPAREN", "ASSIGN", "EQUAL", "NOT_EQUAL", "GREATER", "GREATER_EQUAL", "LESS", "LESS_EQUAL", "IDENT", "SEPARATOR", "QUOTE", "INT", "SOURCE", "TARGET", "ARBITRARY", "TRUE", "FALSE", "SPACER", "INITIALSTATE", "BEGIN", "END", "GLOB", "TYPE", "WS"
    };
    public static final int GLOB=28;
    public static final int SOURCE=19;
    public static final int INT=18;
    public static final int SEPARATOR=16;
    public static final int INITIALSTATE=25;
    public static final int TARGET=20;
    public static final int AND=5;
    public static final int EOF=-1;
    public static final int TRUE=22;
    public static final int LPAREN=6;
    public static final int TYPE=29;
    public static final int RPAREN=7;
    public static final int QUOTE=17;
    public static final int WS=30;
    public static final int GREATER=11;
    public static final int NOT_EQUAL=10;
    public static final int SPACER=24;
    public static final int BEGIN=26;
    public static final int EQUAL=9;
    public static final int OR=4;
    public static final int ASSIGN=8;
    public static final int LESS=13;
    public static final int IDENT=15;
    public static final int LESS_EQUAL=14;
    public static final int END=27;
    public static final int FALSE=23;
    public static final int ARBITRARY=21;
    public static final int GREATER_EQUAL=12;

    // delegates
    public Cat_Constraint gConstraint;
    // delegators


        public CatParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public CatParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            gConstraint = new Cat_Constraint(input, state, this);         
        }
        

    public String[] getTokenNames() { return CatParser.tokenNames; }
    public String getGrammarFileName() { return "/ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g"; }


    Map<String, CAState> stateNames = new HashMap<String, CAState>();

    private String stripQuotes(String s) {
    	return s.substring(1,s.length()-1);
    }

    public static void main(String[] args) throws Exception {
    	CatLexer lex = new CatLexer(new ANTLRFileStream(args[0]));
    	CatParser parser = new CatParser( new CommonTokenStream(lex));
    	CA ca = parser.automaton();
    	System.out.println(ca);
    }



    // $ANTLR start "module"
    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:75:1: module returns [Module value] : ( automaton )+ ;
    public final Module module() throws RecognitionException {
        Module value = null;

        CA automaton1 = null;


        value = new Module();
        try {
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:77:30: ( ( automaton )+ )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:78:2: ( automaton )+
            {
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:78:2: ( automaton )+
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
            	    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:78:3: automaton
            	    {
            	    pushFollow(FOLLOW_automaton_in_module292);
            	    automaton1=automaton();

            	    state._fsp--;

            	    value.getAutomata().add(automaton1);

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
        return value;
    }
    // $ANTLR end "module"


    // $ANTLR start "automaton"
    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:81:1: automaton returns [CA value] : GLOB ( portlist )? ( statelist[ $value.getStates() ] )? ;
    public final CA automaton() throws RecognitionException {
        CA value = null;

        Token GLOB2=null;
        Map portlist3 = null;


        value = new CA();
        try {
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:83:26: ( GLOB ( portlist )? ( statelist[ $value.getStates() ] )? )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:84:2: GLOB ( portlist )? ( statelist[ $value.getStates() ] )?
            {
            GLOB2=(Token)match(input,GLOB,FOLLOW_GLOB_in_automaton320); 
            value.setName(stripQuotes((GLOB2!=null?GLOB2.getText():null)));
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:85:2: ( portlist )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==BEGIN) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:85:4: portlist
                    {
                    pushFollow(FOLLOW_portlist_in_automaton328);
                    portlist3=portlist();

                    state._fsp--;


                    		for (String p: (Collection<String>)portlist3.keySet())
                    			switch ((PortType) portlist3.get(p)) { 							 
                    			case IN: 
                    				value.getPortNames().getInPorts().add(p);
                    				break;
                    			case OUT: 
                    				value.getPortNames().getOutPorts().add(p);
                    				break;
                    			default://portlist3.get(p)==null 
                    				value.getPortNames().getValues().add(p);
                    			}
                    		
                    	

                    }
                    break;

            }

            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:99:2: ( statelist[ $value.getStates() ] )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==IDENT) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:99:4: statelist[ $value.getStates() ]
                    {
                    pushFollow(FOLLOW_statelist_in_automaton338);
                    statelist(value.getStates());

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "automaton"


    // $ANTLR start "portlist"
    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:102:1: portlist returns [Map value] : BEGIN first= port ( SEPARATOR next= port )* END ;
    public final Map portlist() throws RecognitionException {
        Map value = null;

        CatParser.port_return first = null;

        CatParser.port_return next = null;


        value = new HashMap();
        try {
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:104:33: ( BEGIN first= port ( SEPARATOR next= port )* END )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:105:2: BEGIN first= port ( SEPARATOR next= port )* END
            {
            match(input,BEGIN,FOLLOW_BEGIN_in_portlist366); 
            pushFollow(FOLLOW_port_in_portlist372);
            first=port();

            state._fsp--;

            value.put((first!=null?first.name:null), (first!=null?first.type:null));
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:107:2: ( SEPARATOR next= port )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==SEPARATOR) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:107:3: SEPARATOR next= port
            	    {
            	    match(input,SEPARATOR,FOLLOW_SEPARATOR_in_portlist381); 
            	    pushFollow(FOLLOW_port_in_portlist385);
            	    next=port();

            	    state._fsp--;

            	    value.put((next!=null?next.name:null), (next!=null?next.type:null));

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match(input,END,FOLLOW_END_in_portlist393); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "portlist"

    public static class port_return extends ParserRuleReturnScope {
        public String name;
        public PortType type;
    };

    // $ANTLR start "port"
    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:111:1: port returns [String name, PortType type] : IDENT ( SPACER TYPE )? ;
    public final CatParser.port_return port() throws RecognitionException {
        CatParser.port_return retval = new CatParser.port_return();
        retval.start = input.LT(1);

        Token IDENT4=null;
        Token TYPE5=null;

        try {
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:112:37: ( IDENT ( SPACER TYPE )? )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:113:2: IDENT ( SPACER TYPE )?
            {
            IDENT4=(Token)match(input,IDENT,FOLLOW_IDENT_in_port408); 
            retval.name =(IDENT4!=null?IDENT4.getText():null);
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:114:2: ( SPACER TYPE )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==SPACER) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:114:3: SPACER TYPE
                    {
                    match(input,SPACER,FOLLOW_SPACER_in_port414); 
                    TYPE5=(Token)match(input,TYPE,FOLLOW_TYPE_in_port416); 
                    retval.type =PortType.valueOf((TYPE5!=null?TYPE5.getText():null));

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

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


    // $ANTLR start "statelist"
    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:117:1: statelist[List<State> states] : first= state ( SEPARATOR next= state )* ;
    public final void statelist(List<State> states) throws RecognitionException {
        CAState first = null;

        CAState next = null;


        try {
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:118:21: (first= state ( SEPARATOR next= state )* )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:119:2: first= state ( SEPARATOR next= state )*
            {
            pushFollow(FOLLOW_state_in_statelist439);
            first=state();

            state._fsp--;

            states.add(first);
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:120:2: ( SEPARATOR next= state )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==SEPARATOR) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:120:3: SEPARATOR next= state
            	    {
            	    match(input,SEPARATOR,FOLLOW_SEPARATOR_in_statelist447); 
            	    pushFollow(FOLLOW_state_in_statelist451);
            	    next=state();

            	    state._fsp--;

            	    states.add(next);

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "statelist"


    // $ANTLR start "state"
    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:123:1: state returns [CAState value] : IDENT ( INITIALSTATE )? ( celllist[ $value.getMemoryCells().getInitializations().map() ] )? ( transitionlist[ $value.getOutgoing() ] )? ;
    public final CAState state() throws RecognitionException {
        CAState value = null;

        Token IDENT6=null;

        try {
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:124:24: ( IDENT ( INITIALSTATE )? ( celllist[ $value.getMemoryCells().getInitializations().map() ] )? ( transitionlist[ $value.getOutgoing() ] )? )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:125:2: IDENT ( INITIALSTATE )? ( celllist[ $value.getMemoryCells().getInitializations().map() ] )? ( transitionlist[ $value.getOutgoing() ] )?
            {
            IDENT6=(Token)match(input,IDENT,FOLLOW_IDENT_in_state473); 

            		if (stateNames.containsKey((IDENT6!=null?IDENT6.getText():null)))
            			value = stateNames.get((IDENT6!=null?IDENT6.getText():null));
            		else {
            			value = new CAState();
            			value.setName((IDENT6!=null?IDENT6.getText():null));
            			stateNames.put((IDENT6!=null?IDENT6.getText():null),value);
            		}  
            	
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:134:2: ( INITIALSTATE )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==INITIALSTATE) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:134:3: INITIALSTATE
                    {
                    match(input,INITIALSTATE,FOLLOW_INITIALSTATE_in_state480); 
                    value.setStartState(true);

                    }
                    break;

            }

            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:135:2: ( celllist[ $value.getMemoryCells().getInitializations().map() ] )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==BEGIN) ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==IDENT) ) {
                    alt8=1;
                }
            }
            switch (alt8) {
                case 1 :
                    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:135:3: celllist[ $value.getMemoryCells().getInitializations().map() ]
                    {
                    pushFollow(FOLLOW_celllist_in_state488);
                    celllist(value.getMemoryCells().getInitializations().map());

                    state._fsp--;


                    		value.getMemoryCells().getValues().addAll(
                    			value.getMemoryCells().getInitializations().keySet());
                    	

                    }
                    break;

            }

            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:139:2: ( transitionlist[ $value.getOutgoing() ] )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==BEGIN) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:139:3: transitionlist[ $value.getOutgoing() ]
                    {
                    pushFollow(FOLLOW_transitionlist_in_state498);
                    transitionlist(value.getOutgoing());

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "state"


    // $ANTLR start "celllist"
    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:142:1: celllist[Map value] : BEGIN (first= cell ) ( SEPARATOR next= cell )* END ;
    public final void celllist(Map value) throws RecognitionException {
        CatParser.cell_return first = null;

        CatParser.cell_return next = null;


        try {
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:143:12: ( BEGIN (first= cell ) ( SEPARATOR next= cell )* END )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:144:2: BEGIN (first= cell ) ( SEPARATOR next= cell )* END
            {
            match(input,BEGIN,FOLLOW_BEGIN_in_celllist517); 
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:145:2: (first= cell )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:145:3: first= cell
            {
            pushFollow(FOLLOW_cell_in_celllist524);
            first=cell();

            state._fsp--;

            value.put((first!=null?input.toString(first.start,first.stop):null), (first!=null?first.value:null));

            }

            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:146:2: ( SEPARATOR next= cell )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==SEPARATOR) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:146:3: SEPARATOR next= cell
            	    {
            	    match(input,SEPARATOR,FOLLOW_SEPARATOR_in_celllist533); 
            	    pushFollow(FOLLOW_cell_in_celllist537);
            	    next=cell();

            	    state._fsp--;

            	    value.put((first!=null?input.toString(first.start,first.stop):null), (first!=null?first.value:null));

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            match(input,END,FOLLOW_END_in_celllist545); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "celllist"

    public static class cell_return extends ParserRuleReturnScope {
        public String name;
        public String value;
    };

    // $ANTLR start "cell"
    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:150:1: cell returns [String name, String value] : IDENT ( SPACER (val= GLOB | val= INT | val= TRUE | val= FALSE ) )? ;
    public final CatParser.cell_return cell() throws RecognitionException {
        CatParser.cell_return retval = new CatParser.cell_return();
        retval.start = input.LT(1);

        Token val=null;
        Token IDENT7=null;

        try {
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:151:36: ( IDENT ( SPACER (val= GLOB | val= INT | val= TRUE | val= FALSE ) )? )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:152:2: IDENT ( SPACER (val= GLOB | val= INT | val= TRUE | val= FALSE ) )?
            {
            IDENT7=(Token)match(input,IDENT,FOLLOW_IDENT_in_cell560); 
            retval.name =(IDENT7!=null?IDENT7.getText():null);
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:153:2: ( SPACER (val= GLOB | val= INT | val= TRUE | val= FALSE ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==SPACER) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:153:3: SPACER (val= GLOB | val= INT | val= TRUE | val= FALSE )
                    {
                    match(input,SPACER,FOLLOW_SPACER_in_cell566); 
                    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:153:10: (val= GLOB | val= INT | val= TRUE | val= FALSE )
                    int alt11=4;
                    switch ( input.LA(1) ) {
                    case GLOB:
                        {
                        alt11=1;
                        }
                        break;
                    case INT:
                        {
                        alt11=2;
                        }
                        break;
                    case TRUE:
                        {
                        alt11=3;
                        }
                        break;
                    case FALSE:
                        {
                        alt11=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 0, input);

                        throw nvae;
                    }

                    switch (alt11) {
                        case 1 :
                            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:153:11: val= GLOB
                            {
                            val=(Token)match(input,GLOB,FOLLOW_GLOB_in_cell571); 

                            }
                            break;
                        case 2 :
                            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:153:20: val= INT
                            {
                            val=(Token)match(input,INT,FOLLOW_INT_in_cell575); 

                            }
                            break;
                        case 3 :
                            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:153:28: val= TRUE
                            {
                            val=(Token)match(input,TRUE,FOLLOW_TRUE_in_cell579); 

                            }
                            break;
                        case 4 :
                            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:153:37: val= FALSE
                            {
                            val=(Token)match(input,FALSE,FOLLOW_FALSE_in_cell583); 

                            }
                            break;

                    }

                    retval.value =(val!=null?val.getText():null);

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

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


    // $ANTLR start "transitionlist"
    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:156:1: transitionlist[List<Transition> value] : BEGIN (first= transition ) ( SEPARATOR next= transition )* END ;
    public final void transitionlist(List<Transition> value) throws RecognitionException {
        CATransition first = null;

        CATransition next = null;


        try {
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:157:25: ( BEGIN (first= transition ) ( SEPARATOR next= transition )* END )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:158:2: BEGIN (first= transition ) ( SEPARATOR next= transition )* END
            {
            match(input,BEGIN,FOLLOW_BEGIN_in_transitionlist610); 
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:159:2: (first= transition )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:159:3: first= transition
            {
            pushFollow(FOLLOW_transition_in_transitionlist617);
            first=transition();

            state._fsp--;

            value.add(first);

            }

            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:160:2: ( SEPARATOR next= transition )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==SEPARATOR) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:160:3: SEPARATOR next= transition
            	    {
            	    match(input,SEPARATOR,FOLLOW_SEPARATOR_in_transitionlist626); 
            	    pushFollow(FOLLOW_transition_in_transitionlist630);
            	    next=transition();

            	    state._fsp--;

            	    value.add(next);

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            match(input,END,FOLLOW_END_in_transitionlist639); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "transitionlist"


    // $ANTLR start "transition"
    // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:164:1: transition returns [CATransition value] : portlist SPACER constraint SPACER tgt= state ;
    public final CATransition transition() throws RecognitionException {
        CATransition value = null;

        CAState tgt = null;

        Map portlist8 = null;

        Constraint constraint9 = null;


        value = new CATransition();
        try {
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:166:38: ( portlist SPACER constraint SPACER tgt= state )
            // /ufs/maraikar/workspace/org.ect.ea.util/src/cwi/ea/util/text/parse/Cat.g:167:2: portlist SPACER constraint SPACER tgt= state
            {
            pushFollow(FOLLOW_portlist_in_transition663);
            portlist8=portlist();

            state._fsp--;

            value.getPortNames().getValues().addAll(portlist8.keySet());
            match(input,SPACER,FOLLOW_SPACER_in_transition669); 
            pushFollow(FOLLOW_constraint_in_transition671);
            constraint9=constraint();

            state._fsp--;

             value.setConstraint(constraint9); 
            match(input,SPACER,FOLLOW_SPACER_in_transition677); 
            pushFollow(FOLLOW_state_in_transition681);
            tgt=state();

            state._fsp--;

             value.setTarget(tgt); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "transition"

    // Delegated rules
    public Function function() throws RecognitionException { return gConstraint.function(); }
    public Constraint disjunction() throws RecognitionException { return gConstraint.disjunction(); }
    public Literal literal() throws RecognitionException { return gConstraint.literal(); }
    public Parameter parameter() throws RecognitionException { return gConstraint.parameter(); }
    public Constraint composite() throws RecognitionException { return gConstraint.composite(); }
    public Constraint conjunction() throws RecognitionException { return gConstraint.conjunction(); }
    public Element element() throws RecognitionException { return gConstraint.element(); }
    public Constraint equation() throws RecognitionException { return gConstraint.equation(); }
    public Constraint constraint() throws RecognitionException { return gConstraint.constraint(); }


 

    public static final BitSet FOLLOW_automaton_in_module292 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_GLOB_in_automaton320 = new BitSet(new long[]{0x0000000004008002L});
    public static final BitSet FOLLOW_portlist_in_automaton328 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_statelist_in_automaton338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEGIN_in_portlist366 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_port_in_portlist372 = new BitSet(new long[]{0x0000000008010000L});
    public static final BitSet FOLLOW_SEPARATOR_in_portlist381 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_port_in_portlist385 = new BitSet(new long[]{0x0000000008010000L});
    public static final BitSet FOLLOW_END_in_portlist393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_port408 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_SPACER_in_port414 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_TYPE_in_port416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_state_in_statelist439 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_SEPARATOR_in_statelist447 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_state_in_statelist451 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_IDENT_in_state473 = new BitSet(new long[]{0x0000000006000002L});
    public static final BitSet FOLLOW_INITIALSTATE_in_state480 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_celllist_in_state488 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_transitionlist_in_state498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEGIN_in_celllist517 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_cell_in_celllist524 = new BitSet(new long[]{0x0000000008010000L});
    public static final BitSet FOLLOW_SEPARATOR_in_celllist533 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_cell_in_celllist537 = new BitSet(new long[]{0x0000000008010000L});
    public static final BitSet FOLLOW_END_in_celllist545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_cell560 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_SPACER_in_cell566 = new BitSet(new long[]{0x0000000010C40000L});
    public static final BitSet FOLLOW_GLOB_in_cell571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_cell575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_cell579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_cell583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BEGIN_in_transitionlist610 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_transition_in_transitionlist617 = new BitSet(new long[]{0x0000000008010000L});
    public static final BitSet FOLLOW_SEPARATOR_in_transitionlist626 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_transition_in_transitionlist630 = new BitSet(new long[]{0x0000000008010000L});
    public static final BitSet FOLLOW_END_in_transitionlist639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_portlist_in_transition663 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_SPACER_in_transition669 = new BitSet(new long[]{0x0000000000FE8040L});
    public static final BitSet FOLLOW_constraint_in_transition671 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_SPACER_in_transition677 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_state_in_transition681 = new BitSet(new long[]{0x0000000000000002L});

}