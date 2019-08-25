package com.gwdc.rmi;

public class RMIConfig {
    private String server = "localhost";
    private int port = 1277;
    private  String serviceName = "gwdcDataService";

    private static RMIConfig ourInstance = new RMIConfig();

    public static RMIConfig getDefault() {
        return ourInstance;
    }

    public int getPort() {
        return port;
    }

    private RMIConfig() {

    }

    public String getServerURL() {
        return String.format("//%s:%d/%s", server, port, serviceName);
    }
}
