package io.saliou.msscyerbaservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.saliou.msscyerbaservice.model.YerbaDto;
import io.saliou.msscyerbaservice.model.YerbaTypeEnum;
import io.saliou.msscyerbaservice.service.YerbaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(YerbaController.class)
class YerbaDtoControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

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
                .upc("123456789L")
                .price(new BigDecimal("1.99"))
                .quantity(1)
                .build();

    }

    @Test
    void getYerbaById() throws Exception {
        System.out.println(objectMapper.writeValueAsString(yerbaDto));
        Mockito.when(yerbaService.getYerbaById(yerbaDto.getId())).thenReturn(yerbaDto);
        mockMvc.perform(get("/api/v1/yerba/{id}", yerbaDto.getId()))
                .andExpect(status().isOk())
                .andDo(document("v1/yerba-get-by-id", pathParameters(
                        parameterWithName("id").description("The id of the yerba")
                ),
                        responseFields(
                                fieldWithPath("id").description("The id of the yerba"),
                                fieldWithPath("name").description("The name of the yerba"),
                                fieldWithPath("version").description("The version of the yerba"),
                                fieldWithPath("createdAt").description("The date and time the yerba was created"),
                                fieldWithPath("updatedAt").description("The date and time the yerba was updated"),
                                fieldWithPath("yerbaType").description("The type of the yerba"),
                                fieldWithPath("upc").description("The upc of the yerba"),
                                fieldWithPath("price").description("The price of the yerba"),
                                fieldWithPath("quantity").description("The quantity of the yerba")
                        )
                ));
    }

    @Test
    void createYerba() throws Exception {
        ConstrainedFields fields = new ConstrainedFields(YerbaDto.class);

        Mockito.when(yerbaService.createYerba(yerbaDto)).thenReturn(yerbaDto);
        mockMvc.perform(post("/api/v1/yerba")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(yerbaDto)))
                .andExpect(status().isOk())
                .andDo(document("v1/create-yerba",
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("name").description("The name of the yerba"),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdAt").ignored(),
                                fields.withPath("updatedAt").ignored(),
                                fields.withPath("yerbaType").description("The type of the yerba"),
                                fields.withPath("upc").description("The upc of the yerba"),
                                fields.withPath("price").description("The price of the yerba"),
                                fields.withPath("quantity").ignored()
                        ))
                );

        assertEquals(yerbaDto, yerbaService.createYerba(yerbaDto));
    }

    @Test
    void updateYerba() throws Exception {
        ConstrainedFields fields = new ConstrainedFields(YerbaDto.class);

        Mockito.when(yerbaService.updateYerba(yerbaDto.getId(), yerbaDto)).thenReturn(yerbaDto);
        mockMvc.perform(put("/api/v1/yerba/{id}", yerbaDto.getId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(yerbaDto)))
                .andExpect(status().isOk())
                .andDo(document("v1/update-yerba",
                        pathParameters(
                                parameterWithName("id").description("The id of the yerba")
                        ),
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("name").description("The name of the yerba"),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdAt").ignored(),
                                fields.withPath("updatedAt").ignored(),
                                fields.withPath("yerbaType").description("The type of the yerba"),
                                fields.withPath("upc").description("The upc of the yerba"),
                                fields.withPath("price").description("The price of the yerba"),
                                fields.withPath("quantity").ignored()
                        ))
                );

        assertEquals(yerbaDto, yerbaService.updateYerba(yerbaDto.getId(), yerbaDto));
    }

    private static class ConstrainedFields {
        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils.collectionToDelimitedString(
                    this.constraintDescriptions.descriptionsForProperty(path), ". ")));
        }
    }
}