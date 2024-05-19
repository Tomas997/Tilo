package com.io25.tiloproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.io25.tiloproject.model.TiloUser;

import java.util.Optional;

@Repository
public interface TiloUserRepository extends JpaRepository<TiloUser, Long> {

    Optional<TiloUser> findTiloUserByUsername(String userName);
    Optional<TiloUser> findTiloUserById(Long id);

}
