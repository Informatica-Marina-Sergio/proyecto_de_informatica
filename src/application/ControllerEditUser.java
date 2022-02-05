package application;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import db.AdministratorPageConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerEditUser {

	double height;
	double width;
	int userId;

	public ControllerEditUser(double height, double width, int userId) {
		this.height = height;
		this.width = width;
		this.userId = userId;
	}

	@FXML
	private AnchorPane parent;

	@FXML
	private Label subtitle;

	@FXML
	private Label lblPersonalInfo;

	@FXML
	private GridPane grdPersonalInfo;

	@FXML
	private Label lblName;

	@FXML
	private Label lblSurname;

	@FXML
	private Label lblDni;

	@FXML
	private Label lblDob;

	@FXML
	private Label lblEmail;

	@FXML
	private Label lblEmail2;

	@FXML
	private Label lblUser;

	@FXML
	private Label lblPassword;

	@FXML
	private Label lblPassword2;

	@FXML
	private Label lblRole;

	@FXML
	private JFXTextField fieldName;

	@FXML
	private JFXTextField fieldSurname;

	@FXML
	private JFXTextField fieldDNI;

	@FXML
	private JFXDatePicker fieldDob;

	@FXML
	private JFXTextField fieldEmail;

	@FXML
	private JFXTextField fieldEmail2;

	@FXML
	private JFXTextField fieldUser;

	@FXML
	private JFXPasswordField fieldPassword;

	@FXML
	private JFXPasswordField fieldPassword2;

	@FXML
	private JFXComboBox<String> cmbRole;

	@FXML
	private Label lblSchedule;

	@FXML
	private GridPane grdSchedule;

	@FXML
	private Label lblMonday;

	@FXML
	private Label lblTuesday;

	@FXML
	private Label flblWednesday;

	@FXML
	private Label lblThursday;

	@FXML
	private Label lblFriday;

	@FXML
	private Label lblSaturday;

	@FXML
	private Label lblSunday;

	@FXML
	private JFXComboBox<String> cmbMonday;

	@FXML
	private JFXComboBox<String> cmbTuesday;

	@FXML
	private JFXComboBox<String> cmbWednesday;

	@FXML
	private JFXComboBox<String> cmbThursday;

	@FXML
	private JFXComboBox<String> cmbFriday;

	@FXML
	private JFXComboBox<String> cmbSaturday;

	@FXML
	private JFXComboBox<String> cmbSunday;

	@FXML
	private JFXButton btnAccept;

	@FXML
	private JFXButton btnCancel;

	@FXML
	void cancelExit(ActionEvent event) {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void saveNewUser(ActionEvent event) {

		try {

			boolean noEmptyFields = checkAllFields();
			boolean noEmptySchedule = checkScheduleField();
			if (noEmptyFields && noEmptySchedule) {
				boolean checkEmail = checkSecondField("email");
				boolean checkPassword = checkSecondField("password");

				if (checkEmail && checkPassword) {

					AdministratorPageConnection adminDB = new AdministratorPageConnection();
					byte[][] pass = encryptPassword(fieldPassword.getText());
					String[] schedule = new String[] { cmbMonday.getValue(), cmbTuesday.getValue(),
							cmbWednesday.getValue(), cmbThursday.getValue(), cmbFriday.getValue(),
							cmbSaturday.getValue(), cmbSunday.getValue() };
					int[] numScedule = parseSchedule(schedule, adminDB);
					boolean succes = adminDB.updateUser(userId, fieldName.getText(), fieldSurname.getText(), fieldDob.getValue().toString(),
							fieldUser.getText(), fieldDNI.getText(), fieldEmail.getText(), pass[0], pass[1],
							numScedule[0], numScedule[1], numScedule[2], numScedule[3], numScedule[4], numScedule[5],
							numScedule[6], cmbRole.getSelectionModel().getSelectedIndex());

					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
					ControllerAlertDialog control = new ControllerAlertDialog(0, 0, "Actualizaci�n correcta",
							"Los datos del usuario se ha actualizado correctamente.");
					loader.setController(control);
					Parent root = loader.load();

					Stage stage = new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setScene(new Scene(root));
					stage.show();

					stage = (Stage) btnAccept.getScene().getWindow();
					stage.close();

				} else {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
					ControllerAlertDialog control = new ControllerAlertDialog(150, 0, "Error",
							"Por favor, revise los campos, y tenga en cuenta que los campos de repetici�n (email y contrase�a), deben ser iguales.");
					loader.setController(control);
					Parent root = loader.load();

					Stage stage = new Stage();
					stage.initStyle(StageStyle.UNDECORATED);
					stage.setScene(new Scene(root));
					stage.show();
				}
			} else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/AlertDialog.fxml"));
				ControllerAlertDialog control = new ControllerAlertDialog(0, 0, "Error",
						"Es necesario que rellene todos los campos.");
				loader.setController(control);
				Parent root = loader.load();

				Stage stage = new Stage();
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setScene(new Scene(root));
				stage.show();
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@FXML
	void initialize() {

		try {

			if (height == 0) {
				height = 194;
			}
			if (width == 0) {
				width = 600;
			}
			parent.setPrefSize(width, height);
			subtitle.setText("Modificar usuario");

			AdministratorPageConnection adminDB = new AdministratorPageConnection();

			List<String> schedules = adminDB.listSchedules();
			cmbRole.getItems().removeAll(cmbRole.getItems());
			cmbRole.getItems().addAll(adminDB.listRoles());
			cmbRole.getSelectionModel().select("-");

			cmbMonday.getItems().removeAll(cmbMonday.getItems());
			cmbMonday.getItems().addAll(schedules);
			cmbMonday.getSelectionModel().select("-");

			cmbTuesday.getItems().removeAll(cmbTuesday.getItems());
			cmbTuesday.getItems().addAll(schedules);
			cmbTuesday.getSelectionModel().select("-");

			cmbWednesday.getItems().removeAll(cmbWednesday.getItems());
			cmbWednesday.getItems().addAll(schedules);
			cmbWednesday.getSelectionModel().select("-");

			cmbThursday.getItems().removeAll(cmbThursday.getItems());
			cmbThursday.getItems().addAll(schedules);
			cmbThursday.getSelectionModel().select("-");

			cmbFriday.getItems().removeAll(cmbFriday.getItems());
			cmbFriday.getItems().addAll(schedules);
			cmbFriday.getSelectionModel().select("-");

			cmbSaturday.getItems().removeAll(cmbSaturday.getItems());
			cmbSaturday.getItems().addAll(schedules);
			cmbSaturday.getSelectionModel().select("-");

			cmbSunday.getItems().removeAll(cmbSunday.getItems());
			cmbSunday.getItems().addAll(schedules);
			cmbSunday.getSelectionModel().select("-");

			List<String> list = new ArrayList<String>();
			list.add("-");

			for (int i = 0; i <= 24; i++) {
				String hour = Integer.toString(i);
				if (hour.length() < 2) {
					hour = "0" + hour;
				}
				for (int j = 0; j <= 46; j = j + 15) {
					String minute = Integer.toString(j);
					if (minute.length() < 2) {
						minute = "0" + minute;
					}
					list.add(hour + ":" + minute);
				}
			}

			getUser(adminDB);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	byte[][] encryptPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[][] pass = new byte[2][];
		
		if (!fieldPassword.getText().isEmpty() && !fieldPassword2.getText().isEmpty()) {
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();
			System.out.println(salt[0]);
			System.out.println(hash[0]);
			pass[0] = salt;
			pass[1] = hash;
		} else {
			pass[0] = null;
			pass[1] = null;
		}
		
		return pass;
	}

	int[] parseSchedule(String[] schedule, AdministratorPageConnection adminDB) {
		int[] numeric_schedule = new int[7];
		Hashtable<String, Integer> scheduleMap = adminDB.scheduleMap();
		for (int i = 0; i < schedule.length; i++) {
			numeric_schedule[i] = scheduleMap.get(schedule[i]);
		}
		return numeric_schedule;
	}

	boolean checkAllFields() {

		try {

			if ((!fieldName.getText().isEmpty() && fieldName.getText() != null && fieldName.getText() != "")
					&& (!fieldSurname.getText().isEmpty() && fieldSurname.getText() != null
							&& fieldSurname.getText() != "")
					&& (!fieldDNI.getText().isEmpty() && fieldDNI.getText() != null && fieldDNI.getText() != "")
					&& (!fieldDob.getValue().toString().isEmpty() && fieldDob.getValue().toString() != null
							&& fieldDob.getValue().toString() != "")
					&& (!fieldEmail.getText().isEmpty() && fieldEmail.getText() != null && fieldEmail.getText() != "")
					&& (!fieldEmail2.getText().isEmpty() && fieldEmail2.getText() != null
							&& fieldEmail2.getText() != "")
					&& (!fieldUser.getText().isEmpty() && fieldUser.getText() != null && fieldUser.getText() != "")
					&& (!cmbRole.getValue().toString().isEmpty() && cmbRole.getValue() != null
							&& cmbRole.getValue() != "" && cmbRole.getValue() != "-")) {
				if ((fieldPassword.getText().isEmpty() && fieldPassword2.getText().isEmpty())
						|| (!fieldPassword.getText().isEmpty() && fieldPassword.getText() != null
								&& fieldPassword.getText() != "")
								&& (!fieldPassword2.getText().isEmpty() && fieldPassword2.getText() != null
										&& fieldPassword2.getText() != "")) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - checkAllFields() - " + e.toString());
			return false;
		}
	}

	boolean checkSecondField(String field) {

		try {

			switch (field) {

			case "email":
				if (fieldEmail.getText().equals(fieldEmail2.getText())) {
					return true;
				} else {
					return false;
				}
			case "password":
				if (fieldPassword.getText().equals(fieldPassword2.getText())) {
					return true;
				} else {
					return false;
				}
			default:
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - checkSecondField() - " + e.toString());
			return false;
		}

	}

	boolean checkScheduleField() {

		try {
			if (cmbMonday.getValue().toString() != "-" && cmbTuesday.getValue().toString() != "-"
					&& cmbWednesday.getValue().toString() != "-" && cmbThursday.getValue().toString() != "-"
					&& cmbFriday.getValue().toString() != "-" && cmbSaturday.getValue().toString() != "-"
					&& cmbSunday.getValue().toString() != "-") {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("ERROR: controller_administrator.java - checkScheduleField() - " + e.toString());
			return false;
		}

	}

	void getUser(AdministratorPageConnection adminDB) {
		Hashtable<String, Object> user = adminDB.getUser(userId);

		String name = (String) user.get("name");
		String surname = (String) user.get("surname");
		Date dob = (Date) user.get("dob");
		LocalDate dobLD = Instant.ofEpochMilli(dob.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		String idNumber = (String) user.get("idNumber");
		Hashtable<Integer, Integer> schedule = (Hashtable<Integer, Integer>) user.get("schedule");
		int roleId = (int) user.get("roleId");
		String email = (String) user.get("email");
		String userName = (String) user.get("userName");

		fieldName.setText(name);
		fieldSurname.setText(surname);
		fieldDNI.setText(idNumber);
		fieldDob.setValue(dobLD);
		fieldEmail.setText(email);
		fieldEmail2.setText(email);
		fieldUser.setText(userName);
		cmbRole.getSelectionModel().select(roleId);
		cmbMonday.getSelectionModel().select(schedule.get(0));
		cmbTuesday.getSelectionModel().select(schedule.get(1));
		cmbWednesday.getSelectionModel().select(schedule.get(2));
		cmbThursday.getSelectionModel().select(schedule.get(3));
		cmbFriday.getSelectionModel().select(schedule.get(4));
		cmbSaturday.getSelectionModel().select(schedule.get(5));
		cmbSunday.getSelectionModel().select(schedule.get(6));

	}

}