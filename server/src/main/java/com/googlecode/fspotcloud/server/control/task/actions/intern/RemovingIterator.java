/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.server.control.task.actions.intern;

import java.util.Iterator;

/**
 *
 * @author steven
 */
public class RemovingIterator<T> implements Iterator<T> {
    private final Iterator<T> delegate;

    public RemovingIterator(Iterator<T> delegate) {
        this.delegate = delegate;
    }

    
    @Override
    public boolean hasNext() {
        return delegate.hasNext();
    }

    @Override
    public T next() {
        T next = delegate.next();
        remove();
        return next;
    }

    @Override
    public void remove() {
        delegate.remove();
    }
    
}
