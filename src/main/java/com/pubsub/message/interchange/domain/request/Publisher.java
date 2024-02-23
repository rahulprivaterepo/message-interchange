package com.pubsub.message.interchange.domain.request;

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
public class Publisher {
    @NotNull
    @Size(min = 2, max = 10 , message = "publisher name should have at least 2 characters & not exceed 10 characters")
    private String publisherName;
}
