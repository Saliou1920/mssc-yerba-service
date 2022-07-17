package io.saliou.msscyerbaservice.model;

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
public class Yerba {

    private UUID id;
    private String name;
    private Integer version;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private YerbaTypeEnum yerbaType;
    private Long upc;
    private BigDecimal price;
    private Integer quantity;

}
