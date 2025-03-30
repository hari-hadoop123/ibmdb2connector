package com.personal.project.ibmdb2connector.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DB2MetadataReader {

    private final DataSource dataSource;

    public DB2MetadataReader(@Qualifier("ibmdb2Datasource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<List<String>> readMetadata() {

        String sql = "SELECT * FROM SYSIBM.SYSCOLUMNS WHERE TBNAME = 'YOUR_TABLE_NAME'";
        List<List<String>> metadata = new ArrayList<>();
        try (var connection = dataSource.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
           while (resultSet.next()) {
                List<String> row = new ArrayList<>();
                row.add(resultSet.getString("COLNAME")); // Column name
                row.add(resultSet.getString("TYPENAME")); // Data type
                row.add(resultSet.getString("LENGTH")); // Length
                row.add(resultSet.getString("NULLS")); // Nullable
                metadata.add(row);
            }
            
           }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return metadata;
    
    }
}
