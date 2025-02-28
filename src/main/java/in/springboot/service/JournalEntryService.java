package in.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.springboot.entity.JournalEntry;
import in.springboot.entity.User;
import in.springboot.repository.JournalEntryRepo;
import in.springboot.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    UserRepository userRepository;
    
    public void addJournalEntry(JournalEntry journalEntry) {
       journalEntryRepo.save(journalEntry);
    }
    
    public List<JournalEntry> getAllJournalEntries(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getJournalEntryById(Long id) throws Exception{
        return Optional.ofNullable(journalEntryRepo.findById(id).orElseThrow(() -> new Exception("Entry not found")));
    }


    public void deleteEntryById(Long id){
        journalEntryRepo.deleteById(id);
    }


    public void updateJournalEntryById(Long id, String title, String content) throws Exception{
        JournalEntry journalEntry = journalEntryRepo.findById(id).orElseThrow(() -> new Exception("Entry not found"));
        journalEntry.setTitle(title);
        journalEntry.setContent(content);
        journalEntryRepo.save(journalEntry);
    }

    public void updateJournalEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }


    public List<JournalEntry> getEntryByUser(String username){
        User user = userRepository.findByUsername(username);
        if(user!=null){
            return user.getJournalEntries();
        }
        else return null;
    }

    
    @Transactional
    public JournalEntry addJournalEntryInUser(JournalEntry journalEntry, String username){
        User user = userRepository.findByUsername(username);
        if(user!=null){
            JournalEntry journalEntry2 = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(journalEntry2);
            userRepository.save(user);
            return journalEntry2;
        }
        return null;
    }

    public void deleteJournalEntryInUser(String username, Long id){
        User user = userRepository.findByUsername(username);
        JournalEntry journalEntry = journalEntryRepo.findById(id).orElse(null);
        if(user!=null && journalEntry!=null){
            user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
            userRepository.save(user);
            journalEntryRepo.deleteById(id);
        }
    }
}
