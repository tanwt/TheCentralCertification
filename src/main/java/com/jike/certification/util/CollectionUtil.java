package com.jike.certification.util;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author wentong
 */
public class CollectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionUtil.class);

    /**
     * 对list中的BigDecimal字段进行求和
     *
     * @param list
     * @param function
     * @param <T>
     * @return
     */
    public static <T> BigDecimal bigDecimalSum(List<T> list, Function<T, BigDecimal> function) {
        if (CollectionUtils.isEmpty(list)) {
            return BigDecimal.ZERO;
        }
        Optional<BigDecimal> sum = list.stream().map(function).reduce(BigDecimal::add);
        return sum.isPresent() ? sum.get() : BigDecimal.ZERO;
    }

    /**
     * 列表转化为列表
     *
     * @param list
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> List<T> transformList(List<U> list, Function<U, T> function) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(function).collect(Collectors.toList());
    }

    /**
     * 列表转为列表
     * 去重
     *
     * @param sourceList
     * @param function
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> List<T> transformListDistinct(List<U> sourceList, Function<U, T> function) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Collections.emptyList();
        }
        return sourceList.stream().map(function).distinct().collect(Collectors.toList());
    }

    /**
     * 列表转化为Map
     *
     * @param list
     * @param keyExtractor   获取key的方法
     * @param valueExtractor 获取value的方法
     * @param <K>
     * @param <U>
     * @param <V>
     * @return
     */
    public static <K, U, V> Map<K, U> toMap(List<V> list, Function<V, K> keyExtractor, Function<V, U> valueExtractor) {
        if (CollectionUtils.isEmpty(list)) {
            return Maps.newHashMap();
        }
        return list.stream().collect(Collectors.toMap(keyExtractor, valueExtractor, (k1, k2) -> k2));
    }

    public static <K, V> Map<K, V> toMap(List<V> list, Function<V, K> keyExtractor) {
        if (CollectionUtils.isEmpty(list)) {
            return Maps.newHashMap();
        }
        return list.stream().collect(Collectors.toMap(keyExtractor, v -> v, (k1, k2) -> k2));
    }

    /**
     * 列表分组map
     */
    public static <K, V> Map<K, List<V>> toMapGroupingBy(List<V> sourceList, Function<V, K> function) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Maps.newHashMap();
        }
        return sourceList.stream().collect(Collectors.groupingBy(function));
    }

    /**
     * 列表转化为Set
     *
     * @param list
     * @param valueExtractor
     * @param <U>
     * @param <V>
     * @return
     */
    public static <U, V> Set<V> toSet(List<U> list, Function<U, V> valueExtractor) {
        if (CollectionUtils.isEmpty(list)) {
            return Sets.newHashSet();
        }
        return list.stream().map(valueExtractor).collect(Collectors.toSet());
    }

    /**
     * 移除最后一组数据
     *
     * @param list
     * @param function
     * @param limit
     * @param <T>
     * @param <R>
     */
    public static <T, R> void removeLastGroup(List<? extends T> list, Function<? super T, ? extends R> function, int limit) {
        // 如果 list.size() < limit，则表示数据库里面没有更多的数据了，故不再移除最后一组数据
        if (!CollectionUtils.isEmpty(list) && list.size() == limit) {
            int index = list.size() - 1;
            R last = function.apply(list.get(index));
            if (last != null && last.equals(function.apply(list.get(0)))) {
                LOGGER.info("最后一项与第一项相等，不处理");
                return;
            }
            while (index >= 0 && last.equals(function.apply(list.get(index)))) {
                list.remove(index);
                index--;
            }
        }
    }

    public static <T> Optional<T> getLastOptinal(List<? extends T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Optional.empty();
        }

        return Optional.ofNullable(list.get(list.size() - 1));
    }

    public static <T> T getLast(List<? extends T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        return list.get(list.size() - 1);
    }
}
