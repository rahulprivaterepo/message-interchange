package com.pubsub.message.interchange.domain.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.pubsub.message.interchange.domain.request.base.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublisherRequest {
    @NotNull
    private Long publisherId;
    @NotNull
    @Size(min = 2, max = 10 , message = "publisher name should have at least 2 characters & not exceed 10 characters")
    private String publisherName;
}
