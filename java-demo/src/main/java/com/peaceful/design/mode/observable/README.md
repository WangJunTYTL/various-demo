观察者模式
=============

应用场景：假如有abc对象，a对象发生改变，bc对象需要收到通知，这个时候，bc主动去polling对象a的状态，这是一种耗费资源的方式，
这是采用a主动通知bc的，这种模式称为观察者模式，由于这种模式使用普遍，jdk提供了这种模式实现方式Observable Observe