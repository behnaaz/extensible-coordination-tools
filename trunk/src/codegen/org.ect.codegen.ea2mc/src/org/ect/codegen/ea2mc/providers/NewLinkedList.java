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
package org.ect.codegen.ea2mc.providers;

import java.util.List;
import java.util.Stack;
import java.util.Vector;

import org.ect.ea.extensions.portnames.DelayElement;

public class NewLinkedList{
	
	DElmNode<DelayElement> listNode; // A standard node
    DElmNode<DelayElement> head = null; // The head of the list
    DElmNode<DelayElement> tail = null; // The tail of the list
    DElmNode<DelayElement> left = null; // The left of current
    DElmNode<DelayElement> right = null; // The right of current
    DElmNode<DelayElement> current = null; // Last modified node of the list
    static int index=0;
    
    public boolean isEmpty(){
    	return head == tail;
    }
    
    public DElmNode<DelayElement> getCurrent(){
    	return current;
    }
    
    public void setCurrent(DElmNode<DelayElement> node){
    	current = node;
    }
    
    public DElmNode<DelayElement> getHead(){
    	return head;
    }
    
    public void setHead(DElmNode<DelayElement> node){
    	head = node;
    }
    
    private void addDatatoTail(DelayElement a){
    	if(tail == null){
    		tail = new DElmNode<DelayElement>();
    		tail.next = null;
    		tail.prev = null;
    		tail.left = null;
    		tail.right = null;
    		
    		if(head == null){
    			head = new DElmNode<DelayElement>();
    			head.next = tail;
    			head.prev = null;
    			head.left = null;
    			head.right = null;
    		}
    	}
    	else{
    		DElmNode<DelayElement> temp = new DElmNode<DelayElement>(); 
    	    temp.prev = tail;
    	    temp.next = null;
    	    temp.left = null;
    	    temp.right = null;
    	    tail.next = temp;
    	    tail = temp;
    	}
    	
    	tail.prev = head;
    	tail.data = a; 
    	current = tail;
    }
    
    public void sequentialInsertion(DelayElement f){
    	if(this.isEmpty())	addDatatoTail(f);
    	else if(this.current.next==null){
    		DElmNode<DelayElement> temp = new DElmNode<DelayElement>();
    		temp.prev = current;
    		temp.next = null;
    		temp.left = null;
    		temp.right = null;
    		temp.data = f;
    		current.next = temp;
    		current = temp;
    		tail = temp;
    	}
    }
            
    public void parallelInsertion(DelayElement b){
    	if(!this.isEmpty() && current!=null){
    		DElmNode<DelayElement> temp = new DElmNode<DelayElement>();
    		temp.prev = current.prev;
    		temp.next = current.next;
    		temp.left = current.getLastright();
    		temp.left.right = temp;
    		temp.right = null;
    		temp.data = b;
    	}
    	
    }

    public void removeTail(){
    	if(tail != null){
    		if (tail == current) current = tail.prev;
    		tail = null;
        }
    }
    
    public void removeHead(){
    	if(head != null){
    		if (head == current) current = head.next;
    		else head = head.next;
        }
    }

    public DelayElement getCurrentData(){
    	return current.getData();
    }
    
    /**
     * returns all elements having the same depth as e's 
     */
    public List<DelayElement> getParallel(DelayElement e){
    	DElmNode<DelayElement> pointer = this.head.next;
    	List<DelayElement> list = new Vector<DelayElement>();
    	
    	while(pointer!=null){
    		if(contains(pointer,e)){
    			while(pointer!=null){
    				if(!pointer.data.isEqual(e))	list.add(pointer.data);
    				pointer = pointer.right;
    			}
    		}
    	}
    	    	
    	return list;
    }
    
    /**
     * returns a node having "pivot" as its element
     */
    public DElmNode<DelayElement> getNodeof(List<DelayElement> pivot){
    	DElmNode<DelayElement> position = new DElmNode<DelayElement>();
    	DElmNode<DelayElement> tempHead = this.head.next;
    	boolean found = false;
    	Stack<DElmNode<DelayElement>> store = new Stack<DElmNode<DelayElement>>();
    	store.push(tempHead);
    	while(!store.isEmpty()){
    		DElmNode<DelayElement> temp = store.pop();
    		if(temp!=null){
	    		if(temp.getData()==null && temp.right!=null){
	    			store.push(temp.next);
	    			store.push(temp.right);
	    		}
	    		else if(temp.getData()==null && temp.right==null){
	    			store.push(temp.next);
	    		}
	    		else if(temp.getData()!=null){
	    			for(int i=0;i<pivot.size();i++){
	    				if(contains(temp, pivot.get(i))){
	    					position = temp;
	    					found = true;
	    					break;
	    				}
	    			}
	    			if(!found) store.push(temp.next);
	    		}
    		}
    		if(found)	break;
    	}
    	
    	return position;
    }
    
    /**
     * returns a sublist of this, which starts with "to" 
     */
    public NewLinkedList subSeq1(List<DelayElement> to){
    	NewLinkedList sub = new NewLinkedList();
    	DElmNode<DelayElement> Tail = new DElmNode<DelayElement>();
    	//Find an end point of a sublist
    	Tail = getNodeof(to);
    	
    	DElmNode<DelayElement> tempHead = this.head.next;
    	boolean added = false;
    	Stack<DElmNode<DelayElement>> trace = new Stack<DElmNode<DelayElement>>();
    	trace.push(tempHead);
    	while(!trace.isEmpty() && !added){
    		DElmNode<DelayElement> temp = trace.pop();
    		if(temp.getData()==null && temp.right!=null){
    			if(containsInSubList(temp.next, to)){
    				while(!contains(temp,Tail.getData())){
	    				if(temp.left!=null) sub.parallelInsertion(temp.getData());	
	    				else	sub.sequentialInsertion(temp.getData());
	    				DElmNode<DelayElement> horizontal = temp.right;
	    				while(horizontal!=null)
	    				{
	    					sub.parallelInsertion(horizontal.getData());
	    					horizontal = horizontal.right;
	    				}
	    				temp = temp.next;
    				}
    				added = true;
    			}
    			else{
    				if(temp.left!=null)	sub.parallelInsertion(temp.getData());
    				else	sub.sequentialInsertion(temp.getData());
    				trace.push(temp.right);
    			}
    		}
    		else if(temp.getData()==null && temp.right==null){
    			if(containsInSubList(temp.next, to)){
    				while(!contains(temp,Tail.getData())){
    					if(temp.left!=null && temp.left.getData()==null){
    						sub.parallelInsertion(temp.getData());
    					}
    					else	sub.sequentialInsertion(temp.getData());
	    				DElmNode<DelayElement> horizontal = temp.right;
	    				while(horizontal!=null)
	    				{
	    					sub.parallelInsertion(horizontal.getData());
	    					horizontal = horizontal.right;
	    				}
	    				temp = temp.next;
    				}
    				added = true;
    			}
    			else{
    				sub.sequentialInsertion(temp.getData());
    			}
    		}
    		else if(temp.getData()!=null){
    			if(containsInSubList(temp, to)){
	    			while(!contains(temp,Tail.getData())){
	    				sub.sequentialInsertion(temp.getData());
	    				DElmNode<DelayElement> horizontal = temp.right;
	    				while(horizontal!=null){
	    					sub.parallelInsertion(horizontal.getData());
	    					horizontal = horizontal.right;
	    				}
	    				temp = temp.next;
	    			}
	    			added = true;
    			}
    		}
    	}
    	
	    return sub; 
    }
    
    public boolean containsInSubList(DElmNode<DelayElement> sub, List<DelayElement> items){
    	boolean found = false;
    	Stack<DElmNode<DelayElement>> store = new Stack<DElmNode<DelayElement>>();
    	store.push(sub);
    	while(!store.isEmpty() && !found){
    		DElmNode<DelayElement> temp = store.pop();
    		if(temp!=null){
	    		if(temp.getData()==null && temp.right!=null){
	    			store.push(temp.next);
	    			store.push(temp.right);
	    		}
	    		else if(temp.getData()==null && temp.right==null){
	    			store.push(temp.next);
	    		}
	    		else if(temp.getData()!=null){
	    			for(int i=0;i<items.size();i++){
	    				if(contains(temp, items.get(i)))	found = true;
	    			}
	    			if(!found)	store.push(temp.next);
	    		}
    		}
    	}
    	
    	return found;
    }
    
    
    /**
     * returns a sublist of this, which starts from "from" and ends in "to" 
     */
    public NewLinkedList subSeq2(List<DelayElement> from, List<DelayElement> to){
    	NewLinkedList sub = new NewLinkedList();
    	DElmNode<DelayElement> Head = this.head;
    	DElmNode<DelayElement> Tail = new DElmNode<DelayElement>();
    	//find start and end nodes of a sublist
    	Head = getNodeof(from);
    	Tail = getNodeof(to);
    	
    	//make the sublist
    	DElmNode<DelayElement> tempHead = Head.next;
    	while(tempHead!=Tail){
    		sub.sequentialInsertion(tempHead.getData());
	    	DElmNode<DelayElement> horizontal = tempHead.right;
	    	while(horizontal!=null){
	    		sub.parallelInsertion(horizontal.getData());
	    		horizontal = horizontal.right;
	    	}
	    	tempHead = tempHead.next;
    	}
    	return sub;
    }
    
    /**
     * returns a sublist of this from "from" to the end of this list 
     */
    public NewLinkedList subSeq3(List<DelayElement> from){
    	NewLinkedList sub = new NewLinkedList();
    	DElmNode<DelayElement> Head = this.head;
    	//Find a start point of a sublist
    	Head = getNodeof(from);
    	
    	DElmNode<DelayElement> tempHead = Head.next;
    	while(tempHead!=null){
    		sub.sequentialInsertion(tempHead.getData());
    		DElmNode<DelayElement> horizontal = tempHead.right;
    		while(horizontal!=null){
    			sub.parallelInsertion(horizontal.getData());
    			horizontal = horizontal.right;
    		}
    		tempHead = tempHead.next;
    	}
    	
    	return sub;
    }
    
    public NewLinkedList subSeq4(DelayElement from){
    	NewLinkedList sub = new NewLinkedList();
    	DElmNode<DelayElement> tempHead = this.getHead().next;
    	DElmNode<DelayElement> Head = new DElmNode<DelayElement>();
    	Stack<DElmNode<DelayElement>> Store = new Stack<DElmNode<DelayElement>>();
    	boolean start = false;
    	boolean Next = false;
    	
    	while(tempHead!=null && !start){
    		if(tempHead.getData()==null){
    			DElmNode<DelayElement> horizontal = tempHead;
    			while(horizontal!=null){
    				Store.push(horizontal.next);
    				horizontal = horizontal.right;
    			}
    			while(!Store.isEmpty() && !start){
    				DElmNode<DelayElement> element = Store.pop();
    				Next = false;
    				while(!start && element!=null && !Next){
    					if(!element.getDummyTail()){
    						if(element.getData()!=null && contains(element, from)){
    							Head = element;
    							start = true;
    							break;
    						}
    					
    						element = element.next;
    					}
    					else if(element.getDummyTail() && Store.isEmpty() && !start){
    						Next = true;
    						tempHead = element;
    					}
    					else if(!Store.isEmpty() && !start)	element = Store.pop();						
    				}
    				
    				if(start)	tempHead = Head;
    			}
    			
    		}
    		else if(contains(tempHead, from)){
    			Head = tempHead;
    			start = true;
    			break;
    		}
    		tempHead = tempHead.next;
    	}
    	
    	DElmNode<DelayElement> subHead = new DElmNode<DelayElement>();
    	sub.setHead(subHead);
    	sub.getHead().next = Head;
    	
    	return sub;
    }
    
    public boolean contains(DElmNode<DelayElement> current,DelayElement c){
    	DElmNode<DelayElement> temp = current;
    	boolean result = false;
    	
    	while((temp!=null) && !result){
    		if(temp.getData()!=null && temp.getData().isEqual(c))	result = true;
    		else temp=temp.right;
    	}
    	
    	return result;
    }
    
    /**
     * If this list contains d, then remove it from that.
     * In order to check if a delay element is double counted and has a different occurrence order.  
     */
    public void contains_removes(DelayElement d){
    	boolean found = false;
   		DElmNode<DelayElement> temp = this.head.next;
   		
   		while(temp!=null && !found){
   			if(temp.getData().isEqual(d)){
   				temp.prev.next = temp.right;
   				temp.right.left = null;
   				found = true;
   			}
   			else{
   				DElmNode<DelayElement> horizontal = new DElmNode<DelayElement>();
   				horizontal = temp.right;
   				while(horizontal!=null && !found){
   					if(horizontal.getData().isEqual(d)){
   						horizontal.left.right = horizontal.right;
   						if(horizontal.right!=null)	horizontal.right.left = horizontal.left;
   						found = true;
   					}
   					horizontal=horizontal.right;
   				}
   			}

   			temp=temp.next;
    		
    	}
  }
    
    
    /**
    * Check out that a list contains one of a list of DelayElements as a node 
    **/
    public boolean contains(List<DelayElement> list){
    	boolean result = false;
    	DElmNode<DelayElement> tempHead = this.head;
    	Stack<DElmNode<DelayElement>> store = new Stack<DElmNode<DelayElement>>();
    	  
    	store.push(tempHead);
    	while(!store.isEmpty()){
    		DElmNode<DelayElement> temp = store.pop();
    		if(temp!=null){
	    		if(temp.getData()==null && temp.right!=null){
	    			store.push(temp.next);
	    			store.push(temp.right);
	    		}
	    		else if(temp.getData()==null && temp.right==null){
	    			store.push(temp.next);
	    		}
	    		else if(temp.getData()!=null){
	    			for(int i=0;i<list.size();i++){
	    				if(contains(temp,list.get(i))){
	    					result = true;
	    					break;
	    				}
	    			}
	    			if(result)	break;
	    			else	store.push(temp.next);
	    		}
    		}
    	}
    	return result;
    }
    
    
    
 //TODO Check the validity of following codes. 
    
    
    /**
     * Return the same LinkedList as the parameter of this function 
     */
    public static NewLinkedList copy(NewLinkedList list){
    	NewLinkedList reflica = new NewLinkedList();
    	Stack<DElmNode<DelayElement>> store = new Stack<DElmNode<DelayElement>>();
    	Stack<DElmNode<DelayElement>> recOrigin = new Stack<DElmNode<DelayElement>>();
    	Stack<DElmNode<DelayElement>> recNew = new Stack<DElmNode<DelayElement>>();
    	DElmNode<DelayElement> temp = new DElmNode<DelayElement>();
    	temp = list.head.next;
    	store.push(temp);
    	
    	while(!store.isEmpty()){
    		temp = store.pop();
    		if(temp.data==null)	reflica.sequentialInsertion(temp.data);
    		else if(!reflica.exists(temp.data)){
    			reflica.sequentialInsertion(temp.data);
    			copyFeatures(reflica.current,temp);
    		}
    		if(temp.data==null && temp.right!=null){
    			DElmNode<DelayElement> horizontal = temp;
    			horizontal = horizontal.right;
    			while(horizontal!=null){
    				recOrigin.push(horizontal);
    				reflica.parallelInsertion(horizontal.data);
    				DElmNode<DelayElement> node = reflica.getRightmost(reflica.current);
    				copyFeatures(node, temp);
    				recNew.push(node);
    				horizontal = horizontal.right;
    			}
    		}
    		else if(temp.data!=null && temp.right!=null){
    			DElmNode<DelayElement> horizontal = temp;
    			horizontal = horizontal.right;
    			while(horizontal!=null){
    				reflica.parallelInsertion(horizontal.data);
    				DElmNode<DelayElement> node = reflica.getRightmost(reflica.current);
    				copyFeatures(node, temp);
    				horizontal = horizontal.right;
    			}
    		}
    		
    		if(temp!=null
    				&& temp.next!=null)	store.push(temp.next);
    		else if(!recOrigin.isEmpty()){
    			if(recOrigin.peek().next==null){
    				recOrigin.pop();
    				recNew.pop();
    			}
    			else{
    				store.push(recOrigin.pop().next);
    				reflica.setCurrent(recNew.pop());
    			}
    		}
    	}
    	
    	return reflica;
    }
    
    public boolean exists (DelayElement item){
    	boolean result = false;
    	if(!this.isEmpty()){
	    	Stack<DElmNode<DelayElement>> store = new Stack<DElmNode<DelayElement>>();
	    	DElmNode<DelayElement> temp = this.head.next;
	    	store.push(temp);
	    	
	    	while(!store.isEmpty()){
	    		temp = store.pop();
	    		if(temp.data==null){
	    			store.push(temp.next);
	    			temp = temp.right;
	    			while(temp!=null){
	    				store.push(temp);
	    				temp = temp.right;
	    			}
	    		}
	    		else{
	    			result = contains(temp,item);
	    			if(temp.next!=null)	store.push(temp.next);
	    		}
	    		if(result)	break;
	    	}
    	}
    	
    	return result;
    }
    
    public static void copyFeatures(DElmNode<DelayElement> reflica, DElmNode<DelayElement> origin){
    	reflica.setCommon(origin.common);
		reflica.setDummyTail(origin.dummyTail);
		reflica.setVisited(origin.visited);

    }
    
    public DElmNode<DelayElement> getRightmost(DElmNode<DelayElement> pivot){
    	DElmNode<DelayElement> rightMost = new DElmNode<DelayElement>();
    	
    	while(pivot.right!=null){
	    	pivot=pivot.right;
	    }
	    rightMost = pivot;
	    return rightMost;
    }
}
