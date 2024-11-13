package com.thoeun.agriculture.dto;

import com.thoeun.agriculture.models.Field;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class FarmDto {
    private Long farmId;
    private String name;
    private String location;
    private String ownerName;
    private LocalDateTime createdAt = LocalDateTime.now();
    private List<Field> fields = new ArrayList<>();
}
