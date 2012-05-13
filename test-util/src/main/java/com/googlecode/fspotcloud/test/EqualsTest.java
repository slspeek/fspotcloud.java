/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author steven
 */
public abstract class EqualsTest<T> {
    
    T one;
    T theOther;
    T differs;
    Object obj = new Object();
    
    @Before
    public final void setup() {
        one = getOne();
        theOther = getTheOther();
        differs = getDifferentOne();
    }

    
    protected abstract  T getOne();
    protected abstract  T getTheOther();
    protected abstract  T getDifferentOne();
    
    @Test 
    public void testEquals() {
        assertEquals(one, theOther);
    }
    
    @Test 
    public void shouldNotBeEqual() {
        assertFalse(one.equals(obj));
    }
    
    @Test
    public void shouldNotEqual() {
        assertFalse(one.equals(differs));
        
    }
    
    @Test
    public void shouldNotEqualNull() {
        assertFalse(one.equals(null));
    }
    
    @Test public void hashCodeContract() {
        assertEquals(one.hashCode(), theOther.hashCode());
    }
    
}
