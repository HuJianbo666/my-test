package notes;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.RangeSet;
import com.google.common.collect.Sets;
import com.google.common.collect.TreeMultimap;
import com.google.common.collect.TreeRangeMap;
import com.google.common.collect.TreeRangeSet;
import com.google.common.graph.Graph;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.google.common.math.IntMath;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.security.Key;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

import static com.google.common.base.Charsets.UTF_8;


/**
 * @author Hu Jianbo
 * @date: 2018/9/19
 */
public class GuavaTest {

    public static void main(String[] args) {

        // 不可变集合
        ImmutableList<Integer> of = ImmutableList.of(1, 2, 3, 4);

        ImmutableSet.of(1, 2);

        // 读取文件
        try {
            List<String> lines = Files.readLines(new File("D:\\projects\\my\\my-test\\src\\main\\java\\thread\\README.md"), UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 基本类型比较
        int compare = Ints.compare(1, 2);
        Longs.compare(1L, 2L);
        Doubles.compare(1.2222, 2.1111);

        // Joiner、Splitter
        int[] numbers = {1, 2, 3, 4, 5};
        Joiner.on("、").join(Ints.asList(numbers));

        Ints.join(";", numbers);

        String str = "1, 2, 3, 4,,6,7,..";
        Iterable<String> split = Splitter.on(",").omitEmptyStrings().trimResults().split(str);

        // Ints
        int[] arrays = {1, 2, 3, 4, 5};
        int[] arrays2 = {6, 7};
        int a = 4;
        boolean contains = Ints.contains(arrays, a);
        int i = Ints.indexOf(arrays, a);
        Ints.max(arrays);
        int min = Ints.min(arrays);
        int[] concat = Ints.concat(arrays, arrays2);

        // Set交集、并集、差集
        Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4);
        Set<Integer> set2 = Sets.newHashSet(3, 4, 5, 6);

        Sets.SetView<Integer> union = Sets.union(set1, set2);
        Sets.SetView<Integer> difference = Sets.difference(set1, set2);
        Sets.SetView<Integer> intersection = Sets.intersection(set1, set2);

        Map<Object, Object> map1 = Maps.newHashMap();
        map1.put("k1", "v1");
        map1.put("k2", "v2");

        Map<Object, Object> map2 = Maps.newHashMap();
        map2.put("k2", "v2");
        map2.put("k3", "v3");

        MapDifference<Object, Object> mapDifference = Maps.difference(map1, map2);
        boolean b = mapDifference.areEqual();
        Map<Object, MapDifference.ValueDifference<Object>> objectValueDifferenceMap = mapDifference.entriesDiffering();
        Map<Object, Object> objectObjectMap = mapDifference.entriesInCommon();
        Map<Object, Object> objectObjectMap1 = mapDifference.entriesOnlyOnLeft();
        Map<Object, Object> objectObjectMap2 = mapDifference.entriesOnlyOnRight();

        // 验证
        Integer obj = Preconditions.checkNotNull(2);
        Preconditions.checkNotNull(2, "不能为空");
        Preconditions.checkArgument(2 > 1, "2必须要大于：%s", 1);

        // 一个key对应多个value的map
        Multimap<Object, Object> multimap = ArrayListMultimap.create();
        multimap.put("k1", "v1");
        multimap.put("k1", "v2");

        Collection<Object> k1 = multimap.get("k1");

        // concat
        Iterable<Integer> concat1 = Iterables.concat(set1, set2);

        // Strings
        String s = Strings.emptyToNull("1");
        boolean nullOrEmpty = Strings.isNullOrEmpty("");
        String s1 = Strings.padEnd("dd", 10, '_');
        String s2 = Strings.padStart("dd", 10, '_');

        String ab = Strings.repeat("ab", 3);


        try {
            //int r = 1 / 0;
        } catch (Throwable t) {
            Throwables.propagateIfPossible(t);
        }

        // RangeSet
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // [[1..10]]

        Set<Range<Integer>> ranges = rangeSet.asRanges();

        // RangeMap 范围的map
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo"); //{[1,10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar"); //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo"}
        rangeMap.put(Range.open(10, 20), "foo"); //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo", (10,20) => "foo"}
        rangeMap.remove(Range.closed(5, 11)); //{[1,3] => "foo", (3,5) => "bar", (11,20) => "foo"}

        // Collection
        Lists.newArrayListWithCapacity(10);
        Lists.newArrayListWithExpectedSize(10);

        Multimap<Object, Object> multimap1 = HashMultimap.create();

        // Iterables
        Iterables.concat(set1, set2);
        // 出现频率
        int frequency = Collections.frequency(set1, 1);
        Iterables.frequency(set1, 1);

        List<Integer> countUp = Ints.asList(1, 2, 3, 4);
        List<Integer> countDown = Lists.reverse(countUp);
        List<List<Integer>> partition = Lists.partition(countUp, 2);

        // Multimaps
        Multimap<String, Integer> multimap2 = ArrayListMultimap.create();
        multimap2.putAll("b", Ints.asList(2, 4, 6));
        multimap2.putAll("a", Ints.asList(4, 2, 1));
        multimap2.putAll("c", Ints.asList(2, 5, 3));
        TreeMultimap<Integer, String> treeMultimap = Multimaps.invertFrom(multimap2, TreeMultimap.create());

        Map<Integer, Collection<String>> invertMap = treeMultimap.asMap();

        Cache<Object, Object> cache = CacheBuilder.newBuilder().maximumSize(1)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
        cache.put("k1", "v1");
        try {
            cache.get("k1", new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    return null;
                }
            });

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        cache.invalidate("k1");

        Function<String, Integer> lengthFunc = new Function<String, Integer>() {
            @Override
            public Integer apply(String string) {
                return string.length();
            }
        };

        Predicate<String> allCaps = new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return CharMatcher.JAVA_UPPER_CASE.matchesAllOf(input);
            }
        };

        Collection<Integer> filter = Collections2.filter(Lists.newArrayList(), new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input > 0;
            }
        });

        // ListenableFuture
        Futures.addCallback(new ListenableFuture<Object>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Object get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }

            @Override
            public void addListener(Runnable listener, Executor executor) {

            }
        }, new FutureCallback<Object>() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        }, new Executor() {
            @Override
            public void execute(Runnable command) {

            }
        });

        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        ListenableFuture<String> future = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                //int i = 1/ 0;
                return "task done.";
            }
        });
        //future.addListener(new Runnable() {
        //    @Override
        //    public void run() {
        //        System.out.println("listening...");
        //    }
        //}, new Executor() {
        //    @Override
        //    public void execute(Runnable command) {
        //        command.run();
        //    }
        //});

        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("成功，结果是：" + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("业务，业务回滚");
            }
        });

        System.out.println("分支任务启动，回归主线任务。");

        Lists.newArrayList(Splitter.on(",").split(str));

        // CharMatcher字符匹配
        CharMatcher.none().replaceFrom(str, "*");

        CharMatcher.javaUpperCase().retainFrom(str);

        byte[] bytes = str.getBytes(Charsets.UTF_8);

        // CaseFormat大小写格式
        CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, str);

        // IO ByteStreams CharStreams

        // EventBus 见：EventBusTest

        // Math
        int i1 = IntMath.checkedAdd(2, 3);
        IntMath.checkedSubtract(2 ,3);
        IntMath.checkedMultiply(2 , 3);
        // 2的3次方
        int i2 = IntMath.checkedPow(2, 4);
        System.out.println(i2);

        // reflection
        TypeToken<String> stringToken = TypeToken.of(String.class);


    }
}
