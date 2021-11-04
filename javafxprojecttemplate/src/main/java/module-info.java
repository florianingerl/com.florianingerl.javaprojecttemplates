module test1 {
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	
	opens com.florianingerl.javafx.test1 to javafx.graphics;
}