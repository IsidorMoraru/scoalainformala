package com.java.projectfinal.security.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
            select t from Token t
            inner join t.user u
            where u.id = :id and (t.expired = false and t.revoked = false)
            """)
    List<Token> findValidTokensByUserId(@Param("id") UUID id);

    @Query("select t from Token t where t.token = :token and t.expired = false and t.revoked = false")
    Optional<Token> findByTokenAndNotExpiredAndNotRevoked(@Param("token") String token);
}
