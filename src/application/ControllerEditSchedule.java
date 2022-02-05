package application;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Hashtable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import db.AdministratorPageConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerEditSchedule {

	int scheduleId;

	public ControllerEditSchedule(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	@FXML
	private Label subtitle;

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
	void saveNewSchedule(ActionEvent event) {

		try {

			if (checkAllFields()) {
				if (isValidTime()) {

					String checkIn = String.valueOf(fieldCheckIn.getValue()) + ":00";
					String checkOut = String.valueOf(fieldCheckOut.getValue()) + ":00";
					AdministratorPageConnection adminDB = new AdministratorPageConnection();
					boolean success = adminDB.updateSchedule(scheduleId, fieldName.getText(), checkIn, checkOut);

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
		subtitle.setText("Modificar horario");
		AdministratorPageConnection adminDB = new AdministratorPageConnection();
		getSchedule(adminDB);
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

	void getSchedule(AdministratorPageConnection adminDB) {
		Hashtable<String, String> schedule = adminDB.getSchedule(scheduleId);
		String name = schedule.get("name");
		String strCheckInTime = schedule.get("checkInTime");
		String strCheckOutTime = schedule.get("checkOutTime");

		LocalTime checkIn = LocalTime.parse(strCheckInTime);
		LocalTime checkOut = LocalTime.parse(strCheckOutTime);

		fieldName.setText(name);
		fieldCheckIn.setValue(checkIn);
		fieldCheckOut.setValue(checkOut);

	}

}