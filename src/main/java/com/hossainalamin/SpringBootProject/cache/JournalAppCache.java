package com.hossainalamin.SpringBootProject.cache;

import com.hossainalamin.SpringBootProject.entity.ConfigJournalApp;
import com.hossainalamin.SpringBootProject.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JournalAppCache {
    @Autowired
    ConfigJournalAppRepository configJournalAppRepository;
    public Map<String, String> APP_CACHE;
    @PostConstruct
    @Scheduled(cron="0 0/10 * ? * *")
    public boolean init(){
        APP_CACHE = new HashMap<>();
        List<ConfigJournalApp> all = configJournalAppRepository.findAll();
        for (ConfigJournalApp configJournalApp : all) {
            APP_CACHE.put(configJournalApp.getKey(), configJournalApp.getValue());
        }
        return true;
    }
}
