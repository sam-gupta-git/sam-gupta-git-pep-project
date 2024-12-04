package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;

    /**
     * Default constructor
     */
    public MessageService(){
        messageDAO = new MessageDAO();
    }

    /**
     * Parameterized constructor
     * @param messageDAO Other messageDAO to initialize this.messageDAO
     */    
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    /**
     * Method to add a new message, returns the new message object if successful
     * @param message Message object that contains message information
     */ 
    public Message addMessage(Message message){
        return messageDAO.insertMessage(message);
    }

    /**
     * Method to retrieve all messages as a list, call messageDAO to select all messages in table
     */ 
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    /**
     * Method to retrieve list of messages from a specific account
     * @param posted_by The account_id to get messages from, should match an account_id in the account table
     */ 
    public List<Message> getMessagesByAccount(int posted_by) {
        return messageDAO.getMessagesByAccount(posted_by);
    }

    /**
     * Method to retrieve a message by its ID
     * @param message_id The ID of a message to be retrieved
     */ 
    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    /**
     * Method to update the message_text of a message given its ID
     * @param message_id The ID of the message to be updated
     * @param message The message object that contains the updated message
     */ 
    public Message updateMessage(int message_id, Message message) {
        if (messageDAO.getMessageById(message_id) != null){
            messageDAO.updateMessage(message_id, message);
            Message updatedMessage = messageDAO.getMessageById(message_id);
            return updatedMessage;
        }
        return null;
    }

    /**
     * Method to delete a message given its ID
     * @param message_id The ID of the message to be deleted
     */ 
    public Message deleteMessage(int message_id) {
        Message deletedMessage = messageDAO.getMessageById(message_id);
        if (deletedMessage != null){
            messageDAO.deleteMessage(message_id);
            return deletedMessage;
        }
        return null;
    }
}
