package br.com.academiadev.BatataComBaconSpring.mapper;

import org.mapstruct.Mapper;

import br.com.academiadev.BatataComBaconSpring.dto.request.ResponseFileDTO;
import br.com.academiadev.BatataComBaconSpring.model.File;

@Mapper(componentModel = "spring")
public interface FileMapper {

	ResponseFileDTO toDTO(File file);
	
}
