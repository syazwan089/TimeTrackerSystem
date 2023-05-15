module com.syazwan.timetrackersystem {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
                requires com.almasb.fxgl.all;
    
    opens com.syazwan.timetrackersystem to javafx.fxml;
    exports com.syazwan.timetrackersystem;
}