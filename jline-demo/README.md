## 控制台

java的控制台输入是很局限的，我们仅仅可得到它的输入，对于输入的过程是很难操控的，所以当我们想写一个人性化的输入体验的时候，是比较难实现的。
还好java中有种jni的技术，它允许Java代码和其他语言写的代码进行交互。

Jline就是一个使用了C/C++实现的java类库，它可以让你更方便的处理控制台输入。

### 特点

````
支持上下键输入查询历史命令
支持自动提示