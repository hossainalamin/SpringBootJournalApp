package com.hossainalamin.SpringBootProject.cache;

import com.hossainalamin.SpringBootProject.entity.ConfigJournalApp;
import com.hossainalamin.SpringBootProject.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JournalAppCache {
    @Autowired
    ConfigJournalAppRepository configJournalAppRepository;
    public Map<String, String> APP_CACHE =  new HashMap<>();
    @PostConstruct
    public void init(){
        List<ConfigJournalApp> all = configJournalAppRepository.findAll();
        for (ConfigJournalApp configJournalApp : all) {
            APP_CACHE.put(configJournalApp.getKey(), configJournalApp.getValue());
        }
    }
}
