package br.com.academiadev.BatataComBaconSpring.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.google.common.base.Function;

public class Utils {
	
	public static <DTO, T> Page<DTO> toPageDTO(Page<T> page, Function<List<T>, List<DTO>> mapper) {
		return new PageImpl<>(mapper.apply(page.getContent()), PageRequest.of(page.getNumber(), page.getSize()),
				page.getTotalElements());
	}

}
