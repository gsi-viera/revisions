package com.avangenio.ecasa_inv.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.avangenio.ecasa_inv.controller.SectionController;
import com.avangenio.ecasa_inv.detail.SectionDetail;
import com.avangenio.ecasa_inv.domain.EProductType;
import com.avangenio.ecasa_inv.domain.ProductType;

import junit.framework.TestCase;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class EcasaTest extends TestCase {
	@Autowired
	private SectionController sectionController;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private String baseUrl = new String("http://localhost:");
	
	@LocalServerPort
    int randomServerPort;
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void sectioncontextLoads() throws Exception {
		assertThat(sectionController).isNotNull();
	}
	
	@Test
	public void creaeSectionTest() throws URISyntaxException {
		String fullUrl = baseUrl+randomServerPort+ "/ecasa/sections";
        URI uri = new URI(fullUrl);
        
        ProductType productType = new ProductType(EProductType.MEAT);
        SectionDetail sectionDetail = new SectionDetail("Test Section", 68.32, productType);
        
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKZXNzaWNhIiwiaWF0IjoxNjc5MzY3OTA2fQ.ntt_ka7hJEK7lG-5PwnJ8mrakDbL05_2AH6aXVgq0fM");      
 
        HttpEntity<SectionDetail> request = new HttpEntity<>(sectionDetail, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
        //Verify request succeed
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
	}
	
	
	@Test
	public void getSectionTest() throws URISyntaxException {
		String fullUrl = baseUrl+randomServerPort+ "/ecasa/sections/1";
        URI uri = new URI(fullUrl);
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKZXNzaWNhIiwiaWF0IjoxNjc5MzY3OTA2fQ.ntt_ka7hJEK7lG-5PwnJ8mrakDbL05_2AH6aXVgq0fM");      
 
        HttpEntity<String> request = new HttpEntity<String>(headers);
         
        ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
         
        //Verify request succeed
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.FOUND);
		
	}
	
	

}
