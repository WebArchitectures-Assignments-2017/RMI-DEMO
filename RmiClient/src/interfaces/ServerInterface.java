package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
//remote interface for the server
public interface ServerInterface extends Remote {

    public void addTimestamp(Document document) throws RemoteException;
}
