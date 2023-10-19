package com.fidelity.portfolio.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fidelity.portfolio.*;

@Mapper
public interface TradeHistoryMapper {
	@Select("select cash_value,quantity,direction,instrument_id,client_id,trade_id,execution_price,timestamp from trade where client_id = #{client_id}")
	public List<TradeHistory> getTradeHistory(@Param("client_id") long client_id);

}
