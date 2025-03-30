package com.personal.project.ibmdb2connector.service;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.personal.project.ibmdb2connector.utility.TimestampConvertor;

@Service
public class PostgresWriter {

    private final DataSource dataSource;

    public PostgresWriter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void writeToPostgres(List<List<String>> data) {
        String insertSQL = "INSERT INTO your_postgres_table (column1, column2, ...) VALUES (?, ?, ...)";
        try (var connection = dataSource.getConnection(); var preparedStatement = connection.prepareStatement(insertSQL)) {
            for (List<String> row : data) {
                // Set parameters for the prepared statement based on the row data
                preparedStatement.setString(1, row.get(0)); // Example for first column
                preparedStatement.setTimestamp(2, TimestampConvertor.convertStringToTimestamp(row.get(1))); // Example for second column
                // Add more parameters as needed
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
