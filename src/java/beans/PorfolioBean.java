/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.sql.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author ed
 */
@Named()
@SessionScoped
public class PorfolioBean implements Serializable {

    /**
     * Creates a new instance of PorfolioBean
     */
    private String DB_URI = "jdbc:postgresql://localhost:5432/ucandb";
    private String USER_NAME = "postgres";
    private String USER_PASSWORD = "postgres";
    private String webRootPath;
    private String PORTFOLIO_NAME;
    private Connection connection = null;
    private FormulaEvaluator objFormulaEvaluator;
    private DataFormatter objDefaultFormat;

    public PorfolioBean() {
    }

    public void carregarLocalidade() {
        try {
            loadPortfolio("portfolio.xls");
            this.connectToDatabase();
            startupLocalidadeConfig();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void see() {
        try {
            loadPortfolio("portfolio.xls");
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }

    public void loadPortfolio(String portfolioName) {
        System.out.println(this.getClass().getResource(portfolioName).toString());
        this.PORTFOLIO_NAME = this.getClass().getResource(portfolioName).toString();//this.getClass().getResource(portfolioName).getFile();
    }

    public void carregarProductoPortfolio() {
        try {
            loadPortfolio("portfolio.xls");
            this.connectToDatabase();
            System.out.println("1- ");
            startupProductoPortfolioConfig();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void connectToDatabase() {
        try {
            connection = DriverManager.getConnection(DB_URI, USER_NAME, USER_PASSWORD);
            if (connection != null) {
                System.out.println("Conex??o estabelecida");
            } else {
                goToErroPage();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PorfolioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startupProductoPortfolioConfig() {
        try {
            String query = "TRUNCATE TABLE portfolio CASCADE";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            try {
                System.out.println("2- ");
                setupPortfolio("productos",
                        "INSERT INTO portfolio(pk_portfolio, designacao) VALUES(?, ?);",
                        "INSERT INTO portfolio(pk_portfolio, designacao, fk_portfolio_pai) VALUES(?, ?, ?);");
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(PorfolioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadPortfolio() {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URI, USER_NAME, USER_PASSWORD);
            if (connection != null) {
                System.out.println("Connex??o estabelecida");
                startupProductoPortfolioConfig();
            } else {
                goToErroPage();
            }
        } catch (Exception e) {
            Logger.getLogger(PorfolioBean.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void setupPortfolio(String sheetName, String primaryQuery, String secundaryQuery) {

        /*File file = new File(PORTFOLIO_NAME);

        try {
            if (file.createNewFile()) {
                System.out.println("File Created");
            } else {
                System.out.println("File Exists");
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        System.out.println(file.getAbsolutePath());
         */
        var externalContext = FacesContext.getCurrentInstance().getExternalContext();

        String relativeWebPath = "/resources/portfolio.xls";
        ServletContext servletContext = (ServletContext) externalContext.getContext();
        String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);

        FileInputStream fileInputStream = null;

        try {
            File file = new File(absoluteDiskPath);
            fileInputStream = new FileInputStream(file);
            System.out.println("3- ");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(PorfolioBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            System.out.println("IO EXCEPTION");
        }
        objDefaultFormat = new DataFormatter();
        objFormulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
        HSSFSheet workSheet = workbook.getSheet(sheetName);
        HSSFRow row;
        Iterator<Row> rowIterator = workSheet.iterator();
        String previousItem = "";
        String actualItem = "";
        String designation = "";
        String splitCharacter = "\\.";
        String[] fatherList = new String[20];
        int actualFatherIndex = -1;
        Cell cell;
        while (rowIterator.hasNext()) {
            System.out.println("4- ");
            row = (HSSFRow) rowIterator.next();
            Iterator< Cell> cellIterator = row.cellIterator();
            boolean flag = false;
            int i = 0;
            while (i < 2 && cellIterator.hasNext()) {
                cell = cellIterator.next();
                objFormulaEvaluator.evaluate(cell); // This will evaluate the cell, And any type of cell will return string val-ue
                String cellValueStr = objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);
                //System.out.println("0 Home setupportfolio{}\tcellValueStr: " + cellValueStr);
                if (flag == false) {
                    actualItem = cellValueStr;
                } else {
                    designation = cellValueStr;
                }
                flag = !flag;
                i++;
            }
            System.out.println(actualItem + " - " + designation);
            boolean result = actualItem.length() == 1;
            if (result) {
                actualFatherIndex = -1;
                previousItem = actualItem;
                //System.out.println(previousItem);
                String query = primaryQuery;
                try {
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, actualItem);
                    statement.setString(2, designation);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("SQL EXCEPTION: " + e.toString());
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }
            } else {
                int actualSize = actualItem.split(splitCharacter).length;
                int previousSize = previousItem.split(splitCharacter).length;
                if (actualSize > previousSize) {
                    actualFatherIndex++;
                    fatherList[actualFatherIndex] = previousItem;
                } else if (actualSize < previousSize) {
                    int quantStepsBack = previousSize - actualSize;
                    actualFatherIndex -= quantStepsBack;
                }
                String query = secundaryQuery;
                try {
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, actualItem);
                    statement.setString(2, designation);
                    statement.setString(3, fatherList[actualFatherIndex]);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("SQL EXCEPTION: " + e.toString());
                    System.out.println(e.getMessage());
                }
                previousItem = actualItem;
            }
        }
    }

    public void goToErroPage() {
        try {

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public int getQuantChar(String string, String character) {
        int i = 0;
        int quant = 0;
        for (; i < string.length(); i++) {
            if (string.charAt(i) + "" == character) {
                quant += 1;
            }
        }
        return quant;
    }

    private void startupLocalidadeConfig() {
        try {
            String query = "TRUNCATE TABLE localidade CASCADE";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            try {
                setupPortfolio("localidade",
                        "INSERT INTO localidade(pk_localidade, nome) VALUES(?, ?);",
                        "INSERT INTO localidade(pk_localidade, nome, fk_localidade_pai) VALUES(?, ?, ?);");
            } catch (Exception e) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(PorfolioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
