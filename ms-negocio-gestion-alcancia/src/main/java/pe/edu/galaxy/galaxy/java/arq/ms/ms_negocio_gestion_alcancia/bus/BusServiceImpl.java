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

	    ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
	            .connectionString(connectionString)
	            .sender()
	            .queueName(queueName)
	            .buildClient();

	    ServiceBusMessageBatch messageBatch = senderClient.createMessageBatch();
	    List<ServiceBusMessage> listOfMessages = new ArrayList<>();
	    listOfMessages.add(new ServiceBusMessage(String.valueOf(movimientoDTO)));
	    
	    for (ServiceBusMessage message : listOfMessages) {
	        if (messageBatch.tryAddMessage(message)) {
	            continue;
	        }
	        senderClient.sendMessages(messageBatch);
	        log.info("Sent a batch of messages to the queue: " + queueName);
	        
	        messageBatch = senderClient.createMessageBatch();

	        if (!messageBatch.tryAddMessage(message)) {
	        	log.error("Message is too large for an empty batch. Skipping. Max size: %s.", messageBatch.getMaxSizeInBytes());
	        }
	    }

	    if (messageBatch.getCount() > 0) {
	        senderClient.sendMessages(messageBatch);
	        log.info("Sent a batch of messages to the queue: " + queueName);
	    }

	    senderClient.close();
	} 
	 
}
