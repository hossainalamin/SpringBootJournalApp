package com.hossainalamin.SpringBootProject.services;

import com.hossainalamin.SpringBootProject.entity.JournalEntry;
import com.hossainalamin.SpringBootProject.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JournalEntryService {
    @Autowired
    JournalEntryRepository journalEntryRepository;
    public boolean createJournalEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
        return true;
    }
}
