package io.saliou.msscyerbaservice.controller;

import io.saliou.msscyerbaservice.model.Yerba;
import io.saliou.msscyerbaservice.model.YerbaTypeEnum;
import io.saliou.msscyerbaservice.service.YerbaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(YerbaController.class)
class YerbaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private YerbaService yerbaService;
    private Yerba yerba;

    @BeforeEach
    void setUp() {
        yerba = Yerba.builder()
                .id(UUID.randomUUID())
                .name("Yerba")
                .version(1)
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .yerbaType(YerbaTypeEnum.Argentinian)
                .upc(123456789L)
                .price(new BigDecimal("1.99"))
                .quantity(1)
                .build();
    }

    @Test
    void getYerbaById() throws Exception {
        Mockito.when(yerbaService.getYerbaById(yerba.getId())).thenReturn(yerba);
        mockMvc.perform(get("/api/v1/yerba/" + yerba.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void createYerba() {
        Mockito.when(yerbaService.createYerba(yerba)).thenReturn(yerba);

        assertEquals(yerba, yerbaService.createYerba(yerba));
    }

    @Test
    void updateYerba() {
        Mockito.when(yerbaService.updateYerba(yerba.getId(), yerba)).thenReturn(yerba);

        assertEquals(yerba, yerbaService.updateYerba(yerba.getId(), yerba));
    }
}