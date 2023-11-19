package com.example.demo.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mssql.InsertIdentityOperation;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.example.demo.entity.Task;

@SpringBootTest
class TaskRepositoryTest {

    private final JdbcTemplate jdbcTemplate;
    private final TaskRepository repository;
    private static File backupFile;
    private static FileOutputStream out;
    private static FileInputStream in;

    @Autowired
    public TaskRepositoryTest(JdbcTemplate jdbcTemplate, TaskRepository repository) {
	this.jdbcTemplate = jdbcTemplate;
	this.repository = repository;
    }

    /**
     * DBのバックアップ
     * 
     * @throws SQLException
     * @throws DatabaseUnitException
     */
    @BeforeAll
    public static void setUp() throws Exception {
	String jdbcUrl = "jdbc:mysql://localhost/learing_spring";
	Connection jdbConnection = DriverManager.getConnection(jdbcUrl, "", "");
	IDatabaseConnection connection = new DatabaseConnection(jdbConnection);
	try {
	    QueryDataSet parDataSet = new QueryDataSet(connection);
	    // テーブルを指定
	    parDataSet.addTable("Task");
	    String backupDir = "src/test/java/backup";
	    backupFile = File.createTempFile("backup", ".xml", new File(backupDir));
	    out = new FileOutputStream(backupFile);
	    FlatXmlDataSet.write(parDataSet, out);
	    out.flush();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    connection.close();
	}
    }

    /**
     * バックアップしたファイルの復元
     * 
     * @throws Exception
     */
    @AfterAll
    public static void tearDown() throws Exception {
	String jdbcUrl = "jdbc:mysql://localhost/learing_spring";
	Connection jdbConnection = DriverManager.getConnection(jdbcUrl, "", "");
	IDatabaseConnection connection = new DatabaseConnection(jdbConnection);
	try {
	    in = new FileInputStream(backupFile);
	    IDataSet dataSet = new FlatXmlDataSetBuilder().build(in);
	    DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    connection.close();
	    backupFile.delete();
	}
    }

    /**
     * テストデータの投入
     * 
     * @throws Exception
     */
    @BeforeEach
    public void insertTestData() throws Exception {
	Connection connection = this.jdbcTemplate.getDataSource().getConnection();
	IDatabaseConnection dbConnection = new DatabaseConnection(connection);
	DatabaseConfig databaseConfig = dbConnection.getConfig();
	try {
	    IDataSet dataSet = new CsvDataSet(new File("src/test/java/com/example/demo/repository/dbunit"));
	    new InsertIdentityOperation(DatabaseOperation.CLEAN_INSERT).execute(dbConnection, dataSet);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    connection.close();
	    dbConnection.close();
	}
    }

    @Test
    @DisplayName("insertTasktメソッドで1件新規登録できることを確認する")
    public void insertTaskTest() {
	var task = new Task();
	task.setTitle("17時からMTG");
	task.setContent("");
	repository.insertTask(task);

	int actual = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Task");
	assertThat(actual, is(2));
    }
}
