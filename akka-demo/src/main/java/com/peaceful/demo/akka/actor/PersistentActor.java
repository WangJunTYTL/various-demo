package com.peaceful.demo.akka.actor;

import akka.japi.Procedure;
import akka.persistence.RecoveryCompleted;
import akka.persistence.SnapshotOffer;
import akka.persistence.UntypedPersistentActor;

import java.io.Serializable;
import java.util.ArrayList;

import static java.util.Arrays.asList;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/6/23
 * @since 1.6
 */




class ExampleState implements Serializable {
    protected final ArrayList<String> events;

    public ExampleState() {
        this(new ArrayList<String>());
    }

    public ExampleState(ArrayList<String> events) {
        this.events = events;
    }

    public ExampleState copy() {
        return new ExampleState(new ArrayList<String>(events));
    }

    public void update(Evt evt) {
        events.add(evt.getData());
    }

    public int size() {
        return events.size();
    }

    @Override
    public String toString() {
        return events.toString();
    }
}

public class PersistentActor extends UntypedPersistentActor {

    //A persistent actor must have an identifier that doesn't change across different actor incarnations(化身).
    // The identifier must be defined with the persistenceId method.
    @Override
    public String persistenceId() {
        return "sample-id-1";
    }

    private ExampleState state = new ExampleState();

    public int getNumEvents() {
        return state.size();
    }

    @Override
    public void onReceiveRecover(Object msg) {
        if (msg instanceof Evt) {
            state.update((Evt) msg);
        } else if (msg instanceof SnapshotOffer) {
            state = (ExampleState) ((SnapshotOffer) msg).snapshot();
        } else {
            unhandled(msg);
        }
    }

    @Override
    public void onReceiveCommand(Object msg) {
        if (msg instanceof Cmd) {
            final String data = ((Cmd) msg).getData();
            final Evt evt1 = new Evt(data + "-" + getNumEvents());
            final Evt evt2 = new Evt(data + "-" + (getNumEvents() + 1));
            persist(asList(evt1, evt2), new Procedure<Evt>() {
                public void apply(Evt evt) throws Exception {
                    state.update(evt);
                    if (evt.equals(evt2)) {
                        getContext().system().eventStream().publish(evt);
                    }
                }
            });
        } else if (msg.equals("snap")) {
            // IMPORTANT: create a copy of snapshot
            // because ExampleState is mutable !!!
            saveSnapshot(state.copy());
        } else if (msg.equals("print")) {
            System.out.println(state);
        } else {
            unhandled(msg);
        }
    }


}