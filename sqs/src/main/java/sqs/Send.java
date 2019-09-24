package sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class Send {
  private static final String QUEUE_NAME = "QueueA";

  public static void send(){
    final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

    String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();

    SendMessageRequest send_msg_request = new SendMessageRequest()
        .withQueueUrl(queueUrl)
        .withMessageBody("hello everybody")
        .withDelaySeconds(5);
    sqs.sendMessage(send_msg_request);


    // Send multiple messages to the queue
    SendMessageBatchRequest send_batch_request = new SendMessageBatchRequest()
        .withQueueUrl(queueUrl)
        .withEntries(
            new SendMessageBatchRequestEntry(
                "msg_1", "Hello, AWS World."),
            new SendMessageBatchRequestEntry(
                "msg_2", "Goodbye, AWS World.")
                .withDelaySeconds(10));
    sqs.sendMessageBatch(send_batch_request);
  }
}