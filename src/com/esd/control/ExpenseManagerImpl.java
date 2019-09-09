/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.control;

import com.esd.pojo.CompanyBusiness;
import com.esd.pojo.Expense;
import com.esd.util.Constants;
import com.esd.util.Log4jUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Duminda
 */
public class ExpenseManagerImpl implements ExpenseManager {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserActivityImpl userActivityImpl;

    public ExpenseManagerImpl() {

        entityManagerFactory = Persistence.createEntityManagerFactory("Financial_InventoryPU");

        entityManager = entityManagerFactory.createEntityManager();
    }

    public String addExpense(Expense expense) {

        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {


                entityTransaction = entityManager.getTransaction();

                if (!entityTransaction.isActive()) {

                    entityTransaction.begin();

                }

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                String companyBusinessDate = simpleDateFormat.format(expense.getExpenseDate());

                Query q = entityManager.createQuery("SELECT c FROM CompanyBusiness c WHERE c.businessDate = :businessDate");
                q.setParameter("businessDate", companyBusinessDate);

                CompanyBusiness companyBusiness = (CompanyBusiness) q.getSingleResult();

                if (companyBusiness != null) {

                    double cashierAmount = companyBusiness.getCashierAmount();


                    double newCashierAmount = cashierAmount - expense.getExpenseAmount();

                    if (expense.getExpenseType().equals("Other Expenses")) {

                        double dailyProfit = companyBusiness.getBusinessProfit();

                        dailyProfit = dailyProfit - expense.getExpenseAmount();
                        companyBusiness.setBusinessProfit(dailyProfit);

                    }

                    if (newCashierAmount > 0) {
                        companyBusiness.setCashierAmount(newCashierAmount);
                    } else {
                        companyBusiness.setCashierAmount(0);
                    }
                    entityManager.merge(companyBusiness);

                }



                entityManager.persist(expense);

                entityManager.flush();

                entityTransaction.commit();

                return Constants.EXPENSE_ADDED_SUCCESSFULLY;

            } else {

                Log4jUtil.logErrorMessage("public String addExpense(Expense expense) entity manager object null ");
                return Constants.ERROR;

            }

        } catch (NoResultException ex) {


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String companyBusinessDate = simpleDateFormat.format(expense.getExpenseDate());

            GregorianCalendar grCalendar = new GregorianCalendar();
            grCalendar.setTime(expense.getExpenseDate());

            int y = grCalendar.get(GregorianCalendar.YEAR);
            int m = grCalendar.get(GregorianCalendar.MONTH);
            int d = grCalendar.get(GregorianCalendar.DATE);

            String year = Integer.toString(y);

            String monthParam = null;


            switch (m + 1) {

                case 1:
                    monthParam = "January";
                    break;
                case 2:
                    monthParam = "Febuary";
                    break;
                case 3:
                    monthParam = "March";
                    break;
                case 4:
                    monthParam = "April";
                    break;
                case 5:
                    monthParam = "May";
                    break;
                case 6:
                    monthParam = "June";
                    break;
                case 7:
                    monthParam = "July";
                    break;
                case 8:
                    monthParam = "August";
                    break;
                case 9:
                    monthParam = "September";
                    break;
                case 10:
                    monthParam = "Octomber";
                    break;
                case 11:
                    monthParam = "November";
                    break;
                case 12:
                    monthParam = "December";
                    break;

            }

            Query q = entityManager.createQuery("SELECT c.cashierAmount FROM CompanyBusiness c ORDER BY c.companyBusinessId DESC");
            q.setMaxResults(1);
            Double cashierAmount = (Double) q.getSingleResult();


            double newCashierAmount = cashierAmount.doubleValue() - expense.getExpenseAmount();

            if (newCashierAmount < 0) {

                newCashierAmount = 0;

            }


            CompanyBusiness companyBusiness = new CompanyBusiness();

            companyBusiness.setBusinessDate(companyBusinessDate);
            companyBusiness.setBusinessMonth(monthParam);
            companyBusiness.setBusinessYear(year);
            companyBusiness.setCashierAmount(newCashierAmount);
            companyBusiness.setBusinessAmount(0);
            companyBusiness.setDiscountAmount(0);
            companyBusiness.setEnteredBy(expense.getEnteredBy());
            companyBusiness.setEnteredDate(expense.getEnteredDate());

            if (expense.getExpenseType().equals("Other Expenses")) {

                double todayProfit = expense.getExpenseAmount() * -1;
                companyBusiness.setBusinessProfit(todayProfit);
            } else {
                companyBusiness.setBusinessProfit(0);
            }


            double newCashierAmounTmp = cashierAmount - expense.getExpenseAmount();
            companyBusiness.setCashierAmount(newCashierAmount);

            entityManager.persist(companyBusiness);
            entityManager.persist(expense);

            entityManager.flush();

            entityTransaction.commit();

            return Constants.EXPENSE_ADDED_SUCCESSFULLY;

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String addExpense(Expense expense) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }

    public String updateCashAmount(double cashAmount) {


        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {


                entityTransaction = entityManager.getTransaction();

                if (!entityTransaction.isActive()) {

                    entityTransaction.begin();

                }

                Query q = entityManager.createQuery("SELECT c FROM CompanyBusiness c ORDER BY c.companyBusinessId DESC");
                q.setMaxResults(1);

                CompanyBusiness companyBusiness = (CompanyBusiness) q.getSingleResult();

                if (companyBusiness != null) {

                    double cashierAmount = companyBusiness.getCashierAmount();


                    companyBusiness.setCashierAmount(cashAmount);

                    entityManager.merge(companyBusiness);

                }

                entityManager.flush();

                entityTransaction.commit();

                return Constants.CASHIER_AMOUNT_UPDATED_SUCCESSFULLY;

            } else {

                Log4jUtil.logErrorMessage("public String updateCashAmount(double cashAmount) entity manager object null ");
                return Constants.ERROR;

            }

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String updateCashAmount(double cashAmount) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }
}
