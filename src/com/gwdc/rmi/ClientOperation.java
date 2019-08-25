package com.gwdc.rmi;

import com.gwdc.rmi.paramters.Categories;
import com.gwdc.rmi.paramters.Category;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class ClientOperation {
	private static RMIInterface look_up;

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		look_up = (RMIInterface) Naming.lookup(RMIConfig.getDefault().getServerURL());

		Categories categories = new Categories();
		categories.setStartDepth(10);
		categories.setEndDepth(5000);
		categories.setDepthLevel(0.025);
		Category category = new Category("test");
		for (int i = 0; i < 4; i++) {
			category.addCurve("GR"+ i , "GR1" + i);
		}

		categories.addCategoryItem(category);
		long startTime = System.currentTimeMillis();

		Categories subCategories = look_up.getInput(categories, categories.getStartDepth());
		System.out.println((System.currentTimeMillis() - startTime) );
		for (int i = 0; i < categories.getCount() ; i++) {
			double start = categories.getStartDepth() + i * categories.getDepthLevel();
			if (start >= subCategories.getEndDepth()){
				System.out.println("start to retrieve data from server");
				startTime = System.currentTimeMillis();
				subCategories = look_up.getInput(categories, start);
				System.out.println((System.currentTimeMillis() - startTime) );
			}
			int index = (int) ((start - subCategories.getStartDepth()) / subCategories.getDepthLevel());
			subCategories.getCategories().forEach(c -> c.getCurves().forEach(curve -> {

				//System.out.println(curve.getValues()[index]);
			}));
		}

	}
}
