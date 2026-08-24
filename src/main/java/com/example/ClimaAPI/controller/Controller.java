package com.example.ClimaAPI.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.ClimaAPI.service.Service;

import tools.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Controller {

    Service service = new Service();

    private String formatarJson(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object jsonObject = mapper.readValue(json, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao formatar JSON: " + e.getMessage();
        }
    }

    @GetMapping(value = "/temperatura", produces = "application/json")
    public String getTemperatura() {
        String temperaturaRequest = service.getTemperatura();

        return formatarJson(temperaturaRequest);
    }

}
