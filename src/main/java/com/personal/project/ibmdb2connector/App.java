package com.personal.project.ibmdb2connector;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.personal.project.ibmdb2connector.service.DB2MetadataReader;
import com.personal.project.ibmdb2connector.service.PostgresWriter;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class App implements CommandLineRunner 
{
    
    private final DB2MetadataReader db2MetadataReader;

    private final PostgresWriter postgresWriter;

    public App(DB2MetadataReader db2MetadataReader, PostgresWriter postgresWriter) {
        this.db2MetadataReader = db2MetadataReader;
        this.postgresWriter = postgresWriter;
    }


    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
    		// TODO Auto-generated method stub
            List<List<String>> metadata = db2MetadataReader.readMetadata();
            
            if (metadata.isEmpty()) {
                System.out.println("No metadata found.");
            } else {
                postgresWriter.writeToPostgres(metadata);
                System.out.println("Metadata written to Postgres successfully.");}
    		
    	}
}
