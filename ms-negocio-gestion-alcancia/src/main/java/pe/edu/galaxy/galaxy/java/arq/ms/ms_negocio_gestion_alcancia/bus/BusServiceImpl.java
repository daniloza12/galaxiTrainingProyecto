package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.bus;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusMessageBatch;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.MovimientoDTO;

@Slf4j
@RequiredArgsConstructor
@Service
public class BusServiceImpl implements BusService {

	@Value("${azure.service.connectionString}")  
	private String connectionString;    
	
	@Value("${azure.service.queueName}") 
	private String queueName; 
 
	@Override
	public void sendMessage(MovimientoDTO movimientoDTO) {

		// create a Service Bus Sender client for the queue
	    ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
	            .connectionString(connectionString)
	            .sender()
	            .queueName(queueName)
	            .buildClient();

	    // Creates an ServiceBusMessageBatch where the ServiceBus.
	    ServiceBusMessageBatch messageBatch = senderClient.createMessageBatch();

	    // create a list of messages
	    List<ServiceBusMessage> listOfMessages = new ArrayList<>();
	    listOfMessages.add(new ServiceBusMessage(String.valueOf(movimientoDTO)));
	     
	    // We try to add as many messages as a batch can fit based on the maximum size and send to Service Bus when
	    // the batch can hold no more messages. Create a new batch for next set of messages and repeat until all
	    // messages are sent.
	    for (ServiceBusMessage message : listOfMessages) {
	        if (messageBatch.tryAddMessage(message)) {
	            continue;
	        }

	        // The batch is full, so we create a new batch and send the batch.
	        senderClient.sendMessages(messageBatch);
	        log.info("Sent a batch of messages to the queue: " + queueName);
	        
	        
	        // create a new batch
	        messageBatch = senderClient.createMessageBatch();

	        // Add that message that we couldn't before.
	        if (!messageBatch.tryAddMessage(message)) {
	        	log.error("Message is too large for an empty batch. Skipping. Max size: %s.", messageBatch.getMaxSizeInBytes());
	        }
	    }

	    if (messageBatch.getCount() > 0) {
	        senderClient.sendMessages(messageBatch);
	        log.info("Sent a batch of messages to the queue: " + queueName);
	    }

	    //close the client
	    senderClient.close();
	} 
	 
}
