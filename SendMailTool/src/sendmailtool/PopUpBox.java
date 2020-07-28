package sendmailtool;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopUpBox {

	public void SP(int num, StringBuffer log) {
		Stage stage1 = new Stage();
		Button btn = new Button();
		VBox vbox = new VBox();
		VBox vboxs = new VBox();
		HBox hbox = new HBox();
		String texts1 = null;
		ImageView imageview1 = null;
		CheckBox checks1 = new CheckBox("详细信息");
		TextArea textar1 = new TextArea();
		if (num == 1) {
			texts1 = "发送成功";
			imageview1 = new ImageView("img/Gtrue.png");
			checks1.setVisible(false);
		} else if (num == 0) {
			texts1 = "发送失败";
			imageview1 = new ImageView("img/Rfalse.png");
			textar1.setText(log.toString());
			checks1.setVisible(true);
		} else if (num == 3) {
			texts1 = "用户名或密码错误";
			imageview1 = new ImageView("img/Bfalse.png");
			textar1.setText(log.toString());
			checks1.setVisible(false);
		}

		imageview1.setFitHeight(50);
		imageview1.setFitWidth(50);
		checks1.setScaleY(0.7);
		checks1.setScaleX(0.7);
		textar1.setWrapText(true);// 设置文本内容自动换行
		Label label = new Label(texts1, imageview1);
		label.setFont(Font.font(20));
		label.setContentDisplay(ContentDisplay.LEFT);
		vbox.setSpacing(15);
		vbox.setPadding(new Insets(10));
		btn.setText("确 定");
		btn.setMaxHeight(30);
		btn.setMaxWidth(80);
		btn.setMinHeight(30);
		btn.setMinWidth(80);
		btn.setStyle(
				"-fx-background-color: linear-gradient(to right,#34ace0,#4b7bec); -fx-text-fill: white;-fx-background-radius: 25;-fx-border-radius: 25;");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage1.close();
			}
		});
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			btn.setStyle("-fx-background-color: linear-gradient(to right,#34ace0,#4b7bec); -fx-text-fill: white;");
		});
		btn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			btn.setStyle(
					"-fx-background-color: linear-gradient(to right,#34ace0,#4b7bec); -fx-text-fill: white;-fx-background-radius: 25;-fx-border-radius: 25;");
		});

		BorderPane root = new BorderPane();
		vbox.getChildren().addAll(label, btn);
		vbox.setAlignment(Pos.TOP_CENTER);
		// root.setCenter(vbox);
		hbox.getChildren().addAll(checks1);
		hbox.setAlignment(Pos.CENTER_RIGHT);
		vboxs.getChildren().add(hbox);
		vbox.getChildren().add(vboxs);
		root.setCenter(vbox);
		root.setStyle("-fx-background-radius: 25;-fx-border-radius: 22;-fx-border-color:#1F6FB5;-fx-border-width:3px;");
		Scene scene = new Scene(root, 250, 150, Color.TRANSPARENT);
		stage1.initStyle(StageStyle.TRANSPARENT);
		stage1.initModality(Modality.APPLICATION_MODAL);
		stage1.setScene(scene);
		stage1.show();
		textar1.setMinWidth(220);
		textar1.setMaxWidth(220);
		textar1.setMaxHeight(200);
		textar1.setMinHeight(200);
		textar1.setVisible(false);
		vboxs.getChildren().add(textar1);
		vboxs.setSpacing(10);
		checks1.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
//            System.out.println(old_val+"-"+new_val);
				if (new_val) {
					stage1.setHeight(370);
					// textar1
					textar1.setVisible(true);
				} else {
					textar1.setVisible(false);
					stage1.setHeight(150);
				}
			}
		});

		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				// 计算
				stage1.setX(x_stage + m.getScreenX() - x1);
				stage1.setY(y_stage + m.getScreenY() - y1);
			}
		});
		scene.setOnDragEntered(null);
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				// 按下鼠标后，记录当前鼠标的坐标
				x1 = m.getScreenX();
				y1 = m.getScreenY();
				x_stage = stage1.getX();
				y_stage = stage1.getY();
			}
		});
	}

	double x1 = 0;
	double y1 = 0;
	double x_stage = 0;
	double y_stage = 0;
}
