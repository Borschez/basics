package ru.borsch.basics.repository.document;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.borsch.basics.model.document.DocumentEntity;

import java.util.List;

@NoRepositoryBean
public interface DocumentEntityRepository<T extends DocumentEntity> extends JpaRepository<T , Long> {
    List<T> findByRegistrationNumber(String registrationNumber);
    Page<T> findByName(String name, Pageable pageable);
    List<T> findByState(String state);
}
