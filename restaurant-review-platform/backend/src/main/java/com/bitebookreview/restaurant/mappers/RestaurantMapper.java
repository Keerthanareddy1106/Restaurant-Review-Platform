package com.bitebookreview.restaurant.mappers;

import com.bitebookreview.restaurant.domain.RestaurantCreateUpdateRequest;
import com.bitebookreview.restaurant.domain.dtos.GeoPointDto;
import com.bitebookreview.restaurant.domain.dtos.RestaurantCreateUpdateRequestDto;
import com.bitebookreview.restaurant.domain.dtos.RestaurantDto;
import com.bitebookreview.restaurant.domain.dtos.RestaurantSummaryDto;
import com.bitebookreview.restaurant.domain.entities.Restaurant;
import com.bitebookreview.restaurant.domain.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    RestaurantCreateUpdateRequest toRestaurantCreateUpdateRequest(RestaurantCreateUpdateRequestDto dto);

    @Mapping(source = "reviews", target = "totalReviews", qualifiedByName = "populateTotalReviews")
    RestaurantDto toRestaurantDto(Restaurant restaurant);

    @Mapping(source = "reviews", target = "totalReviews", qualifiedByName = "populateTotalReviews")
    RestaurantSummaryDto toSummaryDto(Restaurant restaurant);

    @Named("populateTotalReviews")
    default Integer populateTotalReviews(List<Review> reviews) {
        return reviews.size();
    }

    @Mapping(target = "latitude", expression = "java(geoPoint.getLat())")
    @Mapping(target = "longitude", expression = "java(geoPoint.getLon())")
    GeoPointDto toGeoPointDto(GeoPoint geoPoint);
}
