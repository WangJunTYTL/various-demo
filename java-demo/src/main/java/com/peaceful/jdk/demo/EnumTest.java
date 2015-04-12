package com.peaceful.jdk.demo;

import com.peaceful.common.util.Util;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/4/4.
 */

public class EnumTest {

    /**
     * Enumeration of types of events that may occur on the ZooKeeper
     */
    public enum EventType {
        None (-1),
        NodeCreated (1),
        NodeDeleted (2),
        NodeDataChanged (3),
        NodeChildrenChanged (4);

        private final int intValue;     // Integer representation of value
        // for sending over wire

        EventType(int intValue) {
            this.intValue = intValue;
        }

        public int getIntValue() {
            return intValue;
        }

        public static EventType fromInt(int intValue) {
            switch(intValue) {
                case -1: return EventType.None;
                case  1: return EventType.NodeCreated;
                case  2: return EventType.NodeDeleted;
                case  3: return EventType.NodeDataChanged;
                case  4: return EventType.NodeChildrenChanged;

                default:
                    throw new RuntimeException("Invalid integer value for conversion to EventType");
            }
        }
    }

    public static void main(String[] args) {
       Util.report(EventType.fromInt(2).getIntValue());
       Util.report(EventType.fromInt(2));
    }
}
