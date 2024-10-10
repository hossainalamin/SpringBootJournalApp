package com.hossainalamin.SpringBootProject.controller;

import com.hossainalamin.SpringBootProject.entity.JournalEntry;
import com.hossainalamin.SpringBootProject.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryDataController {
    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntries(){
        List<JournalEntry> getAllData =  journalEntryService.getAllJournalEntries();
        if(getAllData != null && !getAllData.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createJournalEntry(@RequestBody JournalEntry newEntry){
        try {
            newEntry.setDate(LocalDateTime.now());
            journalEntryService.createJournalEntry(newEntry);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/id/{journalId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId journalId){
        Optional<JournalEntry> getJournalById =  journalEntryService.getJournalEntryById(journalId);
        if(getJournalById.isPresent()){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/id/{journalId}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId journalId, @RequestBody JournalEntry updatedEntry){
        JournalEntry journalEntry = journalEntryService.getJournalEntryById(journalId).orElse(null);
        if(journalEntry != null){
            journalEntry.setContent(updatedEntry.getContent() == null && updatedEntry.getContent().equals("") ? journalEntry.getContent() : updatedEntry.getContent());
            journalEntry.setTitle(updatedEntry.getTitle() == null && updatedEntry.getTitle().equals("") ? journalEntry.getTitle() : updatedEntry.getTitle());
        }
        Boolean createStatus = journalEntryService.createJournalEntry(journalEntry);
        if(createStatus) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/id/{jounalId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId jounalId){
        journalEntryService.deleteJournalById(jounalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

