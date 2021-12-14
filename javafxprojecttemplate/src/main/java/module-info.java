module test1 {
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires java.desktop;
	requires javafx.fxml;
	
	opens com.florianingerl.javafx.test1 to javafx.graphics, javafx.fxml;
	
}