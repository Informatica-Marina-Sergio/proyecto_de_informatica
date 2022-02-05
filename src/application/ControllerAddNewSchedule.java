package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import db.AdministratorPageConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerAddNewSchedule {

	@FXML
	private GridPane grdAddSchedule;

	@FXML
	private Label lblName;

	@FXML
	private Label lblCheckIn;

	@FXML
	private Label lblCheckOut;

	@FXML
	private JFXTextField fieldName;

	@FXML
	private JFXTimePicker fieldCheckIn;

	@FXML
	private JFXTimePicker fieldCheckOut;

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
	void saveNewUser(ActionEvent event) {

		try {

			if (checkAllFields()) {
				if (isValidTime()) {
					// Guardar

					Stage stage = (Stage) btnCancel.getScene().getWindow();
					stage.close();

				} else {
					// Horario inv�lido (entrada > salida)
				}
			} else {
				// No ha rellenado todos los campos
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@FXML
	void initialize() {

	}

	boolean checkAllFields() {

		try {

			if ((!fieldName.getText().isEmpty() && fieldName.getText() != null && fieldName.getText() != "")
					&& (!fieldCheckIn.getValue().toString().isEmpty() && fieldCheckIn.getValue() != null
							&& fieldCheckIn.getValue().toString() != "")
					&& (!fieldCheckOut.getValue().toString().isEmpty() && fieldCheckOut.getValue() != null
							&& fieldCheckOut.getValue().toString() != "")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - checkAllFields() - " + e.toString());
			return false;
		}
	}

	boolean isValidTime() {

		try {
			if (fieldCheckOut.getValue().isAfter(fieldCheckIn.getValue())) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}