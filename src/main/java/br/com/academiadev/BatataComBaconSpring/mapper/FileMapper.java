package br.com.academiadev.BatataComBaconSpring.mapper;

import org.mapstruct.Mapper;

import br.com.academiadev.BatataComBaconSpring.dto.request.RequestFileDTO;
import br.com.academiadev.BatataComBaconSpring.model.File;

@Mapper(componentModel = "spring")
public interface FileMapper {

	RequestFileDTO toDTO(File file);
	
}
