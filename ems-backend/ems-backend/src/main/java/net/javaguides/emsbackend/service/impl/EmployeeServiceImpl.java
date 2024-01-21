package net.javaguides.emsbackend.service.impl;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.emsbackend.dto.EmployeeDto;
import net.javaguides.emsbackend.entity.Employee;
import net.javaguides.emsbackend.mapper.EmployeeMapper;
import net.javaguides.emsbackend.repository.EmployeeRepository;
import net.javaguides.emsbackend.service.EmployeeService;
import net.javaguides.emsbackend.exception.ResourceNotFoundException;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) 
	{
		Employee employee=EmployeeMapper.mapToEmployee(employeeDto);
		Employee savedEmployee=employeeRepository.save(employee);
	return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeId(Long employeeId) {
		Employee employee=employeeRepository.findById(employeeId)
				.orElseThrow(
						()->new ResourceNotFoundException("Id not found:"+employeeId));
		
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees=employeeRepository.findAll();
		
		return employees.stream().map((employee)->
		EmployeeMapper.mapToEmployeeDto(employee)).toList();
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
		Employee employee=employeeRepository.findById(employeeId).orElseThrow(
	()-> new ResourceNotFoundException("Employee is not exists with given id:"+employeeId)
	);
		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());
		employee.setEmail(updatedEmployee.getEmail());
		
		Employee updatedEmployeeOdj=employeeRepository.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(updatedEmployeeOdj);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		
		Employee employee=employeeRepository.findById(employeeId).orElseThrow(
				()-> new ResourceNotFoundException("Employee is not exists with given id:"+employeeId)
				);
		employeeRepository.delete(employee);
	}
}
