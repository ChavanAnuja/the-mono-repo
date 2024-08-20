package stage_master;

import javafx.stage.Stage;

public class StageMaster {
	
	public static Stage stage;
	public static Stage getStage() {
		return stage;
	}
	public static void setStage(Stage stage) {
		StageMaster.stage=stage;
	}

}
