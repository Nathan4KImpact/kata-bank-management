# kata-bank-management

Solution : 
- The use cases are implemented in a service (BankService) and are all testable through the BankTMngtests class
- We choose a simple solution to maintain a storage and formatted view of transactions history, into a Json file, whose path is configurable in resources/config.properties project file 

KATA Specifications :
•         Deposit and Withdrawal
•         Account statement (date, amount, balance)
•         Statement printing

User Stories :
•         US 1:
In order to save money
As a bank client
I want to make a deposit in my account

•         US 2:
In order to retrieve some or all of my savings
As a bank client
I want to make a withdrawal from my account

•         US 3:
In order to check my operations
As a bank client
I want to see the history (operation, date, amount, balance) of my operations

