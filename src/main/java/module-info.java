module com.example.ssft {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ssft to javafx.fxml;
    exports com.example.ssft;
}