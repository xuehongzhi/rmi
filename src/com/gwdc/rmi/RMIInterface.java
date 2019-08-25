package com.gwdc.rmi;

import com.gwdc.rmi.paramters.Categories;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
	public Categories getInput(Categories categories, double sDep) throws RemoteException;
}