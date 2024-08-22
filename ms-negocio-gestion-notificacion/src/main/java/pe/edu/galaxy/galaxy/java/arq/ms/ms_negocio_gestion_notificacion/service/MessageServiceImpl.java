package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_notificacion.dto.MovimientoDTO;

@Service
public class MessageServiceImpl implements MessageService {
 
	@Value("${correo.email}") 
	private String correoEmail;    
	
	@Value("${correo.password}") 
	private String correoPassword; 
	
	@Override
	public void envioCorreo(MovimientoDTO movimientoDTO) {
		
        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(correoEmail, correoPassword);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(movimientoDTO.getCorreo())
            );
            message.setSubject("Realizo movimiento de alcancia: " + movimientoDTO.getNombreAlcancia());
            message.setText("Estimado " + movimientoDTO.getNombre() + ","
                    + "\n\n Realizo un  " + movimientoDTO.getTipoMovimiento()
                    + "\n Moneda: " + movimientoDTO.getMoneda()
                    + "\n Monto: " + movimientoDTO.getMonto()
                    + "\n Fecha Operacion: " + movimientoDTO.getFechaMovimiento()
                    + "\n Numero Operacion: " + movimientoDTO.getNumeroOperacion());
            
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    
	}
	
}
