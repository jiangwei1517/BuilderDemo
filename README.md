# BuilderDemo

## 参考资料
Android中的构建者（Builder）模式<http://www.jianshu.com/p/0adc46f457be>

### 优缺点

优点：

良好的封装性，使得客户端不需要知道产品内部实现的细节
建造者独立，扩展性强
### 缺点：

产生多余的Builder对象、Director对象，消耗内存
### 关于线程安全

Builder模式是非线程安全的，如果要在Builder内部类中检查一个参数的合法性，必需要在对象创建完成之后再检查，