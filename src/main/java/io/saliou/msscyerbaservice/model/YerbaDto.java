package io.saliou.msscyerbaservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YerbaDto {

    @NotNull
    private UUID id;
    @Size(min = 4, max = 100)
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
