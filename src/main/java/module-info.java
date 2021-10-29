module project.dictionaryproject {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires org.kordamp.bootstrapfx.core;
  requires javafx.graphics;
  requires java.sql;
  requires mysql.connector.java;
  requires freetts;

//    requires rt;

    opens project.dictionaryproject to javafx.fxml;
  exports project.dictionaryproject;
  exports AppControllers;
  opens AppControllers to javafx.fxml;
}