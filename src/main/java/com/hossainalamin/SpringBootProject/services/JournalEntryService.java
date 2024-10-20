package com.hossainalamin.SpringBootProject.services;

import com.hossainalamin.SpringBootProject.entity.JournalEntry;
import com.hossainalamin.SpringBootProject.entity.Users;
import com.hossainalamin.SpringBootProject.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);
    @Autowired
    private UserService userService;
    public JournalEntry createJournalEntry(JournalEntry journalEntry, String userName){
        Users users = userService.findUserByUserName(userName);
        JournalEntry journalEntry1 = journalEntryRepository.save(journalEntry);
        users.getJournalEntries().add(journalEntry1);
        userService.createUsers(users);
        return journalEntry1;
    }
    public void createJournalEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAllJournalEntries(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getJournalEntryById(ObjectId journalId){
        return journalEntryRepository.findById(journalId);
    }

    public void deleteJournalById(String userName, ObjectId journalId) {
        Users userByUserName = userService.findUserByUserName(userName);
        userByUserName.getJournalEntries().removeIf(x->x.getId().equals(journalId));
        userService.createUsers(userByUserName);
        journalEntryRepository.deleteById(journalId);
    }
}
