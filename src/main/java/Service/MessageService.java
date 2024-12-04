package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    /**
     * Constructor for a flightService when a flightDAO is provided.
     * This is used for when a mock flightDAO that exhibits mock behavior is used in the test cases.
     * This would allow the testing of FlightService independently of FlightDAO.
     * There is no need to modify this constructor.
     * @param flightDAO
     */
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message){
        Message returnMessage = messageDAO.insertMessage(message);
        return returnMessage;
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


}
