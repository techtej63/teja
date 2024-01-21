package net.javaguides.emsbackend.mapper;

import net.javaguides.emsbackend.dto.EmployeeDto;
import net.javaguides.emsbackend.entity.Employee;

public class EmployeeMapper {

	public static EmployeeDto mapToEmployeeDto(Employee emp) {
		return new EmployeeDto(
				emp.getId(),
				emp.getFirstName(),
				emp.getLastName(),
				emp.getEmail()
				);
	}
	
	public static Employee mapToEmployee(EmployeeDto emp) {
		return new Employee(
				emp.getId(),
				emp.getFirstName(),
				emp.getLastName(),
				emp.getEmail()
				);
	}
}
