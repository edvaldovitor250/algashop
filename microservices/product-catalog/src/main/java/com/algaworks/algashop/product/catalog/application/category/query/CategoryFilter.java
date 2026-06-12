package com.algaworks.algashop.product.catalog.application.category.query;

import com.algaworks.algashop.product.catalog.application.utility.SortablePageFilter;
import lombok.*;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CategoryFilter extends SortablePageFilter<CategoryFilter.SortType> {
    private String name;
    private Boolean enabled;

    @Override
    public CategoryFilter.SortType getSortByPropertyOrDefault() {
        return getSortByProperty() == null ? CategoryFilter.SortType.NAME: getSortByProperty();
    }

    @Override
    public Sort.Direction getSortDirectionOrDefault() {
        return getSortDirection() == null ? Sort.Direction.ASC : getSortDirection();
    }

    public boolean isCacheable() {
        return isDefaultFilter();
    }

    private boolean isDefaultFilter(){
        return this.equals(defaultFilter());
    }

    public static CategoryFilter defaultFilter() {
        return CategoryFilter.builder()
        .name(null)
                .enabled(true)
                .sortByProperty(CategoryFilter.SortType.NAME)
                .sortDirection(Sort.Direction.ASC)
                .build();
    }

    public boolean isDefaultFilter(){
        return Boolean.TRUE.equals(enabled) && name == null;
    }

    @Getter
    @RequiredArgsConstructor
    public enum SortType {
        NAME("name");

        private final String propertyName;
    }
}
