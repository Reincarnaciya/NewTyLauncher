package space.typro.reincarnaciya.typicallauncher.back.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import space.typro.reincarnaciya.typicallauncher.back.managers.InformationManager;
import space.typro.reincarnaciya.typicallauncher.back.managers.PanelManager;
import space.typro.reincarnaciya.typicallauncher.back.managers.UserManager;
import space.typro.reincarnaciya.typicallauncher.back.objects.CommunicatePane;
import space.typro.reincarnaciya.typicallauncher.back.objects.User;
import space.typro.reincarnaciya.typicallauncher.Main;
import space.typro.reincarnaciya.typicallauncher.back.objects.AuthorizeUser;
import space.typro.reincarnaciya.typicallauncher.back.utils.NodeUtils;

import java.net.URL;
import java.util.ResourceBundle;

import static space.typro.reincarnaciya.typicallauncher.Main.INFORMATION_MANAGER;

public class LoginController implements Initializable {
    @FXML
    private VBox loginPaneVBOX;
    /*
    @FXML
    private Text loginText;
    @FXML
    private Text passwordText;
    */
    @FXML
    private ComboBox<AuthorizeUser> accountList;
    @FXML
    private ImageView deleteAccountFromList;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox authoAuthorizeCheckBox;
    @FXML
    private CheckBox showPassCheckBox;
    @FXML
    private Button authorizeButton;
    @FXML
    private Hyperlink registerHyperLink;

    private final TextField showPassField = new TextField("");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showPassCheckBox.setOnMouseClicked(this::onShowPassClick);
        showPassField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (showPassCheckBox.isSelected()){
                    passwordField.setText(showPassField.getText());
                }
        });
        authorizeButton.setOnMouseClicked(this::onAuthorizeClick);
        authoAuthorizeCheckBox.setOnMouseClicked(this::onAutoAuthorize);
        registerHyperLink.setOnMouseClicked(this::onRegHyperLink);
        accountList.setOnMouseClicked(this::onAccountList);
        accountList.getItems().addAll(UserManager.authorizeUsers);
        deleteAccountFromList.setOnMouseClicked(this::onDeleteAccountFromList);
        initAccountList();

        if (Main.globalSettings.autoAuthorizeUser.getUser() != null){
            onAuthorizeClick(null);
        }
    }

    private void onDeleteAccountFromList(MouseEvent mouseEvent) {
        if (accountList.getValue() == null) return;
        UserManager.removeUser(accountList.getValue());
        accountList.getItems().remove(accountList.getValue());
        passwordField.clear();
        loginField.clear();
    }

    private void initAccountList() {
        accountList.setCellFactory(new Callback<ListView<AuthorizeUser>, ListCell<AuthorizeUser>>() {
            @Override
            public ListCell<AuthorizeUser> call(ListView<AuthorizeUser> param) {
                return new ListCell<AuthorizeUser>() {
                    @Override
                    protected void updateItem(AuthorizeUser item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setTooltip(new Tooltip(item.getUser().getUserName()));
                            setText(item.getUser().getShowName()); // Установка имени в качестве текста элемента
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
        accountList.setConverter(new StringConverter<AuthorizeUser>() {
            @Override
            public String toString(AuthorizeUser object) {
                return object.getUser().getShowName(); // Отображаемое имя элемента
            }

            @Override
            public AuthorizeUser fromString(String string) {
                return null; // Не требуется для данного примера
            }
        });
        // Обработчик события выбора элемента
        accountList.setOnAction(event -> {
            AuthorizeUser selectedObject = accountList.getValue();
            if (selectedObject != null) {
                String newValue = selectedObject.getUser().getShowName(); // Получение нового значения
                accountList.setPromptText(newValue); // Изменение отображаемого текста
            }
        });
        accountList.setOnAction(event -> {
            AuthorizeUser selectedObject = accountList.getValue();
            if (selectedObject != null) {
                passwordField.setText(selectedObject.getPassWord());
                loginField.setText(selectedObject.getUser().getUserName());
            }
        });
        accountList.setOnMouseEntered(event -> {
            if (accountList.getValue() == null) return;
            accountList.setTooltip(new Tooltip(accountList.getValue().getUser().getUserName()));
        });
        if (!Main.globalSettings.autoAuthorizeUser.equals(AuthorizeUser.NULL_AUTHORIZE_USER) && Main.globalSettings.autoAuthorizeUser.getUser() != null){
            accountList.setValue(Main.globalSettings.autoAuthorizeUser);
            passwordField.setText(Main.globalSettings.autoAuthorizeUser.getPassWord());
            loginField.setText(Main.globalSettings.autoAuthorizeUser.getUser().getUserName());
            authoAuthorizeCheckBox.setSelected(true);
        }

    }

    private void onAccountList(MouseEvent mouseEvent) {

    }

    private void onRegHyperLink(MouseEvent mouseEvent) {
        LauncherController.controller.changeSubScene(LauncherController.Subscene.REGISTR);
    }

    private void onAutoAuthorize(MouseEvent mouseEvent) {
        if (authoAuthorizeCheckBox.isSelected()) return;
        Main.globalSettings.autoAuthorizeUser = AuthorizeUser.NULL_AUTHORIZE_USER;
    }
    private void onAuthorizeClick(MouseEvent mouseEvent) {
        AuthorizeUser authorizeUser = new AuthorizeUser(passwordField.getText(), new User(loginField.getText()));
        authorizeUser.Authorize();
        if (authorizeUser.isAuthorized()){
            UserManager.currentUser = authorizeUser;
            if (authoAuthorizeCheckBox.isSelected()){
                UserManager.addUser(authorizeUser);
                Main.globalSettings.autoAuthorizeUser = authorizeUser;
            }
        } else {
            INFORMATION_MANAGER.showInfo(new CommunicatePane("Не верный логин или пароль").setWaitTime(2).setFadeTime(1).setLayoutY(250), (AnchorPane) authorizeButton.getParent());
        }

    }


    private void onShowPassClick(MouseEvent mouseEvent) {
        if (showPassCheckBox.isSelected()){
            showPassField.setPrefSize(passwordField.getPrefWidth(), passwordField.getPrefHeight());
            showPassField.setText(passwordField.getText());
            NodeUtils.replace(loginPaneVBOX, passwordField, showPassField);
        }else {
            NodeUtils.replace(loginPaneVBOX, showPassField, passwordField);
        }
    }
}
