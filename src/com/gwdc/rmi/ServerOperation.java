package com.gwdc.rmi;

import com.gwdc.rmi.paramters.Categories;
import com.gwdc.rmi.paramters.DataFaker;

import java.nio.ByteBuffer;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.stream.DoubleStream;

public class ServerOperation extends UnicastRemoteObject implements RMIInterface{
	private static final long serialVersionUID = 1L;

	protected ServerOperation() throws RemoteException {
		super();
	}


	
	public static void main(String[] args){
		try {
			LocateRegistry.createRegistry(RMIConfig.getDefault().getPort());
			Naming.rebind(RMIConfig.getDefault().getServerURL(), new ServerOperation());
            System.err.println("Server ready");
            
        } catch (Exception e) {
        	System.err.println("Server exception: " + e.toString());
          e.printStackTrace();
        }
	}


	@Override
	public Categories getInput(Categories categories, double sDep) throws RemoteException {

        fakeData(categories, sDep);
		return categories;
	}

	private ByteBuffer getBytes(DoubleStream ds, int count ) {
		ByteBuffer buffer = ByteBuffer.allocate((int) (count * Double.BYTES));
		ds.forEach(buffer::putDouble);
		return  buffer;
	}

	private int getDataCount(Categories categories) {
		return categories.getCount() / 10;
	}
	private void fakeData(Categories categories, double sDep){
		int count = getDataCount(categories);

		categories.setStartDepth(sDep);
		categories.setEndDepth(sDep + categories.getDepthLevel() * count);

		categories.getCategories().forEach(category -> {
			category.getCurves().forEach(curve -> curve.setValues(DataFaker.getInstance().getValues(categories)));
		});
	}

}
