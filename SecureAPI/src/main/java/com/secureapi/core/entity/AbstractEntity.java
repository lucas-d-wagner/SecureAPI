package com.secureapi.core.entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract Long getId();
	
	@Override
	public boolean equals(Object other) {
		if(Objects.isNull(other))
			return false;
		if(!(other instanceof AbstractEntity))
			return false;
		if(this == other)
			return true;
		
		AbstractEntity otherEntity = (AbstractEntity) other;

		Long id = this.getId();
		Long otherId = otherEntity.getId();
		
		return Objects.equals(id, otherId);
	}
	
	@Override
	public int hashCode() {
		return Objects.isNull(getId()) ? super.hashCode() : getId().intValue();
	}

}
