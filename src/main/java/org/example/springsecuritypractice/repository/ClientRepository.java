package org.example.springsecuritypractice.repository;

import org.example.springsecuritypractice.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
