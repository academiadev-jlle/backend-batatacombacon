package br.com.academiadev.BatataComBaconSpring.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.academiadev.BatataComBaconSpring.dto.post.PostUserDTO;
import br.com.academiadev.BatataComBaconSpring.dto.request.ResponseUserDTO;
import br.com.academiadev.BatataComBaconSpring.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mappings({ //
			@Mapping(target = "id", ignore = true), //
			@Mapping(target = "createdAt", ignore = true), //
			@Mapping(target = "updatedAt", ignore = true), //
			@Mapping(target = "role", ignore = true), //
			@Mapping(target = "authorities", ignore = true), //
	})
	User toUser(PostUserDTO dto);

	@Mappings({ //
			@Mapping(target = "created_at", source = "createdAt", dateFormat = "dd/MM/yyyy HH:mm") //
	})
	ResponseUserDTO toDTO(User user);

	List<User> toUser(List<PostUserDTO> dtos);

	List<ResponseUserDTO> toDTO(List<User> users);

}
