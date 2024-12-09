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
    private VacancyService vacancyService = new VacancyService();
    private PostService postService = new PostService();
    private DepartmentService departmentService = new DepartmentService();
    private CandidateService candidateService = new CandidateService();
    
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
                    	break;
                    }
                    case MANAGER:{
                    	Department requestDepartment = gson.fromJson(request.getRequestMessage(), Department.class);
	                    List<Specialist> allSpecialists = specialistService.findAllEntities();  
	                   List<Specialist> spec = allSpecialists.stream().filter(s -> s.getDepartment().getIdDepartment() == requestDepartment.getIdDepartment()).collect(Collectors.toList());		                    		                    
	                   response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(spec));
                    	break;
                    } 
                    case SCHEDULE_UPDATE:{
		                    Schedule requestSchedule = gson.fromJson(request.getRequestMessage(), Schedule.class);
		                    scheduleService.updateEntity(requestSchedule);
		                    break;
                    }
                    case SCHEDULE_ADD:{
                    	 Schedule requestSchedule = gson.fromJson(request.getRequestMessage(), Schedule.class);
                         scheduleService.saveEntity(requestSchedule);
                         break;
                         }
                    case SCHEDULE_DELETE:{
                    	Schedule requestSchedule = gson.fromJson(request.getRequestMessage(), Schedule.class);
                        scheduleService.deleteEntity(requestSchedule);
                        break;
                        }
                    case VACANCY:{
	                    List<Vacancy> vacanc = vacancyService.findAllEntities();        		                    
	                    response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(vacanc));
                    break;
                    }
                    case POST:{
	                    List<Post> vacanc = postService.findAllEntities();        		                    
	                    response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(vacanc));
                    break;
                    }
                    case VACANCY_ADD:{
                    	Vacancy requestSchedule = gson.fromJson(request.getRequestMessage(), Vacancy.class);
                    	vacancyService.saveEntity(requestSchedule);
                    break;}
                    case VACANCY_UPDATE:{
                    	Vacancy requestSchedule = gson.fromJson(request.getRequestMessage(), Vacancy.class);
                    	vacancyService.updateEntity(requestSchedule);
                    	break;}
                    case VACANCY_DELETE:{
                    	Vacancy requestSchedule = gson.fromJson(request.getRequestMessage(), Vacancy.class);
                    	vacancyService.deleteEntity(requestSchedule);
                    	break;}
                    case POST_ADD:{
                    	Post requestSchedule = gson.fromJson(request.getRequestMessage(), Post.class);
                    	postService.saveEntity(requestSchedule);
                    break;}
                    case POST_UPDATE:{
                    	Post requestSchedule = gson.fromJson(request.getRequestMessage(), Post.class);
                    	postService.updateEntity(requestSchedule);
                    	break;}
                    case POST_DELETE:{
                    	Post requestSchedule = gson.fromJson(request.getRequestMessage(), Post.class);
                    	postService.deleteEntity(requestSchedule);
                    	break;}
                    case DEPARTMENT:{
	                    List<Department> vacanc = departmentService.findAllEntities();        		                    
	                    response = new Response(ResponseStatus.OK, "Готово!", gson.toJson(vacanc));
                    break;}
                    case DEPARTMENT_ADD:{
                    	Department requestSchedule = gson.fromJson(request.getRequestMessage(), Department.class);
                    	departmentService.saveEntity(requestSchedule);
                    break;}
                    case DEPARTMENT_UPDATE:{
                    	Department requestSchedule = gson.fromJson(request.getRequestMessage(), Department.class);
                    	departmentService.updateEntity(requestSchedule);
                    	break;}
                    case DEPARTMENT_DELETE:{
                    	Department requestSchedule = gson.fromJson(request.getRequestMessage(), Department.class);
                    	departmentService.deleteEntity(requestSchedule);
                    	break;}
                    case CANDIDATE_ADD:{
                    	Candidate requestSchedule = gson.fromJson(request.getRequestMessage(), Candidate.class);
                    	candidateService.saveEntity(requestSchedule);
                    break;}
                    case CANDIDATE_UPDATE:{
                    	Candidate requestSchedule = gson.fromJson(request.getRequestMessage(), Candidate.class);
                    	candidateService.updateEntity(requestSchedule);
                    	break;}
                    case CANDIDATE_DELETE:{
                    	Candidate requestSchedule = gson.fromJson(request.getRequestMessage(), Candidate.class);
                    	candidateService.deleteEntity(requestSchedule);
                    	break;}
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
   
    
