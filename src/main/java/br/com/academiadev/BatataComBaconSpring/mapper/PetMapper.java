package br.com.academiadev.BatataComBaconSpring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//import br.com.academiadev.BatataComBaconSpring.dto.post.PostPetDTO;
import br.com.academiadev.BatataComBaconSpring.dto.request.RequestPetDTO;
import br.com.academiadev.BatataComBaconSpring.model.Pet;

@Mapper(componentModel = "spring")
public interface PetMapper {
//	//id, createdAt, updatedAt, usuario
//	@Mappings({//
//		@Mapping(target = "id", ignore = true), //
//		@Mapping(target = "createdAt", ignore = true), //
//		@Mapping(target = "updatedAt", ignore = true), //
//		@Mapping(target = "usuario", ignore = true), //
//	})
//	Pet toPet(PostPetDTO dto);
	//idUsuario
	@Mappings({//
	@Mapping(target = "idUsuario",source = "usuario.id"), //
	@Mapping(target = "created_at",source = "createdAt", dateFormat = "dd/MM/yyyy HH:mm") //
})
	RequestPetDTO toDTO(Pet pet);
	
//	List<Pet> toPet(List<PostPetDTO> dtos);
	
	List<RequestPetDTO> toDTO(List<Pet> pets);

}
