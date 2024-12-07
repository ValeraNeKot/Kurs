package condorcet.Utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import condorcet.Enums.ResponseStatus;
import condorcet.Models.Entities.*;
//import condorcet.Models.ResultMark;
import condorcet.Models.TCP.Request;
import condorcet.Models.TCP.Response;
import condorcet.Services.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class ClientThread implements Runnable {
    private Socket clientSocket;
    private Request request;
    private Response response;
    private Gson gson;
    private BufferedReader in;
    private PrintWriter out;


    private UserService userService = new UserService();
   
    private PersonDataService personDataService = new PersonDataService();

    public ClientThread(Socket clientSocket) throws IOException {
        response = new Response();
        request = new Request();
        this.clientSocket = clientSocket;
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (clientSocket.isConnected()) {
                String message = in.readLine();

                request = gson.fromJson(message, Request.class);
                switch (request.getRequestType()) {
                    /*case REGISTER: {

                        User user = gson.fromJson(request.getRequestMessage(), User.class);

                        if (userService.findAllEntities().stream().noneMatch(x -> x.getLogin().toLowerCase().equals(user.getLogin().toLowerCase()))) {
                            personDataService.saveEntity(user.getPersonData());
                            userService.saveEntity(user);
                            userService.findAllEntities();
                            User returnUser;
                            returnUser = userService.findEntity(user.getId());
                             //returnUser.setUserMarks(null);
                            response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(returnUser));
                        } else {
                            response = new Response(ResponseStatus.ERROR, "Такой пользователь уже существует!", "");
                        }
                        break;
                    }*/
                    case LOGIN: {
                    	// Инициализация администраторской учетной записи
                        DatabaseInitializer dbInitializer = new DatabaseInitializer(userService);
                        dbInitializer.initializeAdminAccount();
                        
                        User requestUser = gson.fromJson(request.getRequestMessage(), User.class);
                        if (userService.findAllEntities().stream().anyMatch(x -> x.getLogin().toLowerCase().equals(requestUser.getLogin().toLowerCase())) && userService.findAllEntities().stream().anyMatch(x -> x.getPassword().equals(requestUser.getPassword()))) {
                            User user = userService.findAllEntities().stream().filter(x -> x.getLogin().toLowerCase().equals(requestUser.getLogin().toLowerCase())).findFirst().get();
                            user = userService.findEntity(user.getIdAccount());
                            response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(user));
                        } else {
                            response = new Response(ResponseStatus.ERROR, "Такого пользователя не существует или неправильный пароль!", "");
                        }
                        break;
                    }
                    
                    case WORKER_UPDATE: {
                    	System.err.println("Типа обнова");
                    	///TO_DO: Придумай как обновить профиль таким образом, чтобы не поломать связи с другими таблицами
                    }
                }
                out.println(gson.toJson(response));
                out.flush();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Клиент " + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " закрыл соединение.");
            try {

                clientSocket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
   
    }
