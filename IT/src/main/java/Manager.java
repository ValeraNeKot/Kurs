import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Enums.RequestType;
import main.Enums.ResponseStatus;
import main.Enums.Roles;
import main.Models.Entities.Schedule;
import main.Models.Entities.Specialist;
import main.Models.Entities.User;
import main.Models.TCP.Request;
import main.Models.TCP.Response;
import main.Utility.ClientSocket;
import main.Utility.ScheduleEntry;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Manager {
	    @FXML
	    private AnchorPane profilePane;
	    @FXML
	    private AnchorPane schedulePane;
	    @FXML
	    private AnchorPane schedulePaneManager;
	    @FXML
	    private ListView<String> scheduleListView;
	    @FXML
	    private TextField loginField;
	    @FXML
	    private TextField roleField;
	    @FXML
	    private PasswordField passwordField;
	    @FXML
	    private TextField visiblePasswordField;
	    @FXML
	    private TextField nameField;
	    @FXML
	    private TextField ageField;
	    @FXML
	    private TextField emailField;
	    @FXML
	    private TextField phoneField;
	    @FXML
	    private TextField postField;
	    @FXML
	    private TextField departmentField;
	    @FXML
	    private Button editButton;
	    @FXML
	    private Button saveButton;
	    @FXML
	    private Button showPasswordButton;
	    @FXML
	    private Button Exit;

	    @FXML
	    private TableView<ScheduleEntry> scheduleTable;
	    @FXML
	    private TableColumn<ScheduleEntry, String> scheduleIdColumn;
	    @FXML
	    private TableColumn<ScheduleEntry, String> employeeIdColumn;
	    @FXML
	    private TableColumn<ScheduleEntry, String> daysColumn;
	    @FXML
	    private TableColumn<ScheduleEntry, String> startTimeColumn;
	    @FXML
	    private TableColumn<ScheduleEntry, String> endTimeColumn;
	    
	    @FXML
	    private ComboBox<String> employeeComboBox;
	    @FXML
	    private TextField daysField;
	    @FXML
	    private TextField startTimeField;
	    @FXML
	    private TextField endTimeField;
	    @FXML
	    private Button addButton;
	    @FXML
	    private Button editButtonManager;
	    @FXML
	    private Button clearButton;
	    @FXML
	    private Button deleteButton;
	    
	    private ObservableList<String> specialists;
	    private ObservableList<ScheduleEntry> scheduleEntries;
	    private List<Specialist> s;
	    
	    
	    @FXML
	    private void initialize() {
	        profilePane.setVisible(false); // Панель профиля скрыта при запуске
	        schedulePane.setVisible(false);
	        schedulePaneManager.setVisible(false);
	    }

	    @FXML
	    private void profil_vis() {
	        profilePane.setVisible(true);  // Включаем видимость панели профиля
	        schedulePane.setVisible(false); // Отключаем видимость панели расписания
	        schedulePaneManager.setVisible(false);
	        loadProfileData();
	    }

	    @FXML
	    private void schedule_vis() {
	        schedulePane.setVisible(true); // Включаем видимость панели расписания
	        profilePane.setVisible(false); // Отключаем видимость панели профиля
	        schedulePaneManager.setVisible(false);
	        loadScheduleData();
	    }
	    
	    
	    @FXML
	    private void schedule_manag_vis() throws IOException {
	        schedulePane.setVisible(false); // Включаем видимость панели расписания
	        profilePane.setVisible(false); // Отключаем видимость панели профиля
	        schedulePaneManager.setVisible(true);
	        loadScheduleDataManage();
	    }
	    
	    private void loadScheduleDataManage() throws IOException {
	    	Request requestModel = new Request();
            requestModel.setRequestMessage(new Gson().toJson(ClientSocket.getInstance().getUser().getSpecialist().getDepartment()));
            requestModel.setRequestType(RequestType.MANAG);
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            String answer = ClientSocket.getInstance().getInStream().readLine();
            Response responseModel = new Gson().fromJson(answer, Response.class);
            Type listType = new TypeToken<List<Specialist>>() {}.getType();
            s = new Gson().fromJson(responseModel.getResponseData(), listType);     
            
            for (Specialist t : s) {
                for(Schedule y: t.getSchedules())
                	scheduleEntries.add(new ScheduleEntry(Integer.toString(y.getIdSchedule()),Integer.toString( t.getPersonData().getId()), y.getDays(), y.getBeginTime().toString(),y.getEndTime().toString()));
           specialists.add(Integer.toString(t.getPersonData().getId()));
            }
                scheduleIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getScheduleId()));
            employeeIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployeeId()));
            daysColumn.setCellValueFactory(cellData -> cellData.getValue().daysProperty());
            startTimeColumn.setCellValueFactory(cellData -> cellData.getValue().startTimeProperty());
            endTimeColumn.setCellValueFactory(cellData -> cellData.getValue().endTimeProperty());
            scheduleTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> fillFieldsWithSchedule(newValue));
            employeeComboBox.setItems(FXCollections.observableArrayList(specialists));
        }
	    
		private Object fillFieldsWithSchedule(ScheduleEntry schedule) {
	        if (schedule != null) {
	            employeeComboBox.setValue(schedule.getEmployeeId());
	            daysField.setText(schedule.getDays());
	            startTimeField.setText(schedule.getStartTime());
	            endTimeField.setText(schedule.getEndTime());
	            return schedule;
	        }
			return schedule;
	        
		}

	    
	    @FXML
	    private void addSchedule() {
	        // Добавление нового графика
	    	String employeeId = employeeComboBox.getValue();
	        String days = daysField.getText();
	        String startTime = startTimeField.getText();
	        String endTime = endTimeField.getText();
	        List<Specialist> b = new ArrayList<Specialist>();
	        b.add(s.get(Integer.parseInt(employeeId)));
	        Schedule newSchedule = new Schedule(b, days, Time.valueOf(startTime), Time.valueOf(endTime));
	        
	        Request requestModel = new Request();
            requestModel.setRequestMessage(new Gson().toJson(newSchedule));
            requestModel.setRequestType(RequestType.SCHEDULE_ADD);
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
	        clearFields();
	        
	    }

	    @FXML
	    private void editSchedule() {
	        // Редактирование выбранного графика
	        ScheduleEntry selectedSchedule = scheduleTable.getSelectionModel().getSelectedItem();
	        if (selectedSchedule != null) {
	            selectedSchedule.setEmployeeId(employeeComboBox.getValue());
	            selectedSchedule.setDays(daysField.getText());
	            selectedSchedule.setStartTime(startTimeField.getText());
	            selectedSchedule.setEndTime(endTimeField.getText());
	            scheduleTable.refresh(); // Обновление таблицы
	            clearFields();
	            
	            Request requestModel = new Request();
	            requestModel.setRequestMessage(new Gson().toJson(selectedSchedule));
	            requestModel.setRequestType(RequestType.SCHEDULE_UPDATE);
	            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
	            ClientSocket.getInstance().getOut().flush();
		        clearFields();
	        }
	    }

	    @FXML
	    private void deleteSchedule() {
	        // Удаление выбранного графика
	    	ScheduleEntry selectedSchedule = scheduleTable.getSelectionModel().getSelectedItem();
	        if (selectedSchedule != null) {
	        	 Request requestModel = new Request();
		            requestModel.setRequestMessage(new Gson().toJson(selectedSchedule));
		            requestModel.setRequestType(RequestType.SCHEDULE_DELETE);
		            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
		            ClientSocket.getInstance().getOut().flush();
			        scheduleEntries.remove(selectedSchedule);
			        scheduleTable.refresh();
			        clearFields();
	        }
	    }

	    @FXML
	    private void clearFields() {
	        // Очистка полей ввода
	        employeeComboBox.setValue(null);
	        daysField.clear();
	        startTimeField.clear();
	        endTimeField.clear();
	    }
	    
	    private void loadScheduleData() {
	        if (ClientSocket.getInstance().getUser().getSpecialist() != null && ClientSocket.getInstance().getUser().getSpecialist().getSchedules() != null) {
	            List<Schedule> schedules = ClientSocket.getInstance().getUser().getSpecialist().getSchedules();
	            ObservableList<String> scheduleItems = FXCollections.observableArrayList();

	            for (Schedule schedule : schedules) {
	                String scheduleInfo = formatSchedule(schedule);
	                scheduleItems.add(scheduleInfo);
	            }

	            scheduleListView.setItems(scheduleItems);
	        }
	    }

	    /**
	     * Форматирует график для отображения в списке.
	     *
	     * @param schedule График
	     * @return Отформатированная строка
	     */
	    private String formatSchedule(Schedule schedule) {
	        Time beginTime = schedule.getBeginTime();
	        Time endTime = schedule.getEndTime();
	        String days = schedule.getDays();

	        return String.format("%s: %s - %s", days, beginTime.toString(), endTime.toString());
	    }
	    
	    @FXML
	    private void back() throws IOException {
	    	ClientSocket.getInstance().setUser(null); 
			Stage stage = (Stage) 
			Exit.getScene().getWindow(); 
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
			Scene newScene = new Scene(root); 
			stage.setScene(newScene);
	    }
	    /**
	     * Загружает данные текущего пользователя в поля.
	     */
	    private void loadProfileData() {
	        loginField.setText(ClientSocket.getInstance().getUser().getLogin());
	        roleField.setText(String.valueOf(ClientSocket.getInstance().getUser().getRole()));
	        passwordField.setText(ClientSocket.getInstance().getUser().getPassword());
	        nameField.setText(ClientSocket.getInstance().getUser().getSpecialist().getPersonData().getName());
	        ageField.setText(String.valueOf(ClientSocket.getInstance().getUser().getSpecialist().getPersonData().getAge()));
	        emailField.setText(ClientSocket.getInstance().getUser().getSpecialist().getPersonData().getMail());
	        phoneField.setText(ClientSocket.getInstance().getUser().getSpecialist().getPersonData().getPhoneNumber());
	        postField.setText(ClientSocket.getInstance().getUser().getSpecialist().getPosition().getNamePost());
	        departmentField.setText(ClientSocket.getInstance().getUser().getSpecialist().getDepartment().getNameDepartment());
	    }

	    /**
	     * Включает редактирование текстовых полей (кроме роли).
	     */
	    @FXML
	    private void enableEditing() {
	        nameField.setEditable(true);
	        ageField.setEditable(true);
	        emailField.setEditable(true);
	        phoneField.setEditable(true);
	        passwordField.setEditable(true);
	        loginField.setEditable(true);
	        visiblePasswordField.setEditable(true);
	        editButton.setVisible(false);
	        saveButton.setVisible(true);

	    }

	    /**
	     * Сохраняет изменения профиля.
	     */
	    @FXML
	    private void saveProfile() {
	        try {
	        	ClientSocket.getInstance().getUser().getSpecialist().getPersonData().setName(nameField.getText());
	        	ClientSocket.getInstance().getUser().getSpecialist().getPersonData().setAge(Integer.parseInt(ageField.getText()));
	        	ClientSocket.getInstance().getUser().getSpecialist().getPersonData().setMail(emailField.getText());
	        	ClientSocket.getInstance().getUser().getSpecialist().getPersonData().setPhoneNumber(phoneField.getText());
	        	ClientSocket.getInstance().getUser().setLogin(loginField.getText());
	        	if(passwordField.isVisible()==true) ClientSocket.getInstance().getUser().setPassword(passwordField.getText());
	        	else ClientSocket.getInstance().getUser().setPassword(visiblePasswordField.getText());

	            //userService.updateUser(currentUser); // Сохранение в базе данных
	        	Request requestModel = new Request();
	            requestModel.setRequestMessage(new Gson().toJson(ClientSocket.getInstance().getUser()));
	            requestModel.setRequestType(RequestType.WORKER_UPDATE);
	            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
	            ClientSocket.getInstance().getOut().flush();
	            
	            nameField.setEditable(false);
	            ageField.setEditable(false);
	            emailField.setEditable(false);
	            phoneField.setEditable(false);
	            passwordField.setEditable(false);
	            loginField.setEditable(false);
	            visiblePasswordField.setEditable(false);
	            saveButton.setVisible(false);
	            editButton.setVisible(true);
	        } catch (Exception e) {
	            // Логирование или уведомление об ошибке
	            System.out.println("Ошибка при сохранении профиля: " + e.getMessage());
	        }
	    }

	    /**
	     * Управляет видимостью пароля.
	     */
	    @FXML
	    private void togglePasswordVisibility() {
	        if (passwordField.isVisible()) {
	            // Переключиться на отображение текста пароля
	            visiblePasswordField.setText(passwordField.getText());
	            visiblePasswordField.setVisible(true);
	            visiblePasswordField.setManaged(true);

	            passwordField.setVisible(false);
	            passwordField.setManaged(false);

	            showPasswordButton.setText("Скрыть");
	        } else {
	            // Переключиться на скрытие текста пароля
	            passwordField.setText(visiblePasswordField.getText());
	            passwordField.setVisible(true);
	            passwordField.setManaged(true);

	            visiblePasswordField.setVisible(false);
	            visiblePasswordField.setManaged(false);

	            showPasswordButton.setText("Показать");
	        }
	    }
}
