package com.spring.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.spring.models.Discussion;
import org.springframework.data.domain.Sort;

public interface DiscussionRepository extends MongoRepository<Discussion, String> {
    @Query("{ 'participants' : { $all : [ ?0, ?1 ] } }")
    List<Discussion> findPrivateDiscussion(String userId1, String userId2);
    @Query("{ 'participants' : { $all : [ ?0] } }")
    List<Discussion> findUserDiscussions(String userId, Sort sort);
}
