package com.stickynotes.repository;

import com.stickynotes.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Notes,Integer> {

    @Query(value="select * from notes where flag=true", nativeQuery = true)
    public List<Notes> getNotesWithFlagTrue();
}
