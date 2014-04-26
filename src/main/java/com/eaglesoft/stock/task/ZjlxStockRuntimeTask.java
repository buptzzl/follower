package com.eaglesoft.stock.task;

import com.eaglesoft.stock.event.ZjlxStockRuntimeEvent;

public class ZjlxStockRuntimeTask extends Task {

	public void extractZjlxStock() {
		System.out.print("processing  ZjlxStockRuntimeEvent");
		submit(new ZjlxStockRuntimeEvent());
	}
}
