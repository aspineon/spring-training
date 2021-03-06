package pl.training.bank.disposition;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pl.training.bank.account.Account;
import pl.training.bank.account.AccountService;
import pl.training.bank.common.ValidatorService;
import pl.training.bank.operation.Operation;
import pl.training.bank.operation.OperationService;

@Log
@RequiredArgsConstructor
public class DispositionService {

    @NonNull
    private AccountService accountService;
    @NonNull
    private OperationService operationService;
    @NonNull
    private ValidatorService validatorService;

    public void process(Disposition disposition) {
        validatorService.validate(disposition, InvalidDispositionException.class);
        Account account = accountService.getByNumber(disposition.getAccountNumber());
        Operation operation = operationService.getBy(disposition.getOperationName());
        operation.execute(account, disposition.getFunds());
        accountService.update(account);
    }

    public void init() {
        log.info("DispositionService: init");
    }

    public void destroy() {
        log.info("DispositionService: destroy");
    }

}
