package com.peaceful.design.mode.observable;

/**
 * 被观察者
 *
 * @author wangjun
 * @version 1.0
 * @since 15/5/8.
 */
public interface ISubject {

    /**
     * 加入观察者
     *
     * @param observable
     */
    void attach(IObservable observable);

    /**
     * 删除观察者
     *
     * @param observable
     */
    void detach(IObservable observable);

    /**
     * 通知观察者
     *
     * @param e
     */
    void inform(Event e);
}
