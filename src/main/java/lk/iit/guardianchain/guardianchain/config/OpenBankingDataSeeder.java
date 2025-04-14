package lk.iit.guardianchain.guardianchain.config;

import lk.iit.guardianchain.guardianchain.model.ApiCategory;
import lk.iit.guardianchain.guardianchain.model.ApiSubCategory;
import lk.iit.guardianchain.guardianchain.model.OpenBankingApi;
import lk.iit.guardianchain.guardianchain.repository.ApiCategoryRepository;
import lk.iit.guardianchain.guardianchain.repository.ApiSubCategoryRepository;
import lk.iit.guardianchain.guardianchain.repository.OpenBankingApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class OpenBankingDataSeeder implements CommandLineRunner {

    @Autowired
    private ApiCategoryRepository categoryRepository;

    @Autowired
    private ApiSubCategoryRepository subCategoryRepository;

    @Autowired
    private OpenBankingApiRepository apiRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            seedData();
        }
    }

    private void seedData() {
        // 1. Account Information Services (AIS)
        ApiCategory ais = createCategory("Account Information Services (AIS)", 
            "Provide access to customers' bank account data.");
        
        List<ApiSubCategory> aisSubCategories = Arrays.asList(
            createSubCategory(ais, "Account Details", "Account related operations"),
            createSubCategory(ais, "Standing Orders", "Standing order operations"),
            createSubCategory(ais, "Direct Debits", "Direct debit operations"),
            createSubCategory(ais, "Scheduled Payments", "Scheduled payment operations"),
            createSubCategory(ais, "Statements", "Account statement operations")
        );

        // 2. Payment Initiation Services (PIS)
        ApiCategory pis = createCategory("Payment Initiation Services (PIS)", 
            "Enable third-party providers to initiate payments on behalf of users.");
        
        List<ApiSubCategory> pisSubCategories = Arrays.asList(
            createSubCategory(pis, "Single Immediate Payments", "Single payment operations"),
            createSubCategory(pis, "Scheduled Payments", "Future-dated payment operations"),
            createSubCategory(pis, "Recurring Payments", "Recurring payment operations"),
            createSubCategory(pis, "Domestic vs International Payments", "Payment type operations")
        );

        // 3. Confirmation of Funds (CoF)
        ApiCategory cof = createCategory("Confirmation of Funds (CoF)", 
            "Used to check whether an account has enough funds for a payment.");
        
        List<ApiSubCategory> cofSubCategories = Arrays.asList(
            createSubCategory(cof, "Funds Check", "Funds availability check operations")
        );

        // 4. Products and Offers
        ApiCategory products = createCategory("Products and Offers", 
            "Provide details on financial products offered by institutions.");
        
        List<ApiSubCategory> productsSubCategories = Arrays.asList(
            createSubCategory(products, "Personal Current Accounts", "Personal account products"),
            createSubCategory(products, "Business Current Accounts", "Business account products"),
            createSubCategory(products, "Credit Cards", "Credit card products"),
            createSubCategory(products, "Savings Accounts", "Savings account products"),
            createSubCategory(products, "Loans and Mortgages", "Loan and mortgage products")
        );

        // 5. Customer Information APIs
        ApiCategory customerInfo = createCategory("Customer Information APIs", 
            "Expose basic details about the customer (with consent).");
        
        List<ApiSubCategory> customerInfoSubCategories = Arrays.asList(
            createSubCategory(customerInfo, "Identity", "Customer identity information"),
            createSubCategory(customerInfo, "KYC Details", "Know Your Customer verification info")
        );

        // 6. Consent and Authorization APIs
        ApiCategory consent = createCategory("Consent and Authorization APIs", 
            "Handle user consent and permissions for data access or payment initiation.");
        
        List<ApiSubCategory> consentSubCategories = Arrays.asList(
            createSubCategory(consent, "Consent Management", "Consent management operations"),
            createSubCategory(consent, "OAuth 2.0 Flows", "OAuth authentication operations"),
            createSubCategory(consent, "Scopes & Permissions", "Permission management operations")
        );

        // 7. Event Notification APIs
        ApiCategory events = createCategory("Event Notification APIs", 
            "Notify third-party apps about account or payment events.");
        
        List<ApiSubCategory> eventsSubCategories = Arrays.asList(
            createSubCategory(events, "Real-time Transaction Notifications", "Transaction notification operations"),
            createSubCategory(events, "Payment Status Updates", "Payment status update operations")
        );

        // 8. Meta/Utility APIs
        ApiCategory meta = createCategory("Meta/Utility APIs", 
            "Support general system needs like health, versioning, etc.");
        
        List<ApiSubCategory> metaSubCategories = Arrays.asList(
            createSubCategory(meta, "API Discovery", "API discovery operations"),
            createSubCategory(meta, "Health Check", "System health check operations"),
            createSubCategory(meta, "API Versioning Info", "API version information operations")
        );

        // Save all categories
        List<ApiCategory> categories = Arrays.asList(ais, pis, cof, products, customerInfo, consent, events, meta);
        categoryRepository.saveAll(categories);

        // Save all subcategories
        List<ApiSubCategory> allSubCategories = Arrays.asList(
            aisSubCategories, pisSubCategories, cofSubCategories, productsSubCategories,
            customerInfoSubCategories, consentSubCategories, eventsSubCategories, metaSubCategories
        ).stream().flatMap(List::stream).toList();
        
        subCategoryRepository.saveAll(allSubCategories);

        // Create and save sample APIs
        createAndSaveApis(ais, aisSubCategories.get(0), "Get list of accounts", "/accounts", "Retrieve list of accounts");
        createAndSaveApis(ais, aisSubCategories.get(0), "Get account balances", "/accounts/{accountId}/balances", "Retrieve account balances");
        createAndSaveApis(ais, aisSubCategories.get(0), "Get account transactions", "/accounts/{accountId}/transactions", "Retrieve account transactions");
        
        createAndSaveApis(pis, pisSubCategories.get(0), "Initiate payment", "/payments", "Initiate a new payment");
        createAndSaveApis(pis, pisSubCategories.get(0), "Get payment status", "/payments/{paymentId}", "Get status of a payment");
    }

    private ApiCategory createCategory(String name, String description) {
        ApiCategory category = new ApiCategory();
        category.setName(name);
        category.setDescription(description);
        return category;
    }

    private ApiSubCategory createSubCategory(ApiCategory category, String name, String description) {
        ApiSubCategory subCategory = new ApiSubCategory();
        subCategory.setName(name);
        subCategory.setDescription(description);
        subCategory.setCategory(category);
        return subCategory;
    }

    private void createAndSaveApis(ApiCategory category, ApiSubCategory subCategory, String name, String context, String description) {
        OpenBankingApi api = new OpenBankingApi();
        api.setName(name);
        api.setContext(context);
        api.setDescription(description);
        api.setCategory(category);
        api.setSubCategory(subCategory);
        apiRepository.save(api);
    }
} 