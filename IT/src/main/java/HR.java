
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Enums.RequestType;
import main.Enums.ResponseStatus;
import main.Enums.Roles;
import main.Models.Entities.Post;
import main.Models.Entities.Schedule;
import main.Models.Entities.Specialist;
import main.Models.Entities.User;
import main.Models.Entities.Vacancy;
import main.Models.TCP.Request;
import main.Models.TCP.Response;
import main.Utility.ClientSocket;
import main.Utility.ScheduleEntry;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HR {

    @FXML
    private AnchorPane profilePane;
    @FXML
    private AnchorPane schedulePane; 
    @FXML
    private AnchorPane vacancyPaneManager;
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
    private TableView<Vacancy> vacancyTable;

    @FXML
    private TableColumn<Vacancy, Integer> idColumn;

    @FXML
    private TableColumn<Vacancy, String> positionColumn;

    @FXML
    private TableColumn<Vacancy, Integer> vacancyCountColumn;

    @FXML
    private ComboBox<String> positionComboBox;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField vacancyCountTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button clearButton;
    
    // Список для хранения вакансий
    private ObservableList<Vacancy> vacancies = FXCollections.observableArrayList();

    // Список должностей
    private ObservableList<String> positions = FXCollections.observableArrayList();
    List<Post> p = new ArrayList<Post>();
    
    @FXML
    private void initialize() throws IOException {
        profilePane.setVisible(false); // Панель профиля скрыта при запуске
        schedulePane.setVisible(false);
        vacancyPaneManager.setVisible(false);
        
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        positionColumn.setCellValueFactory(cellData -> cellData.getValue().postProperty());
        vacancyCountColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty().asObject());

        vacancyTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> fillFieldsWithSchedule(newValue));
        // Загрузка данных из БД
        
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(ClientSocket.getInstance().getUser()));
        requestModel.setRequestType(RequestType.VACANCY);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        Type listType = new TypeToken<List<Vacancy>>() {}.getType();
        List<Vacancy> e = new Gson().fromJson(responseModel.getResponseData(), listType);     	
        vacancies = FXCollections.observableArrayList(e);
        
        requestModel.setRequestMessage(new Gson().toJson(ClientSocket.getInstance().getUser()));
        requestModel.setRequestType(RequestType.POST);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        
        answer = ClientSocket.getInstance().getInStream().readLine();
        responseModel = new Gson().fromJson(answer, Response.class);
        listType = new TypeToken<List<Post>>() {}.getType();
        p = new Gson().fromJson(responseModel.getResponseData(), listType);    
        
        ArrayList<String> k = new ArrayList<String>();
       
        for(Post t: p) k.add(t.getNamePost());
        positions = FXCollections.observableArrayList(k);
        
        vacancyTable.setItems(vacancies);
        positionComboBox.setItems(positions);
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public Post getPositionByName(List<Post> positions, String name) {
        if (positions == null || name == null) {
            return null; // Если список или имя не указаны, возвращаем null
        }
        for (Post position : positions) {
            if (name.equals(position.getNamePost())) {
                return position; // Возвращаем объект, если совпадает имя
            }
        }
        return null; // Если объект с указанным именем не найден
    }
    
    @FXML
    private void addVacancy() {
        try {
            int id = Integer.parseInt(idTextField.getText());
            String position = positionComboBox.getValue();
            int vacancyCount = Integer.parseInt(vacancyCountTextField.getText());

            // Проверка уникальности ID
            if (vacancies.stream().anyMatch(vacancy -> vacancy.getId() == id)) {
                showAlert("Ошибка", "ID уже существует!");
                return;
            }
            
            
            Vacancy newVacancy = new Vacancy(id, getPositionByName(p, position) , vacancyCount);
            vacancies.add(newVacancy);
            
            Request requestModel = new Request();
            requestModel.setRequestMessage(new Gson().toJson(newVacancy));
            requestModel.setRequestType(RequestType.VACANCY_ADD);
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            
            System.out.println("Добавлено в БД: " + newVacancy);
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Неправильный формат ввода!");
        }
    }
    
    @FXML
    private void updateVacancy() {
        try {
            Vacancy selected = vacancyTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert("Ошибка", "Не выбрана строка для обновления!");
                return;
            }

            int id = Integer.parseInt(idTextField.getText());
            String position = positionComboBox.getValue();
            int vacancyCount = Integer.parseInt(vacancyCountTextField.getText());
            if (selected.getId() != id) {
            	showAlert("Ошибка", "Введенный индекс выходит за диапазон значений, либо уже используется!");
            	showAlert("Ошибка", "Неправильный формат ввода!");
            }
            // Обновление данных
            selected.setId(id);
            selected.setPost(getPositionByName(p,position));
            selected.setNumber(vacancyCount);
            vacancyTable.refresh();
            
            Request requestModel = new Request();
            requestModel.setRequestMessage(new Gson().toJson(selected));
            requestModel.setRequestType(RequestType.VACANCY_UPDATE);
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            
            System.out.println("Обновлено в БД: " + selected);
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Неправильный формат ввода!");
        }
    }
    
    @FXML
    private void deleteVacancy() {
        Vacancy selected = vacancyTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Ошибка", "Не выбрана строка для удаления!");
            return;
        }

        vacancies.remove(selected);

        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(selected));
        requestModel.setRequestType(RequestType.VACANCY_DELETE);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();

        System.out.println("Удалено из БД: " + selected);
    }

    @FXML
    private void clear() {
        idTextField.clear();
        positionComboBox.setValue(null);
        vacancyCountTextField.clear();
    }
    
    private Object fillFieldsWithSchedule(Vacancy vacancy) {
        if (vacancy != null) {
        	positionComboBox.setValue(vacancy.getPost().getNamePost());
        	idTextField.setText(String.valueOf(vacancy.getId()));
            vacancyCountTextField.setText(String.valueOf(vacancy.getNumber()));
            return vacancy;
        }
		return vacancy;       
	}
    
    @FXML
    private void profil_vis() {
        profilePane.setVisible(true);  // Включаем видимость панели профиля
        schedulePane.setVisible(false); // Отключаем видимость панели расписания
        vacancyPaneManager.setVisible(false);
        loadProfileData();
    }

    @FXML
    private void schedule_vis() {
        schedulePane.setVisible(true); // Включаем видимость панели расписания
        profilePane.setVisible(false); // Отключаем видимость панели профиля
        vacancyPaneManager.setVisible(false);
        loadScheduleData();
    }
    
    @FXML
    private void vacancy_manag_vis() {
        schedulePane.setVisible(false); // Включаем видимость панели расписания
        profilePane.setVisible(false); // Отключаем видимость панели профиля
        vacancyPaneManager.setVisible(true);
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

    private String formatSchedule(Schedule schedule) {
    	String beginTime = schedule.getBeginTime();
    	String endTime = schedule.getEndTime();
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
