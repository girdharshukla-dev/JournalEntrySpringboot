package in.springboot.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.springboot.entity.JournalEntry;
import in.springboot.service.JournalEntryService;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping()
    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryService.getAllJournalEntries();
    }

    // this controller is to get the mapping of specific user
    @GetMapping("/{username}")
    public List<JournalEntry> getJournalEntriesByUsername(@PathVariable String username) {
        return journalEntryService.getEntryByUser(username);
    }

    @PostMapping()
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            journalEntryService.addJournalEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // this controller is to make entry in a specific user
    @PostMapping("/{username}")
    public ResponseEntity<?> createEntryInUser(@RequestBody JournalEntry journalEntry, @PathVariable String username) {
        try {
            JournalEntry entry = journalEntryService.addJournalEntryInUser(journalEntry, username);
            return new ResponseEntity<>(entry, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable Long myId) throws Exception {

        Optional<JournalEntry> journalEntry = journalEntryService.getJournalEntryById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable Long myId) {
        journalEntryService.deleteEntryById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // this controller is to delete the entry in a specific user
    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<?> deleteEntryInUser(@PathVariable String username, @PathVariable Long id) {
        try {
            journalEntryService.deleteJournalEntryInUser(username, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateEntryById(@PathVariable Long id, @RequestBody JournalEntry myEntry) {
        try {
            Optional<JournalEntry> journalEntry = journalEntryService.getJournalEntryById(id);
            if (journalEntry.isPresent()) {
                journalEntryService.updateJournalEntryById(id, myEntry.getTitle(), myEntry.getContent());
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/id/{username}/{id}")
    public ResponseEntity<?> updateEntryInUser(@PathVariable Long id, @RequestBody JournalEntry journalEntry,
            @PathVariable String username) throws Exception {
        JournalEntry entry = journalEntryService.getJournalEntryById(id).orElse(null);
        if (entry != null) {
            entry.setTitle(journalEntry.getTitle());
            entry.setContent(journalEntry.getContent());
            journalEntryService.updateJournalEntry(entry);
            return new ResponseEntity<>(entry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/health-check")
    public String health() {
        return "Ok";
    }

}
