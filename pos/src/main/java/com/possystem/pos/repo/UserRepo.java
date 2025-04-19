
package com.possystem.pos.repo;

import com.possystem.pos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
//<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
//=======
//>>>>>>> fdd7c5254b3082338dc26244341ca468d8964db2
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{
//<<<<<<< HEAD

    @Query(value="SELECT * FROM User WHERE id=?1", nativeQuery=true)
    User getUserById(Integer userId);
//=======
//    
//>>>>>>> fdd7c5254b3082338dc26244341ca468d8964db2
}
