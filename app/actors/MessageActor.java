package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.FeedResponse;
import data.Message;
import data.NewsAgentResponse;
import services.FeedService;
import services.NewsAgentService;

import java.util.UUID;

public class MessageActor extends UntypedActor {
    // self reference the actor
    private final ActorRef out;

    // define another actor reference
    public MessageActor(ActorRef out) {
        this.out = out;
    }

    //PROPS
    public static Props props(ActorRef out) {
        return Props.create(MessageActor.class, out);
    }

    //Object of feed service
    private FeedService feedService = new FeedService();
    //Object of newsagent service
    private NewsAgentService newsAgentService = new NewsAgentService();


    private FeedResponse feedResponse = new FeedResponse();

    // define another actor reference
    @Override
    public void onReceive(Object message) throws Throwable {
        //send the msg back
        //newsAgent Service
        //feedService
        //send the response back
        ObjectMapper mapper = new ObjectMapper();
        if (message instanceof String) {
            Message messageObject = new Message();
            messageObject.text = (String) message;
            messageObject.sender = Message.Sender.USER;
            out.tell(mapper.writeValueAsString(messageObject), self());
            //writeValueAsString is to convert to a jason object
            //self is to tell use this ref actor itself
            String query = newsAgentService.getNewsAgentResponse("Find " + messageObject.text, UUID.randomUUID()).query;
            feedResponse = feedService.getFeedByQuery(query);
            messageObject.text = (feedResponse.title == null) ? "No results found" : "Showing results for: " + query;
            messageObject.feedResponse = feedResponse;
            messageObject.sender = Message.Sender.BOT;
            out.tell(mapper.writeValueAsString(messageObject), self());
        }
    }
}