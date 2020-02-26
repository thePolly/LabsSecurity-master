package ru;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SocketProxy p = new SocketProxyImpl("SSL");
        p.accept();
    }
}
