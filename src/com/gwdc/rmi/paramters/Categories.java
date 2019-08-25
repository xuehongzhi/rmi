package com.gwdc.rmi.paramters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Categories implements Serializable {
    private static final long serialVersionUID = 1190476516911661470L;
    private double startDepth;
    private double endDepth;
    private double depthLevel;
    private List<Category> categories = new ArrayList<>();


    public Categories() {
    }

    public double getDepthLevel() {
        return depthLevel;
    }

    public int getCount(){
        return (int)((endDepth - startDepth + 1.5) / depthLevel);
    }

    public void setDepthLevel(double depthLevel) {
        this.depthLevel = depthLevel;
    }

    public void setStartDepth(double startDepth) {
        this.startDepth = startDepth;
    }

    public void setEndDepth(double endDepth) {
        this.endDepth = endDepth;
    }

    public double getStartDepth() {
        return startDepth;
    }

    public double getEndDepth() {
        return endDepth;
    }

    public void addCategoryItem(Category item){
        categories.add(item);
    }

    public List<Category> getCategories() {
        return categories;
    }
}
