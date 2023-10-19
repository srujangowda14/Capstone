-- -- Schema definition for the widgets and gadgets

-- Drop the tables if they exist

CREATE TABLE Person (
    id NUMBER PRIMARY KEY,
    email VARCHAR2(100),  -- Adjust the length as needed
    postalcode VARCHAR2(20),  -- Adjust the length as needed
    name VARCHAR2(100),  -- Adjust the length as needed
    date_of_birth DATE,
    country VARCHAR2(50),  -- Adjust the length as needed
    accept_robo_advisor_terms CHAR(1),  -- Assuming it's a Y/N or 0/1 value
    balance NUMBER(15, 2)  -- Assuming a maximum of 15 digits with 2 decimal places
);

CREATE TABLE Trade (
    cash_value NUMBER(15, 2),
    quantity INT,
    direction VARCHAR2(10),  -- Adjust the length as needed
    instrument_id VARCHAR2(100),  -- Adjust the length as needed
    client_id NUMBER,
    trade_id VARCHAR2(100),  -- Adjust the length as needed
    execution_price NUMBER(15, 2)  -- Adjust the precision as needed
);

CREATE TABLE client_identification (
    type VARCHAR2(50),  -- Adjust the length as needed
    value VARCHAR2(100),  -- Adjust the length as needed
    id NUMBER,
    FOREIGN KEY (id) REFERENCES Person (id)
);

CREATE TABLE portfolio (
    client_id NUMBER,
    instrument_id VARCHAR2(50),
    instrument_description VARCHAR2(255),
    bought_price NUMBER,
    quantity NUMBER,
    total_investment NUMBER
);

CREATE TABLE PREFERENCES (
    "CLIENT_ID" NUMBER,
    "INVESTMENT_PURPOSE" VARCHAR(200),
    "RISK_TOLERANCE" VARCHAR(100),
    "INCOME_CATEGORY" VARCHAR(100),
    "LENGTH_OF_INVESTMENT" VARCHAR(100)
);
create table INSTRUMENT_HISTORY (
	instrument_id VARCHAR(7),
	price_date DATE,
	opening_price INT,
	closing_price INT,
	volume_purchased INT,
	category_id VARCHAR(5)
);