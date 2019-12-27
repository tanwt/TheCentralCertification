package com.jike.certification.util;

import com.jike.certification.exception.ApiRuntimeException;
import com.jike.certification.exception.ErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public final class Assert {

    private Assert() {
    }

    public static void notNull(Object object) {
        if (object == null) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR_IS_NULL);
        }
    }

    public static void notNull(Object object, ErrorCode errorCode) {
        if (object == null) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void notNull(Object... object) {
        for (Object obj : object) {
            if (obj == null) {
                throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR_IS_NULL);
            }
        }
    }

    public static void isNull(Object object, ErrorCode errorCode) {
        if (object != null) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void notEmpty(Collection col) {
        if (CollectionUtils.isEmpty(col)) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
        }
    }

    public static void isPositive(Long number) {
        if (number == null || number <= 0) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
        }
    }

    public static void isPositive(Long number, ErrorCode errorCode) {
        if (number == null || number <= 0) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void isPositive(Long... number) {
        for (Long num : number) {
            if (num == null || num <= 0) {
                throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
            }
        }
    }

    public static void isNatual(Long number) {
        if (number == null || number < 0) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
        }
    }

    public static void isNatual(Long... number) {
        for (Long num : number) {
            if (num == null || num < 0) {
                throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
            }
        }
    }

    public static void isZero(Integer number, ErrorCode errorCode) {
        if (number == null || number != 0) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void isPositive(Integer number) {
        if (number == null || number <= 0) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
        }
    }

    public static void isPositive(Integer number, ErrorCode errorCode) {
        if (number == null || number <= 0) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void isPositive(Integer... number) {
        for (Integer num : number) {
            if (num == null || num <= 0) {
                throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
            }
        }
    }

    public static void isNatual(Integer number) {
        if (number == null || number < 0) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
        }
    }

    public static void isNatual(Integer... number) {
        for (Integer num : number) {
            if (num == null || num < 0) {
                throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
            }
        }
    }

    public static void nullOrNatual(Integer number) {
        if (number != null && number < 0) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
        }
    }

    public static void nullOrNatual(Integer... number) {
        for (Integer num : number) {
            if (num != null && num < 0) {
                throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
            }
        }
    }

    public static void notEmpty(String str, ErrorCode errorCode) {
        if (StringUtils.isEmpty(str)) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void notBlank(String str) {
        if (StringUtils.isBlank(str)) {
            throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
        }
    }

    public static void notBlank(String... str) {
        for (String s : str) {
            if (StringUtils.isBlank(s)) {
                throw new ApiRuntimeException(ErrorCode.SYSTEM_PARAM_ERROR);
            }
        }
    }

    public static void notBlank(String str, ErrorCode errorCode) {
        if (StringUtils.isBlank(str)) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void notEmpty(Collection<?> col, ErrorCode errorCode) {
        if (CollectionUtils.isEmpty(col)) {
            throw new ApiRuntimeException(errorCode);
        }
    }

    public static void isTrue(boolean condition, ErrorCode errorCode) {
        if (!condition) {
            throw new ApiRuntimeException(errorCode);
        }
    }
}
