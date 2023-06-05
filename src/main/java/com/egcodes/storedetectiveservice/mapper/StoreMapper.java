package com.egcodes.storedetectiveservice.mapper;

import com.egcodes.storedetectiveservice.api.dto.StoreDTO;
import com.egcodes.storedetectiveservice.persitence.entity.Store;
import java.util.List;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreDTO toDTO(Store entity);

    Store toEntity(StoreDTO dto);

    List<StoreDTO> toDTOs(List<Store> entities);

}