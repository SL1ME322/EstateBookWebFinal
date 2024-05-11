package com.example.estatebookweb.repositories;



import com.example.estatebookweb.models.EstateModel;
import com.example.estatebookweb.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstateRepository  extends JpaRepository<EstateModel, Long> {
    Optional<EstateModel> findEstateModelById(Long id);
    List<EstateModel> findByEstateTypeAndPriceBetween(String estateType, int minPrice, int maxPrice);
    List<EstateModel> findByEstateType(String estateType);
    List<EstateModel> findByCity(String city);
    Page<EstateModel> findByAdNameContaining(String adName, Pageable pageable);

}
