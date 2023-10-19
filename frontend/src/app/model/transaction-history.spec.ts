import { TransactionHistory } from './transaction-history';

describe('TransactionHistory', () => {
  it('should create an instance', () => {
    expect(new TransactionHistory("Goog","Google",100,15,12345,"buy",new Date())).toBeTruthy();
  });
});
