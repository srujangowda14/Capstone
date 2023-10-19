package com.fidelity.portfolio;

public class TradeResponse {
	
	public TradeResponse(Trade trade, int status, String message) {
		super();
		this.trade = trade;
		this.status = status;
		this.message = message;
	}
	public TradeResponse() {
		super();
	}
	Trade trade;
	int status;
	String message;
	public Trade getTrade() {
		return trade;
	}
	public void setTrade(Trade trade) {
		this.trade = trade;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toJson() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"trade\":").append(tradeToJson()).append(",");
        json.append("\"status\":").append(status).append(",");
        json.append("\"message\":\"").append(message).append("\"");
        json.append("}");
        return json.toString();
    }
	
	private String tradeToJson() {
        if (trade == null) {
            return "null";
        }
        StringBuilder json = new StringBuilder();
        json.append("{");

        json.append("\"cashValue\":\"").append(trade.getCashValue()).append("\",");
        json.append("\"quantity\":").append(trade.getQuantity()).append(",");
        json.append("\"direction\":\"").append(trade.getDirection()).append("\",");
        json.append("\"instrumentId\":\"").append(trade.getInstrumentId()).append("\",");
        json.append("\"clientId\":").append(trade.getClientId()).append(",");
        json.append("\"tradeId\":\"").append(trade.getTradeId()).append("\",");
        json.append("\"executionPrice\":\"").append(trade.getExecutionPrice()).append("\"");

        json.append("}");
        return json.toString();
    }
}
