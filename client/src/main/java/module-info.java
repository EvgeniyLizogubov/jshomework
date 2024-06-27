module com.github.evgenylizogubov {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires org.apache.logging.log4j;
    
    
    opens com.github.evgenylizogubov to javafx.fxml;
    exports com.github.evgenylizogubov;
}