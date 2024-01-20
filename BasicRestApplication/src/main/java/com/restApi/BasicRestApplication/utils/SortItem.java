package com.restApi.BasicRestApplication.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public class SortItem {

    @Schema(example = "id") // set a default sorting property for swagger
    private String field;
    private Sort.Direction direction;

}
