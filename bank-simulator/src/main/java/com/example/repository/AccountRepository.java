
package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.config.DBConfig;
import com.example.model.Account;

public class AccountRepository {

  
    public void saveAccount(Account account) {
        String sql = "INSERT INTO account_details (customer_id, account_type, bank_name, branch, balance, status, "
                   + "account_number, ifsc_code, name_on_account, phone_linked_with_bank, saving_amount) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, account.getCustomerId());
            st.setString(2, account.getAccountType());
            st.setString(3, account.getBankName());
            st.setString(4, account.getBranch());
            st.setDouble(5, account.getBalance());
            st.setString(6, account.getStatus());
            st.setString(7, account.getAccountNumber());
            st.setString(8, account.getIfscCode());
            st.setString(9, account.getNameOnAccount());
            st.setString(10, account.getPhoneLinkedWithBank());
            st.setDouble(11, account.getSavingAmount());

            int rows = st.executeUpdate();
            System.out.println(  rows + " row(s) inserted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccountById(int id) {
        String sql = "SELECT * FROM account_details WHERE account_id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery(); 

            if (rs.next()) {
                Account acc = new Account();
                acc.setAccountId(rs.getInt("account_id"));
                acc.setCustomerId(rs.getInt("customer_id"));
                acc.setNameOnAccount(rs.getString("name_on_account"));
                acc.setBalance(rs.getDouble("balance"));
                acc.setBankName(rs.getString("bank_name"));
                return acc;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBalance(int accountId, double newBalance) {
        String sql = "UPDATE account_details SET balance = ? WHERE account_id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setDouble(1, newBalance);
            st.setInt(2, accountId);

            int rows = st.executeUpdate();  
            System.out.println( rows + " row(s) updated.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    public void deleteAccount(int accountId) {
        String sql = "DELETE FROM account_details WHERE account_id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, accountId);

            int rows = st.executeUpdate();  
            System.out.println( rows + " row(s) deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account_details";

        try (Connection conn = DBConfig.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Account acc = new Account();
                acc.setAccountId(rs.getInt("account_id"));
                acc.setCustomerId(rs.getInt("customer_id"));
                acc.setNameOnAccount(rs.getString("name_on_account"));
                acc.setBalance(rs.getDouble("balance"));
                acc.setBankName(rs.getString("bank_name"));
                accounts.add(acc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }
}
