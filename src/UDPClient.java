import java.net.*;
import java.util.*;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");

            byte[] sendData;
            byte[] receiveData = new byte[1024];

            Scanner sc = new Scanner(System.in);
            System.out.println("Connected to UDP Server!");
            System.out.println("Type your message (type 'bye' to exit):");

            while (true) {
                System.out.print("You(Client): ");
                String msg = sc.nextLine();
                sendData = msg.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 5000);
                clientSocket.send(sendPacket);

                if (msg.equalsIgnoreCase("bye")) {
                    System.out.println("Chat ended.");
                    break;
                }

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                String serverMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server: " + serverMsg);
            }

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
