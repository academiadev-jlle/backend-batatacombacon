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
import br.com.academiadev.BatataComBaconSpring.service.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api("Endpoint de Imagens")
@CrossOrigin
public class FileEndpoint {

	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private PetService petService;

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
		return mapper.toDTO(fileRepository.save(file));
	}
	
	@PostMapping("/images/pet/{idPet}")
	@ApiOperation("Faz o upload de uma imagem para um pet")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Imagem salva com sucesso!"), //
			@ApiResponse(code = 400, message = "Erro da exception")//
	})
	public RequestFileDTO uploadPetImage(@PathVariable("idPet") Long idPet, @RequestParam MultipartFile imagem) throws IOException {
		File file = new File(imagem.getOriginalFilename(), imagem.getContentType(), imagem.getBytes());
		file = fileRepository.save(file);
		petService.findById(idPet).getFotos().add(file.getId());
		petService.flush();
		return mapper.toDTO(file);
	}

	@GetMapping("/images/{idImage}")
	@ApiOperation("Faz o download de uma imagem")
	@ApiResponses({ //
			@ApiResponse(code = 200, message = "Download da imagem"), //
			@ApiResponse(code = 404, message = "Imagem não encontrada")//
	})
	public ResponseEntity<byte[]> downloadImage(@PathVariable("idImage") Long idImage) {
		File file = fileRepository.findById(idImage)
				.orElseThrow(() -> new ImagemNaoEncontradaException("Imagem não encontrada"));
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getNome() + "\"")
				.body(file.getFile());
	}
}
