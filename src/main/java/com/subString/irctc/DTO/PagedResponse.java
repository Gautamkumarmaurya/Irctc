package com.subString.irctc.DTO;

import org.springframework.data.domain.Page;

import java.util.List;
   // record like a container class
public record PagedResponse<T> (
        List<T> content ,
        int page ,
        int size,
        long totalElement,
        int totalPages,
        boolean first,
        boolean last

) {
    public static <T> PagedResponse<T> fromPage(Page<T> page) {
        return new PagedResponse<T>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()

        );
    }
}
