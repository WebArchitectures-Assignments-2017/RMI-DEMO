package client;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import interfaces.Document;
import interfaces.ServerInterface;

public class DocumentClient implements Document {

    private final ArrayList<String> document = new ArrayList<String>();

   
    @Override
    //add a string in the arraylist document
    public void addString(String stringa) throws RemoteException {
        document.add(stringa);
        }
  
    @Override
    //override the method Object toString() and concatenate 
    //all the strings present in the document in order to print them
    public String toString() {
    	String out=new String();
        for (String stringa : document) {
            out=out+" "+stringa;
        }
		return out;
    }

    public static void main(String[] args) {
    	//code to take host and port separately
    	String[] adress = args[0].split(":");
        String ip = adress[0];
        int port = Integer.valueOf(adress[1]);
        //create a new document
        Document document = new DocumentClient();
        try {
        	//get regestry and remote object
            Document stub = (Document) UnicastRemoteObject.exportObject(document, 0);
            Registry registry = LocateRegistry.getRegistry(ip,port);            
            ServerInterface server = (ServerInterface) registry.lookup("ServerInterface");
            //add the string in the document passed as arguments 
            for (int i = 1; i < args.length; i++) {document.addString(args[i]);}
            server.addTimestamp(stub);
            //print the document
            System.out.println(((Document) document).toString());

        } catch (RemoteException | NotBoundException e) {
            System.err.println("Server exception"+e);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
           
        } finally {
            try {
                UnicastRemoteObject.unexportObject(document, false);
            } catch (NoSuchObjectException e) {
            }
        }
    }
}
