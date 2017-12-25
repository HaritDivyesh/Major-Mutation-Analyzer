/*
 * Copyright (c) 2007, DoodleProject
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
package net.sf.doodleproject.numerics4j.random;

import net.sf.doodleproject.numerics4j.NumericTestCase;

/**
 * @version $Revision: 1.1 $ $Date: 2007/11/18 23:51:20 $
 */
public class ChiSquaredRandomVariableTest extends NumericTestCase {

    public void testConstructor() {
        testConstructorFailure(Double.NaN);
        testConstructorFailure(-1.0);
        testConstructorFailure(0.0);
        testConstructorSuccess(1.0);
    }

    private void testConstructorFailure(double df) {
        try {
            new ChiSquaredRandomVariable(df);
            fail("Invalid constructor values.");
        } catch (IllegalArgumentException ex) {
            // success
        }
    }

    private void testConstructorSuccess(double df) {
        try {
            new ChiSquaredRandomVariable(df);
        } catch (IllegalArgumentException ex) {
            fail("Valid constructor values.");
        }
    }

    public void testNextRandomVariable() {
        double[] a = new double[100];
        double[] b = new double[a.length];
        double x;
        
        ChiSquaredRandomVariable rv = new ChiSquaredRandomVariable();
        
        for (int i = 0; i < a.length; ++i) {
            a[i] = x = rv.nextRandomVariable();
            assertTrue(0.0 <= x);
            assertTrue(x <= Double.POSITIVE_INFINITY);
        }
        for (int i = 0; i < a.length; ++i) {
            b[i] = x = rv.nextRandomVariable();
            assertTrue(0.0 <= x);
            assertTrue(x <= Double.POSITIVE_INFINITY);
        }
        assertNotRelativelyEquals(a, b, 0.0);
    }
}
