/*
 * Copyright (c) 2004-2007, DoodleProject
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 * 
 * Neither the name of DoodleProject nor the names of its
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
 * THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */
package net.sf.doodleproject.numerics4j;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

public abstract class NumericTestCase extends TestCase {

    public static void assertNotRelativelyEquals(double[] expected,
        double[] actual, double relativeError) {
        assertNotRelativelyEquals(null, expected, actual, relativeError);
    }

    public static void assertNotRelativelyEquals(int[] expected,
        int[] actual) {
        assertNotRelativelyEquals(null, expected, actual);
    }

    public static void assertNotRelativelyEquals(String msg, double[] expected,
        double[] actual, double relativeError) {
        if (expected == null) {
            assertNotNull(msg, actual);
        } else if (actual == null) {
            assertNotNull(msg, expected);
        } else {
            if (expected.length == actual.length) {
                int index = -1;
                for (int i = 0; i < expected.length && index < 0; ++i) {
                    try {
                        assertRelativelyEquals(expected[i], actual[i], relativeError);
                    } catch (AssertionFailedError ex) {
                        index = i;
                    }
                }
                if (index < 0) {
                    // all element matched; force an assertion failure
                    assertFalse(msg, expected[0] == actual[0]);
                }
            }
        }
    }

    public static void assertNotRelativelyEquals(String msg, int[] expected,
        int[] actual) {
        if (expected == null) {
            assertNotNull(msg, actual);
        } else if (actual == null) {
            assertNotNull(msg, expected);
        } else {
            if (expected.length == actual.length) {
                int index = -1;
                for (int i = 0; i < expected.length && index < 0; ++i) {
                    try {
                        assertEquals(expected[i], actual[i]);
                    } catch (AssertionFailedError ex) {
                        index = i;
                    }
                }
                if (index < 0) {
                    // all element matched; force an assertion failure
                    assertFalse(msg, expected[0] == actual[0]);
                }
            }
        }
    }

    public static void assertRelativelyEquals(double expected, double actual,
        double relativeError) {
        assertRelativelyEquals(null, expected, actual, relativeError);
    }

    public static void assertRelativelyEquals(double[] expected,
        double[] actual, double relativeError) {
        assertRelativelyEquals(null, expected, actual, relativeError);
    }

    public static void assertRelativelyEquals(String msg, double expected,
        double actual, double relativeError) {
        if (Double.isNaN(expected)) {
            assertTrue(msg, Double.isNaN(actual));
        } else if (Double.isNaN(actual)) {
            assertTrue(msg, Double.isNaN(expected));
        } else if (Double.isInfinite(actual) || Double.isInfinite(expected)) {
            assertEquals(expected, actual, relativeError);
        } else if (expected == 0.0) {
            assertEquals(msg, actual, expected, relativeError);
        } else {
            double x = Math.abs((expected - actual) / expected);
            assertEquals(msg, 0.0, x, relativeError);
        }
    }

    public static void assertRelativelyEquals(String msg, double[] expected,
        double[] actual, double relativeError) {

        if (expected == null) {
            assertNull(msg, actual);
        } else if (actual == null) {
            assertNull(msg, expected);
        } else {
            assertEquals(msg, expected.length, actual.length);
            for (int i = 0; i < expected.length; ++i) {
                assertRelativelyEquals(msg, expected[i], actual[i],
                    relativeError);
            }
        }
    }
}
