package com.hdh.util;

import java.util.LinkedList;

/**
 * @ClassName LimitedQueue
 * @Description TODO
 * @Author Kikityer
 * @Date 2019/3/21 14:55
 * @Version 1.0.0
 **/
public class LimitedQueue<E> extends LinkedList<E> {
    private static final long serialVersionUID = 1L;
    private int limit;

    public LimitedQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(E o) {
        super.add(o);
        while (size() > limit) {
            super.remove();
        }
        return true;
    }
}
