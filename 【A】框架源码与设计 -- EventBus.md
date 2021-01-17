## 【A】框架源码与设计 -- EventBus



### Topics

EventBus 2.x 到 Eventbus3.x 对反射性能问题进行了怎样的优化？

```
EventBus 2.x 会在 register 方法运行时，遍历所有方法找到回调方法；而EventBus 3.x 则在编译期间，将所有回调方法的信息保存的自己定义的 SubscriberMethodInfo 中，这样可以减少对运行时的性能影响。

```

