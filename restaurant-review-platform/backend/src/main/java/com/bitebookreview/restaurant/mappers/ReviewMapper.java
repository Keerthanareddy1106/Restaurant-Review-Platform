package com.bitebookreview.restaurant.mappers;

import com.bitebookreview.restaurant.domain.ReviewCreateUpdateRequest;
import com.bitebookreview.restaurant.domain.dtos.ReviewCreateUpdateRequestDto;
import com.bitebookreview.restaurant.domain.dtos.ReviewDto;
import com.bitebookreview.restaurant.domain.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    ReviewCreateUpdateRequest toReviewCreateUpdateRequest(ReviewCreateUpdateRequestDto dto);

    ReviewDto toDto(Review review);

}
