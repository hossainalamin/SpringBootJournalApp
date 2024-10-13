package com.hossainalamin.SpringBootProject.controller;

import com.hossainalamin.SpringBootProject.entity.JournalEntry;
import com.hossainalamin.SpringBootProject.entity.Users;
import com.hossainalamin.SpringBootProject.services.JournalEntryService;
import com.hossainalamin.SpringBootProject.services.UserService;
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
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesByUserName(@PathVariable String userName){
        Users user = userService.findUserByUserName(userName);
        List<JournalEntry> getAllData =  user.getJournalEntries();
        if(getAllData != null && !getAllData.isEmpty()){
            return new ResponseEntity<>(getAllData, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{userName}")
    public ResponseEntity<?> createJournalEntry(@RequestBody JournalEntry newEntry, @PathVariable String userName){
        try {
            newEntry.setDate(LocalDateTime.now());
            JournalEntry journalEntry = journalEntryService.createJournalEntry(newEntry, userName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/id/{journalId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId journalId){
        Optional<JournalEntry> getJournalById =  journalEntryService.getJournalEntryById(journalId);
        if(getJournalById.isPresent()){
            return new ResponseEntity<>(getJournalById, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("id/{userName}/{journalId}")
    public ResponseEntity<?> updateJournalById(@PathVariable String userName
            , @PathVariable ObjectId journalId, @RequestBody JournalEntry updatedEntry){
        JournalEntry journalEntry = journalEntryService.getJournalEntryById(journalId).orElse(null);
        if(journalEntry != null){
            journalEntry.setContent(updatedEntry.getContent() == null && updatedEntry.getContent().equals("") ? journalEntry.getContent() : updatedEntry.getContent());
            journalEntry.setTitle(updatedEntry.getTitle() == null && updatedEntry.getTitle().equals("") ? journalEntry.getTitle() : updatedEntry.getTitle());
        }
        journalEntryService.createJournalEntry(journalEntry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/id/{userName}/{journalId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable String userName, @PathVariable ObjectId journalId){
        journalEntryService.deleteJournalById(userName, journalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

