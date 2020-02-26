package ru;

import javax.imageio.ImageIO;
import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Random;

public class SocketProxyImpl implements SocketProxy {
    private ServerSocketFactory serverSocketFactory;
    private ServerSocket serverSocket;

    private SocketFactory socketFactory;
    private Socket socket;

    private BufferedImage image;
    private String serverInfo;

    private String type;

    public SocketProxyImpl(String type){
        this.type = type;
    }

    @Override
    public void sendNext() {
        try {
            ImageIO.write(image, "png", socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void accept() {
        setUpSocketFactories();

        try {
            serverSocket = serverSocketFactory.createServerSocket(10000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Socket socket = serverSocket.accept();
            DataInputStream id = new DataInputStream(socket.getInputStream());
            serverInfo = id.readUTF();
            BufferedInputStream i = new BufferedInputStream(socket.getInputStream());
            image = ImageIO.read(ImageIO.createImageInputStream(i));
            try {
                String[] hostPort = serverInfo.split(";");
                this.socket = socketFactory.createSocket(hostPort[0], Integer.parseInt(hostPort[1]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpSocketFactories(){
        if(type.equals("Simple")) {
            serverSocketFactory = ServerSocketFactory.getDefault();
            socketFactory = SocketFactory.getDefault();
        }
        else if(type.equals("SSL")){
            serverSocketFactory = SSLServerSocketFactory.getDefault();
            socketFactory = SSLSocketFactory.getDefault();
        }
    }

    @Override
    public void createNoise() {
        Random noise = new Random();
        int temp = noise.nextInt() % 256;
        int p = noise.nextInt() % 101;
        for(int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                if(p > 50) {
                    image.setRGB(j, i, temp);
                }
            }
        }
    }
}
