package com.peaceful.demo.akka.actor;

import akka.persistence.SnapshotOffer;
import akka.persistence.Snapshotter;
import akka.persistence.UntypedPersistentView;
import com.peaceful.common.util.Util;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/6/23
 * @since 1.6
 */

public class PersistentViewActor extends UntypedPersistentView {

    @Override
    public String persistenceId() {
        return "some-persistence-id";
    }

    //要观察的actor的persistenceId
    @Override
    public String viewId() {
        return "sample-id-1";
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (isPersistent()) {
            // handle message from Journal...
        } else if (message instanceof String) {
            // handle message from user...
        } else if (message instanceof SnapshotOffer) {
            // handle message from user...
            SnapshotOffer snapshotter = (SnapshotOffer) message;
            Util.report(snapshotter.metadata());
            Util.report(snapshotter.snapshot());
        } else {
            unhandled(message);
        }
    }
}


