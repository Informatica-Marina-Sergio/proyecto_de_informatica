package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import models.CurrentUser;

public class ControllerWorkersStatisticsPage {

	double height;
	double width;
	CurrentUser currentUser;

	public ControllerWorkersStatisticsPage(double height, double width, CurrentUser currentUser) {
		this.height = height;
		this.width = width;
		this.currentUser = currentUser;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane parent;

	@FXML
	private Label subtitle;

	@FXML
	private Label lblTotalWorkers;

	@FXML
	private LineChart<String, Double> lineChartSales;

	@FXML
	private Label lblSales;

	@FXML
	private PieChart pieChartItemsAdded;

	@FXML
	private Label lblItemsAdded;

	@FXML
	private LineChart<String, Double> lineChartEarnings;

	@FXML
	private Label lblEarnings;

	@FXML
	void initialize() {

	}
}