package com.trid3r.marvelapi;

import com.trid3r.marvelapi.domain.LogDomain;
import com.trid3r.marvelapi.domain.ResponseDomain;
import com.trid3r.marvelapi.util.Helper;
import com.trid3r.marvelapi.service.MarvelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MarvelServiceUnitTests {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private MarvelService marvelService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetCharacters() {
		// Arrange
		ResponseDomain expectedResponse = new ResponseDomain(
			200,
			"ok",
			new ResponseDomain.ResponseData(
				0,
				10,
				100,
				10,
				IntStream.range(0, 100)
					.mapToObj(i -> new ResponseDomain.Result(i + 1, "Iron Man", "Genio, millonario, playboy y filántropo"))
					.collect(Collectors.toList())
			)
		);

		// Mocking the restTemplate method
		when(restTemplate.getForObject(anyString(), eq(ResponseDomain.class))).thenReturn(expectedResponse);

		// Act
		ResponseDomain actualResponse = marvelService.getCharacters(100, 0);

		// Assert
		assertEquals(expectedResponse.getData().getResults().size(), actualResponse.getData().getResults().size());
		verify(restTemplate, times(1)).getForObject(anyString(), eq(ResponseDomain.class));
	}

	@Test
	public void testGetCharactersById() {
		// Arrange y Act
		int id = 12345;
		ResponseDomain response = marvelService.getCharactersById(id);

		// Assert
		assertNotNull(response);
		verify(restTemplate, times(1)).getForObject(anyString(), eq(ResponseDomain.class));
	}
}
