package com.java.projectfinal.model.mapper;

import com.java.projectfinal.model.dto.MovieDto;
import com.java.projectfinal.model.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieDto toDto(Movie movie);

    List<MovieDto> toDtoList(List<Movie> movies);

    Movie toEntity(MovieDto movieDto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(MovieDto movieDto, @MappingTarget Movie movie);
}
