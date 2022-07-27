package io.saliou.msscyerbaservice.service.inventory;

import java.util.UUID;

public interface YerbaInventoryService {

    Integer getOnHandInventory(UUID yerbaId);
}
