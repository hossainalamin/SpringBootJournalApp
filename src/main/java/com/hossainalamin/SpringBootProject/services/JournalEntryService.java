package com.hossainalamin.SpringBootProject.services;

import com.hossainalamin.SpringBootProject.entity.JournalEntry;
import com.hossainalamin.SpringBootProject.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    JournalEntryRepository journalEntryRepository;
    public boolean createJournalEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
        return true;
    }
    public List<JournalEntry> getAllJournalEntries(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getJournalEntryById(ObjectId journalId){
        return journalEntryRepository.findById(journalId);
    }

    public void deleteJournalById(ObjectId jounalId) {
        journalEntryRepository.deleteById(jounalId);
    }
}
