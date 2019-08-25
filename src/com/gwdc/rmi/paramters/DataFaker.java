package com.gwdc.rmi.paramters;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import java.util.Random;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.DoubleStream;

public class DataFaker {
    private static DataFaker ourInstance = new DataFaker();

    public static DataFaker getInstance() {
        return ourInstance;
    }

    private LinearInterpolator interplate = new LinearInterpolator();
    private static Random rand = new Random();
    private DataFaker() {

    }

    private double getRealDepth(double startDepth, double endDepth){
        return  startDepth + (endDepth - startDepth) * rand.nextDouble();
    }

    private int getRealCount(double startDepth, double endDepth, double depthLevel) {
        return (int)(Math.abs(endDepth - startDepth + 1.5)  / depthLevel);
    }

    private double getRealDepthLevel(double depthLevel) {
        return depthLevel*rand.nextInt(3) + depthLevel;
    }
    public float[] getValues(Categories categories) {
        double startDepth = getRealDepth(categories.getStartDepth(), categories.getEndDepth());
        double endDepth = getRealDepth(categories.getStartDepth(), categories.getEndDepth());

        if (startDepth > endDepth) {
            double temp = startDepth;
            startDepth = endDepth;
            endDepth = temp;
        }

        double depthLevel = getRealDepthLevel(categories.getDepthLevel());
        int count = getRealCount(startDepth, endDepth, depthLevel);

        System.out.println("count=" + count);
        double[] values =  DoubleStream.iterate(startDepth, new DoubleUnaryOperator() {
            @Override
            public double applyAsDouble(double operand) {

                return operand +  depthLevel;
            }
        }).limit(count).toArray();
        System.out.println(values.length);
        PolynomialSplineFunction interpolate = (PolynomialSplineFunction) interplate.interpolate(values, rand.doubles().limit(count).toArray());

        float[] ret = new float[categories.getCount()];
        for (int i = 0; i < values.length; i++) {
            double depth = categories.getStartDepth() +  i * categories.getDepthLevel();
            if (depth<startDepth || depth> endDepth) {
                ret[i] = -9999;
            } else{
                ret[i] = (float)interpolate.value(depth);
            }
        }
        System.out.println("Original: " + ret.length * 4 / 1024 + " Kb");
        return ret;
    }

}
