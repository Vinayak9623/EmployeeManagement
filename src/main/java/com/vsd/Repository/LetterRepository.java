package com.vsd.Repository;

import com.vsd.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LetterRepository extends JpaRepository<Letter,Long> {


    List<Letter> findByRecipientId(long recipientId);
}
