package com.testboot.test2.repo;

import com.testboot.test2.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface MessageRepo extends JpaRepository<Message,Long> {

}
