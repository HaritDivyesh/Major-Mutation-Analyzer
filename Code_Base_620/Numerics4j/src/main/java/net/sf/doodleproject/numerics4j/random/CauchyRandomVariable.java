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
 * A random variable generator for the Cauchy distribution.
 * </p>
 * <p>
 * References:
 * <ol>
 * <li>Wikipedia contributors, "Cauchy Distribution," Wikipedia, The Free
 * Encyclopedia, <a target="_blank"
 * href="http://en.wikipedia.org/wiki/Cauchy_distribution">
 * http://en.wikipedia.org/wiki/Cauchy_distribution</a></li>
 * </ol>
 * </p>
 * 
 * @since 1.3
 * @version $Revision: 1.4 $ $Date: 2007/11/18 23:51:19 $
 */
public class CauchyRandomVariable extends AbstractContinuousRandomVariable {

    /** The median. */
    private double median;

    /** The standard deviation. */
    private double scale;

    /**
     * Default constructor. The location parameter is set to zero and the scale
     * parameter is set to one.
     */
    public CauchyRandomVariable() {
        this(0.0, 1.0);
    }

    /**
     * Create a random variable with the given location and scale parameters.
     * 
     * @param m the location parameter.
     * @param s the scale parameter.
     */
    public CauchyRandomVariable(double m, double s) {
        this(m, s, new RandomRNG());
    }

    /**
     * Create a random variable with the given parameters.
     * 
     * @param m the location parameter.
     * @param s the scale parameter.
     * @param source the source generator.
     */
    public CauchyRandomVariable(double m, double s, RNG source) {
        super(source);
        setMedian(m);
        setScale(s);
    }

    /**
     * Access the next random variable using the given generator.
     * 
     * @param m the location parameter.
     * @param s the scale parameter.
     * @param source the source generator.
     * @return the next random variable.
     */
    public static double nextRandomVariable(double m, double s, RNG source) {
        double v;
        double u1;

        do {
            u1 = source.nextRandomNumber();
            double u2 = source.nextRandomNumber();
            v = 2.0 * u2 - 1.0;
        } while (u1 * u1 + v * v >= 1);

        return (v / u1) * s + m;
    }

    /**
     * Access the location parameter.
     * 
     * @return the location parameter.
     */
    private double getMedian() {
        return median;
    }

    /**
     * Access the scale parameter.
     * 
     * @return the scale parameter.
     */
    private double getScale() {
        return scale;
    }

    /**
     * Access the next random variable from this generator.
     * 
     * @return the next random variable.
     */
    public double nextRandomVariable() {
        return nextRandomVariable(getMedian(), getScale(), getSource());
    }

    /**
     * Modify the location parameter.
     * 
     * @param m The new location parameter value.
     */
    private void setMedian(double m) {
        if (Double.isNaN(m)) {
            throw new IllegalArgumentException(
                "location parameter must be a valid number.");
        }
        this.median = m;
    }

    /**
     * Modify the scale parameter.
     * 
     * @param s The new scale parameter value.
     */
    private void setScale(double s) {
        if (s <= 0.0 || Double.isNaN(s)) {
            throw new IllegalArgumentException(
                "scale parameter must be positive.");
        }
        this.scale = s;
    }
}
