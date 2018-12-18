package pl.training.bank.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AccountConfig {

    @Bean
    public AccountRepository accountRepository(DataSource dataSource) {
        return new JdbcTemplateAccountRepository(dataSource);
    }

    @Bean
    public AccountNumberGenerator accountNumberGenerator(DataSource dataSource) {
        return new JdbcTemplateIncrementalAccountNumberGenerator(dataSource);
    }

    @Bean
    public AccountService accountService(AccountNumberGenerator accountNumberGenerator, AccountRepository accountRepository) {
        return new AccountService(accountNumberGenerator, accountRepository);
    }

}