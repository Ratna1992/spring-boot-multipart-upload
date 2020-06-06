package com.boot.file.upload.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.boot.file.upload.model.AccountsTransaction;

@Repository
public interface AccountTransactionsRepository extends MongoRepository<AccountsTransaction, Integer> {

}
