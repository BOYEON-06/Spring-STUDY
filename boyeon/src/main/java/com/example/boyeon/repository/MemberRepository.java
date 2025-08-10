package com.example.boyeon.repository;

import com.example.boyeon.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
    @Override
    Iterable<Member> findAll();
}
