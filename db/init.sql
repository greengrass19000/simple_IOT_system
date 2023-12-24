-- init.sql

-- Create the database 'db' if it does not exist
CREATE DATABASE IF NOT EXISTS db;

-- Switch to the 'db' database
USE db;

-- Create table 'node1'
CREATE TABLE node1 (
                                     node1_id INT PRIMARY KEY,
                                     node1_name VARCHAR(255) NOT NULL,
                                     node1_value INT
    );

-- Create table 'node2'
CREATE TABLE node2 (
                                     node2_id INT PRIMARY KEY,
                                     node2_description TEXT,
                                     node2_status VARCHAR(50) DEFAULT 'active'
    );

-- Insert some sample data into 'node1'
INSERT INTO node1 (node1_id, node1_name, node1_value) VALUES
                                                          (1, 'Node1_A', 100),
                                                          (2, 'Node1_B', 200);

-- Insert some sample data into 'node2'
INSERT INTO node2 (node2_id, node2_description, node2_status) VALUES
                                                                  (1, 'Description_A', 'inactive'),
                                                                  (2, 'Description_B', 'active');