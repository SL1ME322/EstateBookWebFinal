package com.example.estatebookweb.repositories;



import com.example.estatebookweb.models.EstateModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstateRepository  extends JpaRepository<EstateModel, Long> {
    //AuthorModel findAuthorModelById(int authorId);
    //List<AuthorModel> findByName(String name);
    //List<AuthorModel> findAuthorModelByNameContainingIgnoreCase(String name);
    Optional<EstateModel> findEstateModelById(Long id);

}
