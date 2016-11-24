package com.peaceful.collection.demo.pv;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by JunWang on 2016/11/22.
 */
public class TreeSetDemo {

    public static void main(String[] args) {
        TreeSet<Ele> eles = new TreeSet<Ele>(comparator);
        // a.equals(b) && a.compare(b) != 0 这将插入到TreeSet
        eles.add(new Ele("a",1));
        eles.add(new Ele("a",2));
        System.out.println(eles.first().count+" "+eles.last().count);
    }

    private static Comparator<Ele> comparator = new Comparator<Ele>() {
        @Override
        public int compare(Ele o1, Ele o2) {
            if (o1.count.equals(o2.count)) {
                return 0;
            }else if (o1.count > o2.count){
                return -1;
            }else {
                return 1;
            }
        }
    };

    private static class Ele {
        public String tag;
        public Integer count;

        public Ele(String tag, Integer count) {
            this.tag = tag;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Ele)) return false;

            Ele ele = (Ele) o;

            return tag.equals(ele.tag);

        }

        @Override
        public int hashCode() {
            return tag.hashCode();
        }
    }
}
