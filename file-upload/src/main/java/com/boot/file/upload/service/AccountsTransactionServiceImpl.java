package com.boot.file.upload.service;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.boot.file.upload.exceptions.AccountTransactionsUploadException;
import com.boot.file.upload.model.AccountsTransaction;
import com.boot.file.upload.repository.AccountTransactionsRepository;
import com.boot.file.upload.to.AccountTransactionCSVTo;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class AccountsTransactionServiceImpl implements AccountsTransactionService {

	@Autowired
	AccountTransactionsRepository repository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public String saveAccountsToDatabase(Reader reader) throws AccountTransactionsUploadException {
		String msg = "Data Saved Successfully";
		if (reader != null) {

			CsvToBean<AccountTransactionCSVTo> csvToBean = new CsvToBeanBuilder<AccountTransactionCSVTo>(reader)
					.withType(AccountTransactionCSVTo.class).withIgnoreLeadingWhiteSpace(true).build();
			// parsed csv file to list of objects
			List<AccountTransactionCSVTo> data = csvToBean.parse();

			// converted list of objects to documents
			List<AccountsTransaction> finalDataList = new ArrayList<>();
			data.stream().forEach(csvdata -> {
				AccountsTransaction accountTransaction = new AccountsTransaction();
				BeanUtils.copyProperties(csvdata, accountTransaction);
				// repository.save(accountTransaction);

				finalDataList.add(accountTransaction);
			});

			Stream<List<AccountsTransaction>> batches = batches(finalDataList, 10000);

			// save processed data to database by chunks
			batches.forEach(obj -> {
				mongoTemplate.insertAll(obj);
			});
			return msg;
		} else {
			// if reader is empty throwing an exception
			throw new AccountTransactionsUploadException("No Data Available, for parsing");
		}

	}

	public  <T> Stream<List<AccountsTransaction>> batches(List<AccountsTransaction> source, int length) {
		int size = source.size();
		if (size <= 0)
			return Stream.empty();
		int fullChunks = (size - 1) / length;
		return IntStream.range(0, fullChunks + 1)
				.mapToObj(n -> source.subList(n * length, n == fullChunks ? size : (n + 1) * length));
	}

}
