package ru;

import sun.security.ssl.SSLSocketFactoryImpl;

import javax.imageio.ImageIO;
import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore", "/examplestore");
        System.setProperty("javax.net.ssl.trustStorePassword", "javajava");
        SocketClient client = SocketClient.createSocketClient(args);
        client.sendServerInfo();
        client.sendImage();
    }
}
