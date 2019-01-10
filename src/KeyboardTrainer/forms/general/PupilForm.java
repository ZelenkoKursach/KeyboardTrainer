package KeyboardTrainer.forms.general;


import KeyboardTrainer.Session;
import KeyboardTrainer.data.exercise.Exercise;
import KeyboardTrainer.data.exercise.ExerciseDAO;
import KeyboardTrainer.data.statistics.StatisticsDAO;
import KeyboardTrainer.forms.common.fxml.FXMLManager;
import KeyboardTrainer.forms.common.fxml.RootWithController;
import KeyboardTrainer.forms.controllers.exercise.player.ExercisePlayerController;
import KeyboardTrainer.forms.controllers.exercise.tree.ExerciseChooserController;
import KeyboardTrainer.forms.controllers.statistics.AverageStatisticsController;
import KeyboardTrainer.forms.general.menu.ChangeContentMenuButton;
import KeyboardTrainer.forms.general.menu.CustomActionMenuButton;
import javafx.stage.Stage;


public class PupilForm extends GeneralForm {
	public PupilForm() {
		setTitle("Лучший клавиатурный тренажёр");
		addMenuButton(
				new CustomActionMenuButton("Продолжить", this::continueAction));
		addMenuButton(new ChangeContentMenuButton("Выбор упражнения", "ExerciseGeneral",
		                                          ExerciseChooserController::new));
		addMenuButton(new ChangeContentMenuButton("Статистика", "AverageStatistics",
		                                          this::getAverageStatisticsController));
		addMenuButton(new ChangeContentMenuButton("Справка", "About"));
	}
	
	private void continueAction() {
		Exercise exercise = ExerciseDAO.getInstance().getFirstNotPassedExercise(Session.getLoggedUser().getId());
		
		RootWithController<ExercisePlayerController> load = FXMLManager.load("ExercisePlayer");
		
		Stage stage = FXMLManager.createStage(load.getRoot(), exercise.getName());
		stage.setResizable(false);
		
		load.getController().init(exercise);
		
		stage.show();
	}
	
	private AverageStatisticsController getAverageStatisticsController() {
		int userId     = Session.getLoggedUser().getId();
		var statistics = StatisticsDAO.getInstance().getAverageStatisticsForUser(userId);
		return new AverageStatisticsController(statistics);
	}
}
