package com.trid3r.marvelapi;

import com.trid3r.marvelapi.domain.LogDomain;
import com.trid3r.marvelapi.repository.LogRepository;
import com.trid3r.marvelapi.service.LogService;
import com.trid3r.marvelapi.service.LogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LogServiceUnitTests {

	@Mock
	private LogRepository logRepository;

	@InjectMocks
	private LogService logService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testFindAllLogs() {
		// Arrange
		List<LogDomain> expectedLogs = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			expectedLogs.add(new LogDomain("User"+i,"getFindAllCharacters","","Hoy"));
		}

		// Mocking the repository method
		when(logRepository.findAll()).thenReturn(expectedLogs);

		// Act
		List<LogDomain> actualLogs = logService.findAllLogs();

		// Assert
		assertEquals(expectedLogs.size(), actualLogs.size());
		verify(logRepository, times(1)).findAll();
	}

	@Test
	public void testAddLog() {
		// Arrange
		String type = "getFindById";
		String id = "123456";

		// Act
		logService.addLog(type, id);

		// Assert
		verify(logRepository, times(1)).save(any(LogDomain.class));
	}
}
