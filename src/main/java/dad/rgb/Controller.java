package dad.rgb;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Controller implements Initializable {
	
	// model
	
	private IntegerProperty red = new SimpleIntegerProperty();
	private IntegerProperty green = new SimpleIntegerProperty();
	private IntegerProperty blue = new SimpleIntegerProperty();
	private ObjectProperty<Color> color = new SimpleObjectProperty<>();
	
	// view
	
	@FXML
	private VBox view;
	
	@FXML
	private Slider redSlider;
	
	@FXML
	private Slider greenSlider;

	@FXML
	private Slider blueSlider;

	@FXML
	private Label redLabel;

	@FXML
	private Label greenLabel;

	@FXML
	private Label blueLabel;
	
	public Controller() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// bindings
		
		red.bindBidirectional(redSlider.valueProperty());
		green.bindBidirectional(greenSlider.valueProperty());
		blue.bindBidirectional(blueSlider.valueProperty());
		
		redLabel.textProperty().bind(red.asString());
		greenLabel.textProperty().bind(green.asString());
		blueLabel.textProperty().bind(blue.asString());
		
		color.bind(
			Bindings.createObjectBinding(() -> {			
				return new Color(red.get() / 255.0, green.get() / 255.0, blue.get() / 255.0, 1.0f);			
			}, red, green, blue)
		);
		
		color.addListener((o, ov, nv) -> {
			view.setBackground(Background.fill(nv));
		});

		red.set(128);
		green.set(128);
		blue.set(128);
		
	}
	
	public VBox getView() {
		return view;
	}

}
