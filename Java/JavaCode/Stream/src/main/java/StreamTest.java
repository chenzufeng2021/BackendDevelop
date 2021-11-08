import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chenzufeng
 * @date 2021/11/8
 * @usage StreamTest
 */
public class StreamTest {
    private static final Logger logger = LoggerFactory.getLogger(StreamTest.class);

    /**
     * forEach：逐一处理
     */
    @Test
    public void forEachDemo() {
        String[] array = {"张无忌", "张翠山", "张三丰", "张一元"};
        Stream<String> stringStream = Stream.of(array);
        // stringStream.forEach(logger::info)
        stringStream.forEach(str -> logger.info(str));
    }

    /**
     * filter：过滤
     */
    @Test
    public void filterDemo() {
        String[] array = {"张无忌", "张三丰", "周芷若"};
        Stream<String> stringStream = Stream.of(array);
        // 过滤
        Stream<String> filterResult = stringStream.filter(str -> str.startsWith("张"));
        filterResult.forEach(logger::info);
    }

    /**
     *  map：映射
     */
    @Test
    public void mapDemo() {
        String[] array = {"10", "12", "18"};
        Stream<String> stringStream = Stream.of(array);
        Stream<Integer> integerStream = stringStream.map(result -> Integer.parseInt(result));
        integerStream.forEach(integer -> logger.info(String.valueOf(integer)));
    }

    /**
     *  count：统计流中的元素个数，得到的值为long型
     */
    @Test
    public void countDemo() {
        String[] array = {"张无忌", "张三丰", "周芷若"};
        Stream<String> stringStream = Stream.of(array);
        long count = stringStream.count();
        logger.info(String.valueOf(count));
    }

    /**
     *  limit：取用前几个
     */
    @Test
    public void limitDemo() {
        String[] array = {"张无忌", "张三丰", "周芷若"};
        Stream<String> stringStream = Stream.of(array);
        // 只取数组中前2个
        Stream<String> limitStream = stringStream.limit(2);
        limitStream.forEach(logger::info);
    }

    /**
     *  skip：跳过前几个
     */
    @Test
    public void skipDemo() {
        String[] array = {"张无忌", "张三丰", "周芷若"};
        Stream<String> stringStream = Stream.of(array);
        Stream<String> newStream = stringStream.skip(2);
        newStream.forEach(logger::info);
    }

    /**
     *  concat：组合
     */
    @Test
    public void concatDemo() {
        Stream<String> stream1 = Stream.of("张三");
        Stream<String> stream2 = Stream.of("李四");
        Stream<String> newStream = Stream.concat(stream1, stream2);
        newStream.forEach(logger::info);
    }

    /**
     *  sorted：排序
     */
    @Test
    public void sortDemo() {
        String[] array = {"c", "b", "a", "a"};
        Stream<String> stringStream = Stream.of(array);
        stringStream.limit(3).sorted().forEach(logger::info);
    }

    /**
     * 分组
     */
    @Test
    public void groupingByDemo() {
        List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");
        Map<String, Long> result = items.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        result.entrySet().stream().forEach(System.out::println);
    }

    /**
     * 查找
     */
    @Test
    public void findDemo() {
        List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");
        Stream<String> stringStream = items.stream();
        // true
        logger.info(String.valueOf(stringStream.allMatch(str -> str.contains("a"))));
        /*
         * 不能直接在stringStream上继续进行处理
         * logger.info(stringStream.filter(str -> str.contains("a")).findFirst().get())
         * 会报错：
         * java.lang.IllegalStateException: stream has already been operated upon or closed
         */
        Stream<String> stringStream1 = items.stream();
        // apple
        logger.info(stringStream1.filter(str -> str.contains("a")).findFirst().get());
    }

    @Test
    public void joinDemo() {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        // [张无忌, 周芷若, 赵敏]
        logger.info(String.valueOf(list));
        // 张无忌,周芷若,赵敏
        logger.info(list.stream().collect(Collectors.joining(",")));
    }

    @Test
    public void reduceDemo() {
        List<Integer> num = Arrays.asList(1, 2, 4, 5, 6, 7);
        // 求和 25
        num.stream().reduce((x, y) -> x + y).ifPresent(System.out::println);
        // 求最大值 7
        num.stream().reduce(Integer::max).ifPresent(System.out::println);
    }
}


























