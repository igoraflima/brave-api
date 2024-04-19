package com.brave.braveapi.util;

import org.apache.commons.lang3.StringUtils;

public class InternalValidationUtils {

    public static boolean isNull(Object obj) { return obj == null; }

    public static boolean isNotNull(Object obj) { return !isNull(obj); }

    public static boolean isBlank(String text) { return StringUtils.isBlank(text); }

    public static boolean isNotBlank(String text) { return !isBlank(text); }
}
