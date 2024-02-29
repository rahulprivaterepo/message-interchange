package com.pubsub.message.interchange.domain.request.base;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageBase {
    @NotNull
    @Size(max = 128 * 1024, message = "Message size cannot exceed 128 KB")
    private String message;
}
