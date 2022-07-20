package io.saliou.msscyerbaservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YerbaDto {

    @NotBlank
    private UUID id;
    private String name;
    private Integer version;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private YerbaTypeEnum yerbaType;

    @Positive
    @NotNull
    private Long upc;

    @NotNull
    @Positive
    private BigDecimal price;
    private Integer quantity;

}
