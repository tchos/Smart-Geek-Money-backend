package com.geekinstitut.smartmoney.util;

import com.geekinstitut.smartmoney.service.AppSettingService;
import com.geekinstitut.smartmoney.service.CategoryService;
import com.geekinstitut.smartmoney.service.ExpenseService;
import com.geekinstitut.smartmoney.service.IncomeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class DataInitializer implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AppSettingService appSettingService;
    private final IncomeService incomeService;
    private final ExpenseService expenseService;

    public DataInitializer(CategoryService categoryService,
                           AppSettingService appSettingService,
                           IncomeService incomeService,
                           ExpenseService expenseService) {
        this.categoryService = categoryService;
        this.appSettingService = appSettingService;
        this.incomeService = incomeService;
        this.expenseService = expenseService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.getAllCategories();
        appSettingService.getAllAppSettings();
        incomeService.getAllIncome();
        expenseService.getAllExpense();
    }
}
