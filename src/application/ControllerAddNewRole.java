package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerAddNewRole {

	@FXML
	private Label title;

	@FXML
	private GridPane grdAddRole;

	@FXML
	private Label lblName;

	@FXML
	private JFXTextField fieldName;

	@FXML
	private JFXButton btnCancel;

	@FXML
	private JFXButton btnAccept;

	@FXML
	void cancelExit(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void saveRole(ActionEvent event) {

		if (checkAllFields()) {
			// Guardar

			Stage stage = (Stage) btnCancel.getScene().getWindow();
			stage.close();

		} else {
			// No se han rellenado todos los campos
		}

	}

	@FXML
	void initialize() {

	}

	boolean checkAllFields() {

		try {

			if ((!fieldName.getText().isEmpty() && fieldName.getText() != null && fieldName.getText() != "")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: ControllerAddNewRole.java - checkAllFields() - " + e.toString());
			return false;
		}

	}

}
