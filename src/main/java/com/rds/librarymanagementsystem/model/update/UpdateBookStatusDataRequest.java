package com.rds.librarymanagementsystem.model.update;

import com.rds.librarymanagementsystem.exception.BookStatusIdentifierException;

import javax.validation.constraints.NotNull;

public class UpdateBookStatusDataRequest {

    @NotNull(message = "Book Id cannot be null")
    private String bookId;
    @NotNull(message = "Status cannot be null")
    private Integer status;
    @NotNull(message = "Borrowed By cannot be null")
    private String borrowedBy;
    @NotNull(message = "Borrowed At cannot be null")
    private String borrowedAt;
    @NotNull(message = "Returned At cannot be null")
    private String returnedAt;

    public UpdateBookStatusDataRequest(String bookId, Integer status, String borrowedBy, String borrowedAt, String returnedAt) {
        this.bookId = bookId;
        this.status = status;
        this.borrowedBy = borrowedBy;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
    }

    public UpdateBookStatusDataRequest(){}

    public Integer getStatus() {
        return status;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public String getBorrowedAt() {
        return borrowedAt;
    }

    public String getReturnedAt() {
        return returnedAt;
    }

    public void validateStatus(){
        if (status != 0 && status != 1){
            throw new BookStatusIdentifierException(status + " is not identified as a valid book status");
        }
    }
}
