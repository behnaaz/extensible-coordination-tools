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
package org.ect.ea.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @generated NOT
 * @author Christian Krause
 */
public class ProductCache<X> {
	
	private HashMap<X, HashMap<X,List<X>>> cache;
	
	public ProductCache() {
		cache = new HashMap<X, HashMap<X,List<X>>>();
	}
	
	public void addProducts(X x1, X x2, List<X> products) {
		initMap(x1);
		cache.get(x1).put(x2, products);
	}
	
	public void addProduct(X x1, X x2, X product) {
		List<X> products = new ArrayList<X>(1);
		products.add(product);
		addProducts(x1,x2,products);
	}
	
	public List<X> getProducts(X x1, X x2) {
		initMap(x1);
		return cache.get(x1).get(x2);
	}
	
	public X getProduct(X x1, X x2) {
		List<X> products = getProducts(x1,x2);
		if (products!=null && !products.isEmpty()) {
			return products.get(0);
		} else {
			return null;
		}
	}
	
	public boolean isDefined(X x1, X x2) {
		return getProduct(x1,x2)!=null;
	}
	
	public boolean isEmpty(X x1, X x2) {
		return isDefined(x1, x2) && getProducts(x1, x2).isEmpty();
	}
	
	public void clear() {
		cache.clear();
	}
	
	private void initMap(X x) {
		if (!cache.containsKey(x)) {
			cache.put(x, new HashMap<X, List<X>>());
		}
	}
	
}
