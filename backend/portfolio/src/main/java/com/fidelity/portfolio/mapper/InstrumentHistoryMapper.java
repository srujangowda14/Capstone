package com.fidelity.portfolio.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fidelity.portfolio.*;

@Mapper
public interface InstrumentHistoryMapper {
	
	@Select("select instrument_id,price_date,opening_price,closing_price,volume_purchased,category_id from instrument_history where instrument_id='Q123' or instrument_id ='Q456' or instrument_id='N123456' or instrument_id = 'N123789'")
	public List<InstrumentHistory> getInstrumentHistoryByStock();
	
	
	@Select("select instrument_id,price_date,opening_price,closing_price,volume_purchased,category_id from instrument_history where instrument_id='T67894' or instrument_id='T67890' or instrument_id='T67895' or instrument_id='T67897' or instrument_id='T67899' or instrument_id='T67880' or instrument_id='T67883' or instrument_id='T67878'")
	public List<InstrumentHistory> getInstrumentHistoryByGovt();
	 
	
	@Select("select instrument_id,price_date,opening_price,closing_price,volume_purchased,category_id from instrument_history where instrument_id='C100'")
	public List<InstrumentHistory> getInstrumentHistoryByCD();
	
	
	@Select("select instrument_id,price_date,opening_price,closing_price,volume_purchased,category_id from instrument_history")
	public List<InstrumentHistory> getInstrumentHistoryByVolume();
	

	
	

}
