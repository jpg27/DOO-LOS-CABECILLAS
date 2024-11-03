package co.com.pgvl.data.dao;

import java.util.UUID;
import co.com.pgvl.entity.ClienteEntity;

public interface ClienteDAO extends CreateDAO<ClienteEntity>, RetrieveDAO<ClienteEntity, UUID>, UpdateDAO<ClienteEntity>, DeleteDAO<UUID> {

}
