package com.nas.customer.service.util;

import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayload;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.nas.core.util.RegexExpressions.ALPHABETIC_MIN_2_CHARS;
import static com.nas.core.util.RegexExpressions.EMAIL;
import static org.junit.jupiter.api.Assertions.*;



// Test Assert
public class AssertTest {
    @Test
    @DisplayName("given null value when assertNotNull called then throw invalid payload exception")
    void should_throw_invalid_payload_exception_given_null_object() {
        ExceptionPayload exceptionPayload = ExceptionPayloadFactory.INVALID_PAYLOAD.get();

        BusinessException actualException = assertThrows(BusinessException.class,
                () -> Assert.assertNotNull(null, exceptionPayload));

        assertEquals(exceptionPayload.getMessage(), actualException.getPayload().getMessage());
    }

    @Test
    @DisplayName("given not null value when assertNotNull called then dont throw invalid payload exception")
    void should_not_throw_invalid_payload_exception_given_not_null_object() {
        assertDoesNotThrow(() -> Assert.assertNotNull("not null", ExceptionPayloadFactory.INVALID_PAYLOAD.get()));
    }

    @Test
    @DisplayName("given invalid email when assertRegex called then throw invalid payload exception")
    void should_throw_invalid_payload_exception_given_invalid_email() {
        BusinessException actualException = assertThrows(BusinessException.class,
                () -> Assert.assertRegex("invalid email", EMAIL));

        assertEquals(ExceptionPayloadFactory.INVALID_PAYLOAD.get().getMessage(), actualException.getPayload().getMessage());
    }

    @Test
    @DisplayName("given valid email when assertRegex called then dont throw invalid payload exception")
    void should_not_throw_invalid_payload_exception_given_working_email() {
        assertDoesNotThrow(() -> Assert.assertRegex("email@domain.com", EMAIL));
    }

    @Test
    @DisplayName("given invalid name when assertRegex called then throw invalid payload exception")
    void should_throw_invalid_payload_exception_given_invalid_value() {

        BusinessException actualException = assertThrows(BusinessException.class,
                () -> Assert.assertRegex("A", ALPHABETIC_MIN_2_CHARS));

        assertEquals(ExceptionPayloadFactory.INVALID_PAYLOAD.get().getMessage(), actualException.getPayload().getMessage());
    }

    @Test
    @DisplayName("given valid name when assertRegex called then dont throw invalid payload exception")
    void should_not_throw_invalid_payload_exception_given_valid_value() {
        assertDoesNotThrow(() -> Assert.assertRegex("WORKING", ALPHABETIC_MIN_2_CHARS));
    }
}
