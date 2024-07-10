package org.example.springsecuritypractice.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

@Slf4j
public class PageableToolsImpl {

    public static HttpResponse<PaginationDto> pageableRepository(Object repositoryValueObject) {

        try {
            final var pageTuts = (Page<?>) repositoryValueObject;

            //new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(repositoryValueObject)
            final var paginationDto = new PaginationDto(pageTuts.getContent(),
                    pageTuts.getNumber(),
                    pageTuts.getTotalPages() - 1,
                    pageTuts.getTotalElements());

            return new HttpResponse<>(HttpStatus.OK, paginationDto);

        } catch (Exception e) {
            return new HttpResponse<>(HttpStatus.OK);
        }

    }

    @Data
    @AllArgsConstructor
    public static class PaginationDto {
        private Object value;
        private Integer currentPage;
        private Integer totalPages;
        private Long totalItems;
    }

}
