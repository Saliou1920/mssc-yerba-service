package io.saliou.msscyerbaservice.service.inventory;

import io.saliou.msscyerbaservice.bootstrap.YerbaLoader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
class YerbaInventoryServiceRestTemplateImplTest {

    @Autowired
    private YerbaInventoryService yerbaInventoryService;

    @Test
    void getOnHandInventory() {
        Integer onHandInventory = yerbaInventoryService.getOnHandInventory(UUID.fromString("bb7ea57d-db02-48ab-a9b7-4ccb030d3650"));
        System.out.println(onHandInventory);
    }
}