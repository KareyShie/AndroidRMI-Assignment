package rmi;

import java.rmi.RemoteException;

public class ToyComputeTaskRegistry {
    public static void main(String[] args) throws RemoteException {
        ToyComputeEngine toyComputeEngine = new ToyComputeEngine();
    }
}
