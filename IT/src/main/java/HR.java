
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
import main.Models.Entities.Candidate;
import main.Models.Entities.Department;
import main.Models.Entities.PersonData;
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
    private AnchorPane postPaneManager;
    @FXML
    private AnchorPane departmentPaneManager;
    @FXML
    private AnchorPane candidatePaneManager;
    @FXML
    private AnchorPane specialistPaneManager;

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
    private TextField idTextField;
    @FXML
    private TextField vacancyCountTextField;
    @FXML
    private TextField idTextFieldPost;
    @FXML
    private TextField nameTextFieldPost;
    @FXML
    private TextField responsibilityTextFieldPost;
    @FXML
    private TextField idTextFieldDepartment;
    @FXML
    private TextField nameTextFieldDepartment;
    @FXML
    private TextField idTextFieldCandidate;
    @FXML
    private TextField nameTextFieldCandidate;
    @FXML
    private TextField ageTextFieldCandidate;
    @FXML 
    private TextField mailTextFieldCandidate;
    @FXML
    private TextField phoneTextFieldCandidate;
    @FXML
    private TextField skilsTextFieldCandidate;
    @FXML
    private TextField educationTextFieldCandidate;
    
    @FXML
    private Button editButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button showPasswordButton;
    @FXML
    private Button Exit;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button addButtonPost;
    @FXML
    private Button updateButtonPost;
    @FXML
    private Button deleteButtonPost;
    @FXML
    private Button clearButtonPost;
    @FXML
    private Button addButtonDepartment;
    @FXML
    private Button updateButtonDepartment;
    @FXML
    private Button deleteButtonDepartment;
    @FXML
    private Button clearButtonDepartment;    
    @FXML
    private Button addButtonCandidate;
    @FXML
    private Button updateButtonCandidate;
    @FXML
    private Button deleteButtonCandidate;
    @FXML
    private Button clearButtonCandidate;
    
    @FXML
    private TableView<Vacancy> vacancyTable;
    @FXML
    private TableView<Post> postTable;
    @FXML
    private TableView<Department> departmentTable;
    @FXML
    private TableView<Candidate> candidateTable;
    
    @FXML
    private TableColumn<Vacancy, Integer> idColumn;
    @FXML
    private TableColumn<Vacancy, String> positionColumn;
    @FXML
    private TableColumn<Vacancy, Integer> vacancyCountColumn;
    @FXML
    private TableColumn<Post, Integer> idColumnPost;
    @FXML
    private TableColumn<Post, String> nameColumn;
    @FXML
    private TableColumn<Post, String> responsibilityColumn;  
    @FXML
    private TableColumn<Department, Integer> idColumnDepartment;
    @FXML
    private TableColumn<Department, String> nameColumnDepartment;
    
    @FXML
    private TableColumn<Candidate, Integer> idColumnCandidate;
    @FXML
    private TableColumn<Candidate, String> nameColumnCandidate;
    @FXML
    private TableColumn<Candidate, Integer> ageColumnCandidate;
    @FXML
    private TableColumn<Candidate, String> mailColumnCandidate;
    @FXML
    private TableColumn<Candidate, String> phoneColumnCandidate;
    @FXML
    private TableColumn<Candidate, String> postColumnCandidate;
    @FXML
    private TableColumn<Candidate, String> skilsColumnCandidate;
    @FXML
    private TableColumn<Candidate, String> educationColumnCandidate;
    
    @FXML
    private ComboBox<String> positionComboBox;
    @FXML
    private ComboBox<String> postComboBoxCandidate;

    @FXML
    private ListView<String> scheduleListView;
    
    // Список для хранения вакансий
    private ObservableList<Vacancy> vacancies = FXCollections.observableArrayList();
    // Список должностей
    private ObservableList<String> positions = FXCollections.observableArrayList();
    private ObservableList<Post> post = FXCollections.observableArrayList();
    private ObservableList<Department> departments = FXCollections.observableArrayList();
    private ObservableList<Candidate> candidates = FXCollections.observableArrayList();
    
    List<Post> p = new ArrayList<Post>();
    
    @FXML
    private void initialize() throws IOException {
        profilePane.setVisible(false); // Панель профиля скрыта при запуске
        schedulePane.setVisible(false);
        vacancyPaneManager.setVisible(false);
        postPaneManager.setVisible(false);
        departmentPaneManager.setVisible(false);
        candidatePaneManager.setVisible(false);
        specialistPaneManager.setVisible(false);
        
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        positionColumn.setCellValueFactory(cellData -> cellData.getValue().postProperty());
        vacancyCountColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty().asObject());
        vacancyTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> fillFieldsWithSchedule(newValue));

        idColumnPost.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().postNameProperty());
        responsibilityColumn.setCellValueFactory(cellData -> cellData.getValue().responsibilityProperty());
        postTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> fillFieldsWithPost(newValue));
        
        idColumnDepartment.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumnDepartment.setCellValueFactory(cellData -> cellData.getValue().postProperty());
        departmentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> fillFieldsWithDepartment(newValue));
        
        idColumnCandidate.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameColumnCandidate.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ageColumnCandidate.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        mailColumnCandidate.setCellValueFactory(cellData -> cellData.getValue().mailProperty());
        phoneColumnCandidate.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        postColumnCandidate.setCellValueFactory(cellData -> cellData.getValue().postProperty());
        skilsColumnCandidate.setCellValueFactory(cellData -> cellData.getValue().skilsProperty());
        educationColumnCandidate.setCellValueFactory(cellData -> cellData.getValue().educationProperty());
        candidateTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> fillFieldsWithCandidate(newValue));
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
    public Vacancy getVacancyByName(List<Vacancy> positions, String name) {
        if (positions == null || name == null) {
            return null; // Если список или имя не указаны, возвращаем null
        }
        for (Vacancy position : positions) {
            if (name.equals(position.getPost().getNamePost())) {
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
   
    @FXML 
    private void addPost() {
        try {
            int id = Integer.parseInt(idTextFieldPost.getText());
            String pos = nameTextFieldPost.getText();
            String resp = responsibilityTextFieldPost.getText();

            // Проверка уникальности ID
            if (post.stream().anyMatch(po -> po.getIdPost()== id)) {
                showAlert("Ошибка", "ID уже существует!");
                return;
            }           
            
            Post newVacancy = new Post(id, pos , resp);
            post.add(newVacancy);
            
            Request requestModel = new Request();
            requestModel.setRequestMessage(new Gson().toJson(newVacancy));
            requestModel.setRequestType(RequestType.POST_ADD);
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            
            System.out.println("Добавлено в БД: " + newVacancy);
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Неправильный формат ввода!");
        }
    }    
    @FXML
    private void updatePost() {
        try {
        	Post selected = postTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert("Ошибка", "Не выбрана строка для обновления!");
                return;
            }

            int id = Integer.parseInt(idTextFieldPost.getText());
            String pos = nameTextFieldPost.getText();
            String resp = responsibilityTextFieldPost.getText();
            if (selected.getIdPost() != id) {
            	showAlert("Ошибка", "Введенный индекс выходит за диапазон значений, либо уже используется!");
            	showAlert("Ошибка", "Неправильный формат ввода!");
            }
            // Обновление данных
            selected.setIdPost(id);
            selected.setNamePost(pos);;
            selected.setResponsibility(resp);;
            postTable.refresh();
            
            Request requestModel = new Request();
            requestModel.setRequestMessage(new Gson().toJson(selected));
            requestModel.setRequestType(RequestType.POST_UPDATE);
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            
            System.out.println("Обновлено в БД: " + selected);
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Неправильный формат ввода!");
        }
    }    
    @FXML
    private void deletePost() {
    	Post selected = postTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Ошибка", "Не выбрана строка для удаления!");
            return;
        }

        post.remove(selected);

        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(selected));
        requestModel.setRequestType(RequestType.POST_DELETE);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();

        System.out.println("Удалено из БД: " + selected);
    }
    @FXML
    private void clearPost() {
    	idTextFieldPost.clear();
        nameTextFieldPost.clear();
        responsibilityTextFieldPost.clear();
    }
    
    @FXML 
    private void addDepartment() {
        try {
            int id = Integer.parseInt(idTextFieldPost.getText());
            String pos = nameTextFieldDepartment.getText();

            // Проверка уникальности ID
            if (departments.stream().anyMatch(po -> po.getIdDepartment()== id)) {
                showAlert("Ошибка", "ID уже существует!");
                return;
            }           
            
            Department newVacancy = new Department(id, pos);
            departments.add(newVacancy);
            
            Request requestModel = new Request();
            requestModel.setRequestMessage(new Gson().toJson(newVacancy));
            requestModel.setRequestType(RequestType.DEPARTMENT_ADD);
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            
            System.out.println("Добавлено в БД: " + newVacancy);
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Неправильный формат ввода!");
        }
    }    
    @FXML
    private void updateDepartment() {
        try {
        	Department selected = departmentTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert("Ошибка", "Не выбрана строка для обновления!");
                return;
            }

            int id = Integer.parseInt(idTextFieldDepartment.getText());
            String pos = nameTextFieldDepartment.getText();
            if (selected.getIdDepartment() != id) {
            	showAlert("Ошибка", "Введенный индекс выходит за диапазон значений, либо уже используется!");
            	showAlert("Ошибка", "Неправильный формат ввода!");
            }
            // Обновление данных
            selected.setIdDepartment(id);
            selected.setNameDepartment(pos);;
            postTable.refresh();
            
            Request requestModel = new Request();
            requestModel.setRequestMessage(new Gson().toJson(selected));
            requestModel.setRequestType(RequestType.DEPARTMENT_UPDATE);
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            
            System.out.println("Обновлено в БД: " + selected);
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Неправильный формат ввода!");
        }
    }    
    @FXML
    private void deleteDepartment() {
    	Department selected = departmentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Ошибка", "Не выбрана строка для удаления!");
            return;
        }

        departments.remove(selected);

        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(selected));
        requestModel.setRequestType(RequestType.DEPARTMENT_DELETE);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();

        System.out.println("Удалено из БД: " + selected);
    }
    @FXML
    private void clearDepartment() {
    	idTextFieldDepartment.clear();
        nameTextFieldDepartment.clear();
    }
    
    
    @FXML 
    private void addCandidate() throws IOException {
        try {
            int id = Integer.parseInt(idTextFieldCandidate.getText());
            String pos = postComboBoxCandidate.getValue();
            String name = nameTextFieldCandidate.getText();
            String mail = mailTextFieldCandidate.getText();
            String phone=  phoneTextFieldCandidate.getText();
            String scills= skilsTextFieldCandidate.getText();
            String educ= educationTextFieldCandidate.getText();
            int age = Integer.parseInt(ageTextFieldCandidate.getText());
        	

            // Проверка уникальности ID
            if (candidates.stream().anyMatch(po -> po.getId().getId()== id)) {
                showAlert("Ошибка", "ID уже существует!");
                return;
            }           
            
            Request requestModel = new Request();
            requestModel.setRequestMessage(new Gson().toJson(ClientSocket.getInstance().getUser()));
            requestModel.setRequestType(RequestType.VACANCY);
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            
            String answer = ClientSocket.getInstance().getInStream().readLine();
            Response responseModel = new Gson().fromJson(answer, Response.class);
            Type listType = new TypeToken<List<Vacancy>>() {}.getType();
            List<Vacancy> e = new Gson().fromJson(responseModel.getResponseData(), listType);     
            
            Candidate newVacancy = new Candidate(new PersonData(id, name,age, mail,phone), getVacancyByName(e,pos),scills,educ);
            candidates.add(newVacancy);
            
            requestModel = new Request();
            requestModel.setRequestMessage(new Gson().toJson(newVacancy));
            requestModel.setRequestType(RequestType.CANDIDATE_ADD);
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            
            System.out.println("Добавлено в БД: " + newVacancy);
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Неправильный формат ввода!");
        }
    }    
    @FXML
    private void updateCandidate() throws IOException {
        try {
        	Candidate selected = candidateTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert("Ошибка", "Не выбрана строка для обновления!");
                return;
            }

            int id = Integer.parseInt(idTextFieldCandidate.getText());
            String pos = postComboBoxCandidate.getValue();
            String name = nameTextFieldCandidate.getText();
            String mail = mailTextFieldCandidate.getText();
            String phone=  phoneTextFieldCandidate.getText();
            String scills= skilsTextFieldCandidate.getText();
            String educ= educationTextFieldCandidate.getText();
            int age = Integer.parseInt(ageTextFieldCandidate.getText());;
            if (selected.getId().getId() != id) {
            	showAlert("Ошибка", "Введенный индекс выходит за диапазон значений, либо уже используется!");
            	showAlert("Ошибка", "Неправильный формат ввода!");
            }
            
            Request requestModel = new Request();
            requestModel.setRequestMessage(new Gson().toJson(ClientSocket.getInstance().getUser()));
            requestModel.setRequestType(RequestType.VACANCY);
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            
            String answer = ClientSocket.getInstance().getInStream().readLine();
            Response responseModel = new Gson().fromJson(answer, Response.class);
            Type listType = new TypeToken<List<Vacancy>>() {}.getType();
            List<Vacancy> e = new Gson().fromJson(responseModel.getResponseData(), listType);    
            
            // Обновление данных
            selected.getId().setId(id);
            selected.getId().setAge(age);
            selected.getId().setName(name);
            selected.getId().setMail(mail);
            selected.getId().setPhoneNumber(phone);
            selected.setVacancy(getVacancyByName(e,pos));
            selected.setEducation(educ);
            selected.setSkills(scills);
            candidateTable.refresh();
            
            requestModel = new Request();
            requestModel.setRequestMessage(new Gson().toJson(selected));
            requestModel.setRequestType(RequestType.CANDIDATE_UPDATE);
            ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
            ClientSocket.getInstance().getOut().flush();
            
            System.out.println("Обновлено в БД: " + selected);
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Неправильный формат ввода!");
        }
    }    
    @FXML
    private void deleteCandidate() {
    	Candidate selected = candidateTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Ошибка", "Не выбрана строка для удаления!");
            return;
        }

        candidates.remove(selected);

        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(selected));
        requestModel.setRequestType(RequestType.CANDIDATE_DELETE);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();

        System.out.println("Удалено из БД: " + selected);
    }
    @FXML
    private void clearCandidates() {
        postComboBoxCandidate.setValue(null);
        nameTextFieldCandidate.clear();
        mailTextFieldCandidate.clear();
        phoneTextFieldCandidate.clear();
        skilsTextFieldCandidate.clear();
        educationTextFieldCandidate.clear();
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
    private Object fillFieldsWithPost(Post vacancy) {
        if (vacancy != null) {
        	idTextFieldPost.setText(String.valueOf(vacancy.getIdPost()));
        	nameTextFieldPost.setText(vacancy.getNamePost());
        	responsibilityTextFieldPost.setText(vacancy.getResponsibility());
            return vacancy;
        }
		return vacancy;       
	}
    private Object fillFieldsWithDepartment(Department vacancy) {
        if (vacancy != null) {
        	idTextFieldDepartment.setText(String.valueOf(vacancy.getIdDepartment()));
        	nameTextFieldDepartment.setText(vacancy.getNameDepartment());
            return vacancy;
        }
		return vacancy;       
	}
    private Object fillFieldsWithCandidate(Candidate vacancy) {
        if (vacancy != null) {
        	postComboBoxCandidate.setValue(vacancy.getVacancy().getPost().getNamePost());
        	idTextFieldCandidate.setText(String.valueOf(vacancy.getId().getId()));
        	nameTextFieldCandidate.setText(String.valueOf(vacancy.getId().getName()));
        	ageTextFieldCandidate.setText(String.valueOf(vacancy.getId().getAge()));
        	mailTextFieldCandidate.setText(String.valueOf(vacancy.getId().getMail()));
        	phoneTextFieldCandidate.setText(String.valueOf(vacancy.getId().getPhoneNumber()));
        	skilsTextFieldCandidate.setText(String.valueOf(vacancy.getSkills()));
        	educationTextFieldCandidate.setText(String.valueOf(vacancy.getEducation()));
            return vacancy;
        }
		return vacancy;       
	}
    
    @FXML
    private void profil_vis() {
        profilePane.setVisible(true);  // Включаем видимость панели профиля
        schedulePane.setVisible(false); // Отключаем видимость панели расписания
        vacancyPaneManager.setVisible(false);
        postPaneManager.setVisible(false);
        departmentPaneManager.setVisible(false);
        candidatePaneManager.setVisible(false);
        specialistPaneManager.setVisible(false);
        loadProfileData();
    }
    @FXML
    private void schedule_vis() {
        schedulePane.setVisible(true); // Включаем видимость панели расписания
        profilePane.setVisible(false); // Отключаем видимость панели профиля
        vacancyPaneManager.setVisible(false);
        postPaneManager.setVisible(false);
        departmentPaneManager.setVisible(false);
        candidatePaneManager.setVisible(false);
        specialistPaneManager.setVisible(false);
        loadScheduleData();
    }  
    @FXML
    private void vacancy_manag_vis() throws IOException {
        schedulePane.setVisible(false); // Включаем видимость панели расписания
        profilePane.setVisible(false); // Отключаем видимость панели профиля
        postPaneManager.setVisible(false);
        departmentPaneManager.setVisible(false);
        candidatePaneManager.setVisible(false);
        specialistPaneManager.setVisible(false);
        loadVacancy();
        vacancyPaneManager.setVisible(true);
    }
    @FXML
    private void department_manag_vis() throws IOException {
    	schedulePane.setVisible(false); // Включаем видимость панели расписания
        profilePane.setVisible(false); // Отключаем видимость панели профиля
        postPaneManager.setVisible(false);        
        candidatePaneManager.setVisible(false);
        specialistPaneManager.setVisible(false);
        vacancyPaneManager.setVisible(false);
        loadDepartment();
        departmentPaneManager.setVisible(true);
    }    
    @FXML
    private void candidate_manag_vis() throws IOException {
    	schedulePane.setVisible(false); // Включаем видимость панели расписания
        profilePane.setVisible(false); // Отключаем видимость панели профиля
        postPaneManager.setVisible(false);
        departmentPaneManager.setVisible(false);
        specialistPaneManager.setVisible(false);
        vacancyPaneManager.setVisible(false);
        loadCandidate();
        candidatePaneManager.setVisible(true);
    }
    @FXML
    private void specialist_manag_vis() {
    	schedulePane.setVisible(false); // Включаем видимость панели расписания
        profilePane.setVisible(false); // Отключаем видимость панели профиля
        postPaneManager.setVisible(false);
        departmentPaneManager.setVisible(false);
        candidatePaneManager.setVisible(false);
        specialistPaneManager.setVisible(true);
        vacancyPaneManager.setVisible(false);
    }
       @FXML
    private void post_manag_vis() throws IOException {
    	schedulePane.setVisible(false); // Включаем видимость панели расписания
        profilePane.setVisible(false); // Отключаем видимость панели профиля
        departmentPaneManager.setVisible(false);
        candidatePaneManager.setVisible(false);
        specialistPaneManager.setVisible(false);
        vacancyPaneManager.setVisible(false);
        loadPost();
        postPaneManager.setVisible(true);
    }
    
    private void loadPost() throws IOException {
    	Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(ClientSocket.getInstance().getUser()));
        requestModel.setRequestType(RequestType.POST);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        Type listType = new TypeToken<List<Post>>() {}.getType();
        List<Post> e = new Gson().fromJson(responseModel.getResponseData(), listType);     	
        post = FXCollections.observableArrayList(e);
                
        postTable.setItems(post);   	
    };  
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
    private void loadVacancy() throws IOException {
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
    	
    };
    private void loadDepartment() throws IOException {
    	Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(ClientSocket.getInstance().getUser()));
        requestModel.setRequestType(RequestType.DEPARTMENT);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        Type listType = new TypeToken<List<Department>>() {}.getType();
        List<Department> e = new Gson().fromJson(responseModel.getResponseData(), listType);     	
        departments = FXCollections.observableArrayList(e);
        departmentTable.setItems(departments);
    };
    private void loadCandidate()  throws IOException{
    	Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(ClientSocket.getInstance().getUser()));
        requestModel.setRequestType(RequestType.CANDIDATE);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        Type listType = new TypeToken<List<Candidate>>() {}.getType();
        List<Candidate> e = new Gson().fromJson(responseModel.getResponseData(), listType);     	
        candidates = FXCollections.observableArrayList(e);
        candidateTable.setItems(candidates);
    };
    
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
