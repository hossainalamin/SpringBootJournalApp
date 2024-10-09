package com.hossainalamin.SpringBootProject.controller;

import com.hossainalamin.SpringBootProject.entity.JournalEntry;
import com.hossainalamin.SpringBootProject.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryDataController {
    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAllJournalEntries(){
        return journalEntryService.getAllJournalEntries();
    }

    @PostMapping
    public String createJournalEntry(@RequestBody JournalEntry newEntry){
        journalEntryService.createJournalEntry(newEntry);
        return "Journal Entry Created";
    }
    @GetMapping("/id/{journalId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId journalId){
        return journalEntryService.getJournalEntryById(journalId).orElse(null);

    }
    @PutMapping("/id/{journalId}")
    public JournalEntry updateJournalById(@PathVariable ObjectId journalId, @RequestBody JournalEntry updatedEntry){
        JournalEntry journalEntry = journalEntryService.getJournalEntryById(journalId).orElse(null);
        if(journalEntry != null){
            journalEntry.setContent(updatedEntry.getContent() == null && updatedEntry.getContent().equals("") ? journalEntry.getContent() : updatedEntry.getContent());
            journalEntry.setTitle(updatedEntry.getTitle() == null && updatedEntry.getTitle().equals("") ? journalEntry.getTitle() : updatedEntry.getTitle());
        }
        journalEntryService.createJournalEntry(journalEntry);
        return journalEntry;
    }

    @DeleteMapping("/id/{jounalId}")
    public boolean deleteJournalById(@PathVariable ObjectId jounalId){
        journalEntryService.deleteJournalById(jounalId);
        return true;
    }

}

