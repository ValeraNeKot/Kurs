package condorcet.Utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import condorcet.Enums.ResponseStatus;
import condorcet.Models.Entities.*;
import condorcet.Models.TCP.Request;
import condorcet.Models.TCP.Response;
import condorcet.Services.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class ClientThread implements Runnable {
    private Socket clientSocket;
    private Request request;
    private Response response;
    private Gson gson;
    private BufferedReader in;
    private PrintWriter out;


    private UserService userService = new UserService();
    private PersonDataService personDataService = new PersonDataService();
    private SpecialistService specialistService = new SpecialistService();
    private ScheduleService scheduleService = new ScheduleService();
    
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
                    	User requestUser = gson.fromJson(request.getRequestMessage(), User.class);
                    	requestUser.getSpecialist().setUser(requestUser);
                    	personDataService.updateEntity(requestUser.getSpecialist().getPersonData());
                    	userService.updateEntity(requestUser);
                    	///TO_DO: Придумай как обновить профиль таким образом, чтобы не поломать связи с другими таблицами
                    	///Ответ: Id не меняется, а значит - связь не теряется!
                    }
                    case MANAG:{
		                    Department requestDepartment = gson.fromJson(request.getRequestMessage(), Department.class);
		                    List<Specialist> allSpecialists = specialistService.findAllEntities();
		                    List<Specialist> spec = allSpecialists.stream().filter(s -> s.getDepartment().getIdDepartment() == requestDepartment.getIdDepartment()).collect(Collectors.toList());
		                    response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(spec));
                    }
                    case SCHEDULE_UPDATE:{
		                    Schedule requestSchedule = gson.fromJson(request.getRequestMessage(), Schedule.class);
		                    scheduleService.updateEntity(requestSchedule);
                    }
                    case SCHEDULE_ADD:{
                    	 Schedule requestSchedule = gson.fromJson(request.getRequestMessage(), Schedule.class);
                         scheduleService.saveEntity(requestSchedule);
                         }
                    case SCHEDULE_DELETE:{
                    	Schedule requestSchedule = gson.fromJson(request.getRequestMessage(), Schedule.class);
                        scheduleService.deleteEntity(requestSchedule);
                        }
                }
                out.println(gson.toJson(response));
                out.flush();        
                }}
       			catch (Exception e) {
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
   
    
