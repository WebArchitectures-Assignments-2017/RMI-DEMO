package interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

//remote interface for the client. Used for use the addstring method in order to add the timestamp required
public interface Document extends Remote, Serializable {

    public void addString(String stringa) throws RemoteException;
}