package Level2.Lesson4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ChatForm {

    public Button btnSend;
    @FXML
    TextArea mainTextArea;
    @FXML
    TextField messageTextField;
    public void btnSendClickAction() {
        SendTextFromField();
    }
    public void KeyEnterPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER)  {
            SendTextFromField();
        }
    }
    protected void SendTextFromField(){
        mainTextArea.appendText(messageTextField.getText()+"\n");
        messageTextField.setText("");
    }
}
