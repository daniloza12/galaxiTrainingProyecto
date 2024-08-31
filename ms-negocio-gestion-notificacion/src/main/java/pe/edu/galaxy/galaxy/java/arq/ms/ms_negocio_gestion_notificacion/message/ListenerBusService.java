package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.message;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.dto.ClienteNotificacionDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.dto.MovimientoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.feing.FeingClienteServiceClient;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.service.MessageService;


@Slf4j
@RequiredArgsConstructor
@Service
public class ListenerBusService {

	@Value("${azure.service.connectionString}") 
	private String connectionString;
	
	@Value("${azure.service.queueName}") 
	private String queueName; 
 	
    private ServiceBusProcessorClient processorClient;
    private final MessageService messageService;
    //private final ClienteService clienteService;
	//private final ClienteFeingService clienteService; 
	private final FeingClienteServiceClient clienteService; 

	
    @PostConstruct
    public void start() {
    	 
        processorClient = new ServiceBusClientBuilder()
            .connectionString(connectionString)
            .processor()
            .queueName(queueName)
            .processMessage(this::processMessage)
            .processError(this::processError)
            .buildProcessorClient();

        processorClient.start();
    }

    @PreDestroy
    public void stop() {
        if (processorClient != null) {
            processorClient.close();
        }
    }

    private void processMessage( ServiceBusReceivedMessageContext context) {
        // Lógica para procesar el mensaje
        log.info("Received message: " + context.getMessage().getBody().toString());
        
        String input = context.getMessage().getBody().toString();
        
        // 1. Eliminar 'MovimientoDTO(' al principio y ')' al fina

        String cleanedInput = input.substring(input.indexOf("(") + 1, input.length() - 1);
        // 2. Separar en pares clave-valor
        String[] keyValuePairs = cleanedInput.split(", (?=\\w+=)");

        // 3. Crear un mapa para almacenar los valores
        Map<String, String> valuesMap = new HashMap<>();
        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=", 2);
            valuesMap.put(entry[0].trim(), entry[1].trim());
        }
        
        // 4. Convertir el mapa a un objeto MovimientoDTO
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setNombreAlcancia(valuesMap.get("nombreAlcancia"));
        movimientoDTO.setTipoMovimiento(valuesMap.get("tipoMovimiento"));
        movimientoDTO.setMoneda(valuesMap.get("moneda"));
        movimientoDTO.setMonto(new BigDecimal(valuesMap.get("monto")));
        movimientoDTO.setFechaMovimiento(LocalDate.parse(valuesMap.get("fechaMovimiento")));
        movimientoDTO.setNumeroOperacion(valuesMap.get("numeroOperacion"));
        
       ClienteNotificacionDTO clienteDto = clienteService.findById(Long.parseLong(valuesMap.get("clienteId")));
        
       // ResponseEntity<ClienteNotificacionDTO> responseAPi = clienteRepository.findByIdToNoyify(Long.parseLong(valuesMap.get("clienteId")));
       // ClienteNotificacionDTO clienteDto = responseAPi.getBody();
        
        if(!clienteDto.getCorreo().isEmpty()) {
			movimientoDTO.setCorreo(clienteDto.getCorreo());
	        movimientoDTO.setNombre(clienteDto.getNombres().concat(" ").concat(clienteDto.getApellidos()));
	        messageService.envioCorreo(movimientoDTO);
        }
		 
    }

    private void processError(ServiceBusErrorContext context) {
    	log.error("Error occurred: " + context.getException().getMessage());
    }
    
    
    
    
	/*@Override
	public void processMessage() throws InterruptedException {
		
		// Create an instance of the processor through the ServiceBusClientBuilder
        ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
            .connectionString(connectionString)
            .processor()
            .queueName(queueName)
            .processMessage(MessageServiceImpl::processMessage)
            .processError(context -> processError(context))
            .buildProcessorClient();

        System.out.println("Starting the processor");
        processorClient.start();

        TimeUnit.SECONDS.sleep(10);
        System.out.println("Stopping and closing the processor");
        processorClient.close();
		
	}
    
	private static void processMessage(ServiceBusReceivedMessageContext context) {
	    ServiceBusReceivedMessage message = context.getMessage();
	    System.out.printf("Processing message. Session: %s, Sequence #: %s. Contents: %s%n", message.getMessageId(),
	        message.getSequenceNumber(), message.getBody());
	}
	    
	private static void processError(ServiceBusErrorContext context) {
	    System.out.printf("Error when receiving messages from namespace: '%s'. Entity: '%s'%n",
	        context.getFullyQualifiedNamespace(), context.getEntityPath());

	    if (!(context.getException() instanceof ServiceBusException)) {
	        System.out.printf("Non-ServiceBusException occurred: %s%n", context.getException());
	        return;
	    }

	    ServiceBusException exception = (ServiceBusException) context.getException();
	    ServiceBusFailureReason reason = exception.getReason();

	    if (reason == ServiceBusFailureReason.MESSAGING_ENTITY_DISABLED
	        || reason == ServiceBusFailureReason.MESSAGING_ENTITY_NOT_FOUND
	        || reason == ServiceBusFailureReason.UNAUTHORIZED) {
	        System.out.printf("An unrecoverable error occurred. Stopping processing with reason %s: %s%n",
	            reason, exception.getMessage());
	    } else if (reason == ServiceBusFailureReason.MESSAGE_LOCK_LOST) {
	        System.out.printf("Message lock lost for message: %s%n", context.getException());
	    } else if (reason == ServiceBusFailureReason.SERVICE_BUSY) {
	        try {
	            // Choosing an arbitrary amount of time to wait until trying again.
	            TimeUnit.SECONDS.sleep(1);
	        } catch (InterruptedException e) {
	            System.err.println("Unable to sleep for period of time");
	        }
	    } else {
	        System.out.printf("Error source %s, reason %s, message: %s%n", context.getErrorSource(),
	            reason, context.getException());
	    }
	}*/
	
}
