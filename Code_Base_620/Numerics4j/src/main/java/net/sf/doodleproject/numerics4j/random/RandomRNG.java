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

import java.util.Random;

/**
 * A random number generator that delegates random number generation to a
 * {@link java.util.Random} object.
 * 
 * @since 1.3
 * @version $Revision: 1.3 $ $Date: 2007/11/18 23:51:19 $
 */
public class RandomRNG implements RNG {

    /** the random number generator delegate. */
    private Random delegate;

    /**
     * Create a random number generator using a default {@link java.util.Random}
     * delegate.
     */
    public RandomRNG() {
        this(new Random());
    }

    /**
     * Create a random number generator using a {@link java.util.Random}
     * delegate initialized with the given seed.
     * 
     * @param seed the seed used to initialize the {@link java.util.Random}
     *        delegate.
     */
    public RandomRNG(long seed) {
        this(new Random(seed));
    }

    /**
     * Create a random number generator using the given {@link java.util.Random}
     * delegate.
     * 
     * @param rng the {@link java.util.Random} delegate.
     */
    public RandomRNG(Random rng) {
        super();
        setDelegate(rng);
    }

    /**
     * Access the delegate value.
     * 
     * @return the delegate.
     */
    private Random getDelegate() {
        return delegate;
    }

    /**
     * Access the next random number from this generator.
     * 
     * @return the next random number.
     */
    public double nextRandomNumber() {
        return getDelegate().nextDouble();
    }

    /**
     * Modify the delegate value.
     * 
     * @param value the new delegate value.
     */
    private void setDelegate(Random value) {
        if (value == null) {
            throw new IllegalArgumentException("Random can not be null.");
        }
        this.delegate = value;
    }
}
