package de.volkswagen.f73.backend.stall;

import de.volkswagen.f73.backend.employee.EmployeeRepository;

public class StallMapper {

    public Stall convertDTOtoStall(StallDTO stallDTO, EmployeeRepository employeeRepository) {
        Stall stallToAdd = Stall.builder()
                .operatingCost(stallDTO.getOperatingCost())
                .type(stallDTO.getType())
                .build();
        setSellerForStall(stallDTO, employeeRepository, stallToAdd);
        setIDForStall(stallDTO, stallToAdd);
        return stallToAdd;
    }

    public StallDTO covertStallToDTO(Stall stall){
        StallDTO stallDTO = StallDTO.builder()
                .id(stall.getId())
                .operatingCost(stall.getOperatingCost())
                .type(stall.getType())
                .build();
        setSellerForDTO(stall, stallDTO);
        return stallDTO;
    }

    private void setSellerForDTO(Stall stall, StallDTO stallDTO) {
        if(stall.getSeller() != null){
            stallDTO.setSeller(stall.getSeller().getId());
        }
    }

    private void setSellerForStall(StallDTO stallDTO, EmployeeRepository employeeRepository, Stall stallToAdd) {
        if (stallDTO.getSeller() != null) {
            stallToAdd.setSeller(employeeRepository.findById(stallDTO.getSeller()).get());
        }
    }

    private void setIDForStall(StallDTO stallDTO, Stall stallToAdd) {
        if (stallDTO.getId() != null) {
            stallToAdd.setId(stallDTO.getId());
        }
    }

}
