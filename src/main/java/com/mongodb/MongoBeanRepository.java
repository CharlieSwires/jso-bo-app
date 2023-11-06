package com.mongodb;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
public interface MongoBeanRepository extends MongoRepository<MongoBean, String>{

    public List<MongoBean> findAll();
    public Page<MongoBean> findAll(Pageable pageable);

    @SuppressWarnings("unchecked")
    public MongoBean save(MongoBean entity);
	
    public Optional<MongoBean> findById(String id);
    public void deleteById(String id);

////	@Query("{ 'firstname' : ?0, 'surname' : ?1 }")
//	public void deleteByFirstnameSurname(String firstname, String surname);
	
    @Query("{ firstname : ?0 , surname : ?1 }")
    public List<MongoBean> findByFirstnameSurname(String firstname, String surname);
    public List<MongoBean> findBySurname(String surname);

    public List<MongoBean> findByFirstname(String firstname);

	
//	@Query("{ 'name' : { $regex: '?0.*?1.*' } }")
//	public List<MongoBean> findBeansByRegexpFirstSecond(String first, String second);
}
