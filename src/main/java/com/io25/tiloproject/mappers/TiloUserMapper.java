package com.io25.tiloproject.mappers;

import com.io25.tiloproject.dto.TiloUserRestDTO;
import com.io25.tiloproject.model.TiloUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TiloUserMapper {
    TiloUserMapper INSTANCE = Mappers.getMapper(TiloUserMapper.class);

    TiloUserRestDTO toDTO (TiloUser tiloUser);
    List<TiloUserRestDTO> toDTO (List<TiloUser> tiloUser);

}
