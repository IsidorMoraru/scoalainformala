package com.java.projectfinal.security.token;

import com.java.projectfinal.model.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_generator")
    @SequenceGenerator(name = "token_generator", sequenceName = "token_seq", allocationSize = 1)
    @Column(name = "id")
    public int id;

    @Column(unique = true)
    public String token;

    @Column(name = "tokentype")
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
}
