import java.net.*;
import java.util.*;

public class UDPServer {
    public static void main(String[] args){
        try{
            DatagramSocket serverSocket = new DatagramSocket(5000);
            System.out.println("Server started... Waiting for client message...");
            
            byte[] receiveData = new byte[1024];
            byte[] sendData;
            
            Scanner sc = new Scanner(System.in);
            
             while (true) {
                // Receive message from client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                
                String clientMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Client: " + clientMsg);
                
                // If client says 'bye' then exit
                if (clientMsg.equalsIgnoreCase("bye")) {
                    System.out.println("Client ended the chat.");
                    break;
                }
                
                // Get reply from server user
                System.out.print("You(Server): ");
                String reply = sc.nextLine();

                // Send reply back to client
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                sendData = reply.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
             }   
             serverSocket.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
             
    }
}
