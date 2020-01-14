package com.jike.certification.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

/**
 * @author wentong
 * @date 2020-01-13
 */
public class MyBeanUtils extends BeanUtils {

    public static <S, T> T  myCopyProperties(S source, T target) throws BeansException {
        copyProperties(source, target);
        return target;
    }
}
