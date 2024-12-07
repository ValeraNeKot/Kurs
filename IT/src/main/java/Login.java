import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Enums.RequestType;
import main.Enums.ResponseStatus;
import main.Enums.Roles;
import main.Models.Entities.User;
import main.Models.TCP.Request;
import main.Models.TCP.Response;
import main.Utility.ClientSocket;

import java.io.IOException;

public class Login {
    @FXML
    private PasswordField passwordfieldPassword;
    @FXML
    private TextField textfieldLogin;

    public Button buttonLogin;
    public Label labelMessage;

    public void Login_Pressed(ActionEvent actionEvent) throws IOException {
        User user = new User();
        user.setLogin(textfieldLogin.getText());
        user.setPassword(passwordfieldPassword.getText());
        Request requestModel = new Request();
        requestModel.setRequestMessage(new Gson().toJson(user));
        requestModel.setRequestType(RequestType.LOGIN);
        ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
        ClientSocket.getInstance().getOut().flush();
        
        String answer = ClientSocket.getInstance().getInStream().readLine();
        Response responseModel = new Gson().fromJson(answer, Response.class);
        System.out.println("Получилось1");
        if (responseModel.getResponseStatus() == ResponseStatus.OK) {
            labelMessage.setVisible(false);
            System.out.println("Получилось2");
			ClientSocket.getInstance().setUser(new Gson().fromJson(responseModel.getResponseData(), User.class)); 
			Stage stage = (Stage) buttonLogin.getScene().getWindow(); 
			Parent root; root = FXMLLoader.load(getClass().getResource("/Worker.fxml"));
			Scene newScene = new Scene(root); 
			stage.setScene(newScene);
			 
        } else {
            labelMessage.setVisible(true);
            System.out.println("НЕ получилось");
        }
    }

	/*
	 * public void Register_Pressed(ActionEvent actionEvent) throws IOException {
	 * Stage stage = (Stage) buttonLogin.getScene().getWindow(); Parent root =
	 * FXMLLoader.load(getClass().getResource("Register.fxml")); Scene newScene =
	 * new Scene(root); stage.setScene(newScene);
	 * 
	 * }
	 */
}
