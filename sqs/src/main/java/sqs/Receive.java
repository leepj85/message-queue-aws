package sqs;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;

import java.util.List;

public class Receive implements RequestHandler<SQSEvent, Void> {

  private static final String QUEUE_NAME = "QueueA";

  @Override
  public Void handleRequest(SQSEvent input, Context context) {
        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
        LambdaLogger logger = context.getLogger();

        for (SQSEvent.SQSMessage m : input.getRecords()) {
          logger.log(m.getBody());
          System.out.println(new String(m.getBody()));
        }
    return null;
  }
}