package controller;


import javafx.event.ActionEvent;

import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import javafx.stage.FileChooser;

import java.io.*;




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

    public void initialize(){

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
        new Alert(Alert.AlertType.CONFIRMATION, "Do you want open a new Window").show();
        

//        mnBar.getMenus().get(0).getItems().get(0).fire();
        mnNew.fire();
    }

    public void btnImageOpenClickOnAction(ActionEvent actionEvent) {

    }

    public void btnImageSaveClickOnAction(ActionEvent actionEvent) {
    }

    public void btnImageExitClickOnAction(ActionEvent actionEvent) {
    }

    public void btnImageCopyClickOnAction(ActionEvent actionEvent) {
    }

    public void btnImagePasteClickOnAction(ActionEvent actionEvent) {
    }

    public void btnImageClickOnAction(ActionEvent actionEvent) {
    }
}