package com.example.employee;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Test
    void shouldReturnAllEmployees() {
        EmployeeRepository repository = mock(EmployeeRepository.class);
        EmployeeService service = new EmployeeService(repository);

        when(repository.findAll()).thenReturn(
                List.of(new Employee("Sai", "sai@example.com", "DevOps"))
        );

        List<Employee> employees = service.getAllEmployees();

        assertEquals(1, employees.size());
        assertEquals("Sai", employees.get(0).getName());
        verify(repository, times(1)).findAll();
    }
}
