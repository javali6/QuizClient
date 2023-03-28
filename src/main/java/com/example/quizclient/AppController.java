package com.example.quizclient;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class AppController {

    private static final Integer PORT = 4447;
    private Socket socket;
    private PrintWriter printWriter;

    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    private String name;

    @FXML
    private TextField answer;
    @FXML
    private TextField nick;

    @FXML
    private Button joinButton;

    @FXML
    private Button sendButton;


    @FXML
    public void initialize() {
        try {
            socket = new Socket("localhost", PORT);
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void joinGame(ActionEvent event) {
        name = nick.getText();
    }

    @FXML
    void sendAnswer(ActionEvent event) {
        String ans = answer.getText();
        String stringToSend = ans + ";" +name;
        try {
            dataOutputStream.writeUTF(stringToSend);
            dataOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
