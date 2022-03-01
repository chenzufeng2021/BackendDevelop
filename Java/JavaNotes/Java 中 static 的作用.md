# static 关键字的用途

方便在==没有创建对象==的情况下进行调用（方法、变量）。

# 成员变量

```java
public class Student {
    // 静态成员变量
    private static String SchoolName;
    private static int nums;
    
    // 非静态成员变量
    private String name;
    private int age;
}
```

在类中一个成员变量可用 static 关键字来修饰，这样的成员变量称为 static 成员变量，或==静态成员变量==。而没有用 static 关键字修饰的成员变量称为非静态成员变量。

<font color=red>静态成员变量是属于类的，也就是说，该成员变量并不属于某个对象，即使有多个该类的对象实例，静态成员变量也只有一个。只要静态成员变量所在的类被加载，这个静态成员变量就会被分配内存空间</font>。因此在引用该静态成员变量时，通常不需要生成该类的对象，而是通过类名直接引用。引用的方法是“==类名 . 静态变量名==”。当然仍然可以通过“==对象名 . 静态变量名==”的方式引用该静态成员变量。

相对应的非静态成员变量则属于对象而非类，只有在内存中构建该类对象时，非静态成员变量才被分配内存空间。

# 成员方法

```java
public class Student {
    private static String SchoolName;
    private static int nums;
    
    // 静态成员方法
    public static String getSchoolName() {
        return Student.SchoolName;
    }
}
```

Java 中也支持用 static 关键字修饰的成员方法，即==静态成员方法==。与此相对应的没有用 static 修饰的成员方法称为非静态成员方法。

与静态成员变量类似，静态成员方法是==类方法==，它属于类本身而不属于某个对象。因此<font color=red>静态成员方法不需要创建对象就可以被调用</font>，而非静态成员方法则需要通过对象来调用。

<font color=red>在静态成员方法中不能使用 this、super 关键字，也不能调用非静态成员方法，同时不能引用非静态成员变量</font>。因为<font color=red>静态成员方法属于类而不属于某个对象，而 this、super 都是对象的引用</font>，非静态成员方法和成员变量也都属于对象。

所以<font color=red>当某个静态成员方法被调用时，该类的对象可能还没有被创建</font>，那么在静态成员方法中调用对象属性的方法或成员变量显然是不合适的。~~即使该类的对象已经被创建，也是无法确定它究意是调用哪个对象的方法，或是哪个对象中的成员变量的~~。

# static代码块

```java
public class Student {
    private static String SchoolName;
    private static int nums;
    
    // 静态代码块
    static {
        Student.SchoolName = "清风小学";
        Student.nums = 0;
    }
}
```

static 代码块又称为静态代码块，或静态初始化器。它是在类中独立于成员函数的代码块。<font color=red>static 代码块不需要程序主动调用，在 JVM 加载类时系统会执行 static 代码块</font>，因此在static 代码块中可以做一些类成员变量的初始化工作。如果一个类中有多个 static 代码块，JVM将会按顺序依次执行。需要注意的是，<font color=red>所有的 static 代码块只能在 JVM 加载类时被执行一次</font>。

# static 内部类

```java
public class Student {
    private static String SchoolName;
    private static int nums;
    
    // 静态内部类
    static class test{
        public test() {
            System.out.println("Hello，student!" );
        }
    }
}
```

在 Java 中还支持用 static 修饰的内部类，称为==静态内部类==。

静态成员内部类的特点主要是它本身是类相关的内部类，所以它<font color=red>可以不依赖于外部类实例而被实例化</font>。

静态内部类不能访问其外部类的实例成员（包括普通的成员变量和方法），只能访问外部类的==类成员==（包括静态成员变量和静态方法）。

即使是静态内部类的实例方法（非静态成员方法）也不能访问其外部类的实例成员。

# 参考资料

[详解Java 中 static 的作用](https://zhuanlan.zhihu.com/p/259368621)