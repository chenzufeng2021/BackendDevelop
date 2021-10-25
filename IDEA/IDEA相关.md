# 快捷键

## 生成Get/Set方法快捷键

`Alt + Ins`(Insert)

<img src="IDEA相关.assets/生成GetSet方法快捷键.png" alt="生成GetSet方法快捷键" style="zoom:50%;" />

快捷输入

```java
String sessionCode = (String) session.getAttribute("validateCode");
```



```java
session.getAttribute("validateCode").cas
```



# 配置

## 使用Lambda表达式

使用了`Lambda`表达式，`IDEA`报错：`Lambda expressions are not supported at language level '7'`。需要在`Project Structure`中做以下修改：

![支持Lambda](IDEA相关.assets/支持Lambda.png)



## IDEA每次打开新的Maven项目都需要重新配置

<img src="IDEA相关.assets/SettingForNewProject.png" alt="SettingForNewProject" style="zoom:50%;" />

设置`Maven`相关地址：

<img src="IDEA相关.assets/MavenSetting.png" alt="MavenSetting" style="zoom:50%;" />



## IDEA去除xml文件sql语句背景色

Step 1：Settings $\rightarrow$ Editor$\rightarrow$ Inspections $\rightarrow$ SQL $\rightarrow$ `No data sources configure`取消勾选

Step 2：Settings $\rightarrow$ Editor $\rightarrow$ Inspections $\rightarrow$ SQL $\rightarrow$ `SQL dialect detection`取消勾选

![IDEA去除xml文件sql语句背景色](IDEA相关.assets/IDEA去除xml文件sql语句背景色.png)

Step 3：Settings $\rightarrow$ Editor $\rightarrow$ Color Scheme $\rightarrow$ General $\rightarrow$ Code $\rightarrow$ `Injected language fragment`去掉`Background`勾选

![IDEA去除xml文件sql语句背景色1](IDEA相关.assets/IDEA去除xml文件sql语句背景色1.png)

## 创建mapper.xml模板

![image-20210624154349759](IDEA相关.assets/image-20210624154349759.png)

## propertie图标变灰色且无代码提示

![image-20210728224140032](IDEA相关.assets/image-20210728224140032.png)

## 设置编码

![image-20211013232224805](IDEA相关.assets/image-20211013232224805.png)

