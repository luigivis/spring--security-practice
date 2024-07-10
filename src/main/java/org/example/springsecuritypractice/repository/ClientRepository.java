package org.example.springsecuritypractice.repository;

import org.example.springsecuritypractice.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query(value = """
            select *
            from client
            where id LIKE CONCAT('%', :param, '%')
            or address LIKE CONCAT('%', :param, '%')
            or name LIKE CONCAT('%', :param, '%')
            or last_name LIKE CONCAT('%', :param, '%')
            or email LIKE CONCAT('%', :param, '%')
            or phone LIKE CONCAT('%', :param, '%')
            or status LIKE CONCAT('%', :param, '%')
            LIMIT 100
            """, nativeQuery = true)
    List<ClientEntity> searchClientEntities(@Param("param") String param);

}
