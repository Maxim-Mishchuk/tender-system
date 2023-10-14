package com.labs.tenderservice.repository.impl.ram;

import com.labs.tenderservice.repository.UserRepository;
import com.labs.tenderservice.entity.user.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository
@Scope("prototype")
public class RAMUserRepository extends RAMRepository<User> implements UserRepository{}
