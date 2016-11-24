package com.peaceful.collection.demo.pv;

/**
 * Created by JunWang on 2016/11/21.
 */
public class VisitorPV {

    public String url;
    public Long count;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitorPV)) return false;

        VisitorPV visitorPV = (VisitorPV) o;

        return url.equals(visitorPV.url);

    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }
}
