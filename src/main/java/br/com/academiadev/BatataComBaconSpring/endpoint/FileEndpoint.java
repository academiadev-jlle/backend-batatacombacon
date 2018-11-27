package br.com.academiadev.BatataComBaconSpring.endpoint;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.net.HttpHeaders;

import br.com.academiadev.BatataComBaconSpring.dto.request.RequestFileDTO;
import br.com.academiadev.BatataComBaconSpring.exception.ImagemNaoEncontradaException;
import br.com.academiadev.BatataComBaconSpring.mapper.FileMapper;
import br.com.academiadev.BatataComBaconSpring.model.File;
import br.com.academiadev.BatataComBaconSpring.repository.FileRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@Api("Endpoint de Imagens")
public class FileEndpoint {

	@Autowired
	private FileRepository repository;

	@Autowired
	private FileMapper mapper;

	@PostMapping("/images")
	@ApiOperation("Faz o upload de uma imagem")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Imagem salva com sucesso!"), //
			@ApiResponse(code = 400, message = "Erro da exception")//
	})
	public RequestFileDTO uploadImage(@RequestParam MultipartFile imagem) throws IOException {
		File file = new File(imagem.getOriginalFilename(), imagem.getContentType(), imagem.getBytes());
		return mapper.toDTO(repository.save(file));
	}

	@GetMapping("/images/{idImage}")
	@ApiOperation("Faz o download de uma imagem")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Download da imagem"), //
			@ApiResponse(code = 404, message = "Imagem não encontrada")//
	})
	public ResponseEntity<byte[]> downloadImage(@PathVariable("idImage") Long idImage) {
		File file = repository.findById(idImage)
				.orElseThrow(() -> new ImagemNaoEncontradaException("Imagem não encontrada"));
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getNome() + "\"")
				.body(file.getFile());
	}
}
