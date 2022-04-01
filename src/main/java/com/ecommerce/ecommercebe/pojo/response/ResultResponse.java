package com.ecommerce.ecommercebe.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultResponse {

    private Integer message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}