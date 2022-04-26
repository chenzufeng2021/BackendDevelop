# 文档更新日志

| 版本 | 更新日期   | 操作     |
| ---- | ---------- | -------- |
| v1.0 | 2022/04/24 | 添加笔记 |

# HttpClient 简介

HttpClient 是 Apache Jakarta Common 下的子项目，用来提供高效的、最新的、功能丰富的支持 HTTP 协议的客户端编程工具包，并且它支持 HTTP 协议最新的版本和建议。

## 使用步骤

### HttpGet 请求响应步骤

1、创建客户端：创建 `HttpClient` 对象，可以使用 `HttpClients.createDefault()`；

2、创建请求：

- 如果是==无参数的 GET 请求==，则直接使用构造方法 `HttpGet(String url)` 创建 `HttpGet` 对象即可；
- 如果是==带参数GET请求==，则
  - 可以先使用 `URIBuilder(String url)` 创建对象，再调用 `addParameter(String param, String value)`，或 `setParameter(String param, String value)` 来设置请求参数，并调用 build() 方法构建一个 URI 对象。再用构造方法 `HttpGet(URI uri)` 来创建 HttpGet 对象。
  - 直接拼接：`?type=java`

3、发送请求：创建 `HttpResponse`，调用 `HttpClient` 对象的 `execute(HttpUriRequest request)` 发送请求，该方法返回一个 `HttpResponse`。

- 调用 `HttpResponse` 的 `getAllHeaders()、getHeaders(String name)` 等方法可获取服务器的响应头；
- 调用 `HttpResponse` 的 `getEntity()` 方法可获取 HttpEntity 对象，该对象包装了服务器的响应内容。程序可通过该对象获取服务器的响应内容。
- 通过调用 `getStatusLine().getStatusCode()` 可以获取响应状态码。

4、释放连接。

### HttpPost 请求响应步骤

1、创建客户端：创建 `HttpClient` 对象，可以使用 `HttpClients.createDefault()`；

2、创建请求：

- 如果是==无参数的 GET 请求==，则直接使用构造方法 `HttpPost(String url)` 创建 `HttpPost` 对象即可；
- 如果是==带参数 POST 请求==，先构建 HttpEntity 对象并设置请求参数，然后调用 setEntity(HttpEntity entity) 创建 HttpPost 对象。

3、发送请求：创建 `HttpResponse`，调用 `HttpClient` 对象的 `execute(HttpUriRequest request)` 发送请求，该方法返回一个 `HttpResponse`。

- 调用 `HttpResponse` 的 `getAllHeaders()、getHeaders(String name)` 等方法可获取服务器的响应头；
- 调用 `HttpResponse` 的 `getEntity()` 方法可获取 HttpEntity 对象，该对象包装了服务器的响应内容。程序可通过该对象获取服务器的响应内容。
- 通过调用 `getStatusLine().getStatusCode()` 可以获取响应状态码。

 4、释放连接。

# 示例

## 依赖

```xml
<dependencies>
    <!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient -->
    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpclient</artifactId>
        <version>4.5.13</version>
    </dependency>
</dependencies>
```

## 无参 Get 请求

```java
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 普通的无参数 GET 请求
 * @author chenzufeng
 * @date 2022/4/24
 */
public class DoGet {
    public static void main(String[] args) {
        String url = "http://localhost:8080/myServlet";
        // 1 获取客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 2 创建请求
        HttpGet httpGet = new HttpGet(url);
        // 3 发送请求
        CloseableHttpResponse httpResponse = null;
        try {
            // 3.1 执行
            httpResponse = httpClient.execute(httpGet);
            // 3.2 响应状态码
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            System.out.println("响应状态码为：" + statusCode);
            // 3.3 响应信息
            HttpEntity httpEntity = httpResponse.getEntity();
            System.out.println("响应内容为：" + EntityUtils.toString(httpEntity));

            // 4 释放资源
            EntityUtils.consume(httpEntity);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## 有参 GET 请求

两种方式[^3]：

- 直接将参数拼接到 url 后面，如：`?type=java`；
- 使用 URIBuilder 的方法设置参数：`setParameter("type", "java")`

### 配置请求信息

```java
RequestConfig requestConfig = RequestConfig.custom()
        // 连接超时时间
        .setConnectTimeout(500)
        // 请求超时时间
        .setConnectionRequestTimeout(500)
        // 读写超时时间
        .setSocketTimeout(500)
        // 是否重定向，默认开启
        .setRedirectsEnabled(true)
        .build();
httpGet.setConfig(requestConfig);
```

### 代码实现

```java
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author chenzufeng
 * @date 2022/4/24
 */
public class DoGetParam {
    public static void main(String[] args) throws URISyntaxException {
        String url = "http://localhost:8080/myServlet";
        // 1 获取客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2 创建请求
        URI uri = new URIBuilder(url).setParameter("type", "java").build();
        HttpGet httpGet = new HttpGet(uri);
        // 3 配置请求信息
        RequestConfig requestConfig = RequestConfig.custom()
                // 连接超时时间
                .setConnectTimeout(500)
                // 请求超时时间
                .setConnectionRequestTimeout(500)
                // 读写超时时间
                .setSocketTimeout(500)
                // 是否重定向，默认开启
                .setRedirectsEnabled(true)
                .build();
        httpGet.setConfig(requestConfig);
        // 4 发送请求
        CloseableHttpResponse httpResponse = null;
        try {
            // 4.1 执行
            httpResponse = httpClient.execute(httpGet);
            // 4.2 响应状态码
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            System.out.println("响应状态码为：" + statusCode);
            // 4.3 响应信息
            HttpEntity httpEntity = httpResponse.getEntity();
            System.out.println("响应内容为：" + EntityUtils.toString(httpEntity));

            // 5 释放资源
            EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```



# 参考资料

[^1]:[Java中发送Http请求之httpClient-七国的天下，我要九十九的博客-CSDN博客](https://blog.csdn.net/ABestRookie/article/details/121503091)
[^2]:[springboot实战之常用http客户端整合 - 云+社区 - 腾讯云 (tencent.com)](https://cloud.tencent.com/developer/article/1537173)
[^3]:[HttpClient使用详解与实战一：普通的GET和POST请求 - 简书 (jianshu.com)](https://www.jianshu.com/p/375be5929bed)





