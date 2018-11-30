package br.com.academiadev.BatataComBaconSpring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.academiadev.BatataComBaconSpring.dto.post.PostUserDTO;
import br.com.academiadev.BatataComBaconSpring.dto.request.RequestUserDTO;
import br.com.academiadev.BatataComBaconSpring.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mappings({ //
			@Mapping(target = "id", ignore = true), //
			@Mapping(target = "createdAt", ignore = true), //
			@Mapping(target = "updatedAt", ignore = true), //
			@Mapping(target = "role", ignore = true), //
	})
	User toUser(PostUserDTO dto);

	@Mappings({ //
			@Mapping(target = "created_at", source = "createdAt", dateFormat = "dd/MM/yyyy HH:mm") //
	})
	RequestUserDTO toDTO(User user);

	List<User> toUser(List<PostUserDTO> dtos);

	List<RequestUserDTO> toDTO(List<User> users);

}
