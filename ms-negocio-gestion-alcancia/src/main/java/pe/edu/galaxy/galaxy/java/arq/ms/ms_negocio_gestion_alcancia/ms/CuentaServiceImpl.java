package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.ms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_alcancia.dto.MovimientoDTO;

@Slf4j
@RequiredArgsConstructor
@Service
public class CuentaServiceImpl implements CuentaService {

	@Value("${api.url.alcanciaMovimiento}")
	private String cuentaDetallesUrl;

	private final RestTemplate restTemplate;

	@Override
	public void realizarCuentaMovimiento(MovimientoDTO movimientoDTO) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity<MovimientoDTO> requestEntity = new HttpEntity<>(movimientoDTO, headers);

		ResponseEntity<Void> response = restTemplate.exchange(cuentaDetallesUrl, HttpMethod.POST, requestEntity,
				Void.class);

		log.info("Respuesta api " + response.toString());
	}

}
