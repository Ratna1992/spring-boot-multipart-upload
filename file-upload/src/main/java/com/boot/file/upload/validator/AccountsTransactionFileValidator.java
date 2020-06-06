package com.boot.file.upload.validator;

import org.springframework.web.multipart.MultipartFile;

public interface AccountsTransactionFileValidator {

	boolean validateAccountsTransactionFile(MultipartFile file);

}
