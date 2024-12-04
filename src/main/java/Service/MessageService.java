package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message){
        return messageDAO.insertMessage(message);
    }


    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    public Message updateMessage(int message_id, Message message) {
        if (messageDAO.getMessageById(message_id) != null){
            messageDAO.updateMessage(message_id, message);
            Message updatedMessage = messageDAO.getMessageById(message_id);
            return updatedMessage;
        }
        return null;
    }

    public Message deleteMessage(int message_id) {
        Message deletedMessage = messageDAO.getMessageById(message_id);
        if (deletedMessage != null){
            messageDAO.deleteMessage(message_id);
            return deletedMessage;
        }
        return null;
    }


}
