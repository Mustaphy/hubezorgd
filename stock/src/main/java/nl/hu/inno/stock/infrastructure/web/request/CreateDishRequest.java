package nl.hu.inno.stock.infrastructure.web.request;

import java.util.List;
import java.util.UUID;

public class CreateDishRequest {
    public String name;
    public List<UUID> ingredients;
}
