package com.desafio.ibm.techmanager.repository;

import com.desafio.ibm.techmanager.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

    @Query(nativeQuery = true, value = "SELECT * FROM buscar_usuarios(:param)")
    List<User> buscarUsuarios(@Param("param") String param);
}
