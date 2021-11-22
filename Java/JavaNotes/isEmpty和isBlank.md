# StringUtils

在 Netty、Apache commons-lang3、Spring 等包中，都存在类似的 String 工具类。虽然大部分框架都会自己集成，但用的最多的，方法最全的还要属 Apache commons-lang3 工具包。

## 引入依赖

```xml
<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.12.0</version>
</dependency>

<!-- @Test -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.7.0</version>
</dependency>
```

# isEmpty 和 isBlank 区别

## isEmpty

判断==字符串==是否为空字符串，只要有一个任意字符（==包括空白字符==）就不为空。

isEmpty 的方法源码：

```java
public static boolean isEmpty(CharSequence cs) {
    return cs == null || cs.length() == 0;
}
```

这个方法只判断了是否为 null 或者长度为 0。意味着，<font color=red>如果用户输入 "   " 等空白字符，这个方法结果就是不为空了</font>。

|   输入内容   | 是否为空 |
| :----------: | :------: |
|    "   "     |    否    |
|      ""      |    是    |
| "Java技术栈" |    否    |

## isBlank

判断==字符串==是否为空字符串，==全部空白字符==也为空。

isBlank 的方法源码：

```java
public static boolean isBlank(CharSequence cs) {
    int strLen = length(cs);
    if (strLen == 0) {
        return true;
    } else {
        for(int i = 0; i < strLen; ++i) {
            // 只要有一个字符不为空白字符就返回 false
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
```

|   输入内容   | 是否为空 |
| :----------: | :------: |
|    "   "     |    是    |
|      ""      |    是    |
| "Java技术栈" |    否    |

