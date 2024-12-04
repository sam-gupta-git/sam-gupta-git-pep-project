package Controller;

import Model.Message;
import Model.Account;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public SocialMediaController(){
        accountService = new AccountService();
        messageService = new MessageService();
    }
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageById);
        app.post("/messages", this::postMessageHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);
        app.post("/register", this::postAccountHandler);
        app.post("/login", this::postAccountLoginHandler);
        app.get("/accounts/{account_id}/messages", this::getMessagesByAccount);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    private void postMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        if (message.getMessage_text().length() > 0 && message.getMessage_text().length() < 255 && accountService.getAccountById(message.getPosted_by()) != null){
            Message addedMessage = messageService.addMessage(message);
            ctx.json(mapper.writeValueAsString(addedMessage));
        } else {    
            ctx.status(400);
        }
        
    }

    private void updateMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        if (message.getMessage_text().length() > 0 && message.getMessage_text().length() < 255 && messageService.getMessageById(message_id) != null){
            Message updatedMessage = messageService.updateMessage(message_id, message);
            ctx.json(mapper.writeValueAsString(updatedMessage));
        } else {    
            ctx.status(400);
        }
    }

    private void getAllMessagesHandler(Context ctx){
        ctx.json(messageService.getAllMessages());
    }

    private void getMessageById(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message fetchedMessage = messageService.getMessageById(message_id);
        System.out.println(fetchedMessage);
        if (fetchedMessage != null){
            ctx.json(mapper.writeValueAsString(fetchedMessage));
        }
    }

    private void deleteMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        if (messageService.getMessageById(message_id) != null){
            Message deletedMessage = messageService.deleteMessage(message_id);
            ctx.json(mapper.writeValueAsString(deletedMessage));
        } 
    }

    private void postAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        if (accountService.getAccountByUsername(account.getUsername()) == null && account.getUsername().length() != 0 && account.getPassword().length() >= 4){
            Account addedAccount = accountService.registerAccount(account);
            ctx.json(mapper.writeValueAsString(addedAccount));
        } else {    
            ctx.status(400);
        }
    }

    private void postAccountLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account accountLogin = accountService.verifyLogin(account.getUsername(), account.getPassword());
        if (accountLogin != null){
            ctx.json(mapper.writeValueAsString(accountLogin));
        } else {    
            ctx.status(401);
        }
    }

    private void getMessagesByAccount(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int posted_by = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> fetchedMessages = messageService.getMessagesByAccount(posted_by);
        ctx.json(mapper.writeValueAsString(fetchedMessages));
        
    }

}