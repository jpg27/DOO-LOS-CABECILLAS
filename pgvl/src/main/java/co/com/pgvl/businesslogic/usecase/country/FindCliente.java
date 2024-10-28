package co.com.pgvl.businesslogic.usecase.country;

import java.util.List;

import co.com.pgvl.businesslogic.usecase.UseWithReturn;
import co.com.pgvl.domain.ClienteDomain;

public interface FindCliente extends UseWithReturn<ClienteDomain, List<ClienteDomain>> {

}
