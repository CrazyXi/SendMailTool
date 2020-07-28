package sendmailtool;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * @author xiwood
 */
public class SendMailTool extends Application {

	private final Image off1 = new Image("img/off1.png");
	private final Image off2 = new Image("img/off2.png");
	private final Image off3 = new Image("img/off3.png");
	private final Image hide1 = new Image("img/hide1.png");
	private final Image hide2 = new Image("img/hide2.png");
	private final Image hide3 = new Image("img/hide3.png");
	private final ObservableList stmpstr = FXCollections.observableArrayList("smtp.gmail.com", "smtp.163.com",
			"smtp.126.com", "smtp.foxmail.com", "smtp.qq.com", "smtp.sohu.com", "smtp.139.com", "smtp.aliyun.com");
	private final ObservableList codingstr = FXCollections.observableArrayList("UTF-8", "UTF-16", "GBK", "GB2312",
			"ISO-8859-1", "ASCII", "Unicode");
	PopUpBox pub = new PopUpBox();

	@Override
	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		GridPane gpTitle = new GridPane();
		gpTitle.setAlignment(Pos.CENTER_LEFT);
		gpTitle.setPadding(new Insets(10));
		Label lbTitle = new Label("SendMailTool");
		ImageView CloseimageView = new ImageView(off1);
		ImageView HideimageView = new ImageView(hide1);
		HBox titlels = new HBox();

		CloseimageView.setFitHeight(21);
		CloseimageView.setFitWidth(21);
		HideimageView.setFitHeight(5);
		HideimageView.setFitWidth(30);

		gpTitle.setStyle("-fx-background-color: #1F6FB5;-fx-background-radius: 15;-fx-border-radius: 15;");
		gpTitle.add(lbTitle, 0, 0);
		titlels.getChildren().addAll(HideimageView, CloseimageView);
		titlels.setSpacing(10);
		titlels.setAlignment(Pos.CENTER_RIGHT);
		gpTitle.add(titlels, 3, 0);
		Label labeluser = new Label("用户名:");
		Label labelpwd = new Label("密码:");
		Label labeladdressee = new Label("收信人:");
		Label labelserver = new Label("服务器:");
		Label labelport = new Label("端口:");
		Label labeltitle = new Label("标题:");
		Label labeltext = new Label("正文:");
		TextField edittextuser = new TextField();// 用户名
		PasswordField passwordtext1 = new PasswordField();// 密码
		TextField edittextaddressee = new TextField();// 收信人
		ComboBox<String> editableComboBox = new ComboBox<String>();// 服务器
		TextField edittextport = new TextField();// 端口
		TextField edittexttitle = new TextField();// 标题
		TextArea textfields1 = new TextArea();// 正文
		CheckBox checks1 = new CheckBox("SSL");
		HBox hbox = new HBox();
		Button btne = new Button("发 送");
//		DropShadow shadow = new DropShadow();
		CheckBox checks2 = new CheckBox("HTML");
		ComboBox codingComboBox = ComboBoxBuilder.create().items(FXCollections.observableArrayList(codingstr)).build();
		HBox hbox2 = new HBox();
		editableComboBox.setId("second-editable");
		editableComboBox.setPromptText("");// 备注
		editableComboBox.setItems(stmpstr);
		editableComboBox.setEditable(true);
		edittextport.setPromptText("25");
		edittextport.setMaxWidth(80);
		textfields1.setMaxWidth(350);
		textfields1.setWrapText(true);// 设置文本内容自动换行
		edittextuser.setMaxWidth(200);
		edittextuser.setMaxHeight(30);
		edittextuser.setMinHeight(30);
		edittextport.setMaxHeight(30);
		edittextport.setMinHeight(30);
		passwordtext1.setMaxHeight(30);
		passwordtext1.setMinHeight(30);
		edittexttitle.setMaxHeight(30);
		edittexttitle.setMinHeight(30);
		editableComboBox.setMaxHeight(30);
		editableComboBox.setMinHeight(30);
		edittextaddressee.setMaxHeight(30);
		edittextaddressee.setMinHeight(30);
		hbox2.setSpacing(20);
		codingComboBox.setValue("UTF-8");
		codingComboBox.setMaxWidth(140);
		codingComboBox.setMinWidth(140);
		passwordtext1.setMaxWidth(200);
		edittexttitle.setMaxWidth(300);
		edittextaddressee.setMaxWidth(200);
		btne.setMaxHeight(30);
		btne.setMaxWidth(100);
		btne.setMinHeight(30);
		btne.setMinWidth(100);
		codingComboBox.setDisable(true);
		btne.setStyle(
				"-fx-background-color: linear-gradient(to right,#34ace0,#4b7bec); -fx-text-fill: white;-fx-background-radius: 25;-fx-border-radius: 25;");
		gpTitle.setMaxHeight(40);
		gpTitle.setMinHeight(40);
		GridPane.setHgrow(lbTitle, Priority.ALWAYS);
		GridPane gpane = new GridPane();
		gpane.setPadding(new Insets(10));
		gpane.setHgap(10);
		gpane.setVgap(10);
		gpane.add(labeluser, 0, 2);
		gpane.add(edittextuser, 1, 2);
		gpane.add(labelpwd, 0, 3);
		gpane.add(passwordtext1, 1, 3);
		gpane.add(labeladdressee, 0, 4);
		gpane.add(edittextaddressee, 1, 4);
		gpane.add(labelserver, 0, 5);
		gpane.add(editableComboBox, 1, 5);
		gpane.add(labelport, 0, 6);
		hbox.getChildren().addAll(edittextport, checks1);
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER_LEFT);
		gpane.add(hbox, 1, 6);
		gpane.add(labeltitle, 0, 7);
		gpane.add(edittexttitle, 1, 7);
		gpane.add(labeltext, 0, 8);
		GridPane.setValignment(labeltext, VPos.TOP);// 设置网格面板向上对齐
		gpane.add(textfields1, 1, 8);
		hbox2.getChildren().addAll(checks2, codingComboBox, btne);
		hbox2.setAlignment(Pos.CENTER_RIGHT);
		gpane.add(hbox2, 1, 9);
		GridPane.setHalignment(hbox2, HPos.RIGHT);
		checks2.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
//            System.out.println(old_val+"-"+new_val);
				if (new_val) {
					codingComboBox.setDisable(false);
				} else {
					codingComboBox.setDisable(true);
				}
			}
		});
		HideimageView.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			HideimageView.setImage(hide1);
		});
		HideimageView.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			HideimageView.setImage(hide2);
			HideimageView.setFitHeight(7);
		});
		HideimageView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			HideimageView.setImage(hide3);
			HideimageView.setFitHeight(7);
			Timeline oneSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(0.1), (ActionEvent event1) -> {
				primaryStage.setIconified(true);
			}));
			oneSecondsWonder.setCycleCount(1);// 设置循环的次数
			oneSecondsWonder.play();
		});
		CloseimageView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			CloseimageView.setImage(off3);
			Timeline oneSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(0.1), (ActionEvent event1) -> {
				primaryStage.close();
			}));
			oneSecondsWonder.setCycleCount(1);// 设置循环的次数
			oneSecondsWonder.play();
		});
		CloseimageView.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			CloseimageView.setImage(off2);
		});
		CloseimageView.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			CloseimageView.setImage(off1);
		});
		btne.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			btne.setStyle("-fx-background-color: linear-gradient(to right,#34ace0,#4b7bec); -fx-text-fill: white;");
		});
		btne.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			btne.setStyle(
					"-fx-background-color: linear-gradient(to right,#34ace0,#4b7bec); -fx-text-fill: white;-fx-background-radius: 25;-fx-border-radius: 25;");
		});
		final String styletext1 = edittextuser.getStyle();
		btne.setOnAction((ActionEvent event) -> {
			Timeline oneSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1.5), (ActionEvent event1) -> {
				edittextuser.setStyle(styletext1);
				passwordtext1.setStyle(styletext1);
				edittextaddressee.setStyle(styletext1);
				editableComboBox.setStyle(styletext1);
				edittextport.setStyle(styletext1);
				edittexttitle.setStyle(styletext1);
				textfields1.setStyle(styletext1);
				edittextuser.setPromptText("");
				passwordtext1.setPromptText("");
				edittextaddressee.setPromptText("");
				editableComboBox.setPromptText("");
				edittextport.setPromptText("25");
				edittexttitle.setPromptText("");
				textfields1.setPromptText("");
				btne.setDisable(false);
				btne.setScaleX(btne.getScaleX() - 0.05);
				btne.setScaleY(btne.getScaleY() - 0.05);
				System.out.println("Timeline------------------------");
			}));
			// oneSecondsWonder.setCycleCount(Timeline.INDEFINITE);
			oneSecondsWonder.setCycleCount(1);// 设置循环的次数
			oneSecondsWonder.play();
			btne.setScaleX(btne.getScaleX() + 0.05);
			btne.setScaleY(btne.getScaleY() + 0.05);
			// System.out.println(codingComboBox.getValue().toString());
			if (edittextuser.getText().trim().length() <= 0) {
				edittextuser.setStyle("-fx-border-color:red");
				edittextuser.setPromptText("不能为空");
				oneSecondsWonder.play();
				return;
			}
			if (!checkEmail(edittextuser.getText().trim())) {
				edittextuser.setStyle("-fx-border-color:red");
				edittextuser.setText("");
				edittextuser.setPromptText("格式错误");
				oneSecondsWonder.play();
				return;
			}
			if (passwordtext1.getText().trim().equals("")) {
				passwordtext1.setStyle("-fx-border-color:red");
				passwordtext1.setPromptText("不能为空");
				oneSecondsWonder.play();
				return;
			}
			if (edittextaddressee.getText().trim().equals("")) {
				edittextaddressee.setStyle("-fx-border-color:red");
				edittextaddressee.setPromptText("不能为空");
				oneSecondsWonder.play();
				return;
			}
			if (!checkEmail(edittextaddressee.getText().trim())) {
				edittextaddressee.setStyle("-fx-border-color:red");
				edittextaddressee.setText("");
				edittextaddressee.setPromptText("格式错误");
				oneSecondsWonder.play();
				return;
			}
			if (editableComboBox.getValue() == null) {
				editableComboBox.setStyle("-fx-border-color:red");
				editableComboBox.setPromptText("不能为空");
				oneSecondsWonder.play();
				return;
			}
			if (edittextport.getText().trim().equals("")) {
				edittextport.setStyle("-fx-border-color:red");
				edittextport.setPromptText("不能为空");
				oneSecondsWonder.play();
				return;
			}
			if (edittexttitle.getText().trim().equals("")) {
				edittexttitle.setStyle("-fx-border-color:red");
				edittexttitle.setPromptText("不能为空");
				oneSecondsWonder.play();
				return;
			}
			if (!textfields1.getText().trim().equals("")) {
				btne.setDisable(true);
				sendmail(edittextuser.getText(), passwordtext1.getText(), edittextaddressee.getText(),
						editableComboBox.getValue(), edittextport.getText(), edittexttitle.getText(),
						textfields1.getText(), checks1.isSelected(), checks2.isSelected(),
						codingComboBox.getValue().toString());
			} else {
				textfields1.setStyle("-fx-border-color:red");
				textfields1.setPromptText("不能为空");
				oneSecondsWonder.play();
				return;
			}
		});

		root.setTop(gpTitle);
		root.setCenter(gpane);
		Scene scene = new Scene(root, 450, 600, Color.TRANSPARENT);
		root.setStyle("-fx-background-radius: 25;-fx-border-radius: 22;-fx-border-color:#1F6FB5;-fx-border-width:3px;");
		primaryStage.initStyle(StageStyle.TRANSPARENT);// 设定窗口无边框
//		primaryStage.setAlwaysOnTop(true);// 置顶
		primaryStage.getIcons().add(new Image("img/smico.png"));// 设置窗口图标
		primaryStage.setOpacity(0.98);// 设置窗口透明度
		primaryStage.setScene(scene);
		primaryStage.show();
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				// 计算
				primaryStage.setX(x_stage + m.getScreenX() - x1);
				primaryStage.setY(y_stage + m.getScreenY() - y1);
			}
		});
		scene.setOnDragEntered(null);
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				// 按下鼠标后，记录当前鼠标的坐标
				x1 = m.getScreenX();
				y1 = m.getScreenY();
				x_stage = primaryStage.getX();
				y_stage = primaryStage.getY();
			}
		});
	}

	double x1 = 0;
	double y1 = 0;
	double x_stage = 0;
	double y_stage = 0;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	public void sendmail(final String user, final String password, String addressee, String host, String port,
			String headline, String text, boolean onssl, boolean ishtml, String coding) {
		Properties properties = System.getProperties(); // 获取系统属性
		properties.put("mail.smtp.host", host); // 设置邮件服务器
		properties.put("mail.smtp.port", port); // 端口号 //设置邮件端口
		properties.put("mail.transport.protocol", "smtp");// 必须选择协议
		properties.put("mail.smtp.auth", "true");// 设置邮件是否验证
		// SSL加密连接容易延迟
		properties.put("mail.smtp.ssl.enable", "" + onssl + "");// 设置是否使用ssl安全连接 setProperty
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		};
		Session session = Session.getInstance(properties, auth);
		session.setDebug(true);// 设置是否显示debug信息 true 会在控制台显示相关信息
		MimeMessage message = new MimeMessage(session);
		try {// 邮件账号校验
			Transport ts = session.getTransport();
			ts.connect();// user, password
		} catch (MessagingException au) {
			pub.SP(3, resb(au.getStackTrace(), au));
			return;
		}

		try {
			String nick = "";
			try {
				nick = javax.mail.internet.MimeUtility.encodeText(user); // 设置发送人的自定义名称
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			message.setFrom(new InternetAddress(nick + "<" + user + ">"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(addressee));// 设置收件人
			message.setSubject(headline);// 设置邮件标题
			if (ishtml) {
				message.setContent(text, "text/html;charset=" + coding);// 设置正文编码格式
			} else {
				message.setText(text);
			}
			Transport.send(message);// 发送邮件
			pub.SP(1, null);
			System.out.println("Sent message successfully....from runoob.com----邮件已发送s");
		} catch (MessagingException mex) {
			mex.printStackTrace();
			pub.SP(0, resb(mex.getStackTrace(), mex));
		}
	}

	public boolean checkEmail(String email) {// 邮箱账号校验方法
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public StringBuffer resb(Object[] obj, MessagingException mse) {
		StringBuffer sb = new StringBuffer();
		sb.append(mse.toString());
		for (int i = 0; i < obj.length; i++) {
			sb.append(obj[i]).append("\n");
		}
		return sb;
	}

}
