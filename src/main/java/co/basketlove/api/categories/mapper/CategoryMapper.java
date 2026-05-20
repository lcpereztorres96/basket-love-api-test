package co.basketlove.api.categories.mapper;

import co.basketlove.api.categories.dto.CategoryResponse;
import co.basketlove.api.categories.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse toResponse(Category category);
}