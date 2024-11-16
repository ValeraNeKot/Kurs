import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
//import main.Enums.RequestType;
//import main.Models.ResultMark;
import main.Utility.GetService;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Diagram implements Initializable {
    public Button buttonBack;
    public BarChart Chart;
    public CategoryAxis categoryAxis;
    public NumberAxis numberAxis;
    //List<ResultMark> resultMarkList;
    public void Back_Pressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Flights.fxml"));
        Scene newScene = new Scene(root);
        stage.setScene(newScene);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //GetService<ResultMark> resultMarkGetService = new GetService<>(ResultMark.class);
        //Type listType = new TypeToken<ArrayList<ResultMark>>() {
        //}.getType();
        //resultMarkList = new Gson().fromJson(resultMarkGetService.GetEntities(RequestType.CONDORCET), listType);
		/*
		 * for (ResultMark resultMark: resultMarkList) { categoryAxis.setLabel("Рейс");
		 * numberAxis.setLabel("Оценка"); XYChart.Series series = new XYChart.Series();
		 * series.setName(resultMark.getFlight().getRoute().getDeparturePoint()+"-"+
		 * resultMark.getFlight().getRoute().getArrivalPoint());
		 * series.getData().add(new XYChart.Data<>("",resultMark.getAverageMark()));
		 * Chart.getData().add(series); }
		 */

    }
}
