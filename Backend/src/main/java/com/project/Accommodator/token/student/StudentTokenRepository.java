package com.project.Accommodator.token.student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**

 This interface extends the CrudRepository for the StudentToken model and defines custom methods for the StudentToken repository.
 */
public interface StudentTokenRepository extends CrudRepository<StudentToken, Integer> {

  @Query(value = """
      select t from StudentToken t inner join Student u\s
      on t.user.studentId = u.studentId\s
      where u.studentId = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<StudentToken> findAllValidTokenByUser(Integer id);

  Optional<StudentToken> findByToken(String token);
}
