package co.com.pgvl.controller.response;

import java.util.ArrayList;
import java.util.List;

import co.com.pgvl.crosscutting.helpers.ObjectHelper;

public abstract class ResponseWithData<T> extends Response {
	
	private List<T> data = new ArrayList<>();

	public final  List<T> getData() {
		return data;
	}

	public final void setData(final List<T> data) {
		this.data = ObjectHelper.getDefault(data, this.data);
	}

}
