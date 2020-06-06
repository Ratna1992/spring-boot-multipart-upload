package com.boot.file.upload.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.boot.file.upload.exceptions.AccountTransactionsUploadException;
import com.boot.file.upload.service.AccountsTransactionService;
import com.boot.file.upload.validator.AccountsTransactionFileValidator;

@RestController
public class AccountController {

	@Autowired
	AccountsTransactionFileValidator accountsTransactionFileValidator;

	@Autowired
	AccountsTransactionService accountsTransactionService;

	@PostMapping("/upload")
	public String uploadFileToSaveAccountInformation(@RequestParam("file") MultipartFile file) {

		// file validation for checking empty and format
		accountsTransactionFileValidator.validateAccountsTransactionFile(file);

		// getting stream from the file
		try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			return accountsTransactionService.saveAccountsToDatabase(reader);
		} catch (AccountTransactionsUploadException | IOException e) {
			// throwing exception to global exceptional handler
			throw new AccountTransactionsUploadException(e.getMessage());
		}

	}

	@PostMapping("/uploadBulk")
	public String uploadFilesToSaveAccountInformation(@RequestParam("file") MultipartFile[] file) {
		//just to show-exception of request-size
		return null;
	}
}
