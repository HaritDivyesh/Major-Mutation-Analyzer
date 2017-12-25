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

/**
 * <p>
 * A random variable generator for the Bernoulli distribution.
 * </p>
 * <p>
 * References:
 * <ol>
 * <li>Wikipedia contributors, "Bernoulli Distribution," Wikipedia, The Free
 * Encyclopedia, <a target="_blank"
 * href="http://en.wikipedia.org/wiki/Bernoulli_distribution">
 * http://en.wikipedia.org/wiki/Bernoulli_distribution</a></li>
 * </ol>
 * </p>
 * 
 * @since 1.3
 * @version $Revision: 1.3 $ $Date: 2007/11/18 23:51:19 $
 */
public class BernoulliRandomVariable extends AbstractDiscreteRandomVariable {

    /** the probability of success. */
    private double probabilityOfSuccess;

    /**
     * Default constructor. The probability of success is set to 0.5.
     */
    public BernoulliRandomVariable() {
        this(0.5);
    }

    /**
     * Create a random variable with the given probability of success.
     * 
     * @param p the probability of success.
     */
    public BernoulliRandomVariable(double p) {
        this(p, new RandomRNG());
    }

    /**
     * Create a random variable with the given probability of success and source
     * generator.
     * 
     * @param p the probability of success.
     * @param source the source generator.
     */
    public BernoulliRandomVariable(double p, RNG source) {
        super(source);
        setProbabilityOfSuccess(p);
    }

    /**
     * Access the next random variable using the given generator.
     * 
     * @param p the probability of success.
     * @param source the source generator.
     * @return the next random variable.
     */
    public static int nextRandomVariable(double p, RNG source) {
        return (p <= source.nextRandomNumber()) ? 1 : 0;
    }

    /**
     * Access the probability of success.
     * 
     * @return the probability of success.
     */
    private double getProbabilityOfSuccess() {
        return probabilityOfSuccess;
    }

    /**
     * Access the next random variable from this generator.
     * 
     * @return the next random variable.
     */
    public int nextRandomVariable() {
        return nextRandomVariable(getProbabilityOfSuccess(), getSource());
    }

    /**
     * Modify the probability of success.
     * 
     * @param p the new probability of success.
     */
    private void setProbabilityOfSuccess(double p) {
        if (Double.isNaN(p) || p <= 0.0 || p >= 1.0) {
            throw new IllegalArgumentException("probability of success must"
                + "be between 0.0 and 1.0, exclusive.");
        }
        this.probabilityOfSuccess = p;
    }

}
