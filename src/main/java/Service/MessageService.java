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


    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int flight_id) {
        return messageDAO.getMessageById(flight_id);
    }


}
