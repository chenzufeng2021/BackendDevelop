# 引入

在日常开发中，我们会经常要在类中定义布尔类型的变量，例如==success/isSuccess==。

从语义上面来讲，两种命名方式都可以讲的通，并且也都没有歧义。那么还有什么原则可以参考来让我们做选择呢？

根据JavaBeans Specification规定，如果是普通的参数`propertyName`，要以以下方式定义其setter/getter：

```java
public <PropertyType> get<PropertyName>();

public void set<PropertyName>(<PropertyType> a);
```

但是，<font color=red>布尔类型的变量`propertyName`则是单独定义的</font>：

```java
public boolean is<PropertyName>();

public void set<PropertyName>(boolean m);
```

success 方法的 getter 应该是 isSuccess/getSuccess，而 isSuccess 的 getter 应该是 isIsSuccess/getIsSuccess。

但是很多人，在使用isSuccess作为属性名的时候，还是会采用isSuccess/getSuccess作为 getter 方法名，**尤其是现在的很多 IDE 在默认生成 getter 的时候也是会生成 isSuccess。**

在一般情况下，其实是没有影响的。但是有一种特殊情况就会有问题，那就是发生<font color=red>==序列化==的时候可能会导致参数转换异常</font>。

# 序列化导致参数转换异常

先来定义一个 JavaBean：

```java
class Model implements Serializable {
   private static final long serialVersionUID = 1836697963736227954L;
   private boolean isSuccess;
    
   public boolean isSuccess() {
       return isSuccess;
   }
    
   public void setSuccess(boolean success) {
       isSuccess = success;
   }

   public String getHollis(){
       return "hollischuang";
   }
}
```

在这个 JavaBean 中，有一个成员变量 isSuccess，三个方法：分别是IDE帮我们自动生成的isSuccess和setSuccess，另外一个是作者自己增加的一个符合getter命名规范的方法。

分别使用不同的 JSON 序列化工具来对这个类的对象进行序列化和反序列化：

```java
public class BooleanMainTest {
     public static void main(String[] args) throws IOException {
         // 定义一个Model类型
         Model model = new Model();
         model.setSuccess(true);
         
         // 使用fastjson(1.2.16)序列化model成字符串并输出
         System.out.println("Serializable Result With fastjson :" + JSON.toJSONString(model));
         
         // 使用Gson(2.8.5)序列化model成字符串并输出
         Gson gson =new Gson();
         System.out.println("Serializable Result With Gson :" + gson.toJson(model));
         
         // 使用jackson(2.9.7)序列化model成字符串并输出
         ObjectMapper om = new ObjectMapper();
         System.out.println("Serializable Result With jackson :" + om.writeValueAsString(model));
     }
}
```

以上代码输出结果：

```markdown
Serializable Result With fastjson :{"hollis":"hollischuang", "success":true}

Serializable Result With Gson :{"isSuccess":true}

Serializable Result With jackson :{"success":true, "hollis":"hollischuang"}
```

在 fastjson 和 jackson 的结果中，<font color=red>原来类中的 isSuccess 字段被序列化成 success，并且其中还包含 hollis 值</font>。而 Gson 中只有 isSuccess 字段。

我们可以得出结论：<font color=red>fastjson 和 jackson 在==把对象序列化成 json 字符串==的时候，是通过==反射==遍历出该类中的==所有 getter 方法==，得到 getHollis 和 isSuccess，然后根据 JavaBeans 规则，他会认为这是两个属性 hollis 和 success 的值</font>。直接序列化成 json：

```markdown
{“hollis”:”hollischuang”, ”success”:true}
```

但是 Gson 并不是这么做的，他是通过反射遍历该类中的==所有属性==，并把其值序列化成 json：

```markdown
{“isSuccess”:true}
```

可以看到，<font color=red>**由于不同的序列化工具，在进行序列化的时候使用到的策略是不一样的**，所以，对于同一个类的同一个对象的序列化结果可能是不同的</font>。

如果我们把一个对象使用fastjson进行序列化，再使用Gson反序列化会发生什么呢？

```java
 public class BooleanMainTest {
     public static void main(String[] args) throws IOException {
         Model model = new Model();
         model.setSuccess(true);
         Gson gson = new Gson();
         System.out.println(gson.fromJson(JSON.toJSONString(model),Model.class));
     }
 }
```

以上代码，输出结果：

```markdown
Model[isSuccess=false]
```

这和我们预期的结果完全相反，原因是<font color=red>因为JSON框架通过扫描所有的getter后发现有一个isSuccess方法，然后根据JavaBeans的规范，解析出变量名为success，把model对象序列化城字符串后内容为{"success":true}。根据{"success":true}这个json串，Gson框架在通过解析后，通过反射寻找Model类中的success属性，但是Model类中只有isSuccess属性，所以，最终反序列化后的Model类的对象中，isSuccess则会使用默认值false</font>。

所以，**建议使用 success 而不是 isSuccess 这种形式。** 这样，该类里面的成员变量是success，getter方法是isSuccess，这是完全符合JavaBeans规范的。无论哪种序列化框架，执行结果都一样。就从源头避免了这个问题。

# 参考资料

[1] [新来个技术总监：谁在用 isXxx 形式定义布尔类型年后不用来了！](https://mp.weixin.qq.com/s/F4YoOeuhY9ZsOZWt7CLAbg)