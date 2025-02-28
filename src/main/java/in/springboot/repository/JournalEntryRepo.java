package in.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.springboot.entity.JournalEntry;

@Repository
public interface JournalEntryRepo extends JpaRepository<JournalEntry, Long>{
    
}
