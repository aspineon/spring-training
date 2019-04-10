package pl.training.bank.account;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import pl.training.bank.common.ResultPage;

@Transactional
@RequiredArgsConstructor
public class AccountService {

    @NonNull
    private AccountNumberGenerator accountNumberGenerator;
    @NonNull
    private AccountRepository accountRepository;

    public Account create() {
        String accountNumber = accountNumberGenerator.next();
        Account account = new Account(accountNumber);
        return accountRepository.save(account);
    }

    public Account getByNumber(String accountNumber) {
        return accountRepository.getByNumber(accountNumber)
                .orElseThrow(AccountNotFoundException::new);
    }

    public ResultPage<Account> get(int pageNumber, int pageSize) {
        Page<Account> accountsPage = accountRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return new ResultPage<>(accountsPage.getContent(), pageNumber, accountsPage.getTotalPages());
    }

    public void update(Account account) {
        getByNumber(account.getNumber());
        accountRepository.save(account);
    }

}
