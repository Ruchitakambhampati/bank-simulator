package com.example.setup;

import java.sql.Connection;
import java.sql.Statement;

import com.example.config.DBConfig;

public class DatabaseInitializer {
    public static void initialize() {
        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement()) {

            
            String customerTable =
                "CREATE TABLE IF NOT EXISTS customer_details ("
              + "customer_id INT AUTO_INCREMENT PRIMARY KEY,"
              + "username VARCHAR(100) NOT NULL,"
              + "password VARCHAR(255) NOT NULL,"
              + "aadhar_number VARCHAR(20) UNIQUE,"
              + "permanent_address TEXT,"
              + "state VARCHAR(100),"
              + "country VARCHAR(100),"
              + "city VARCHAR(100),"
              + "email VARCHAR(100) UNIQUE,"
              + "phone_number VARCHAR(15) UNIQUE,"
              + "status VARCHAR(20),"
              + "dob DATE,"
              + "age INT,"
              + "created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
              + "modified_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
              + "gender VARCHAR(10),"
              + "father_name VARCHAR(100),"
              + "mother_name VARCHAR(100)"
              + ");";

          
            String accountTable =
                "CREATE TABLE IF NOT EXISTS account_details ("
              + "account_id INT AUTO_INCREMENT PRIMARY KEY,"
              + "customer_id INT,"
              + "account_type VARCHAR(50),"
              + "bank_name VARCHAR(100),"
              + "branch VARCHAR(100),"
              + "balance DECIMAL(15,2) DEFAULT 0.00,"
              + "status VARCHAR(20),"
              + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
              + "modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
              + "account_number VARCHAR(20) UNIQUE,"
              + "ifsc_code VARCHAR(20),"
              + "name_on_account VARCHAR(100),"
              + "phone_linked_with_bank VARCHAR(15),"
              + "saving_amount DECIMAL(15,2),"
              + "FOREIGN KEY (customer_id) REFERENCES customer_details(customer_id)"
              + ");";

          
            String transactionTable =
                "CREATE TABLE IF NOT EXISTS transaction_details ("
              + "transaction_id INT AUTO_INCREMENT PRIMARY KEY,"
              + "utr_number VARCHAR(50) UNIQUE,"
              + "date_of_transaction TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
              + "transaction_amount DECIMAL(15,2),"
              + "debited_date TIMESTAMP NULL,"
              + "account_id INT,"
              + "balance_amount DECIMAL(15,2),"
              + "description TEXT,"
              + "modified_by VARCHAR(100),"
              + "receiver_by VARCHAR(100),"
              + "transaction_type ENUM('deposit','withdrawal'),"
              + "mode_of_transaction ENUM('credit','debit','upi','qr'),"
              + "FOREIGN KEY (account_id) REFERENCES account_details(account_id)"
              + ");";

            
            String branchTable =
                "CREATE TABLE IF NOT EXISTS bank_branch ("
              + "branch_id INT AUTO_INCREMENT PRIMARY KEY,"
              + "branch_name VARCHAR(100),"
              + "bank_name VARCHAR(100),"
              + "ifsc_code VARCHAR(20) UNIQUE,"
              + "address TEXT,"
              + "city VARCHAR(100),"
              + "state VARCHAR(100),"
              + "country VARCHAR(100),"
              + "phone_number VARCHAR(15)"
              + ");";

           
            stmt.execute(customerTable);
            stmt.execute(accountTable);
            stmt.execute(transactionTable);
            stmt.execute(branchTable);

            System.out.println(" All tables created successfully (if not exist).");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
