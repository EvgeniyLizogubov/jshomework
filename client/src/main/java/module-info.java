module com.github.evgenylizogubov {
    requires javafx.controls;
    requires javafx.fxml;
    
    
    opens com.github.evgenylizogubov to javafx.fxml;
    exports com.github.evgenylizogubov;
}