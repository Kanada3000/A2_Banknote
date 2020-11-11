package com.myproject.banknote.repo;

import com.myproject.banknote.entities.Banknote;
// import com.myproject.banknote.entities.RawMaterial;
import com.myproject.banknote.entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface StorageRepository extends JpaRepository<Storage, UUID> {
    @Query("SELECT s FROM Storage s WHERE s.banknote = :banknote")
    Storage findByBanknote(@Param("banknote") Banknote banknote);
}