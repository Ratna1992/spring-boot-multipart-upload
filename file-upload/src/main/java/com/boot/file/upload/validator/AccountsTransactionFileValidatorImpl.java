package com.boot.file.upload.validator;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boot.file.upload.exceptions.AccountTransactionsUploadException;

@Service
public class AccountsTransactionFileValidatorImpl implements AccountsTransactionFileValidator {

	@Override
	public boolean validateAccountsTransactionFile(MultipartFile file) {
		boolean isValid = true;
		// checking for empty file
		if (file.isEmpty()) {
			isValid = false;
			throw new AccountTransactionsUploadException("Empty Request,no file available");
		}

		// checking for file extension
		String fileName[] = file.getOriginalFilename().split("\\.");
		String extension = fileName[fileName.length - 1];
		if (!extension.equalsIgnoreCase("CSV")) {
			isValid = false;
			throw new AccountTransactionsUploadException("Invalid Format, upload CSV only");
		}
		return isValid;
	}

}
