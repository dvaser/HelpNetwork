package com.hack.deprem.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

public record CreateAssistancePointRequest(
        @NotBlank
        String location
) {
}
