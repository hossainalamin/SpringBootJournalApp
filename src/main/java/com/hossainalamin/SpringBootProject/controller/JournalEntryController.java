package com.hossainalamin.SpringBootProject.controller;

import com.hossainalamin.SpringBootProject.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {
   private Map<Long, JournalEntry> journerEntries = new HashMap<>();

   @GetMapping
   public List<JournalEntry> getAllJournalEntries(){
       return new ArrayList<>(journerEntries.values());
   }

   @PostMapping
   public String createJournalEntry(@RequestBody JournalEntry newEntry){
//       journerEntries.put(newEntry.getId(), newEntry);
       return "Journal Entry Created";
   }
   @GetMapping("/id/{journalId}")
   public JournalEntry getJournalEntryById(@PathVariable Long journalId){
       return journerEntries.get(journalId);
   }
   @PutMapping("/id/{journalId}")
   public JournalEntry updateJournalById(@PathVariable Long journalId, JournalEntry updatedEntry){
       return journerEntries.put(journalId, updatedEntry);
   }

   @DeleteMapping("/id/{jounalId}")
   public JournalEntry deleteJournalById(@PathVariable Long jounalId){
       return journerEntries.remove(jounalId);
   }

}
