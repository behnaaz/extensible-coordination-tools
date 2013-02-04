/**
 * This package provides clock extensions for extendible automata.
 * <p>
 * The general idea of automata with time is the following: Transitions are 
 * instantaneous, and time may only elapse when the automaton remains in one of
 * its states. To measure passing of time, the automaton is augmented with a 
 * finite set of clocks. The possibility to fire a transition, as well as the 
 * dwell time in states, is restricted by clock constraints associated to 
 * transitions and states, respectively.   
 * </p>
 * <p>
 * In the editor, there are four extensions available:
 * <ol> 
 * <li>Clock (Automaton)</li> 
 * <li>Clock guard (Transition)</li> 
 * <li>Clock update (Transition) </li>
 * <li>Invariant (State)</li>
 * </ol>
 * </p>
 * <p>
 * <ol>
 * <li>All clocks used in guards, invariants and assignments (updates) must be
 * declared on automaton level.</li>
 * <li>Transitions are associated with clock constraints called <i>guards</i>, 
 * restricting the possibilities to fire the transition. That means, a 
 * transition may only be taken if the current values of the clocks satisfy the
 * guard.<br>
 * Valid guard formulas consist of comparisons of clocks (or clock differences)
 * to naturals, and boolean combinations of these. The syntax of a guard g
 * is described by the following BNF grammar:
 * 
 * <blockquote>
 *   g = x &sim; c &#124; x - y &sim; c &#124; true &#124; false &#124; 
 *       g1 &quot;&amp;&amp;&quot; g2 &#124; g1 &quot;||&quot; g2 &#124; 
 *       &quot;!&quot; g, 
 * </blockquote>
 * for x, y being clocks, c being a natural constant, and 
 * &sim; &isin; {<, &le;, =, &ge;, >};
 * </li>
 * <li>Transitions are associated with clock assignments called <i>updates</i> 
 * (name adopted from <span style="font-variant:small-caps">Uppaal)</span>. An 
 * update sets a clock to a given value. At the moment, for the given model, 
 * it is only possible to reset a clock to zero, but this may change in future 
 * versions.<br>
 * Valid updates consist of a comma separated list of assignments (to zero).
 * </li>
 * <li>States are associated with clock constraints called <i>invariants</i>, 
 * constraining the values of clocks for which the automaton may stay in this 
 * state. The grammar for invariant constraint is essentially the same as for 
 * guards, apart from the fact that invariants have to be convex. Thus, they 
 * may not contain logical or (&quot;||&quot;) and logical negation 
 * (&quot;!&quot;). This is to ensure that invariants hold among subsequent 
 * steps.
 * </li>
 * </ol>
 * </p>
 */
package org.ect.ea.extensions.clocks;