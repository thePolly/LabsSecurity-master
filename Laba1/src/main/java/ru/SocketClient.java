package ru;

import java.awt.*;
import java.io.OutputStream;

public interface SocketClient {
    void sendServerInfo();

    void sendImage();

    default String getParam(String[] args, String name){
        if(!name.substring(0, 2).equals("--")){
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < args.length - 1; i++) {
            if(args[i].equals(name)){
                return args[i + 1];
            }
        }
        throw new IllegalArgumentException();
    }

    static SocketClient createSocketClient(String[] args){
        for (int i = 0; i < args.length - 1; i++) {
            if(args[i].equals("--type")){
                if(args[i + 1].equals("Simple")){
                    return new SocketClientImpl(args);
                }
                else if(args[i + 1].equals("SSL")){
                    return new SSLSocketClientImpl(args);
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
        }
        return null;
    }
}
