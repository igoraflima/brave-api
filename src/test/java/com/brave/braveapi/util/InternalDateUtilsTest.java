package com.brave.braveapi.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static com.brave.braveapi.util.InternalDateUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class InternalDateUtilsTest {

    @Test
    @DisplayName(value = "Unitary test method - minHoursDay()")
    void testMinHoursDay() {
        assertEquals("14/04/2024 00:00:00", parseToString(minHoursDay(new Date()), "dd/MM/yyyy HH:mm:ss"));
    }
}
