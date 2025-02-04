package shop.mtcoding.blog.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserJPARepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email = :email and u.password = :password")
    Optional<User> findByIdAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("select u from User u where u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query ("select count(r) from Resume r where r.user.id = :userId")
    Integer findAllbyUserId (@Param("userId") Integer userId);

    @Query("select count(a.resume) from Apply a where a.isPass in ('2') and a.resume.user.id = :userId")
    Integer findByUserIdN1 (@Param("userId") Integer userId);

    @Query("select count(a.resume) from Apply a where (a.isPass = '3' or a.isPass = '4') and a.resume.user.id = :userId ")
    Integer findByUserId34 (@Param("userId") Integer userId);
}