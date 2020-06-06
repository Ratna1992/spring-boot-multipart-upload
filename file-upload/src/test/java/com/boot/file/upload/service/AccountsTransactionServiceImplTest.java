package com.boot.file.upload.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.boot.file.upload.exceptions.AccountTransactionsUploadException;
import com.boot.file.upload.model.AccountsTransaction;
import com.boot.file.upload.to.AccountTransactionCSVTo;
import com.opencsv.bean.CsvToBean;

@RunWith(MockitoJUnitRunner.class)
class AccountsTransactionServiceImplTest {

	@Mock
	MongoTemplate mockMongoTemplate;
	@Mock
	CsvToBean<AccountTransactionCSVTo> mockCsvToBean;
	@InjectMocks
	AccountsTransactionServiceImpl accountsTransactionServiceImpl;

	List<AccountsTransaction> list = null;
	Reader reader1 = null;
	Reader reader2 = null;
	List<AccountTransactionCSVTo> csvlist = null;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		list = new ArrayList<>();
		AccountsTransaction ac1 = new AccountsTransaction();
		ac1.setId("1");
		ac1.setAccount("NA");
		ac1.setAccountId("T00695247");
		ac1.setAmount(1d);
		ac1.setDay(1);
		ac1.setFullDate("1/1/2013");
		ac1.setFullDateWithTime("2013-01-01T11:02:40");
		ac1.setFullTime("11:02:40");
		ac1.setkSymbol("");
		ac1.setMonth(1);
		ac1.setOperation("Credit");
		ac1.setTransId("A00002378");
		ac1.setType("credit");
		ac1.setYear(2020);
		AccountsTransaction ac2 = new AccountsTransaction();
		ac2.setId("2");
		ac2.setAccount("NA");
		ac2.setAccountId("A00695247");
		ac2.setAmount(1d);
		ac2.setDay(1);
		ac2.setFullDate("1/1/2013");
		ac2.setFullDateWithTime("2013-01-01T11:02:40");
		ac2.setFullTime("11:02:40");
		ac2.setkSymbol("");
		ac2.setMonth(1);
		ac2.setOperation("Credit");
		ac2.setTransId("T00002378");
		ac2.setType("debit");
		ac2.setYear(2020);

		list.add(ac1);
		list.add(ac2);
		InputStream stream = new ByteArrayInputStream(
				"1,T00695247,A00002378,Credit,Credit in Cash,700,700,,,NA,2013,1,1,1/1/2013,11:02:40,2013-01-01T11:02:40"
						.getBytes());
		reader2 = new BufferedReader(new InputStreamReader(stream));
		csvlist = new ArrayList<>();
		list.forEach(obj -> {
			AccountTransactionCSVTo csvObj = new AccountTransactionCSVTo();
			try {
				BeanUtils.copyProperties(csvObj, obj);
				csvlist.add(csvObj);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	@Test
	void testSaveAccountsToDatabase() throws Exception {
		when(mockCsvToBean.parse()).thenReturn(csvlist);
		when(mockMongoTemplate.insertAll(list)).thenReturn(list);
		try {
			accountsTransactionServiceImpl.saveAccountsToDatabase(reader1);
		} catch (AccountTransactionsUploadException e) {

		}
		assertEquals(accountsTransactionServiceImpl.saveAccountsToDatabase(reader2), "Data Saved Successfully");
	}

	@Test
	void testBatches() throws Exception {
		assertEquals(2l, accountsTransactionServiceImpl.batches(list, 1).count());
		assertEquals(0l, accountsTransactionServiceImpl.batches(Collections.emptyList(), 1).count());
	}

}
