# HashMap遍历的四种方法

## 使用 entrySet

```java
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
for(Map.Entry<Integer, Integer> entry : map.entrySet()){
	System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue())
}
```

注意：For-Each 循环是 Java 5 新引入的，所以只能在 Java 5 以上的版本中使用。如果遍历的 map 是 null，For-Each 循环会抛出 `NullPointerException` 异常，所以==在遍历之前应该判断是否为空引用==。

## KeySet 或 values

如果只需要用到 map 的 keys 或 values 时，可以==遍历 KeySet 或者 values== 代替entrySet。

```java
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
 
// iterating over keys only
for (Integer key : map.keySet()) {
	System.out.println("Key = " + key);
}
 
// iterating over values only
for (Integer value : map.values()) {
	System.out.println("Value = " + value);
}
```

## 使用 Iterator 迭代

### 使用泛型

```java
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
while (entries.hasNext()) {
	Map.Entry<Integer, Integer> entry = entries.next();
	System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
}
```

### 不使用泛型

```java
Map map = new HashMap();
Iterator entries = map.entrySet().iterator();
while (entries.hasNext()) {
	Map.Entry entry = (Map.Entry) entries.next();
	Integer key = (Integer)entry.getKey();
	Integer value = (Integer)entry.getValue();
	System.out.println("Key = " + key + ", Value = " + value);
}
```

### 注意点

可以使用同样的技术迭代 keyset 或者 values。

这个似乎有点多余但它具有自己的优势：

- 首先，它是遍历老 Java 版本 map 的唯一方法。
- 另外一个重要的特性是，可以在迭代的时候从 map 中删除 entries 的（通过调用`iterator.remover()`）唯一方法。如果试图在 For-Each 迭代的时候删除entries，将会得到 unpredictable resultes 异常。



## 迭代 keys 并搜索 values

```java
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
for (Integer key : map.keySet()) {
	Integer value = map.get(key);
	System.out.println("Key = " + key + ", Value = " + value);
}
```

这个方法看上去比[使用 entrySet](# 使用 entrySet)更简洁，但是实际上它==更慢、更低效==。通过 key 得到 value 值更耗时（这个方法在所有实现 map 接口的 map 中比[使用 entrySet](# 使用 entrySet)慢$20\%-200\%$）。

## 代码实现

```java
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestHashMap {
    public static void main(String[] args) {
        Map<Integer,String> map =new HashMap<Integer,String>();
        map.put(1, "xiao");
        map.put(2, "chao");
        map.put(3, "shang");
        map.put(4, "xue");
        // 方法一
        for(Map.Entry<Integer,String> entry: map.entrySet()) {
            System.out.println("方法一：key = " + entry.getKey() + "---value= " + entry.getValue());
        }

        // 方法二
        for(Integer key: map.keySet()) {
            System.out.println("方法二：key = " + key);
        }

        for(String value:map.values()) {
            System.out.println("方法二：value = " + value);
        }
        
        // 方法三
        Iterator<Map.Entry<Integer,String>> entries = map.entrySet().iterator();
        while(entries.hasNext()) {
            Map.Entry<Integer,String> entry = entries.next();
            System.out.println("方法三：key = " + entry.getKey() + "--value = " + entry.getValue());
        }

        // 方法四
        for(Integer key: map.keySet()) {
            String value = map.get(key);
            System.out.println("方法四：Key = " + key + ", Value = " + value);
        }
    }
}
```



## 参考资料

[HashMap遍历的四种方法_John的博客-CSDN博客_hashmap遍历](https://blog.csdn.net/scgyus/article/details/79105211)

