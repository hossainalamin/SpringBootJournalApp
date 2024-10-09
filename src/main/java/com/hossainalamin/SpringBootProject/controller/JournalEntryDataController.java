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
    public JournalEntry updateJournalById(@PathVariable ObjectId journalId, JournalEntry updatedEntry){
        return null;
    }

    @DeleteMapping("/id/{jounalId}")
    public JournalEntry deleteJournalById(@PathVariable ObjectId jounalId){
        return null;
    }

}

