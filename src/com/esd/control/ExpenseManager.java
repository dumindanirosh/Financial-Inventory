/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esd.control;

import com.esd.pojo.Expense;

/**
 *
 * @author Duminda
 */
public interface ExpenseManager {

    public String addExpense(Expense expense);

    public String updateCashAmount(double cashAmount);

}
