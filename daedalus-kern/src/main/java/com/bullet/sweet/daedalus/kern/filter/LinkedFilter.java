package com.bullet.sweet.daedalus.kern.filter;

import lombok.AllArgsConstructor;

/**
 * Created by zhanlan on 16/11/20.
 */

@AllArgsConstructor
public class LinkedFilter<T> implements Filter<T> {

    private final Filter<T> current;
    private final Filter<T> next;



    @Override
    public void filter(T t) {
        if (current != null) {
            current.filter(t);
        }
        next.filter(t);
    }
}
