package com.gwdc.rmi.paramters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category  implements Serializable {
    private static final long serialVersionUID = 3159907478403155210L;
    private String filePath;
    private List<Curve> curves = new ArrayList<>();

    public String getFilePath() {
        return filePath;
    }


    public Category(String filePath) {
        this.filePath = filePath;
    }

    public List<Curve> getCurves() {
        return curves;
    }

    public void addCurve(String curveName, String alias){
        Curve item = new Curve(curveName);
        item.setRedirection(alias);
        curves.add(item);
    }
}
