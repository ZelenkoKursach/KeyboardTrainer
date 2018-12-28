package KeyboardTrainer.forms.general.menu;


import KeyboardTrainer.forms.common.fxml.FXMLManager;
import KeyboardTrainer.forms.common.fxml.RootWithController;
import KeyboardTrainer.forms.general.ContentArea;
import javafx.scene.Parent;


/**
 * Кнопка меню, меняющая содержимое главной формы.
 */
public class ChangeContentMenuButton extends MenuButton {
	private Parent root;
	
	public ChangeContentMenuButton(String text, String formName) {
		this(text, formName, null);
	}
	
	public ChangeContentMenuButton(String text, String formName, ContentArea controller) {
		super(text);
		
		RootWithController<ContentArea> rootWithController = FXMLManager.load(formName, controller);
		if (rootWithController.getController() != null) {
			rootWithController.getController().init();
		}
		root = rootWithController.getRoot();
	}
	
	@Override
	protected void action() {
		generalForm.setContent(root);
	}
}
