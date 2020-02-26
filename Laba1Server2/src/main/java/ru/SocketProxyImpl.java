package ru;

import javax.imageio.ImageIO;
import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class SocketProxyImpl implements SocketProxy {


    static final int port = 10009;



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
    public void accept() {

        setUpSocketFactories();

        try {
            serverSocket = serverSocketFactory.createServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Socket socket = serverSocket.accept();
            //DataInputStream id = new DataInputStream(socket.getInputStream());
            //serverInfo = id.readUTF();
            BufferedInputStream i = new BufferedInputStream(socket.getInputStream());
            image = ImageIO.read(ImageIO.createImageInputStream(i));
            File outfile = new File("image.jpg");
            ImageIO.write(image, "png", outfile);


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


}
