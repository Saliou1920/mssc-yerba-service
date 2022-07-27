package io.saliou.msscyerbaservice.bootstrap;

import io.saliou.msscyerbaservice.domain.Yerba;
import io.saliou.msscyerbaservice.model.YerbaTypeEnum;
import io.saliou.msscyerbaservice.repository.YerbaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Component
public class YerbaLoader implements CommandLineRunner {

    public final static String YERBA_1_UPC = "0631234200036";
    public final static String YERBA_2_UPC = "0631234300019";
    public final static String YERBA_3_UPC = "0631234300010";

    private final YerbaRepository yerbaRepository;

    @Autowired
    public YerbaLoader(YerbaRepository yerbaRepository) {
        this.yerbaRepository = yerbaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadYerbaObjects();
    }

    private void loadYerbaObjects() {
        if(yerbaRepository.count() == 0) {
            yerbaRepository.save(Yerba.builder()
                    .name("Yerba")
                    .version(1)
                    .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                    .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                    .yerbaType(YerbaTypeEnum.Argentinian)
                    .upc(YERBA_1_UPC)
                    .price(new BigDecimal("10.99"))
                    .minOnHand(1)
                    .quantity(1)
                    .build());
            yerbaRepository.save(Yerba.builder()
                    .name("Yerba")
                    .version(1)
                    .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                    .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                    .yerbaType(YerbaTypeEnum.Paraguayan)
                    .upc(YERBA_2_UPC)
                    .price(new BigDecimal("11.99"))
                    .minOnHand(1)
                    .quantity(10)
                    .build());
            yerbaRepository.save(Yerba.builder()
                    .name("Yerba")
                    .version(1)
                    .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                    .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                    .yerbaType(YerbaTypeEnum.Paraguayan)
                    .upc(YERBA_3_UPC)
                    .price(new BigDecimal("12.99"))
                    .minOnHand(1)
                    .quantity(10)
                    .build());
        }
    }
}
