package com.nas.core.util;

import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayload;
import com.nas.core.exception.ExceptionPayloadFactory;

import java.util.regex.Pattern;

public interface Assert {
    ExceptionPayload payload = ExceptionPayloadFactory.INVALID_PAYLOAD.get();

    static void assertNotNull(Object value, ExceptionPayload exception) {
        if (value == null) throw new BusinessException(exception);
    }

    static void assertNotNull(Object value) {
        if (value == null) throw new BusinessException(payload);
    }
    static void assertRegex(String value, String regex) {
        if (value == null || !Pattern.compile(regex).matcher(value).matches())
            throw new BusinessException(payload);
    }

    static void isValid(Integer value, ExceptionPayload exception) {
        if (value < 1 || value > 5) throw new BusinessException(exception);
    }

    static void assertNotBlank(String value) {
        if (value == null || value.isBlank()) throw new BusinessException(payload);
    }
}
