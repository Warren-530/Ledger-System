/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger.system;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static database.DatabaseConnector.getConnection;

public class LedgerCharts extends Application {

    private static final Connection connection = getConnection();

    @Override
    public void start(Stage primaryStage) {
        // PieChart for Spending by Category
        PieChart spendingPieChart = createSpendingPieChart(1); // Pass user_id = 1 as example

        // BarChart for Savings Growth
        BarChart<String, Number> savingsBarChart = createSavingsBarChart(1); // Pass user_id = 1 as example

        // BarChart for Loan Repayment
        BarChart<String, Number> loanRepaymentBarChart = createLoanRepaymentBarChart(1); // Pass user_id = 1 as example

        // LineChart for Spending Trends
        BarChart<String, Number> spendingTrendChart = createSpendingTrendChart(1); // Pass user_id = 1 as example

        VBox vbox = new VBox(10, spendingPieChart, savingsBarChart, loanRepaymentBarChart, spendingTrendChart);
        Scene scene = new Scene(vbox, 800, 800);

        primaryStage.setTitle("Ledger Data Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public PieChart createSpendingPieChart(int userId) {
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Spending by Category");

        String sql = "SELECT description, SUM(amount) as total_amount " +
                "FROM transactions " +
                "WHERE user_id = ? AND transaction_type = 'debit' " +
                "GROUP BY description";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String category = rs.getString("description");
                double totalAmount = rs.getDouble("total_amount");
                pieChart.getData().add(new PieChart.Data(category, totalAmount));
            }
        } catch (Exception e) {
            System.out.println("Error creating PieChart: " + e.getMessage());
        }

        return pieChart;
    }

    public BarChart<String, Number> createSavingsBarChart(int userId) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        yAxis.setLabel("Savings Amount");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Savings Growth Over Time");

        String sql = "SELECT DATE_FORMAT(date, '%Y-%m') as month, SUM(amount) as total_savings " +
                "FROM transactions " +
                "WHERE user_id = ? AND transaction_type = 'savings' " +
                "GROUP BY month";

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Savings Growth");

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String month = rs.getString("month");
                double totalSavings = rs.getDouble("total_savings");
                series.getData().add(new XYChart.Data<>(month, totalSavings));
            }
        } catch (Exception e) {
            System.out.println("Error creating BarChart: " + e.getMessage());
        }

        barChart.getData().add(series);
        return barChart;
    }

    public BarChart<String, Number> createLoanRepaymentBarChart(int userId) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        yAxis.setLabel("Repayment Amount");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Loan Repayments Over Time");

        String sql = "SELECT DATE_FORMAT(created_at, '%Y-%m') as month, SUM(principal_amount) as total_repaid " +
                "FROM loans " +
                "WHERE user_id = ? AND status = 'repaid' " +
                "GROUP BY month";

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Loan Repayments");

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String month = rs.getString("month");
                double totalRepaid = rs.getDouble("total_repaid");
                series.getData().add(new XYChart.Data<>(month, totalRepaid));
            }
        } catch (Exception e) {
            System.out.println("Error creating Loan Repayment BarChart: " + e.getMessage());
        }

        barChart.getData().add(series);
        return barChart;
    }

    public BarChart<String, Number> createSpendingTrendChart(int userId) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        yAxis.setLabel("Total Spending");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Spending Trends Over Time");

        String sql = "SELECT DATE_FORMAT(date, '%Y-%m') as month, SUM(amount) as total_spent " +
                "FROM transactions " +
                "WHERE user_id = ? AND transaction_type = 'debit' " +
                "GROUP BY month";

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Spending Trends");

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String month = rs.getString("month");
                double totalSpent = rs.getDouble("total_spent");
                series.getData().add(new XYChart.Data<>(month, totalSpent));
            }
        } catch (Exception e) {
            System.out.println("Error creating Spending Trend BarChart: " + e.getMessage());
        }

        barChart.getData().add(series);
        return barChart;
    }

    public static void main(String[] args) {
        launch(args);
    }
}