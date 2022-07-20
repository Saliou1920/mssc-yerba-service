package io.saliou.msscyerbaservice.controller;

import io.saliou.msscyerbaservice.model.YerbaDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(YerbaController.class)
class YerbaDtoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private YerbaService yerbaService;
    private YerbaDto yerbaDto;

    @BeforeEach
    void setUp() {
        yerbaDto = YerbaDto.builder()
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
        Mockito.when(yerbaService.getYerbaById(yerbaDto.getId())).thenReturn(yerbaDto);
        mockMvc.perform(get("/api/v1/yerba/" + yerbaDto.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void createYerba() throws Exception {
        Mockito.when(yerbaService.createYerba(yerbaDto)).thenReturn(yerbaDto);
        mockMvc.perform(post("/api/v1/yerba")
                .contentType("application/json")
                .content("{\"name\":\"Yerba\",\"version\":1,\"createdAt\":\"2020-01-01T00:00:00.000Z\",\"updatedAt\":\"2020-01-01T00:00:00.000Z\",\"yerbaType\":\"Argentinian\",\"upc\":123456789,\"price\":1.99,\"quantity\":1}"))
                .andExpect(status().isOk());

        assertEquals(yerbaDto, yerbaService.createYerba(yerbaDto));
    }

    @Test
    void updateYerba() throws Exception {
        Mockito.when(yerbaService.updateYerba(yerbaDto.getId(), yerbaDto)).thenReturn(yerbaDto);
        mockMvc.perform(put("/api/v1/yerba/" + yerbaDto.getId())
                .contentType("application/json")
                .content("{\"name\":\"Yerba\",\"version\":1,\"createdAt\":\"2020-01-01T00:00:00.000Z\",\"updatedAt\":\"2020-01-01T00:00:00.000Z\",\"yerbaType\":\"Argentinian\",\"upc\":123456789,\"price\":1.99,\"quantity\":1}"))
                .andExpect(status().isOk());

        assertEquals(yerbaDto, yerbaService.updateYerba(yerbaDto.getId(), yerbaDto));
    }
}