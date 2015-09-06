

### sleep & wait 的区别

sleep()方法，我们首先要知道该方法是属于Thread类中的。而wait()方法，则是属于Object类中的。
sleep 让出cpu资源不会让出lock，监控状态依然保持者
wait 让出cpu资源也会让出lock，而当调用wait()方法的时候，线程会放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象调用notify()方法后本线程才进入对象锁定池准备


fork/join 框架
http://www.javacodegeeks.com/2013/02/java-7-forkjoin-framework-example.html