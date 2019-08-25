package com.gwdc.rmi.paramters;


import java.io.Serializable;

import java.util.Random;
import java.util.function.DoubleSupplier;
import java.util.stream.DoubleStream;
import java.util.zip.Deflater;


public class Curve  implements Serializable {
    private static final long serialVersionUID = -1160770612193963099L;

    private String name;
    private String redirection;
    private float[] values;

    public float[]  getValues() {
        return values;
    }


    public void setValues(float[] s) {
        values = s;
    }

    public Curve(String name) {
        this.name = name;
        this.values = null;
    }

    public String getRedirection() {
        return redirection;
    }

    public void setRedirection(String redirection) {
        this.redirection = redirection;
    }
}
