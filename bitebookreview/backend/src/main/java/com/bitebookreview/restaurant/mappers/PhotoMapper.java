package com.bitebookreview.restaurant.mappers;

import com.bitebookreview.restaurant.domain.dtos.PhotoDto;
import com.bitebookreview.restaurant.domain.entities.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {

    PhotoDto toDto(Photo photo);

}
