package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.message;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
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
public class ListenerBusServiceImpl implements ListenerBusService {

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
        log.info("Received message: " + context.getMessage().getBody().toString());
        
        String input = context.getMessage().getBody().toString();
        String cleanedInput = input.substring(input.indexOf("(") + 1, input.length() - 1);
        String[] keyValuePairs = cleanedInput.split(", (?=\\w+=)");

        Map<String, String> valuesMap = new HashMap<>();
        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=", 2);
            valuesMap.put(entry[0].trim(), entry[1].trim());
        }
        
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setNombreAlcancia(valuesMap.get("nombreAlcancia"));
        movimientoDTO.setTipoMovimiento(valuesMap.get("tipoMovimiento"));
        movimientoDTO.setMoneda(valuesMap.get("moneda"));
        movimientoDTO.setMonto(new BigDecimal(valuesMap.get("monto")));
        movimientoDTO.setFechaMovimiento(LocalDate.parse(valuesMap.get("fechaMovimiento")));
        movimientoDTO.setNumeroOperacion(valuesMap.get("numeroOperacion"));
        movimientoDTO.setClienteId(Long.parseLong(valuesMap.get("clienteId")));
        sendEmail(movimientoDTO);
    }
    
    @Override
    public void sendEmail(MovimientoDTO movimientoDTO) {
    	
    	ClienteNotificacionDTO clienteDto = clienteService.findById(movimientoDTO.getClienteId());
        
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
	
}
