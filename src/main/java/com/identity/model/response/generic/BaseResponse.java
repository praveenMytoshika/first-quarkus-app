package com.identity.model.response.generic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.identity.enums.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class BaseResponse<T> {
	String successMsg;

	@Builder.Default
	Status status = Status.SUCCESS;

	T data;
	String errorMsg;
	String error;
	String token;
}
