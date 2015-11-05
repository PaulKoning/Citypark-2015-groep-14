/**
 * Bank.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package bankclient;

public interface Bank extends java.rmi.Remote {
    public boolean transfer(int accountfrom, int accountto, double amount) throws java.rmi.RemoteException;
    public boolean transferMetPin(int accountfrom, int accountto, double amount, int pin) throws java.rmi.RemoteException;
    public boolean doCreditCheck(int accountnr, double amount) throws java.rmi.RemoteException;
}
