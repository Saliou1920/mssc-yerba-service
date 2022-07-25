package io.saliou.msscyerbaservice.domain;

import io.saliou.msscyerbaservice.model.YerbaTypeEnum;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Yerba {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column( length = 36, name = "id", updatable = false, nullable = false)
    private UUID id;
    private String name;

    @Version
    private Integer version;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @CreationTimestamp
    private Timestamp updatedAt;
    @Enumerated(EnumType.STRING)
    private YerbaTypeEnum yerbaType;

    @Column(unique = true)
    private String upc;
    private BigDecimal price;
    private Integer minOnHand;
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Yerba yerba = (Yerba) o;
        return id != null && Objects.equals(id, yerba.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
