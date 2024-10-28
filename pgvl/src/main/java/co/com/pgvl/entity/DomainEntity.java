package co.com.pgvl.entity;

import java.util.UUID;

import co.com.pgvl.crosscutting.helpers.UUIDHelper;

class DomainEntity {
	
	private UUID id;
	
	protected DomainEntity(final UUID id) {
		setId(id);
	}

	protected UUID getId() {
		return id;
	}

	protected void setId(final UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
	}
}
