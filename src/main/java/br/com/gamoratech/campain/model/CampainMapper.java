package br.com.gamoratech.campain.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CampainMapper {
    public static CampainDTO toDTO(Campain campain) {
        if (campain == null) {
            return null;
        }
        return new CampainDTO(
                campain.id,
                campain.name,
                campain.description,
                campain.startDate,
                campain.endDate
        );
    }

    public static Campain toEntity(CampainDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Campain(
                dto.getName(),
                dto.getDescription(),
                dto.getStartDate(),
                dto.getEndDate()
        );
    }

    public static List<CampainDTO> toDTOList(List<Campain> campains) {
        if (campains == null || campains.isEmpty()) {
            return Collections.emptyList();
        }
        return campains.stream()
                .map(CampainMapper::toDTO)
                .collect(Collectors.toList());
    }
}
