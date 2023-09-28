package com.project.Accommodator.token.owner;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing OwnerToken entities.
 */
public interface OwnerTokenRepository extends CrudRepository<OwnerToken, Integer> {

  @Query(value = """
      select t from OwnerToken t inner join Owner u\s
      on t.user.ownerId = u.ownerId\s
      where u.ownerId = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<OwnerToken> findAllValidTokenByUser(Integer id);

  Optional<OwnerToken> findByToken(String token);
}
