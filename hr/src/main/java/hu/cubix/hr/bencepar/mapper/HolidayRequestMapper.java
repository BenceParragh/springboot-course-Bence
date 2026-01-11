package hu.cubix.hr.bencepar.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.cubix.hr.bencepar.dto.HolidayRequestDto;
import hu.cubix.hr.bencepar.model.HolidayRequest;


@Mapper(componentModel = "spring")
public interface HolidayRequestMapper {

	
	List<HolidayRequestDto> holidayRequestsToDtos(List<HolidayRequest> holidayRequests);	
	
	@Mapping(source = "employee.id", target = "employeeId")
	@Mapping(source = "approver.id", target = "approverId")	
	HolidayRequestDto holidayRequestToDto(HolidayRequest holidayRequest);

	@Mapping(target = "employee", ignore = true)
	@Mapping(target = "approver", ignore = true)
	HolidayRequest dtoToHolidayRequest(HolidayRequestDto holidayRequestDto);

	List<HolidayRequest> dtosToHolidayRequests(List<HolidayRequestDto> holidayRequestDtos);
	

}
