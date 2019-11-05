package top.hitotright.demo.spdemo.domain;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Cacheable(cacheNames = {"user_find_by_name"})
    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    @Query("from User u where u.name=:name")
        //这里事hsq和sql不一样
    User findUser(@Param("name") String name);
}
