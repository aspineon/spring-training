package pl.training.bank.disposition;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DispositionMapper {

    Disposition toDisposition(DispositionTransferObject dispositionTransferObject);

}
