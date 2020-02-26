package ru;

import javax.imageio.ImageIO;
import javax.net.ServerSocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        SocketProxy p = new SocketProxyImpl("SSL");
        p.accept();
        p.createNoise();
        p.sendNext();
    }
}
