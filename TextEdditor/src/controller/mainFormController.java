package controller;


import javafx.event.ActionEvent;

import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import javafx.stage.FileChooser;

import java.io.*;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class mainFormController {

    public TextArea txtArea;
    public MenuItem mnNew;
    public MenuItem mnItemOpen;
    public MenuItem mnIItemSave;
    public MenuItem mnExit;
    public MenuItem btnSelectAll;
    public MenuItem btnCopy;
    public MenuItem btnPaste;
    public MenuItem btnCut;
    public Menu btnHelp;
    public MenuBar mnBar;
    public TextField txtFind;
    public TextField txtReplace;
    public Button btnReplace;
    public ToggleButton btnRegEx;
    public ToggleButton btnCaseSensitive;
    public Button btnUp;
    public TextField txtWordCount;
    public TextField txtFindCount;
    public Button btnDown;

    private Matcher matcher;
    private Matcher matcher1;
    public boolean textChanged;


    public void initialize(){
        txtWordCount.setDisable(true);
        txtArea.textProperty().addListener((observable, oldValue, newValue) -> setTextTitle());
        txtArea.textProperty().addListener((observable, oldValue, newValue) -> getWordCount());

        txtFind.textProperty().addListener((observable, oldValue, newValue) -> textChanged = true);

    }

    public void mnItemOpenOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
        );
        File file = fileChooser.showOpenDialog(null);
        try{
            FileInputStream fis = new FileInputStream(file);
            byte[] fileBytes = new byte[fis.available()];

            fis.read(fileBytes);
            String fileContent = new String(fileBytes);
            txtArea.setText(fileContent);
            fis.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void mnNewClickOnAction(ActionEvent actionEvent) {
        txtArea.clear();
    }

    public void mnItemSaveClickOnAction(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser1 = new FileChooser();
        fileChooser1.setTitle("select a Folder");
        File file =fileChooser1.showSaveDialog(null);

        byte[] bytes = txtArea.getText().getBytes();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
    }

    public void mnExitClockOnAction(ActionEvent actionEvent) {

        System.exit(0);
    }

    public void btnSelectAllClickOnActions(ActionEvent actionEvent) {
        txtArea.selectAll();


    }

    public void btnCopyClickOnAction(ActionEvent actionEvent) {

        Clipboard systemClipboard = Clipboard.getSystemClipboard();
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(txtArea.getSelectedText());
        systemClipboard.setContent(clipboardContent);
    }

    public void btnPasteClickOnAction(ActionEvent actionEvent) {
        if (txtArea.getSelectedText().isEmpty()){
            Clipboard systemClipboard = Clipboard.getSystemClipboard();
            int caretPosition = txtArea.getCaretPosition();
            txtArea.insertText(caretPosition,systemClipboard.getString());
        }
        else{
            txtArea.replaceSelection("");

            Clipboard systemClipboard = Clipboard.getSystemClipboard();
            int caretPosition = txtArea.getCaretPosition();
            txtArea.insertText(caretPosition,systemClipboard.getString());

        }
    }

    public void btnCutClickOnAction(ActionEvent actionEvent) {
        Clipboard systemClipboard = Clipboard.getSystemClipboard();
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(txtArea.getSelectedText());

        systemClipboard.setContent(clipboardContent);
        txtArea.replaceSelection("");
    }

    public void btnhelpClickOnAction(ActionEvent actionEvent) {
    }
    public void btnImageNewClickOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> response = new Alert(Alert.AlertType.
                CONFIRMATION, "Do you want open a new Window?").showAndWait();

        if (response.get().getButtonData().isDefaultButton()){
//            mnBar.getMenus().get(0).getItems().get(0).fire();
            Optional<ButtonType> response1 = new Alert(Alert.AlertType.
                    CONFIRMATION, "Do you want Save previous slide?").showAndWait();
            if(response1.get().getButtonData().isDefaultButton()){
                mnIItemSave.fire();
            }
            mnNew.fire();

        }
//        if(response.get().getButtonData().isCancelButton()){
//
//        }


    }

    public void btnImageOpenClickOnAction(ActionEvent actionEvent) {
        mnItemOpen.fire();
    }

    public void btnImageSaveClickOnAction(ActionEvent actionEvent) {
        mnIItemSave.fire();
    }

    public void btnImageExitClickOnAction(ActionEvent actionEvent) {
        mnExit.fire();
    }

    public void btnImageCopyClickOnAction(ActionEvent actionEvent) {
        btnCopy.fire();
    }

    public void btnImagePasteClickOnAction(ActionEvent actionEvent) {
        btnPaste.fire();
    }
    public void btnImageClickOnAction(ActionEvent actionEvent) {
        btnCut.fire();
    }

    public void btnDownClickOnAction(ActionEvent actionEvent) {
        txtArea.deselect();
        String text = txtFind.getText();

        if (textChanged){

            int flag =0;
            if (!btnRegEx.isSelected()){flag = flag | Pattern.LITERAL;}
            if (!btnCaseSensitive.isSelected()){flag = flag | Pattern.CASE_INSENSITIVE;}
            matcher = Pattern.compile("^"+ text + "$" ,flag).matcher(txtArea.getText());
            textChanged = false;
        }
        if (matcher.find()){
            txtArea.selectRange(matcher.start(), matcher.end());
        }else{
            matcher.reset();
        }
    }
    public void btnUpClickonAction(ActionEvent actionEvent) {

    }

    public void btnReplaceClickOnAction(ActionEvent actionEvent) {
    }

    public void btnRegEx(ActionEvent actionEvent) {
        textChanged = true;
        btnDown.fire();
    }
    public void btnCaseSensitiveClickOnAction(ActionEvent actionEvent) {
        textChanged = true;
        btnDown.fire();
    }
    public void setTextTitle(){

    }
    public void getWordCount(){
        txtWordCount.setDisable(false);
        matcher1 = Pattern.compile("\\S+",0).matcher(txtArea.getText());
        int count =0;

        while (matcher1.find()){
            count++;
        }
        txtWordCount.setText(String.valueOf(count));
    }
}