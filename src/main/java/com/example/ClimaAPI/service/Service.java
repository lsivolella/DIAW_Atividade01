package com.example.ClimaAPI.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class Service {

    private final String LATITUDE_BH = "-19.9297";
    private final String LONGITUDE_BH = "-43.966034";

    private String consultarURL(String apiURL) {
        String dados = "";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            dados = responseEntity.getBody();
        } else {
            dados = "Falha ao obter dados. Código de status: " + responseEntity.getStatusCode();
        }
        return dados;
    }

    public String getTemperatura() {
        String apiURL = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&current_weather=true",
                LATITUDE_BH,
                LONGITUDE_BH);
        String dados = consultarURL(apiURL);
        return dados;
    }

    public String getTemperatura(String latitude, String longitude) {
        String apiURL = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&current_weather=true",
                latitude,
                longitude);
        String dados = consultarURL(apiURL);
        return dados;
    }

    public JsonNode getCoordenadas(String cidade) {
        String apiURL = String.format(
                "https://geocoding-api.open-meteo.com/v1/search?name=%s&count=1&language=pt&format=json",
                cidade.replace(" ", "+"));
        String dados = consultarURL(apiURL);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readTree(dados);
    }
}
