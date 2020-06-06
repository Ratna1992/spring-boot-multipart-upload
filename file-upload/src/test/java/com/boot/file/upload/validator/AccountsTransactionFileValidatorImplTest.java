package com.boot.file.upload.validator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import com.boot.file.upload.exceptions.AccountTransactionsUploadException;

@SpringBootTest
class AccountsTransactionFileValidatorImplTest {

	@InjectMocks
	AccountsTransactionFileValidatorImpl mockAccountsTransactionFileValidator;

	MockMultipartFile csvFile1 = null;
	MockMultipartFile csvFile2 = null;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		csvFile1 = new MockMultipartFile("file", "test.csv", "text/csv", "some xml".getBytes());
		csvFile2 = new MockMultipartFile("file", "test.txt", "text/csv", "some xml".getBytes());
	}

	@Test()
	void testValidateAccountsTransactionFile() throws Exception {
		assertTrue(mockAccountsTransactionFileValidator.validateAccountsTransactionFile(csvFile1));
		try {
			mockAccountsTransactionFileValidator.validateAccountsTransactionFile(csvFile2);
		} catch (AccountTransactionsUploadException e) {
		}

	}

}
