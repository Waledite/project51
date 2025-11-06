module edu.westga.cs6241.weather_app_uwg {
    requires javafx.controls;
    requires javafx.fxml;
	requires org.apache.commons.csv;

    opens edu.westga.cs6241.weather_app_uwg to javafx.fxml, javafx.controls;
    exports edu.westga.cs6241.weather_app_uwg;
}